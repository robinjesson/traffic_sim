package road_elements;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
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
  /**
   * Returns the next road to take
   * Should be used when an agent arrive at the end of its road
   */
  @Pure
  public static Road nextRoad(final Point2d current, final Point2d end, final RoadNetwork network) {
    RoadAStar aStar = new RoadAStar();
    RoadPath path = aStar.solve(current, end, network);
    int count = 0;
    for (final RoadSegment roadSegment : path) {
      return ((Road) roadSegment);
    }
    return null;
  }
  
  @SyntheticMember
  public GPS() {
    super();
  }
}
