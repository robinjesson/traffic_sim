package drawers;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.arakhne.afc.nodefx.Drawer;
import org.arakhne.afc.nodefx.ZoomableGraphicsContext;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.Car;
import road_elements.Road;

@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class CarDrawer implements Drawer<Car> {
  @Override
  public void draw(final ZoomableGraphicsContext gc, final Car element) {
    Road currentRoad = element.getRoad();
    int pos1D = element.getPos1D();
    double t = 0;
    double _distanceKilometers = currentRoad.getDistanceKilometers();
    t = (pos1D / _distanceKilometers);
    int _beginX = currentRoad.getBeginX();
    int _endX = currentRoad.getEndX();
    double x = (((1 - t) * _beginX) + (t * _endX));
    int _beginY = currentRoad.getBeginY();
    int _endY = currentRoad.getEndY();
    double y = (((1 - t) * _beginY) + (t * _endY));
    gc.fillOval(x, y, 10, 10);
    gc.strokeOval(x, y, 10, 10);
  }
  
  @Override
  @Pure
  public Class<? extends Car> getPrimitiveType() {
    return ((Class<? extends Car>) Car.class);
  }
  
  @SyntheticMember
  public CarDrawer() {
    super();
  }
}
