package drawers;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import javafx.scene.paint.Color;
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
    gc.setFill(Color.RED);
    gc.setStroke(Color.RED);
    gc.fillRect((x - (CarDrawer.width / 2)), (y - (CarDrawer.width / 2)), CarDrawer.width, CarDrawer.width);
    gc.strokeRect((x - (CarDrawer.width / 2)), (y - (CarDrawer.width / 2)), CarDrawer.width, CarDrawer.width);
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
