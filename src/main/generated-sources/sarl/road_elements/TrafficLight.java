package road_elements;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.Road;
import road_elements.RoadObject;

/**
 * @author tiantian
 */
@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class TrafficLight extends RoadObject {
  public TrafficLight(final int pos1D, final Road currentRoad) {
    throw new Error("Unresolved compilation problems:"
      + "\nInvalid number of arguments. The constructor RoadObject(Road, double, double) is not applicable for the arguments (int,Road)"
      + "\nType mismatch: cannot convert from int to Road"
      + "\nType mismatch: cannot convert from Road to double");
  }
  
  @Override
  @Pure
  @SyntheticMember
  public TrafficLight clone() {
    try {
      return (TrafficLight) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  private static final long serialVersionUID = 1177218349L;
}
