package road_elements;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.MobileRoadObject;
import road_elements.Road;
import road_elements.TrafficLayers;

/**
 * @author robin
 */
@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class Car extends MobileRoadObject {
  public Car(final int pos1D, final Road currentRoad, final TrafficLayers trafficLayers) {
    super(pos1D, currentRoad, trafficLayers);
    Road _road = super.getRoad();
    if ((_road != null)) {
      super.getRoad().addObject(this);
    }
    trafficLayers.addCar(this);
  }
  
  @Override
  public Point2d getCoordinates() {
    double _pos1D = this.getPos1D();
    double _distanceKilometers = this.getRoad().getDistanceKilometers();
    double t = (_pos1D / _distanceKilometers);
    int _beginX = this.getRoad().getBeginX();
    int _endX = this.getRoad().getEndX();
    double x = (((1 - t) * _beginX) + (t * _endX));
    int _beginY = this.getRoad().getBeginY();
    int _endY = this.getRoad().getEndY();
    double y = (((1 - t) * _beginY) + (t * _endY));
    return new Point2d(x, y);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public Car clone() {
    try {
      return (Car) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  private static final long serialVersionUID = -675334357L;
}
