package road_elements;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.arakhne.afc.gis.maplayer.MultiMapLayer;
import org.arakhne.afc.gis.ui.GisCanvas;
import org.arakhne.afc.nodefx.ZoomablePane;
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
    MultiMapLayer loadedResource = this.getRoads();
    BorderPane root = new BorderPane();
    Label messageBar = new Label("");
    messageBar.setTextAlignment(TextAlignment.CENTER);
    MultiMapLayer<MultiMapLayer> rootLayer = new MultiMapLayer<MultiMapLayer>();
    rootLayer.addMapLayer(loadedResource);
    GisCanvas<MultiMapLayer<MultiMapLayer>> _gisCanvas = new GisCanvas<MultiMapLayer<MultiMapLayer>>(rootLayer);
    ZoomablePane<MultiMapLayer<MultiMapLayer>> scrollPane = new ZoomablePane<MultiMapLayer<MultiMapLayer>>(_gisCanvas);
    root.setCenter(scrollPane);
    root.setBottom(messageBar);
    Scene scene = new Scene(root, 1024, 768);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  @SyntheticMember
  public Test() {
    super();
  }
}
