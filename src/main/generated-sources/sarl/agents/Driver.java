package agents;

import capacities.Drive;
import capacities.DrivingNormal;
import events.ArrivedAtDestination;
import events.ArrivedAtEndRoad;
import events.Influence;
import events.MoveForward;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Destroy;
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

@SarlSpecification("0.9")
@SarlElementType(19)
@SuppressWarnings("all")
public class Driver extends Agent {
  protected Car car;
  
  protected GPS gps;
  
  protected Point2d currentPoint;
  
  protected Point2d arrivalPoint;
  
  protected Point2d begSegment;
  
  protected Point2d endSegment;
  
  protected synchronized void initProperties(final Car car, final Point2d arrivalPoint, final RoadNetwork network) {
    this.car.setRoad(this.gps.getNextRoad());
    this.begSegment = this.gps.getNextPoint();
    this.endSegment = this.gps.getNextPoint();
    this.car.setCoordinates(this.begSegment, this.endSegment);
    this.currentPoint = this.car.getCoordinates();
  }
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    synchronized (this) {
      DrivingNormal _drivingNormal = new DrivingNormal();
      this.<DrivingNormal>setSkill(_drivingNormal);
      Object _get = occurrence.parameters[1];
      RoadNetwork network = ((RoadNetwork) _get);
      Object _get_1 = occurrence.parameters[0];
      this.car = ((Car) _get_1);
      this.currentPoint = this.car.getCoordinates();
      Point2d _point2d = new Point2d(50, 50);
      this.arrivalPoint = _point2d;
      GPS _gPS = new GPS(this.currentPoint, this.arrivalPoint, network);
      this.gps = _gPS;
      this.car.setRoad(this.gps.getNextRoad());
      this.begSegment = this.gps.getNextPoint();
      this.endSegment = this.gps.getNextPoint();
      if ((this.endSegment == null)) {
        this.killThis();
      }
      boolean _hasNextRoad = this.gps.hasNextRoad();
      if ((!_hasNextRoad)) {
        this.killThis();
        return;
      }
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
      UUID _iD = this.getID();
      Influence _influence = new Influence(_iD, this.arrivalPoint, this.endSegment);
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_influence);
    }
  }
  
  private void $behaviorUnit$Destroy$1(final Destroy occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("The agent was stopped.");
  }
  
  private void $behaviorUnit$ArrivedAtEndRoad$2(final ArrivedAtEndRoad occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("ARRIVED END OF ROAD");
    synchronized (this) {
      Road nextRoadToTake = this.gps.getNextRoad();
      this.begSegment = this.endSegment;
      this.endSegment = this.gps.getNextPoint();
      if ((this.endSegment == null)) {
        this.killThis();
      }
      this.car.setCoordinates(this.begSegment, this.endSegment);
      this.car.getRoad().removeObject(this.car);
      this.car.setRoad(nextRoadToTake);
      this.car.getRoad().addObject(this.car);
      this.car.setPos1D(0);
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
      UUID _iD = this.getID();
      Influence _influence = new Influence(_iD, this.arrivalPoint, this.endSegment);
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_influence);
    }
  }
  
  private void $behaviorUnit$ArrivedAtDestination$3(final ArrivedAtDestination occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("ARRIVED AT DESTINATION");
    this.killThis();
  }
  
  private void $behaviorUnit$MoveForward$4(final MoveForward occurrence) {
    try {
      synchronized (this) {
        double _pos1D = this.car.getPos1D();
        Drive _$CAPACITY_USE$CAPACITIES_DRIVE$CALLER = this.$castSkill(Drive.class, (this.$CAPACITY_USE$CAPACITIES_DRIVE == null || this.$CAPACITY_USE$CAPACITIES_DRIVE.get() == null) ? (this.$CAPACITY_USE$CAPACITIES_DRIVE = this.$getSkill(Drive.class)) : this.$CAPACITY_USE$CAPACITIES_DRIVE);
        int _speed = _$CAPACITY_USE$CAPACITIES_DRIVE$CALLER.getSpeed();
        this.car.setPos1D((_pos1D + _speed));
        this.car.setCoordinates(this.begSegment, this.endSegment);
        this.currentPoint = this.car.getCoordinates();
        Thread.sleep(250);
        DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
        UUID _iD = this.getID();
        Influence _influence = new Influence(_iD, this.arrivalPoint, this.endSegment);
        _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_influence);
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private void $behaviorUnit$Destroy$5(final Destroy occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("The agent was stopped.");
  }
  
  protected synchronized void killThis() {
    this.car.removeFromLayer();
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$castSkill(Lifecycle.class, (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = this.$getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
    _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
  }
  
  @Pure
  protected synchronized Point2d getCurrentPoint() {
    return this.currentPoint;
  }
  
  @Pure
  protected synchronized Point2d getArrivalPoint() {
    return this.arrivalPoint;
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
  
  @Extension
  @ImportedCapacityFeature(Drive.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$CAPACITIES_DRIVE;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Drive.class, ($0$CAPACITY_USE$CAPACITIES_DRIVE == null || $0$CAPACITY_USE$CAPACITIES_DRIVE.get() == null) ? ($0$CAPACITY_USE$CAPACITIES_DRIVE = $0$getSkill(Drive.class)) : $0$CAPACITY_USE$CAPACITIES_DRIVE)", imported = Drive.class)
  private Drive $CAPACITY_USE$CAPACITIES_DRIVE$CALLER() {
    if (this.$CAPACITY_USE$CAPACITIES_DRIVE == null || this.$CAPACITY_USE$CAPACITIES_DRIVE.get() == null) {
      this.$CAPACITY_USE$CAPACITIES_DRIVE = $getSkill(Drive.class);
    }
    return $castSkill(Drive.class, this.$CAPACITY_USE$CAPACITIES_DRIVE);
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$0(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$ArrivedAtDestination(final ArrivedAtDestination occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ArrivedAtDestination$3(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Destroy(final Destroy occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Destroy$1(occurrence));
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Destroy$5(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$MoveForward(final MoveForward occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$MoveForward$4(occurrence));
  }
  
  /**
   * synchronized def initProperties(car : Car, arrivalPoint : Point2d, network : RoadNetwork) : void {
   * 
   * this.car.road = this.gps.nextRoad
   * this.begSegment=this.gps.nextPoint
   * this.endSegment = this.gps.nextPoint
   * this.car.setCoordinates(this.begSegment, this.endSegment)
   * this.currentPoint = this.car.coordinates
   * 
   * }
   */
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$ArrivedAtEndRoad(final ArrivedAtEndRoad occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ArrivedAtEndRoad$2(occurrence));
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
