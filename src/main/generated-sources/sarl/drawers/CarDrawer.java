package drawers;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.nodefx.Drawer;
import org.arakhne.afc.nodefx.ZoomableGraphicsContext;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.Car;
import road_elements.Road;

@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class CarDrawer implements Drawer<Car> {
  private static final int width = 2;
  
  @Override
  public void draw(final ZoomableGraphicsContext gc, final Car element) {
    Road currentRoad = element.getRoad();
    double position = element.getPosition().getX();
    Point2d coord = element.getCoordinates();
    double carX = coord.getX();
    double carY = coord.getY();
    double _x = currentRoad.getBeginPoint().getPoint().getX();
    if ((carX == _x)) {
      carY--;
    }
    gc.setFill(element.color);
    gc.setStroke(element.color);
    double _x_1 = coord.getX();
    double _y = coord.getY();
    gc.fillRect((_x_1 - (CarDrawer.width / 2)), (_y - (CarDrawer.width / 2)), CarDrawer.width, CarDrawer.width);
    double _x_2 = coord.getX();
    double _y_1 = coord.getY();
    gc.strokeRect((_x_2 - (CarDrawer.width / 2)), (_y_1 - (CarDrawer.width / 2)), CarDrawer.width, 
      CarDrawer.width);
    gc.restore();
  }
  
  @Override
  @Pure
  public Class<? extends Car> getPrimitiveType() {
    return Car.class;
  }
  
  @SyntheticMember
  public CarDrawer() {
    super();
  }
}
