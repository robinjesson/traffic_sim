package road_elements;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import org.arakhne.afc.gis.mapelement.MapElement;
import org.arakhne.afc.gis.maplayer.MapElementLayer;
import org.arakhne.afc.gis.maplayer.MultiMapLayer;
import org.arakhne.afc.gis.maplayer.TreeMapElementLayer;
import org.arakhne.afc.gis.road.StandardRoadNetwork;
import org.arakhne.afc.gis.road.layer.RoadNetworkLayer;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.Car;
import road_elements.Road;
import road_elements.RoadObject;

/**
 * @author robin
 */
@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class TrafficLayers extends MultiMapLayer {
  private Rectangle2d worldRect;
  
  private RoadNetworkLayer roadNetworkLayer;
  
  private MapElementLayer<MapElement> mapElementLayer;
  
  public TrafficLayers(final ArrayList<Road> roadList, final ArrayList<RoadObject> objectList) {
    super();
    Rectangle2d _rectangle2d = new Rectangle2d();
    this.worldRect = _rectangle2d;
    this.worldRect.setFromCorners((-200), (-200), 200, 200);
    StandardRoadNetwork network = new StandardRoadNetwork(this.worldRect);
    for (final Road road : roadList) {
      network.addRoadSegment(road);
    }
    RoadNetworkLayer _roadNetworkLayer = new RoadNetworkLayer(network);
    this.roadNetworkLayer = _roadNetworkLayer;
    TreeMapElementLayer<MapElement> _treeMapElementLayer = new TreeMapElementLayer<MapElement>(this.worldRect);
    this.mapElementLayer = _treeMapElementLayer;
    boolean _isNullOrEmpty = IterableExtensions.isNullOrEmpty(objectList);
    if ((!_isNullOrEmpty)) {
      this.mapElementLayer.addMapElements(objectList);
    }
    this.addMapLayer(this.roadNetworkLayer);
    this.addMapLayer(this.mapElementLayer);
  }
  
  @Pure
  public static ArrayList<Road> roadListFactory() {
    ArrayList<Road> roadList = new ArrayList<Road>();
    Road r0 = new Road(0, 0, 0, 20, 100);
    roadList.add(r0);
    Road _road = new Road(0, 20, 0, 100, 100);
    roadList.add(_road);
    Road _road_1 = new Road(0, 100, 100, 100, 100);
    roadList.add(_road_1);
    Road _road_2 = new Road(100, 100, 100, 50, 100);
    roadList.add(_road_2);
    Road _road_3 = new Road(100, 50, 100, 0, 100);
    roadList.add(_road_3);
    Road _road_4 = new Road(100, 0, 0, 0, 100);
    roadList.add(_road_4);
    Road _road_5 = new Road(0, 20, 20, 20, 70);
    roadList.add(_road_5);
    Road _road_6 = new Road(50, 50, 100, 50, 70);
    roadList.add(_road_6);
    Road _road_7 = new Road(20, 20, 50, 20, 50);
    roadList.add(_road_7);
    Road _road_8 = new Road(20, 50, 50, 50, 50);
    roadList.add(_road_8);
    Road _road_9 = new Road(20, 30, 50, 30, 50);
    roadList.add(_road_9);
    Road _road_10 = new Road(20, 20, 20, 30, 50);
    roadList.add(_road_10);
    Road _road_11 = new Road(20, 30, 20, 50, 50);
    roadList.add(_road_11);
    Road _road_12 = new Road(50, 20, 50, 30, 50);
    roadList.add(_road_12);
    Road _road_13 = new Road(50, 30, 50, 50, 50);
    roadList.add(_road_13);
    return roadList;
  }
  
  /**
   * def static carListFactory(roads : ArrayList<Road>) : ArrayList<RoadObject> {
   * var carList : ArrayList<RoadObject> = new ArrayList<RoadObject>()
   * 
   * for(road:roads){
   * var nbCarRandom = (Math.random() * 3) as int
   * for (var i = 0; i < nbCarRandom; i++) {
   * var posRandom = (Math.random() * road.distanceKilometers) as int
   * carList.add(new Car(posRandom, road, 0, 0))
   * }
   * }
   * 
   * for (road : roads) {
   * for (var i = 0; i < 1; i++) {
   * var posRandom = 0 as int
   * carList.add(new Car(posRandom, road,  0, 0))
   * }
   * }
   * return carList
   * }
   */
  public boolean addCar(final Car car) {
    return this.mapElementLayer.addMapElement(car);
  }
  
  @Pure
  public MapElementLayer<MapElement> getMapElementLayer() {
    return this.mapElementLayer;
  }
  
  @Pure
  public RoadNetworkLayer getRoadNetworkLayer() {
    return this.roadNetworkLayer;
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
  public TrafficLayers clone() {
    try {
      return (TrafficLayers) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  private static final long serialVersionUID = 2859716412L;
}
