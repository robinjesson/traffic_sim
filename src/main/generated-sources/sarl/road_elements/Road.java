package road_elements;

import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.UUID;
import org.arakhne.afc.gis.road.RoadPolyline;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.Car;
import road_elements.RoadObject;

/**
 * @author robin
 */
@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class Road extends RoadPolyline {
  private ArrayList<RoadObject> objects;
  
  private int beginX;
  
  private int beginY;
  
  private int endX;
  
  private int endY;
  
  private double distanceKilometers;
  
  private int speedLimit;
  
  @DefaultValueSource
  public Road(final int beginX, final int beginY, final int endX, final int endY, @DefaultValue("road_elements.Road#NEW_0") final int speedLimit) {
    super();
    this.beginX = beginX;
    this.beginY = beginY;
    this.endX = endX;
    this.endY = endY;
    this.addPoint(beginX, beginY);
    this.addPoint(endX, endY);
    ArrayList<RoadObject> _arrayList = new ArrayList<RoadObject>();
    this.objects = _arrayList;
    this.speedLimit = speedLimit;
    double _pow = Math.pow((endX - beginX), 2);
    double _pow_1 = Math.pow((endY - beginY), 2);
    this.distanceKilometers = Math.sqrt((_pow + _pow_1));
  }
  
  /**
   * Default value for the parameter speedLimit
   */
  @SyntheticMember
  @SarlSourceCode("50")
  private static final int $DEFAULT_VALUE$NEW_0 = 50;
  
  @Pure
  public ArrayList<RoadObject> getObjects() {
    return this.objects;
  }
  
  @Pure
  public int getBeginX() {
    return this.beginX;
  }
  
  @Pure
  public int getEndX() {
    return this.endX;
  }
  
  @Pure
  public int getBeginY() {
    return this.beginY;
  }
  
  @Pure
  public int getEndY() {
    return this.endY;
  }
  
  public boolean addObject(final RoadObject obj) {
    return this.objects.add(obj);
  }
  
  public RoadObject removeObject(final UUID id) {
    RoadObject objToRemove = null;
    for (final RoadObject obj : this.objects) {
      UUID _uUID = obj.getUUID();
      if ((_uUID == id)) {
        objToRemove = obj;
      }
    }
    if ((objToRemove == null)) {
      throw new IllegalArgumentException((("The UUID " + id) + " doesn\'t exist"));
    }
    this.objects.remove(objToRemove);
    return objToRemove;
  }
  
  @Pure
  public ArrayList<Car> listOfCars() {
    ArrayList<Car> cars = new ArrayList<Car>();
    for (final RoadObject obj : this.objects) {
      if ((obj instanceof Car)) {
        cars.add(((Car)obj));
      }
    }
    return cars;
  }
  
  @Pure
  public double getDistanceKilometers() {
    return this.distanceKilometers;
  }
  
  @Pure
  public double getDistanceMeters() {
    return (this.distanceKilometers * 1000);
  }
  
  @Pure
  public int getSpeedLimitKMH() {
    return this.speedLimit;
  }
  
  @Pure
  public double getSpeedLimitMS() {
    return (this.speedLimit * 3.6);
  }
  
  /**
   * }
   * def getFrontCarDistance(car : Car) : double{
   * for(roadCar as Car : this.listOfCars){
   * if(car.pos1D < roadCar.pos1D) return roadCar.pos1D - car.pos1D
   * }
   */
  @Pure
  public double getFrontCarDistance(final Car car) {
    double dist = 10000;
    ArrayList<Car> _listOfCars = this.listOfCars();
    for (final Car c : _listOfCars) {
      int _pos1D = c.getPos1D();
      int _pos1D_1 = car.getPos1D();
      if (((_pos1D - _pos1D_1) < dist)) {
        int _pos1D_2 = c.getPos1D();
        int _pos1D_3 = car.getPos1D();
        dist = (_pos1D_2 - _pos1D_3);
      }
    }
    return 0;
  }
  
  @Pure
  public RoadObject getObjectsByUUID(final UUID id) {
    for (final RoadObject current : this.objects) {
      UUID _uUID = current.getUUID();
      if ((_uUID == id)) {
        return current;
      }
    }
    throw new IllegalArgumentException("This UUID doesn\'t exist");
  }
  
  public void disp() {
    InputOutput.<String>println(("    beginX=" + Integer.valueOf(this.beginX)));
    InputOutput.<String>println(("    beginY=" + Integer.valueOf(this.beginY)));
    InputOutput.<String>println(("    endX=" + Integer.valueOf(this.endX)));
    InputOutput.<String>println(("    endY=" + Integer.valueOf(this.endY)));
    InputOutput.<String>print("    [");
    for (final RoadObject obj : this.objects) {
      String _plus = (obj + " ");
      InputOutput.<String>print(_plus);
    }
    InputOutput.<String>print("]");
  }
  
  @DefaultValueUse("int,int,int,int,int")
  @SyntheticMember
  public Road(final int beginX, final int beginY, final int endX, final int endY) {
    this(beginX, beginY, endX, endY, $DEFAULT_VALUE$NEW_0);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Road other = (Road) obj;
    if (other.beginX != this.beginX)
      return false;
    if (other.beginY != this.beginY)
      return false;
    if (other.endX != this.endX)
      return false;
    if (other.endY != this.endY)
      return false;
    if (Double.doubleToLongBits(other.distanceKilometers) != Double.doubleToLongBits(this.distanceKilometers))
      return false;
    if (other.speedLimit != this.speedLimit)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + this.beginX;
    result = prime * result + this.beginY;
    result = prime * result + this.endX;
    result = prime * result + this.endY;
    result = prime * result + (int) (Double.doubleToLongBits(this.distanceKilometers) ^ (Double.doubleToLongBits(this.distanceKilometers) >>> 32));
    result = prime * result + this.speedLimit;
    return result;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public Road clone() {
    try {
      return (Road) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  private static final long serialVersionUID = -10593594356L;
}
