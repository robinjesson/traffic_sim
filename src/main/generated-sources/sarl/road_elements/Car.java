package road_elements;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.MobileRoadObject;
import road_elements.Road;

/**
 * @author robin
 */
@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class Car extends MobileRoadObject {
  public Car(final int pos1D, final Road currentRoad, final double x, final double y) {
    throw new Error("Unresolved compilation problems:"
      + "\nInvalid number of arguments. The constructor MobileRoadObject(int, Road, RoadConnection) is not applicable for the arguments (int,Road,double,double)"
      + "\nType mismatch: cannot convert from double to RoadConnection");
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
  private static final long serialVersionUID = 637790605L;
}
