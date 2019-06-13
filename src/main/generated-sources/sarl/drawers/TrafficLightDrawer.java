package drawers;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import javafx.scene.paint.Color;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.nodefx.Drawer;
import org.arakhne.afc.nodefx.ZoomableGraphicsContext;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.TrafficLight;

@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class TrafficLightDrawer implements Drawer<TrafficLight> {
  private static final int width = 2;
  
  @Override
  public void draw(final ZoomableGraphicsContext gc, final TrafficLight element) {
    Point2d coord = element.getCoordinates();
    gc.setFill(Color.RED);
    gc.setStroke(Color.RED);
    double _y = coord.getY();
    gc.fillOval(coord.getX(), (_y + 10), 5, 5);
    gc.setFill(Color.ORANGE);
    gc.setStroke(Color.ORANGE);
    double _y_1 = coord.getY();
    gc.fillOval(coord.getX(), (_y_1 + 5), 5, 5);
    gc.setFill(Color.GREEN);
    gc.setStroke(Color.GREEN);
    gc.fillOval(coord.getX(), coord.getY(), 5, 5);
  }
  
  @Override
  @Pure
  public Class<? extends TrafficLight> getPrimitiveType() {
    return TrafficLight.class;
  }
  
  @SyntheticMember
  public TrafficLightDrawer() {
    super();
  }
}
