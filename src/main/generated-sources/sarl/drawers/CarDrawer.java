package drawers;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import javafx.scene.paint.Color;
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
    double pos1D = element.getPos1D();
    Point2d coord = element.getCoordinates();
    gc.setFill(Color.RED);
    gc.setStroke(Color.RED);
    double _x = coord.getX();
    double _y = coord.getY();
    gc.fillRect((_x - (CarDrawer.width / 2)), (_y - (CarDrawer.width / 2)), CarDrawer.width, CarDrawer.width);
    double _x_1 = coord.getX();
    double _y_1 = coord.getY();
    gc.strokeRect((_x_1 - (CarDrawer.width / 2)), (_y_1 - (CarDrawer.width / 2)), CarDrawer.width, 
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
