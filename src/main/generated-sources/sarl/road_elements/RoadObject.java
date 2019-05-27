package road_elements;

import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Objects;
import java.util.UUID;
import org.arakhne.afc.gis.mapelement.MapCircle;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.Road;

/**
 * @author robin
 */
@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadObject extends MapCircle {
  private int pos1D;
  
  private final UUID uuid;
  
  private Road road;
  
  @DefaultValueSource
  public RoadObject(@DefaultValue("road_elements.RoadObject#NEW_0") final int pos1D, final Road road, final double x, final double y) {
    super(x, y, 10);
    this.pos1D = pos1D;
    this.uuid = UUID.randomUUID();
    this.road = road;
  }
  
  /**
   * Default value for the parameter pos1D
   */
  @SyntheticMember
  @SarlSourceCode("0")
  private static final int $DEFAULT_VALUE$NEW_0 = 0;
  
  @Pure
  public Road getRoad() {
    return this.road;
  }
  
  public void setRoad(final Road road) {
    this.road = road;
  }
  
  @Pure
  public int getPos1D() {
    return this.pos1D;
  }
  
  public void setPos1D(final int pos1D) {
    this.pos1D = pos1D;
  }
  
  @DefaultValueUse("int,road_elements.Road,double,double")
  @SyntheticMember
  public RoadObject(final Road road, final double x, final double y) {
    this($DEFAULT_VALUE$NEW_0, road, x, y);
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
    if (other.pos1D != this.pos1D)
      return false;
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
    result = prime * result + this.pos1D;
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
  private static final long serialVersionUID = 1663872184L;
}
