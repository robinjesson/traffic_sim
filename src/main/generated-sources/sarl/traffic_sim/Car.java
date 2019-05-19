package traffic_sim;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_sim.RoadObject;

@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class Car extends RoadObject {
  public Car() {
    super();
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
  private final static long serialVersionUID = 2230L;
}
