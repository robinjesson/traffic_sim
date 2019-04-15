import org.arakhne.afc.gis.road.RoadPolyline;
import org.arakhne.afc.gis.road.StandardRoadNetwork;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;



public class Test extends Application{

	public static void main(String[] args) {
		Application.launch(args);
		
	}
	
	public void drawRoads(GraphicsContext gc ) {
		RoadPolyline p=new RoadPolyline();
		p.addPoint(10, 10);
		p.addPoint(10, 20);
		
		final Rectangle2d worldRect = new Rectangle2d();
		/*worldRect.setFromCorners(0,0,500,500);*/
		StandardRoadNetwork network = new StandardRoadNetwork(worldRect);
		network.addRoadSegment(p);
		//RoadPolylineDrawer drawer=new RoadPolylineDrawer() ;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(300, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawRoads(gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
	}

}
