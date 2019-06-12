package events;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import java.util.Objects;
import java.util.UUID;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * @author robin
 */
@SarlSpecification("0.9")
@SarlElementType(15)
@SuppressWarnings("all")
public class Influence extends Event {
  public UUID agentId;
  
  public Point2d arrivalPoint;
  
  public Point2d nextPoint;
  
  public Influence(final UUID agentId, final Point2d aPoint, final Point2d nextPoint) {
    this.agentId = agentId;
    this.arrivalPoint = aPoint;
    this.nextPoint = nextPoint;
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
    Influence other = (Influence) obj;
    if (!Objects.equals(this.agentId, other.agentId)) {
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
    result = prime * result + Objects.hashCode(this.agentId);
    return result;
  }
  
  /**
   * Returns a String representation of the Influence event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("agentId", this.agentId);
    builder.add("arrivalPoint", this.arrivalPoint);
    builder.add("nextPoint", this.nextPoint);
  }
  
  @SyntheticMember
  private static final long serialVersionUID = 5059968541L;
}
