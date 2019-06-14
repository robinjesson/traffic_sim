package drawers;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import javafx.scene.paint.Color;
import org.arakhne.afc.math.geometry.PathElementType;
import org.arakhne.afc.math.geometry.d1.d.Point1d;
import org.arakhne.afc.math.geometry.d2.afp.PathIterator2afp;
import org.arakhne.afc.math.geometry.d2.d.OrientedRectangle2d;
import org.arakhne.afc.math.geometry.d2.d.PathElement2d;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Vector2d;
import org.arakhne.afc.nodefx.Drawer;
import org.arakhne.afc.nodefx.ZoomableGraphicsContext;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.Car;

@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class CarDrawer implements Drawer<Car> {
  private static final int width = 2;
  
  @Override
  public void draw(final ZoomableGraphicsContext gc, final Car element) {
    final Point2d position2d = new Point2d();
    final Vector2d tangent2d = new Vector2d();
    Color c = gc.rgb(100);
    gc.setFill(c);
    gc.setStroke(c);
    gc.save();
    Point1d position = element.getPosition();
    element.getPosition().getSegment().projectsOnPlane(position.getX(), position.getY(), position2d, tangent2d);
    int width = 2;
    double _x = position2d.getX();
    double _y = position2d.getY();
    OrientedRectangle2d r = new OrientedRectangle2d(_x, _y, 1, 0, (width / 2.0), (width / 2.0));
    r.rotate(tangent2d.getOrientationAngle());
    PathIterator2afp<PathElement2d> iterator = r.getPathIterator();
    gc.beginPath();
    while (iterator.hasNext()) {
      {
        PathElement2d cmp = iterator.next();
        PathElementType _type = cmp.getType();
        if (_type != null) {
          switch (_type) {
            case MOVE_TO:
              gc.moveTo(cmp.getToX(), cmp.getToY());
              break;
            case LINE_TO:
              gc.lineTo(cmp.getToX(), cmp.getToY());
              break;
            case CLOSE:
              gc.closePath();
              break;
            default:
              throw new IllegalStateException();
          }
        } else {
          throw new IllegalStateException();
        }
      }
    }
    gc.fill();
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
