package road_elements;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import org.arakhne.afc.gis.road.path.RoadPath;
import org.arakhne.afc.gis.road.path.astar.RoadAStar;
import org.arakhne.afc.gis.road.primitive.RoadNetwork;
import org.arakhne.afc.gis.road.primitive.RoadSegment;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.Road;

@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class GPS {
  private ArrayList<Road> listRoads = new ArrayList<Road>();
  
  public GPS(final Point2d current, final Point2d end, final RoadNetwork network) {
    RoadAStar aStar = new RoadAStar();
    RoadPath path = aStar.solve(current, end, network);
    int count = 0;
    for (final RoadSegment roadSegment : path) {
      this.listRoads.add(((Road) roadSegment));
    }
  }
  
  @Pure
  public Road getNextRoad() {
    boolean _hastNextRoad = this.hastNextRoad();
    if (_hastNextRoad) {
      Road r = this.listRoads.get(0);
      this.listRoads.remove(0);
      return r;
    }
    return null;
  }
  
  @Pure
  public boolean hastNextRoad() {
    int _size = this.listRoads.size();
    return (_size != 0);
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
}
