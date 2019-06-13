package road_elements;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.arakhne.afc.math.geometry.d1.d.Point1d;
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
  public TrafficLight(final Point1d position, final Road currentRoad) {
    super(position, currentRoad);
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
  private static final long serialVersionUID = -1677496240L;
}
