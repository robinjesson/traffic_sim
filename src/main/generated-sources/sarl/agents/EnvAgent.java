package agents;

import agents.Driver;
import com.google.common.base.Objects;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.application.Platform;
import javax.inject.Inject;
import org.arakhne.afc.gis.maplayer.MultiMapLayer;
import org.arakhne.afc.gis.road.primitive.RoadNetwork;
import org.arakhne.afc.gis.road.primitive.RoadSegment;
import org.arakhne.afc.gis.ui.GisCanvas;
import org.arakhne.afc.math.geometry.d1.d.Point1d;
import org.eclipse.xtext.xbase.lib.Conversions;
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
  private final TreeMap<UUID, Car> agentId_Cars = new TreeMap<UUID, Car>();
  
  private final AtomicBoolean freeze = new AtomicBoolean();
  
  private GisCanvas<MultiMapLayer<TrafficLayers>> canvas;
  
  private RoadNetwork network;
  
  private TrafficLayers trafficLayers;
  
  private AtomicInteger influences = new AtomicInteger();
  
  private int time = 0;
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    this.influences.set(0);
    synchronized (this) {
      Object _get = occurrence.parameters[1];
      this.canvas = ((GisCanvas<MultiMapLayer<TrafficLayers>>) _get);
      Object _get_1 = occurrence.parameters[0];
      this.trafficLayers = ((TrafficLayers) _get_1);
      this.network = this.trafficLayers.getRoadNetworkLayer().getRoadNetwork();
    }
    this.spawnCarAndAgent(3);
  }
  
  private void $behaviorUnit$Influence$1(final Influence occurrence) {
    synchronized (this) {
      UUID agentId = occurrence.agentId;
      Car car = this.getCarByAgentId(agentId);
      if ((car != null)) {
        car.setInfluence(occurrence);
        int v = this.influences.incrementAndGet();
        int _size = this.agentId_Cars.size();
        if ((v >= _size)) {
          this.computeInfluences();
        }
      }
    }
  }
  
  protected synchronized int computeInfluences() {
    int _xblockexpression = (int) 0;
    {
      this.freeze.set(true);
      long now = System.currentTimeMillis();
      long restart = (now + 250);
      int _xtrycatchfinallyexpression = (int) 0;
      try {
        int _xblockexpression_1 = (int) 0;
        {
          this.influences.set(0);
          Set<Map.Entry<UUID, Car>> _entrySet = this.agentId_Cars.entrySet();
          for (final Map.Entry<UUID, Car> b : _entrySet) {
            {
              Car body = b.getValue();
              UUID agentId = body.getInfluence().agentId;
              Car car = this.getCarByAgentId(agentId);
              DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
              MoveForward _moveForward = new MoveForward();
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
              _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_moveForward, _function);
              while ((System.currentTimeMillis() < restart)) {
              }
            }
          }
          final Runnable _function = () -> {
            this.canvas.drawContent();
          };
          Platform.runLater(_function);
          _xblockexpression_1 = this.time++;
        }
        _xtrycatchfinallyexpression = _xblockexpression_1;
      } finally {
        this.freeze.set(false);
      }
      _xblockexpression = _xtrycatchfinallyexpression;
    }
    return _xblockexpression;
  }
  
  protected synchronized Car removeAgentAndCar(final UUID idAgent) {
    return this.agentId_Cars.remove(idAgent);
  }
  
  @DefaultValueSource
  protected synchronized Collection<UUID> spawnCarAndAgent(@DefaultValue("agents.EnvAgent#SPAWNCARANDAGENT_0") final int nb) {
    Collection<UUID> colID = new ArrayList<UUID>();
    for (int i = 0; (i < nb); i++) {
      {
        int nbRoads = this.network.getRoadSegments().size();
        double _random = Math.random();
        int indexRoadRandom = ((int) (_random * nbRoads));
        RoadSegment _get = ((RoadSegment[])Conversions.unwrapArray(this.network.getRoadSegments(), RoadSegment.class))[indexRoadRandom];
        Road selectedRoad = ((Road) _get);
        Point1d _point1d = new Point1d(selectedRoad, 0, 1);
        Car car = new Car(_point1d, selectedRoad, this.trafficLayers);
        Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$castSkill(Lifecycle.class, (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = this.$getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
        UUID id = _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.spawn(Driver.class, car, this);
        this.agentId_Cars.put(id, car);
        colID.add(id);
      }
    }
    return colID;
  }
  
  /**
   * Default value for the parameter nb
   */
  @SyntheticMember
  @SarlSourceCode("1")
  private static final int $DEFAULT_VALUE$SPAWNCARANDAGENT_0 = 1;
  
  @Pure
  protected synchronized Car getCarByAgentId(final UUID agentId) {
    return this.agentId_Cars.get(agentId);
  }
  
  @Pure
  protected synchronized RoadNetwork getNetwork() {
    return this.network;
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
  protected final Collection<UUID> spawnCarAndAgent() {
    return spawnCarAndAgent($DEFAULT_VALUE$SPAWNCARANDAGENT_0);
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
    EnvAgent other = (EnvAgent) obj;
    if (other.time != this.time)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + this.time;
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
