package road_elements;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import org.arakhne.afc.gis.maplayer.MultiMapLayer;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.Road;
import road_elements.RoadObject;
import road_elements.TrafficLayers;

@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class Test extends Application {
  public static void main(final String[] args) {
    Application.launch(args);
    System.out.print(1);
  }
  
  @Pure
  public MultiMapLayer getRoads() {
    ArrayList<Road> roads = TrafficLayers.roadListFactory();
    ArrayList<RoadObject> cars = TrafficLayers.carListFactory(roads);
    return new TrafficLayers(roads, cars);
  }
  
  @Override
  public void start(final Stage primaryStage) throws Exception {
    throw new Error("Unresolved compilation problems:"
      + "\nZoomablePane cannot be resolved."
      + "\nGisCanvas cannot be resolved.");
  }
  
  @SyntheticMember
  public Test() {
    super();
  }
}
