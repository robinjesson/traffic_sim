package road_elements;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.arakhne.afc.math.geometry.d1.d.Point1d;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.Road;
import road_elements.RoadObject;
import road_elements.TrafficLayers;

/**
 * @author tiantian
 */
@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class MobileRoadObject extends RoadObject {
  private TrafficLayers trafficLayers;
  
  public MobileRoadObject(final Point1d position, final Road currentRoad, final TrafficLayers trafficLayers) {
    super(position, currentRoad);
    this.trafficLayers = trafficLayers;
  }
  
  public void removeFromLayer() {
    synchronized (this.trafficLayers.getMapElementLayer()) {
      this.trafficLayers.getMapElementLayer().removeMapElement(this);
    }
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    return result;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public MobileRoadObject clone() {
    try {
      return (MobileRoadObject) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  private static final long serialVersionUID = 724033456L;
}
