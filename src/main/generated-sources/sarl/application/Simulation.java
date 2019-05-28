package application;

import com.google.common.base.Objects;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.arakhne.afc.gis.io.shape.GISShapeFileReader;
import org.arakhne.afc.gis.mapelement.MapElement;
import org.arakhne.afc.gis.maplayer.MapElementLayer;
import org.arakhne.afc.gis.maplayer.MultiMapLayer;
import org.arakhne.afc.gis.maplayer.TreeMapElementLayer;
import org.arakhne.afc.gis.road.RoadPolyline;
import org.arakhne.afc.gis.road.StandardRoadNetwork;
import org.arakhne.afc.gis.road.layer.RoadNetworkLayer;
import org.arakhne.afc.gis.road.primitive.RoadNetworkException;
import org.arakhne.afc.gis.ui.GisCanvas;
import org.arakhne.afc.io.shape.ESRIBounds;
import org.arakhne.afc.io.shape.ShapeElementType;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.nodefx.ZoomablePane;
import org.arakhne.afc.vmutil.Resources;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.GPS;
import road_elements.Road;

@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class Simulation extends Application {
  @Pure
  public RoadNetworkLayer getRoads() {
    ArrayList<Road> roadList = new ArrayList<Road>();
    Road _road = new Road(0, 0, 0, 20, 100);
    roadList.add(_road);
    Road _road_1 = new Road(0, 20, 0, 100, 100);
    roadList.add(_road_1);
    Road _road_2 = new Road(0, 100, 100, 100, 100);
    roadList.add(_road_2);
    Road _road_3 = new Road(100, 100, 100, 50, 100);
    roadList.add(_road_3);
    Road _road_4 = new Road(100, 50, 100, 0, 100);
    roadList.add(_road_4);
    Road _road_5 = new Road(100, 0, 0, 0, 100);
    roadList.add(_road_5);
    Road _road_6 = new Road(0, 20, 20, 20, 70);
    roadList.add(_road_6);
    Road _road_7 = new Road(50, 50, 100, 50, 70);
    roadList.add(_road_7);
    Road _road_8 = new Road(20, 20, 50, 20, 50);
    roadList.add(_road_8);
    Road _road_9 = new Road(20, 50, 50, 50, 50);
    roadList.add(_road_9);
    Road _road_10 = new Road(20, 30, 50, 30, 50);
    roadList.add(_road_10);
    Road _road_11 = new Road(20, 20, 20, 30, 50);
    roadList.add(_road_11);
    Road _road_12 = new Road(20, 30, 20, 50, 50);
    roadList.add(_road_12);
    Road _road_13 = new Road(50, 20, 50, 30, 50);
    roadList.add(_road_13);
    Road _road_14 = new Road(50, 30, 50, 50, 50);
    roadList.add(_road_14);
    Rectangle2d worldRect = new Rectangle2d();
    worldRect.setFromCorners((-200), (-200), 200, 200);
    StandardRoadNetwork network = new StandardRoadNetwork(worldRect);
    for (final Road road : roadList) {
      network.addRoadSegment(road);
    }
    Point2d p1 = new Point2d(0, 0);
    Point2d p2 = new Point2d(100, 100);
    InputOutput.<Road>println(GPS.nextRoad(p1, p2, network));
    return new RoadNetworkLayer(network);
  }
  
  @Override
  public void start(final Stage primaryStage) throws Exception {
    File file = new File("/home/tiantian/Documents/IA51/Etienne/traffic_sim/src/main/resources/application/quartier_polyline.shp");
    MapElementLayer<?> _loadShapeFile = this.loadShapeFile(file);
    RoadNetworkLayer loadedResource = ((RoadNetworkLayer) _loadShapeFile);
    BorderPane root = new BorderPane();
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
    InputStream _resourceAsStream = Resources.getResourceAsStream(Simulation.class, "/traffic_light_32.png");
    Image applicationIcon32 = new Image(_resourceAsStream);
    primaryStage.getIcons().add(applicationIcon32);
    primaryStage.setTitle("Traffic Simulation");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  public MapElementLayer<?> loadShapeFile(final File file) {
    try {
      StandardRoadNetwork network = null;
      MapElementLayer<MapElement> layer = null;
      GISShapeFileReader reader = new GISShapeFileReader(file);
      Rectangle2d worldRect = new Rectangle2d();
      ESRIBounds esriBounds = reader.getBoundsFromHeader();
      worldRect.setFromCorners(esriBounds.getMinX(), 
        esriBounds.getMinY(), 
        esriBounds.getMaxX(), 
        esriBounds.getMaxY());
      ShapeElementType _shapeElementType = reader.getShapeElementType();
      boolean _equals = Objects.equal(_shapeElementType, ShapeElementType.POLYLINE);
      if (_equals) {
        reader.setMapElementType(RoadPolyline.class);
      }
      MapElement element = null;
      while (((element = reader.read()) != null)) {
        if ((element instanceof RoadPolyline)) {
          if ((network == null)) {
            StandardRoadNetwork _standardRoadNetwork = new StandardRoadNetwork(worldRect);
            network = _standardRoadNetwork;
          }
          RoadPolyline sgmt = ((RoadPolyline)element);
          try {
            network.addRoadSegment(sgmt);
          } catch (final Throwable _t) {
            if (_t instanceof RoadNetworkException) {
              final RoadNetworkException e = (RoadNetworkException)_t;
              throw new RuntimeException(e);
            } else {
              throw Exceptions.sneakyThrow(_t);
            }
          }
        } else {
          if ((layer == null)) {
            TreeMapElementLayer<MapElement> _treeMapElementLayer = new TreeMapElementLayer<MapElement>();
            layer = _treeMapElementLayer;
            try {
              layer.addMapElement(element);
            } catch (final Throwable _t_1) {
              if (_t_1 instanceof RoadNetworkException) {
                final RoadNetworkException e_1 = (RoadNetworkException)_t_1;
                throw new RuntimeException(e_1);
              } else {
                throw Exceptions.sneakyThrow(_t_1);
              }
            }
          }
        }
      }
      if ((network != null)) {
        RoadNetworkLayer networkLayer = new RoadNetworkLayer(network);
        return networkLayer;
      }
      return layer;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @SyntheticMember
  public Simulation() {
    super();
  }
}
