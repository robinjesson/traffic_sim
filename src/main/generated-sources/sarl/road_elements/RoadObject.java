package road_elements;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Objects;
import java.util.UUID;
import org.arakhne.afc.gis.mapelement.MapPoint;
import org.arakhne.afc.math.geometry.d1.Segment1D;
import org.arakhne.afc.math.geometry.d1.d.Point1d;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.Road;

@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadObject extends MapPoint {
  private Point1d position;
  
  private UUID uuid;
  
  public RoadObject(final Point1d position) {
    super(0, 0);
    this.position = position;
    this.uuid = UUID.randomUUID();
  }
  
  @Pure
  public synchronized Road getRoad() {
    Segment1D<?, ?> _segment = this.position.getSegment();
    return ((Road) _segment);
  }
  
  public synchronized void setRoad(final Road road) {
    this.position.setSegment(road);
  }
  
  @Pure
  public synchronized Point1d getPosition() {
    return this.position;
  }
  
  public synchronized void setPosition(final Point1d position) {
    this.position = position;
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
  private static final long serialVersionUID = 1587700775L;
}
