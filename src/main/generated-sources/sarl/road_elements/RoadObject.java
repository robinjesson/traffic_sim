package road_elements;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.arakhne.afc.gis.mapelement.MapCircle;
import org.arakhne.afc.math.geometry.d1.d.Point1d;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.Road;

/**
 * RoadObject class is used for every objects which can be on the road
 * like Cars, TrafficLights
 */
@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadObject extends MapCircle {
  private Point1d position;
  
  private Road road;
  
  public RoadObject(final Point1d position, final Road road) {
    super(0, 0, 10);
    Point1d _point1d = new Point1d();
    this.position = _point1d;
    this.road = road;
  }
  
  @Pure
  public synchronized Road getRoad() {
    return this.road;
  }
  
  public synchronized void setRoad(final Road road) {
    this.road = road;
  }
  
  @Pure
  public synchronized Point1d getPosition() {
    return this.position;
  }
  
  public synchronized void setPosition(final Point1d position) {
    this.position = position;
  }
  
  @Pure
  public synchronized Point2d getCoordinates() {
    double _x = this.position.getX();
    double _distanceFromStart = this.road.getDistanceFromStart(0);
    double t = (_x / _distanceFromStart);
    int _beginX = this.road.getBeginX();
    int _endX = this.road.getEndX();
    double x = (((1 - t) * _beginX) + (t * _endX));
    int _beginY = this.road.getBeginY();
    int _endY = this.road.getEndY();
    double y = (((1 - t) * _beginY) + (t * _endY));
    return new Point2d(x, y);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    return result;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public RoadObject clone() {
    try {
      return (RoadObject) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  private static final long serialVersionUID = -1475728636L;
}
