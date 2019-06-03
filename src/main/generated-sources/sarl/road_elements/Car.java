package road_elements;

import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.MobileRoadObject;
import road_elements.Road;
import road_elements.TrafficLayers;

/**
 * @author robin
 */
@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class Car extends MobileRoadObject {
  @DefaultValueSource
  public Car(final int pos1D, final Road currentRoad, final TrafficLayers trafficLayers, @DefaultValue("road_elements.Car#NEW_0") final double x, @DefaultValue("road_elements.Car#NEW_1") final double y) {
    super(pos1D, currentRoad, x, y);
    Road _road = super.getRoad();
    if ((_road != null)) {
      super.getRoad().addObject(this);
    }
    trafficLayers.addCar(this);
  }
  
  /**
   * Default value for the parameter x
   */
  @SyntheticMember
  @SarlSourceCode("0")
  private static final double $DEFAULT_VALUE$NEW_0 = 0;
  
  /**
   * Default value for the parameter y
   */
  @SyntheticMember
  @SarlSourceCode("0")
  private static final double $DEFAULT_VALUE$NEW_1 = 0;
  
  @DefaultValueUse("int,road_elements.Road,road_elements.TrafficLayers,double,double")
  @SyntheticMember
  public Car(final int pos1D, final Road currentRoad, final TrafficLayers trafficLayers) {
    this(pos1D, currentRoad, trafficLayers, $DEFAULT_VALUE$NEW_0, $DEFAULT_VALUE$NEW_1);
  }
  
  @DefaultValueUse("int,road_elements.Road,road_elements.TrafficLayers,double,double")
  @SyntheticMember
  public Car(final int pos1D, final Road currentRoad, final TrafficLayers trafficLayers, final double x) {
    this(pos1D, currentRoad, trafficLayers, x, $DEFAULT_VALUE$NEW_1);
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
  private static final long serialVersionUID = -1761683462L;
}
