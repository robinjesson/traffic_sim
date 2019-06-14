package road_elements;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Objects;
import java.util.UUID;
import org.arakhne.afc.gis.mapelement.MapCircle;
import org.arakhne.afc.math.geometry.d1.d.Point1d;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.Road;

@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadObject extends MapCircle {
  private Point1d position;
  
  private UUID uuid;
  
  private Road road;
  
  public RoadObject(final Point1d position, final Road road) {
    super(0, 0, 10);
    Point1d _point1d = new Point1d();
    this.position = _point1d;
    this.uuid = UUID.randomUUID();
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
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    RoadObject other = (RoadObject) obj;
    if (!Objects.equals(this.uuid, other.uuid)) {
      return false;
    }
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Objects.hashCode(this.uuid);
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
  private static final long serialVersionUID = -1406058470L;
}
