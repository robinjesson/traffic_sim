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
import org.arakhne.afc.math.geometry.d2.d.Point2d;
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
  
  @DefaultValueSource
  public Road(final Point2d beg, final Point2d end, @DefaultValue("road_elements.Road#NEW_1") final int speed) {
    this(((int) beg.getX()), ((int) beg.getY()), ((int) end.getX()), ((int) end.getY()), speed);
  }
  
  /**
   * Default value for the parameter speed
   */
  @SyntheticMember
  @SarlSourceCode("50")
  private static final int $DEFAULT_VALUE$NEW_1 = 50;
  
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
  
  @Pure
  public Point2d getBegin() {
    return new Point2d(this.beginX, this.beginY);
  }
  
  @Pure
  public Point2d getEnd() {
    return new Point2d(this.endX, this.endY);
  }
  
  public boolean addObject(final RoadObject obj) {
    return this.objects.add(obj);
  }
  
  public boolean removeObject(final RoadObject obj) {
    return this.objects.remove(obj);
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
  
  @Pure
  public ArrayList<Car> getSameDirectionCars(final Car car) {
    ArrayList<Car> res = new ArrayList<Car>();
    ArrayList<Car> _listOfCars = this.listOfCars();
    for (final Car c : _listOfCars) {
      if ((c != car)) {
        if ((car.getRoad().getBeginPoint().getPoint().equals(c.getRoad().getBeginPoint().getPoint()) && 
          car.getRoad().getEndPoint().equals(c.getRoad().getEndPoint().getPoint()))) {
          res.add(c);
        }
      }
    }
    return res;
  }
  
  @Pure
  public ArrayList<Car> getFrontCars(final Car car) {
    ArrayList<Car> cars = this.getSameDirectionCars(car);
    ArrayList<Car> res = new ArrayList<Car>();
    for (final Car c : cars) {
      double _x = c.getPosition().getX();
      double _x_1 = car.getPosition().getX();
      if (((_x - _x_1) > 0)) {
        res.add(c);
      }
    }
    return res;
  }
  
  @Pure
  public Car getFrontCarOf(final Car car) {
    ArrayList<Car> lCars = this.getFrontCars(car);
    int _size = lCars.size();
    InputOutput.<String>println(("SIZE " + Integer.valueOf(_size)));
    int _size_1 = lCars.size();
    if ((_size_1 != 0)) {
      Car frontCar = lCars.get(0);
      double _x = frontCar.getPosition().getX();
      double _x_1 = car.getPosition().getX();
      double frontCarDistance = (_x - _x_1);
      for (final Car c : lCars) {
        if (((car.getPosition().getX() < c.getPosition().getX()) && ((c.getPosition().getX() - car.getPosition().getX()) < frontCarDistance))) {
          frontCar = c;
          double _x_2 = c.getPosition().getX();
          double _x_3 = car.getPosition().getX();
          frontCarDistance = (_x_2 - _x_3);
        }
      }
      return frontCar;
    }
    return null;
  }
  
  @Pure
  public double getFrontCarDistanceOf(final Car car) {
    double _xifexpression = (double) 0;
    int _size = this.getFrontCars(car).size();
    if ((_size >= 1)) {
      double _x = this.getFrontCarOf(car).getPosition().getX();
      double _x_1 = car.getPosition().getX();
      _xifexpression = (_x - _x_1);
    } else {
      _xifexpression = 100000;
    }
    return _xifexpression;
  }
  
  @DefaultValueUse("int,int,int,int,int")
  @SyntheticMember
  public Road(final int beginX, final int beginY, final int endX, final int endY) {
    this(beginX, beginY, endX, endY, $DEFAULT_VALUE$NEW_0);
  }
  
  @DefaultValueUse("org.arakhne.afc.math.geometry.d2.d.Point2d,org.arakhne.afc.math.geometry.d2.d.Point2d,int")
  @SyntheticMember
  public Road(final Point2d beg, final Point2d end) {
    this(beg, end, $DEFAULT_VALUE$NEW_1);
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
  private static final long serialVersionUID = -3832513437L;
}
