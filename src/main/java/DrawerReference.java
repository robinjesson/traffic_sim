import org.arakhne.afc.nodefx.Drawer;
import org.eclipse.xtext.xbase.lib.Pure;



/** This interface is used for marking drawable elements.

 * The drawable elements are able to provide an instance of its {@link Drawer drawer}.

 *

 * @param <T> the type of the primitives to draw.

 * @author $Author: sgalland$

 * @version $FullVersion$

 * @mavengroupid $GroupId$

 * @mavenartifactid $ArtifactId$

 * @since 16.0

 */

public interface DrawerReference<T> {



	/** Replies the preferred drawer for this drawable object.

	 *

	 * @return the drawer or {@code null} if none.

	 */

	@Pure

	Drawer<? super T> getDrawer();



	/** Change the preferred drawer for this drawable object.

	 *

	 * @param drawer the drawer or {@code null} if none.

	 */

	void setDrawer(Drawer<? super T> drawer);



}