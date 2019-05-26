import java.util.ServiceLoader;

import org.arakhne.afc.nodefx.Drawer;
import org.arakhne.afc.references.SoftValueTreeMap;
import org.arakhne.afc.vmutil.ClassComparator;
import org.arakhne.afc.vmutil.asserts.AssertMessages;
import org.eclipse.xtext.xbase.lib.Pure;

import drawers.CarDrawer;
import road_elements.Car;

import java.util.Iterator;

public class LoadServ {
	private static ServiceLoader services;
	private static SoftValueTreeMap<Class<?>, Drawer<?>> buffer = new SoftValueTreeMap<>(ClassComparator.SINGLETON);
	
	public static void main(String[]args) {
		ServiceLoader s=ServiceLoader.load(CarDrawer.class);
		System.out.println(s.toString());
		
	}
	
	public static void reload() {

		buffer.clear();

		services.reload();

	}
	
	@SuppressWarnings("unchecked")
	@Pure
	public static Iterator<Drawer<?>> getAllDrawers() {

		if (services == null) {

			services = ServiceLoader.load(Drawer.class);

		}

		return services.iterator();

	}
	
	@SuppressWarnings("unchecked")

	@Pure

	public static <T> Drawer<T> getDrawerFor(Class<? extends T> type) {

		assert type != null : AssertMessages.notNullParameter();

		final Drawer<?> bufferedType = buffer.get(type);

		Drawer<T> defaultChoice = null;

		if (bufferedType != null) {

			defaultChoice = (Drawer<T>) bufferedType;

		} else {

			final Iterator<Drawer<?>> iterator = getAllDrawers();

			while (iterator.hasNext()) {

				final Drawer<?> drawer = iterator.next();

				final Class<?> drawerType = drawer.getPrimitiveType();

				if (drawerType.equals(type)) {

					defaultChoice = (Drawer<T>) drawer;

					break;

				} else  if (drawerType.isAssignableFrom(type)

					&& (defaultChoice == null

						|| drawerType.isAssignableFrom(defaultChoice.getPrimitiveType()))) {

					defaultChoice = (Drawer<T>) drawer;

				}

			}

			if (defaultChoice != null) {

				buffer.put(type, defaultChoice);

			}

		}

		return defaultChoice;

	}
	
	
	
	@Pure

	@SuppressWarnings("unchecked")

	public static <T> Drawer<? super T> getDrawerFor(T instance) {

		if (instance != null) {

			if (instance instanceof DrawerReference) {

				final DrawerReference<T> drawable = (DrawerReference<T>) instance;

				Drawer<? super T> drawer = drawable.getDrawer();

				if (drawer != null) {

					return drawer;

				}

				drawer = getDrawerFor(instance.getClass());

				drawable.setDrawer(drawer);

				return drawer;

			}

			return getDrawerFor(instance.getClass());

		}

		return null;

	}
	
}
