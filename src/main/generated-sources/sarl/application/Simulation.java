package application;

import agents.EnvAgent;
import com.google.common.base.Objects;
import io.sarl.bootstrap.SRE;
import io.sarl.bootstrap.SREBootstrap;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.arakhne.afc.gis.io.shape.GISShapeFileReader;
import org.arakhne.afc.gis.mapelement.MapComposedElement;
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
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.Road;
import road_elements.RoadObject;
import road_elements.ShapeReader;
import road_elements.TrafficLayers;

@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class Simulation extends Application {
  private TrafficLayers trafficLayers = new TrafficLayers(new ArrayList<Road>(), new ArrayList<RoadObject>());
  
  @Pure
  public TrafficLayers getTrafficLayers() {
    return this.trafficLayers;
  }
  
  @Pure
  public TrafficLayers getRoads() {
    ArrayList<Road> roads = TrafficLayers.roadListFactory();
    ArrayList<RoadObject> cars = new ArrayList<RoadObject>();
    return new TrafficLayers(roads, cars);
  }
  
  @Override
  public void start(final Stage primaryStage) throws Exception {
    BorderPane root = new BorderPane();
    ShapeReader reader = new ShapeReader();
    File file = new File("src/main/resources/application/quartier_polyline.shp");
    this.trafficLayers = this.getRoads();
    MultiMapLayer<TrafficLayers> rootLayer = new MultiMapLayer<TrafficLayers>();
    rootLayer.addMapLayer(this.trafficLayers);
    GisCanvas<MultiMapLayer<TrafficLayers>> _gisCanvas = new GisCanvas<MultiMapLayer<TrafficLayers>>(rootLayer);
    ZoomablePane<MultiMapLayer<TrafficLayers>> scrollPane = new ZoomablePane<MultiMapLayer<TrafficLayers>>(_gisCanvas);
    root.setCenter(scrollPane);
    Scene scene = new Scene(root, 1024, 768);
    scene.getStylesheets().add("application.css");
    InputStream _resourceAsStream = Resources.getResourceAsStream(Simulation.class, "/traffic_light_32.png");
    Image applicationIcon32 = new Image(_resourceAsStream);
    primaryStage.getIcons().add(applicationIcon32);
    primaryStage.setTitle("Traffic Simulation");
    primaryStage.setScene(scene);
    primaryStage.show();
    SREBootstrap bootstrap = SRE.getBootstrap();
    bootstrap.startAgent(EnvAgent.class, this.trafficLayers);
  }
  
  public MapElementLayer<?> loadShapeFile2(final File file) {
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
  
  public static MultiMapLayer shapeToLayers() {
    try {
      ArrayList<Road> list = new ArrayList<Road>();
      File file = new File("src/main/resources/application/quartier_polyline.shp");
      GISShapeFileReader reader = new GISShapeFileReader(file);
      Rectangle2d worldRect = new Rectangle2d();
      ESRIBounds esriBounds = reader.getBoundsFromHeader();
      worldRect.setFromCorners(
        esriBounds.getMinX(), 
        esriBounds.getMinY(), 
        esriBounds.getMaxX(), 
        esriBounds.getMaxY());
      MultiMapLayer<MapElementLayer<? extends MapElement>> multiMapLayer = new MultiMapLayer<MapElementLayer<? extends MapElement>>();
      RoadNetworkLayer roadNetworkLayer = null;
      TreeMapElementLayer<MapElement> mapElementLayer = new TreeMapElementLayer<MapElement>(worldRect);
      StandardRoadNetwork network = new StandardRoadNetwork(worldRect);
      ShapeElementType _shapeElementType = reader.getShapeElementType();
      boolean _equals = Objects.equal(_shapeElementType, ShapeElementType.POLYLINE);
      if (_equals) {
        reader.setMapElementType(RoadPolyline.class);
      }
      MapElement element = null;
      int count = 0;
      while (((element = reader.read()) != null)) {
        {
          if ((element instanceof RoadPolyline)) {
            RoadPolyline sgmt = ((RoadPolyline)element);
            if ((network == null)) {
              StandardRoadNetwork _standardRoadNetwork = new StandardRoadNetwork(worldRect);
              network = _standardRoadNetwork;
            }
            Iterable<MapComposedElement.PointGroup> _groups = sgmt.groups();
            for (final MapComposedElement.PointGroup group : _groups) {
              for (int i = 0; (i < (IterableExtensions.size(group.points()) - 1)); i++) {
                {
                  double _x = ((Point2d[])Conversions.unwrapArray(group.points(), Point2d.class))[i].getX();
                  int xBegin = ((int) _x);
                  double _y = ((Point2d[])Conversions.unwrapArray(group.points(), Point2d.class))[i].getY();
                  int yBegin = ((int) _y);
                  double _x_1 = ((Point2d[])Conversions.unwrapArray(group.points(), Point2d.class))[(i + 1)].getX();
                  int xEnd = ((int) _x_1);
                  double _y_1 = ((Point2d[])Conversions.unwrapArray(group.points(), Point2d.class))[(i + 1)].getY();
                  int yEnd = ((int) _y_1);
                  Road road = new Road(xBegin, yBegin, xEnd, yEnd);
                  network.addRoadSegment(road);
                }
              }
            }
          }
          count++;
        }
      }
      RoadNetworkLayer _roadNetworkLayer = new RoadNetworkLayer(network);
      roadNetworkLayer = _roadNetworkLayer;
      multiMapLayer.addMapLayer(roadNetworkLayer);
      multiMapLayer.addMapLayer(mapElementLayer);
      return multiMapLayer;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
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
  public Simulation() {
    super();
  }
}
