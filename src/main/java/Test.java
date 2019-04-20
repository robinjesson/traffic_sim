import java.util.ArrayList;
import java.util.List;

import org.arakhne.afc.gis.maplayer.MapElementLayer;
import org.arakhne.afc.gis.maplayer.MultiMapLayer;
import org.arakhne.afc.gis.primitive.GISContainer;
import org.arakhne.afc.gis.road.RoadPolyline;
import org.arakhne.afc.gis.road.StandardRoadNetwork;
import org.arakhne.afc.gis.road.layer.RoadNetworkLayer;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.gis.ui.GisPane;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.TextAlignment;



public class Test extends Application{

	public static void main(String[] args) {
		Application.launch(args);
		
	}
	
	public MapElementLayer<?> getRoads( ) {
		RoadPolyline p1=new RoadPolyline();
		p1.addPoint(0,0);
		p1.addPoint(0,100);
		
		RoadPolyline p2=new RoadPolyline();
		p2.addPoint(0,100);
		p2.addPoint(100,100);
		
		RoadPolyline p3=new RoadPolyline();
		p3.addPoint(100,100);
		p3.addPoint(100,0);
		
		RoadPolyline p4=new RoadPolyline();
		p4.addPoint(100,0);
		p4.addPoint(0,0);
		
		RoadPolyline p5=new RoadPolyline();
		p5.addPoint(50,0);
		p5.addPoint(50,100);
		
		
		
		
		final Rectangle2d worldRect = new Rectangle2d();
		worldRect.setFromCorners(0,0,500,500);
		StandardRoadNetwork network = new StandardRoadNetwork(worldRect);
		network.addRoadSegment(p1);
		network.addRoadSegment(p2);
		network.addRoadSegment(p3);
		network.addRoadSegment(p4);
		network.addRoadSegment(p5);
		return new RoadNetworkLayer(network);
	}
	
	@Override
	public void start(Stage primaryStage) {
		final List<MapElementLayer> containers = new ArrayList<>();

		final StringBuilder filename = new StringBuilder();


		final MapElementLayer loadedResource = getRoads();
		if (loadedResource != null) {
			containers.add(loadedResource);
		}
		
		final GISContainer container;

		if (containers.size() == 1) {
			container = containers.get(0);
		} else {
			final MultiMapLayer layer = new MultiMapLayer<>();
			for (final MapElementLayer child : containers) {
				layer.addMapLayer(child);
			}
			container = layer;
		}
		
		final BorderPane root = new BorderPane();
		final Label messageBar = new Label(""); //$NON-NLS-1$
		messageBar.setTextAlignment(TextAlignment.CENTER);
		final GisPane scrollPane = new GisPane(container);
		
		root.setCenter(scrollPane);

		root.setBottom(messageBar);



		final Scene scene = new Scene(root, 1024, 768);

		scene.getStylesheets().add("application.css"); //$NON-NLS-1$



		primaryStage.setScene(scene);

		primaryStage.show();
		
	}

}
