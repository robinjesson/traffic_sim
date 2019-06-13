package agents;

import agents.Driver;
import com.google.common.base.Objects;
import events.ArrivedAtDestination;
import events.ArrivedAtEndRoad;
import events.Influence;
import events.MoveForward;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Initialize;
import io.sarl.core.Lifecycle;
import io.sarl.core.Logging;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.DynamicSkillProvider;
import io.sarl.lang.core.Scope;
import io.sarl.lang.core.Skill;
import io.sarl.lang.util.ClearableReference;
import io.sarl.lang.util.SerializableProxy;
import java.io.ObjectStreamException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import org.arakhne.afc.gis.road.primitive.RoadNetwork;
import org.arakhne.afc.gis.road.primitive.RoadSegment;
import org.arakhne.afc.math.geometry.d1.d.Point1d;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.Car;
import road_elements.Road;
import road_elements.TrafficLayers;

/**
 * @author robin
 */
@SarlSpecification("0.9")
@SarlElementType(19)
@SuppressWarnings("all")
public class EnvAgent extends Agent {
  private RoadNetwork network;
  
  private TrafficLayers trafficLayers;
  
  private final TreeMap<UUID, Car> agentId_Cars = new TreeMap<UUID, Car>();
  
  private AtomicInteger nbInfluenceToCompute = new AtomicInteger();
  
  private AtomicInteger time = new AtomicInteger();
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    this.nbInfluenceToCompute.set(0);
    this.time.set(0);
    Object _get = occurrence.parameters[0];
    this.trafficLayers = ((TrafficLayers) _get);
    this.network = this.trafficLayers.getRoadNetworkLayer().getRoadNetwork();
    this.spawnCarAndAgent();
  }
  
  private void $behaviorUnit$Influence$1(final Influence occurrence) {
    UUID agentId = occurrence.agentId;
    Car car = this.getCarByAgentId(agentId);
    car.setInfluence(occurrence);
    this.nbInfluenceToCompute.incrementAndGet();
    int _size = this.agentId_Cars.size();
    if ((this.nbInfluenceToCompute.intValue() >= _size)) {
      this.computeInfluences();
    }
  }
  
  protected void computeInfluences() {
    try {
      Thread.sleep(250);
      Set<Map.Entry<UUID, Car>> _entrySet = this.agentId_Cars.entrySet();
      for (final Map.Entry<UUID, Car> b : _entrySet) {
        {
          Car body = b.getValue();
          UUID agentId = body.getInfluence().agentId;
          Car car = this.getCarByAgentId(agentId);
          boolean _equals = car.getCoordinates().equals(body.getInfluence().arrivalPoint);
          if (_equals) {
            DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
            ArrivedAtDestination _arrivedAtDestination = new ArrivedAtDestination();
            class $SerializableClosureProxy implements Scope<Address> {
              
              private final UUID agentId;
              
              public $SerializableClosureProxy(final UUID agentId) {
                this.agentId = agentId;
              }
              
              @Override
              public boolean matches(final Address elt) {
                UUID _uUID = elt.getUUID();
                return Objects.equal(_uUID, agentId);
              }
            }
            final Scope<Address> _function = new Scope<Address>() {
              @Override
              public boolean matches(final Address elt) {
                UUID _uUID = elt.getUUID();
                return Objects.equal(_uUID, agentId);
              }
              private Object writeReplace() throws ObjectStreamException {
                return new SerializableProxy($SerializableClosureProxy.class, agentId);
              }
            };
            _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_arrivedAtDestination, _function);
            return;
          }
          boolean _equals_1 = car.getCoordinates().equals(body.getInfluence().nextPoint);
          if (_equals_1) {
            DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1 = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
            ArrivedAtEndRoad _arrivedAtEndRoad = new ArrivedAtEndRoad();
            class $SerializableClosureProxy_1 implements Scope<Address> {
              
              private final UUID agentId;
              
              public $SerializableClosureProxy_1(final UUID agentId) {
                this.agentId = agentId;
              }
              
              @Override
              public boolean matches(final Address elt) {
                UUID _uUID = elt.getUUID();
                return Objects.equal(_uUID, agentId);
              }
            }
            final Scope<Address> _function_1 = new Scope<Address>() {
              @Override
              public boolean matches(final Address elt) {
                UUID _uUID = elt.getUUID();
                return Objects.equal(_uUID, agentId);
              }
              private Object writeReplace() throws ObjectStreamException {
                return new SerializableProxy($SerializableClosureProxy_1.class, agentId);
              }
            };
            _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1.emit(_arrivedAtEndRoad, _function_1);
            return;
          }
          DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_2 = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
          MoveForward _moveForward = new MoveForward();
          class $SerializableClosureProxy_2 implements Scope<Address> {
            
            private final UUID agentId;
            
            public $SerializableClosureProxy_2(final UUID agentId) {
              this.agentId = agentId;
            }
            
            @Override
            public boolean matches(final Address elt) {
              UUID _uUID = elt.getUUID();
              return Objects.equal(_uUID, agentId);
            }
          }
          final Scope<Address> _function_2 = new Scope<Address>() {
            @Override
            public boolean matches(final Address elt) {
              UUID _uUID = elt.getUUID();
              return Objects.equal(_uUID, agentId);
            }
            private Object writeReplace() throws ObjectStreamException {
              return new SerializableProxy($SerializableClosureProxy_2.class, agentId);
            }
          };
          _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_2.emit(_moveForward, _function_2);
        }
      }
      this.nbInfluenceToCompute.set(0);
      this.time.incrementAndGet();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected Car removeAgentAndCar(final UUID idAgent) {
    return this.agentId_Cars.remove(idAgent);
  }
  
  @DefaultValueSource
  protected synchronized UUID spawnCarAndAgent(@DefaultValue("agents.EnvAgent#SPAWNCARANDAGENT_0") final int time) {
    while ((this.time.intValue() < 0)) {
    }
    int nbRoads = this.network.getRoadSegments().size();
    double _random = Math.random();
    int indexRoadRandom = ((int) (_random * nbRoads));
    RoadSegment _get = ((RoadSegment[])Conversions.unwrapArray(this.network.getRoadSegments(), RoadSegment.class))[indexRoadRandom];
    Road selectedRoad = ((Road) _get);
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info(selectedRoad);
    Point1d _point1d = new Point1d(selectedRoad, 0, 1);
    Car car = new Car(_point1d, selectedRoad, this.trafficLayers);
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$castSkill(Lifecycle.class, (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = this.$getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
    UUID id = _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.spawn(Driver.class, car, this.network, this);
    this.agentId_Cars.put(id, car);
    return id;
  }
  
  /**
   * Default value for the parameter time
   */
  @SyntheticMember
  @SarlSourceCode("0")
  private static final int $DEFAULT_VALUE$SPAWNCARANDAGENT_0 = 0;
  
  @Pure
  protected Car getCarByAgentId(final UUID agentId) {
    return this.agentId_Cars.get(agentId);
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
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$0(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Influence(final Influence occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Influence$1(occurrence));
  }
  
  @DefaultValueUse("int")
  @SyntheticMember
  protected final UUID spawnCarAndAgent() {
    return spawnCarAndAgent($DEFAULT_VALUE$SPAWNCARANDAGENT_0);
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
  public EnvAgent(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public EnvAgent(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public EnvAgent(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
}
