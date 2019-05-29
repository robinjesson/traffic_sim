package road_elements;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.InputOutput;
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
    super(pos1D, currentRoad, x, y);
    Road _road = super.getRoad();
    if ((_road != null)) {
      super.getRoad().addObject(this);
      InputOutput.<Road>println(this.getRoad());
    }
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
