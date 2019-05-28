package road_elements;

import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import org.arakhne.afc.gis.io.shape.GISShapeFileReader;
import org.arakhne.afc.gis.mapelement.MapElement;
import org.arakhne.afc.gis.maplayer.MapElementLayer;
import org.arakhne.afc.gis.maplayer.TreeMapElementLayer;
import org.arakhne.afc.gis.road.RoadPolyline;
import org.arakhne.afc.gis.road.StandardRoadNetwork;
import org.arakhne.afc.gis.road.layer.RoadNetworkLayer;
import org.arakhne.afc.gis.road.primitive.RoadSegment;
import org.arakhne.afc.io.dbase.DBaseFileFilter;
import org.arakhne.afc.io.shape.ESRIBounds;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.vmutil.FileSystem;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class ShapeReader {
  /**
   * Read a ESRI Shape file.
   * 
   * @param file the file to read.
   * @return the map layer.
   */
  public RoadNetworkLayer loadRoads(final File file) {
    MapElementLayer<?> _loadShapeFile = this.loadShapeFile(file, true);
    return ((RoadNetworkLayer) _loadShapeFile);
  }
  
  /**
   * Read a ESRI Shape file.
   * 
   * @param file the file to read.
   * @param elementType the type of the elements
   * @return the map layer.
   */
  @DefaultValueSource
  public MapElementLayer<?> loadShapeFile(final File file, @DefaultValue("road_elements.ShapeReader#LOADSHAPEFILE_0") final boolean isRoad) {
    try {
      StandardRoadNetwork network = null;
      MapElementLayer<MapElement> layer = null;
      File dbfFile = FileSystem.replaceExtension(file, DBaseFileFilter.EXTENSION_DBASE_FILE);
      URL _xifexpression = null;
      boolean _canRead = dbfFile.canRead();
      if (_canRead) {
        _xifexpression = ((dbfFile == null ? null : dbfFile.toURI()) == null ? null : (dbfFile == null ? null : dbfFile.toURI()).toURL());
      } else {
        _xifexpression = null;
      }
      URL dbfUrl = _xifexpression;
      FileInputStream is = new FileInputStream(file);
      try {
        class $AssertEvaluator$ {
          final boolean $$result;
          $AssertEvaluator$(final FileInputStream is) {
            this.$$result = (is != null);
          }
        }
        assert new $AssertEvaluator$(is).$$result;
        GISShapeFileReader reader = new GISShapeFileReader(is, null, dbfUrl);
        try {
          final Rectangle2d worldRect = new Rectangle2d();
          final ESRIBounds esriBounds = reader.getBoundsFromHeader();
          worldRect.setFromCorners(esriBounds.getMinX(), esriBounds.getMinY(), esriBounds.getMaxX(), esriBounds.getMaxY());
          if (isRoad) {
            reader.setMapElementType(RoadPolyline.class);
          }
          MapElement element = null;
          if (isRoad) {
            while (((element = reader.read()) != null)) {
              if ((element instanceof RoadPolyline)) {
                if ((network == null)) {
                  StandardRoadNetwork _standardRoadNetwork = new StandardRoadNetwork(worldRect);
                  network = _standardRoadNetwork;
                }
                network.addRoadSegment(((RoadSegment)element));
              } else {
                if ((layer == null)) {
                  TreeMapElementLayer<MapElement> _treeMapElementLayer = new TreeMapElementLayer<MapElement>(worldRect);
                  layer = _treeMapElementLayer;
                }
                layer.addMapElement(element);
              }
            }
          } else {
            while (((element = reader.read()) != null)) {
              {
                if ((layer == null)) {
                  TreeMapElementLayer<MapElement> _treeMapElementLayer = new TreeMapElementLayer<MapElement>(worldRect);
                  layer = _treeMapElementLayer;
                }
                layer.addMapElement(element);
              }
            }
          }
        } finally {
          reader.close();
        }
      } finally {
        is.close();
      }
      if ((network != null)) {
        final RoadNetworkLayer networkLayer = new RoadNetworkLayer(network);
        return networkLayer;
      }
      return layer;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Default value for the parameter isRoad
   */
  @SyntheticMember
  @SarlSourceCode("false")
  private static final boolean $DEFAULT_VALUE$LOADSHAPEFILE_0 = false;
  
  /**
   * Read a ESRI Shape file.
   * 
   * @param file the file to read.
   * @param elementType the type of the elements
   * @return the map layer.
   */
  @DefaultValueUse("java.io.File,boolean")
  @SyntheticMember
  public final MapElementLayer<?> loadShapeFile(final File file) {
    return loadShapeFile(file, $DEFAULT_VALUE$LOADSHAPEFILE_0);
  }
  
  @SyntheticMember
  public ShapeReader() {
    super();
  }

public static void loadRoads(File file) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub");
}

public static void loadRoads(File file) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub");
}
}
