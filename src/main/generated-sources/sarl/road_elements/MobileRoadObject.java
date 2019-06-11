package road_elements;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.arakhne.afc.gis.road.primitive.RoadConnection;
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
  private RoadConnection entryRoadConnection;
  
  private TrafficLayers trafficLayers;
  
  public MobileRoadObject(final double pos1D, final Road currentRoad, final TrafficLayers trafficLayers) {
    super(pos1D, currentRoad);
    this.trafficLayers = trafficLayers;
  }
  
  public void removeFromLayer() {
    this.trafficLayers.getMapElementLayer().removeMapElement(this);
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
  private static final long serialVersionUID = 2240737139L;
}
