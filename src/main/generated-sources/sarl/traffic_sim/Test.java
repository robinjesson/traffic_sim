package traffic_sim;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.arakhne.afc.gis.maplayer.MultiMapLayer;
import org.arakhne.afc.gis.road.RoadPolyline;
import org.arakhne.afc.gis.road.StandardRoadNetwork;
import org.arakhne.afc.gis.road.layer.RoadNetworkLayer;
import org.arakhne.afc.gis.ui.GisCanvas;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.nodefx.ZoomablePane;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class Test extends Application {
  @Pure
  public void main(final String[] args) {
    Application.launch(args);
  }
  
  @Pure
  public RoadNetworkLayer getRoads() {
    RoadPolyline p1 = null;
    p1.addPoint(0, 0);
    p1.addPoint(0, 100);
    RoadPolyline p2 = null;
    p2.addPoint(0, 100);
    p2.addPoint(100, 100);
    RoadPolyline p3 = null;
    p3.addPoint(100, 100);
    p3.addPoint(100, 0);
    RoadPolyline p4 = null;
    p4.addPoint(100, 0);
    p4.addPoint(0, 0);
    RoadPolyline p5 = null;
    p5.addPoint(50, 0);
    p5.addPoint(50, 100);
    Rectangle2d worldRect = null;
    worldRect.setFromCorners(0, 0, 500, 500);
    StandardRoadNetwork network = new StandardRoadNetwork(worldRect);
    network.addRoadSegment(p1);
    network.addRoadSegment(p2);
    network.addRoadSegment(p3);
    network.addRoadSegment(p4);
    network.addRoadSegment(p5);
    return new RoadNetworkLayer(network);
  }
  
  @Override
  public void start(final Stage primaryStage) throws Exception {
    RoadNetworkLayer loadedResource = this.getRoads();
    BorderPane root = null;
    Label messageBar = new Label("");
    messageBar.setTextAlignment(TextAlignment.CENTER);
    MultiMapLayer<RoadNetworkLayer> rootLayer = new MultiMapLayer<RoadNetworkLayer>();
    rootLayer.addMapLayer(loadedResource);
    GisCanvas<MultiMapLayer<RoadNetworkLayer>> _gisCanvas = new GisCanvas<MultiMapLayer<RoadNetworkLayer>>(rootLayer);
    ZoomablePane<MultiMapLayer<RoadNetworkLayer>> scrollPane = new ZoomablePane<MultiMapLayer<RoadNetworkLayer>>(_gisCanvas);
    root.setCenter(scrollPane);
    root.setBottom(messageBar);
    Scene scene = new Scene(root, 1024, 768);
    scene.getStylesheets().add("application.css");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  @SyntheticMember
  public Test() {
    super();
  }
}
