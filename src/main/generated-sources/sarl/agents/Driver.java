package agents;

import events.ArrivedAtDestination;
import events.ArrivedAtEndRoad;
import events.Influence;
import events.MoveForward;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Initialize;
import io.sarl.core.Lifecycle;
import io.sarl.core.Logging;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.DynamicSkillProvider;
import io.sarl.lang.core.Skill;
import io.sarl.lang.util.ClearableReference;
import java.util.Collection;
import java.util.UUID;
import javax.inject.Inject;
import org.arakhne.afc.gis.road.primitive.RoadNetwork;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
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
  
  protected GPS gps;
  
  protected boolean normalDirection = true;
  
  protected synchronized void initProperties(final Car car, final Point2d arrivalPoint, final RoadNetwork network) {
    this.car = car;
    this.currentPoint = this.car.getCoordinates();
    this.arrivalPoint = arrivalPoint;
    this.speed = 0;
    GPS _gPS = new GPS(this.currentPoint, this.arrivalPoint, network);
    this.gps = _gPS;
  }
  
  private void $behaviorUnit$ArrivedAtEndRoad$0(final ArrivedAtEndRoad occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("ARRIVED END OF ROAD");
    synchronized (this) {
      Road nextRoadToTake = this.gps.getNextRoad();
      if ((nextRoadToTake == null)) {
        this.killThis();
      }
      this.car.setRoad(nextRoadToTake);
      Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER_1 = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
      int _beginX = nextRoadToTake.getBeginX();
      String _plus = (Integer.valueOf(_beginX) + " ");
      int _beginY = nextRoadToTake.getBeginY();
      int _endX = nextRoadToTake.getEndX();
      int _endY = nextRoadToTake.getEndY();
      _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER_1.info((((((_plus + Integer.valueOf(_beginY)) + "; ") + Integer.valueOf(_endX)) + " ") + Integer.valueOf(_endY)));
      Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER_2 = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
      _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER_2.info(this.car.getCoordinates());
      boolean _equals = this.currentPoint.equals(nextRoadToTake.getBegin());
      if (_equals) {
        this.car.setPos1D(0);
        this.normalDirection = true;
      } else {
        boolean _equals_1 = this.currentPoint.equals(nextRoadToTake.getEnd());
        if (_equals_1) {
          this.car.setPos1D(this.car.getRoad().getDistanceKilometers());
          this.normalDirection = false;
        }
      }
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
      UUID _iD = this.getID();
      Influence _influence = new Influence(_iD, this.arrivalPoint);
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_influence);
    }
  }
  
  private void $behaviorUnit$ArrivedAtDestination$1(final ArrivedAtDestination occurrence) {
    this.killThis();
  }
  
  protected void killThis() {
    this.car.removeFromLayer();
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$castSkill(Lifecycle.class, (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = this.$getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
    _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
  }
  
  private void $behaviorUnit$MoveForward$2(final MoveForward occurrence) {
    try {
      synchronized (this) {
        if (this.normalDirection) {
          double _pos1D = this.car.getPos1D();
          this.car.setPos1D((_pos1D + 1));
        } else {
          double _pos1D_1 = this.car.getPos1D();
          this.car.setPos1D((_pos1D_1 - 1));
        }
        this.currentPoint = this.car.getCoordinates();
        Thread.sleep(250);
        DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
        UUID _iD = this.getID();
        Influence _influence = new Influence(_iD, this.arrivalPoint);
        _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_influence);
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Pure
  protected Point2d getCurrentPoint() {
    return this.currentPoint;
  }
  
  @Pure
  protected Point2d getArrivalPoint() {
    return this.arrivalPoint;
  }
  
  private void $behaviorUnit$Initialize$3(final Initialize occurrence) {
  }
  
  @Extension
  @ImportedCapacityFeature(Logging.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_LOGGING;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Logging.class, ($0$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || $0$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_LOGGING = $0$getSkill(Logging.class)) : $0$CAPACITY_USE$IO_SARL_CORE_LOGGING)", imported = Logging.class)
  private Logging $CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = $getSkill(Logging.class);
    }
    return $castSkill(Logging.class, this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
  }
  
  @Extension
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(DefaultContextInteractions.class, ($0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || $0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $0$getSkill(DefaultContextInteractions.class)) : $0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS)", imported = DefaultContextInteractions.class)
  private DefaultContextInteractions $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $getSkill(DefaultContextInteractions.class);
    }
    return $castSkill(DefaultContextInteractions.class, this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
  }
  
  @Extension
  @ImportedCapacityFeature(Lifecycle.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Lifecycle.class, ($0$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || $0$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = $0$getSkill(Lifecycle.class)) : $0$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE)", imported = Lifecycle.class)
  private Lifecycle $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = $getSkill(Lifecycle.class);
    }
    return $castSkill(Lifecycle.class, this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$3(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$ArrivedAtDestination(final ArrivedAtDestination occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ArrivedAtDestination$1(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$MoveForward(final MoveForward occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$MoveForward$2(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$ArrivedAtEndRoad(final ArrivedAtEndRoad occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ArrivedAtEndRoad$0(occurrence));
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
    if (other.normalDirection != this.normalDirection)
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
    result = prime * result + (this.normalDirection ? 1231 : 1237);
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
