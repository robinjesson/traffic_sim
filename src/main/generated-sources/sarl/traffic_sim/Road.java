package traffic_sim;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.UUID;
import org.arakhne.afc.gis.road.RoadPolyline;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_sim.RoadObject;

@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class Road extends RoadPolyline {
  private ArrayList<RoadObject> objects;
  
  public Road() {
    super();
    ArrayList<RoadObject> _arrayList = new ArrayList<RoadObject>();
    this.objects = _arrayList;
  }
  
  public boolean addObject(final RoadObject object) {
    return this.objects.add(object);
  }
  
  @Pure
  public RoadObject getByUUID(final UUID id) {
    for (final RoadObject current : this.objects) {
      UUID _uUID = current.getUUID();
      if ((_uUID == id)) {
        return current;
      }
    }
    return null;
  }
  
  public RoadObject removeObject(final UUID id) {
    RoadObject object = this.getByUUID(id);
    this.objects.remove(object);
    return object;
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
  public Road clone() {
    try {
      return (Road) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 766179578L;
}
