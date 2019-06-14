package agents;

import agents.EnvAgent;
import capacities.Drive;
import capacities.DrivingDangerous;
import capacities.DrivingNormal;
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
import java.util.Random;
import java.util.UUID;
import javax.inject.Inject;
import org.arakhne.afc.gis.road.primitive.RoadConnection;
import org.arakhne.afc.gis.road.primitive.RoadSegment;
import org.arakhne.afc.math.geometry.d1.d.Point1d;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.eclipse.xtext.xbase.lib.Conversions;
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
  
  protected GPS gps;
  
  private Random random = new Random();
  
  protected Point2d begSegment;
  
  protected Point2d endSegment;
  
  private EnvAgent env;
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    synchronized (this) {
      int _nextInt = this.random.nextInt(2);
      switch (_nextInt) {
        case 0:
          DrivingNormal _drivingNormal = new DrivingNormal();
          this.<DrivingNormal>setSkill(_drivingNormal);
          break;
        default:
          DrivingDangerous _drivingDangerous = new DrivingDangerous();
          this.<DrivingDangerous>setSkill(_drivingDangerous);
          break;
      }
      Object _get = occurrence.parameters[1];
      this.env = ((EnvAgent) _get);
      Object _get_1 = occurrence.parameters[0];
      this.car = ((Car) _get_1);
      this.currentPoint = this.car.getCoordinates();
      Point2d _point2d = new Point2d(50, 50);
      this.arrivalPoint = _point2d;
      this.begSegment = this.car.getRoad().getBeginPoint().getPoint();
      this.endSegment = this.car.getRoad().getEndPoint().getPoint();
      this.car.setCoordinates(this.begSegment, this.endSegment);
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
  protected void ArrivedAtEndRoad() {
    synchronized (this) {
      RoadConnection b = this.car.getRoad().getBeginPoint();
      RoadConnection e = this.car.getRoad().getEndPoint();
      Road selectedRoad = null;
      boolean _isNearPoint = b.isNearPoint(this.car.getCoordinates());
      if (_isNearPoint) {
        do {
          {
            double _nextDouble = this.random.nextDouble();
            int _connectedSegmentCount = b.getConnectedSegmentCount();
            int index = ((int) (_nextDouble * _connectedSegmentCount));
            RoadSegment _get = ((RoadSegment[])Conversions.unwrapArray(b.getConnectedSegments(), RoadSegment.class))[index];
            selectedRoad = ((Road) _get);
          }
        } while((selectedRoad == this.car.getRoad()));
      } else {
        do {
          {
            double _nextDouble = this.random.nextDouble();
            int _connectedSegmentCount = e.getConnectedSegmentCount();
            int index = ((int) (_nextDouble * _connectedSegmentCount));
            RoadSegment _get = ((RoadSegment[])Conversions.unwrapArray(e.getConnectedSegments(), RoadSegment.class))[index];
            selectedRoad = ((Road) _get);
          }
        } while((selectedRoad == this.car.getRoad()));
      }
      this.car.getRoad().removeObject(this.car);
      this.car.setRoad(selectedRoad);
      this.car.getRoad().addObject(this.car);
      boolean _isNearPoint_1 = this.car.getRoad().getBeginPoint().isNearPoint(this.car.getCoordinates());
      if (_isNearPoint_1) {
        this.begSegment = this.car.getRoad().getBeginPoint().getPoint();
        this.endSegment = this.car.getRoad().getEndPoint().getPoint();
      } else {
        this.begSegment = this.car.getRoad().getEndPoint().getPoint();
        this.endSegment = this.car.getRoad().getBeginPoint().getPoint();
      }
      this.car.setCoordinates(this.begSegment, this.endSegment);
      Point1d _position = this.car.getPosition();
      _position.setX(0);
    }
  }
  
  protected void ArrivedAtDestination() {
    synchronized (this) {
      this.killThis();
    }
  }
  
  protected synchronized void killThis() {
    this.env.removeAgentAndCar(this.getID());
    this.car.getRoad().removeObject(this.car);
    this.car.removeFromLayer();
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$castSkill(Lifecycle.class, (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = this.$getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
    _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
  }
  
  private void $behaviorUnit$MoveForward$2(final MoveForward occurrence) {
    synchronized (this) {
      boolean _equals = this.car.getCoordinates().equals(this.car.getInfluence().arrivalPoint);
      if (_equals) {
        this.ArrivedAtDestination();
      }
      boolean _equals_1 = this.car.getCoordinates().equals(this.car.getInfluence().nextPoint);
      if (_equals_1) {
        this.ArrivedAtEndRoad();
      }
      double distFrontCar = this.car.getRoad().getFrontCarDistanceOf(this.car);
      Point1d _position = this.car.getPosition();
      double _x = this.car.getPosition().getX();
      Drive _$CAPACITY_USE$CAPACITIES_DRIVE$CALLER = this.$castSkill(Drive.class, (this.$CAPACITY_USE$CAPACITIES_DRIVE == null || this.$CAPACITY_USE$CAPACITIES_DRIVE.get() == null) ? (this.$CAPACITY_USE$CAPACITIES_DRIVE = this.$getSkill(Drive.class)) : this.$CAPACITY_USE$CAPACITIES_DRIVE);
      int _speed = _$CAPACITY_USE$CAPACITIES_DRIVE$CALLER.getSpeed();
      _position.setX((_x + _speed));
      this.car.setCoordinates(this.begSegment, this.endSegment);
      this.currentPoint = this.car.getCoordinates();
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
      UUID _iD = this.getID();
      Influence _influence = new Influence(_iD, this.arrivalPoint, this.endSegment);
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_influence);
    }
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
  private void $guardEvaluator$Destroy(final Destroy occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Destroy$1(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$MoveForward(final MoveForward occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$MoveForward$2(occurrence));
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
