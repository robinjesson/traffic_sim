package agents;

import events.EndOfRoad;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.DynamicSkillProvider;
import java.util.Collection;
import java.util.UUID;
import javax.inject.Inject;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.Car;
import road_elements.GPS;
import road_elements.Road;

/**
 * @author robin
 */
@SarlSpecification("0.9")
@SarlElementType(19)
@SuppressWarnings("all")
public class Driver extends Agent {
  protected Point2d currentPoint;
  
  protected Point2d arrivalPoint;
  
  protected Car car;
  
  protected int speed;
  
  protected synchronized void initProperties(final int pos1D, final Road firstRoad, final Point2d startPoint, final Point2d arrivalPoint) {
    throw new Error("Unresolved compilation problems:"
      + "\nInvalid number of arguments. The constructor Car(int, Road, RoadConnection) is not applicable for the arguments (int,Road)");
  }
  
  private void $behaviorUnit$EndOfRoad$0(final EndOfRoad occurrence) {
    Road nextRoadToTake = null;
    synchronized (this) {
      nextRoadToTake = GPS.nextRoad(this.currentPoint, this.arrivalPoint, this.car.getRoad().getRoadNetwork());
      this.car.setRoad(nextRoadToTake);
      this.car.setPos1D(0);
    }
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$EndOfRoad(final EndOfRoad occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$EndOfRoad$0(occurrence));
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
    Driver other = (Driver) obj;
    if (other.speed != this.speed)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + this.speed;
    return result;
  }
  
  @SyntheticMember
  public Driver(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public Driver(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public Driver(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
}
