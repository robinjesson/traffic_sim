package road_elements;

import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.io.File;
import org.arakhne.afc.gis.maplayer.MapElementLayer;
import org.arakhne.afc.gis.road.layer.RoadNetworkLayer;

@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class ShapeReader {
  /**
   * Read a ESRI Shape file.
   * 
   * 
   * 
   * @param file the file to read.
   * 
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
    throw new Error("Unresolved compilation problems:"
      + "\nDBaseFileFilter cannot be resolved to a type."
      + "\nGISShapeFileReader cannot be resolved."
      + "\nEXTENSION_DBASE_FILE cannot be resolved"
      + "\ngetBoundsFromHeader cannot be resolved"
      + "\nminX cannot be resolved"
      + "\nminY cannot be resolved"
      + "\nmaxX cannot be resolved"
      + "\nmaxY cannot be resolved"
      + "\nmapElementType cannot be resolved"
      + "\nread cannot be resolved"
      + "\nread cannot be resolved"
      + "\nclose cannot be resolved");
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
}
