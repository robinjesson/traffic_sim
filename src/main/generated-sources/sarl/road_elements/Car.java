package road_elements;

import events.Influence;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Random;
import javafx.scene.paint.Color;
import org.arakhne.afc.math.geometry.d1.d.Point1d;
import org.arakhne.afc.math.geometry.d2.Point2D;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.MobileRoadObject;
import road_elements.Road;
import road_elements.TrafficLayers;

/**
 * @author robin
 */
@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class Car extends MobileRoadObject {
  public boolean normalDirection = true;
  
  public Color color;
  
  private double x;
  
  private double y;
  
  private Influence influence;
  
  public Car(final Point1d position, final Road currentRoad, final TrafficLayers trafficLayers) {
    super(position, currentRoad, trafficLayers);
    Road _road = super.getRoad();
    if ((_road != null)) {
      super.getRoad().addObject(this);
    }
    trafficLayers.addCar(this);
    this.setCoordinates(currentRoad.getBegin(), currentRoad.getEnd());
    this.color = this.randomColorPicker();
  }
  
  @Pure
  public Point2d getCoordinates() {
    return new Point2d(this.x, this.y);
  }
  
  public void setCoordinates(final Point2D beg, final Point2D end) {
    double _x = end.getX();
    double _x_1 = beg.getX();
    double _pow = Math.pow((_x - _x_1), 2);
    double _y = end.getY();
    double _y_1 = beg.getY();
    double _pow_1 = Math.pow((_y - _y_1), 2);
    double dist = Math.sqrt((_pow + _pow_1));
    double _x_2 = this.getPosition().getX();
    double t = (_x_2 / dist);
    double _x_3 = beg.getX();
    double _x_4 = end.getX();
    this.x = (((1 - t) * _x_3) + (t * _x_4));
    double _y_2 = beg.getY();
    double _y_3 = end.getY();
    this.y = (((1 - t) * _y_2) + (t * _y_3));
  }
  
  @Pure
  public Color randomColorPicker() {
    Random rand = new Random();
    float r = rand.nextFloat();
    float g = rand.nextFloat();
    float b = rand.nextFloat();
    return new Color(r, g, b, 1);
  }
  
  public Influence setInfluence(final Influence influence) {
    return this.influence = influence;
  }
  
  @Pure
  public Influence getInfluence() {
    return this.influence;
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
    Car other = (Car) obj;
    if (other.normalDirection != this.normalDirection)
      return false;
    if (Double.doubleToLongBits(other.x) != Double.doubleToLongBits(this.x))
      return false;
    if (Double.doubleToLongBits(other.y) != Double.doubleToLongBits(this.y))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + (this.normalDirection ? 1231 : 1237);
    result = prime * result + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
    result = prime * result + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
    return result;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public Car clone() {
    try {
      return (Car) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  private static final long serialVersionUID = -257972405L;
}
