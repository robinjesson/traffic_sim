package road_elements;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.arakhne.afc.gis.road.RoadPolyline;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.Car;
import road_elements.RoadObject;

/**
 * @author robin
 */
@SarlSpecification("0.8")
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
  
  public Road(final int beginX, final int beginY, final int endX, final int endY, final int speedLimit) {
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
    if ((objToRemove != null)) {
      this.objects.remove(objToRemove);
    }
    return objToRemove;
  }
  
  @Pure
  public List<Car> listOfCars() {
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
  public int getSpeedLimit() {
    return this.speedLimit;
  }
  
  @Pure
  public double getFrontCarDistance(final Car car) {
    double dist = 0;
    List<Car> _listOfCars = this.listOfCars();
    for (final Car c : _listOfCars) {
    }
    return dist;
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
  private final static long serialVersionUID = -4470318466L;
}
