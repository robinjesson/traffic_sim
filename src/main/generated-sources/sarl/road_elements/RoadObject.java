package road_elements;

import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.arakhne.afc.attrs.attr.Attribute;
import org.arakhne.afc.attrs.attr.AttributeException;
import org.arakhne.afc.attrs.attr.AttributeType;
import org.arakhne.afc.attrs.attr.AttributeValue;
import org.arakhne.afc.attrs.collection.AttributeChangeListener;
import org.arakhne.afc.attrs.collection.AttributeProvider;
import org.arakhne.afc.gis.location.GeoId;
import org.arakhne.afc.gis.location.GeoLocation;
import org.arakhne.afc.gis.primitive.GISElement;
import org.arakhne.afc.vmutil.json.JsonBuffer;
import org.eclipse.xtext.xbase.lib.Pure;
import road_elements.Road;

/**
 * @author robin
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class RoadObject implements GISElement {
  private int pos1D;
  
  private final UUID uuid;
  
  private Road road;
  
  @DefaultValueSource
  public RoadObject(@DefaultValue("road_elements.RoadObject#NEW_0") final int pos1D, final Road road) {
    this.pos1D = pos1D;
    this.uuid = UUID.randomUUID();
    this.road = road;
  }
  
  /**
   * Default value for the parameter pos1D
   */
  @SyntheticMember
  @SarlSourceCode("0")
  private final static int $DEFAULT_VALUE$NEW_0 = 0;
  
  @Pure
  public Road getRoad() {
    return this.road;
  }
  
  public void setRoad(final Road road) {
    this.road = road;
  }
  
  @Pure
  public int getPos1D() {
    return this.pos1D;
  }
  
  public void setPos1D(final int pos1D) {
    this.pos1D = pos1D;
  }
  
  /**
   * def isOnRight :boolean{
   * return this.onRight
   * }
   */
  public int copyAttributes(final GISElement arg0) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public String getName() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public String hashKey() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void setName(final String arg0) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public GeoId getGeoId() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public GeoLocation getGeoLocation() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public UUID getUUID() {
    return this.uuid;
  }
  
  public void addAttributeChangeListener(final AttributeChangeListener arg0) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void addAttributes(final Map<String, Object> arg0) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void addAttributes(final AttributeProvider arg0) throws AttributeException {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void flush() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public boolean isEventFirable() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public boolean removeAllAttributes() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public boolean removeAttribute(final String arg0) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void removeAttributeChangeListener(final AttributeChangeListener arg0) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public boolean renameAttribute(final String arg0, final String arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public boolean renameAttribute(final String arg0, final String arg1, final boolean arg2) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Attribute setAttribute(final Attribute arg0) throws AttributeException {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Attribute setAttribute(final String arg0, final AttributeValue arg1) throws AttributeException {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Attribute setAttribute(final String arg0, final boolean arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Attribute setAttribute(final String arg0, final int arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Attribute setAttribute(final String arg0, final long arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Attribute setAttribute(final String arg0, final float arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Attribute setAttribute(final String arg0, final double arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Attribute setAttribute(final String arg0, final String arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Attribute setAttribute(final String arg0, final UUID arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Attribute setAttribute(final String arg0, final URL arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Attribute setAttribute(final String arg0, final URI arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Attribute setAttribute(final String arg0, final Date arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Attribute setAttribute(final String arg0, final InetAddress arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Attribute setAttribute(final String arg0, final InetSocketAddress arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Attribute setAttribute(final String arg0, final Enum<?> arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Attribute setAttribute(final String arg0, final Class<?> arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public Attribute setAttributeType(final String arg0, final AttributeType arg1) throws AttributeException {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void setAttributes(final Map<String, Object> arg0) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void setAttributes(final AttributeProvider arg0) throws AttributeException {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void setEventFirable(final boolean arg0) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public Iterable<Attribute> attributes() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void freeMemory() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public Collection<String> getAllAttributeNames() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public Collection<Attribute> getAllAttributes() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public Map<AttributeType, Collection<Attribute>> getAllAttributesByType() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public AttributeValue getAttribute(final String arg0) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public boolean getAttribute(final String arg0, final boolean arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public int getAttribute(final String arg0, final int arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public long getAttribute(final String arg0, final long arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public float getAttribute(final String arg0, final float arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public double getAttribute(final String arg0, final double arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public String getAttribute(final String arg0, final String arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public UUID getAttribute(final String arg0, final UUID arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public URL getAttribute(final String arg0, final URL arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public URI getAttribute(final String arg0, final URI arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public Date getAttribute(final String arg0, final Date arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public AttributeValue getAttribute(final String arg0, final AttributeValue arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public InetAddress getAttribute(final String arg0, final InetAddress arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public InetAddress getAttribute(final String arg0, final InetSocketAddress arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public <T extends Enum<T>> T getAttribute(final String arg0, final T arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public Class<?> getAttribute(final String arg0, final Class<?> arg1) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public boolean getAttributeAsBool(final String arg0) throws AttributeException {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public Date getAttributeAsDate(final String arg0) throws AttributeException {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public double getAttributeAsDouble(final String arg0) throws AttributeException {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public Enum<?> getAttributeAsEnumeration(final String arg0) throws AttributeException {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public <T extends Enum<T>> T getAttributeAsEnumeration(final String arg0, final Class<T> arg1) throws AttributeException {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public float getAttributeAsFloat(final String arg0) throws AttributeException {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public InetAddress getAttributeAsInetAddress(final String arg0) throws AttributeException {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public int getAttributeAsInt(final String arg0) throws AttributeException {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public Class<?> getAttributeAsJavaClass(final String arg0) throws AttributeException {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public long getAttributeAsLong(final String arg0) throws AttributeException {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public String getAttributeAsString(final String arg0) throws AttributeException {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public URI getAttributeAsURI(final String arg0) throws AttributeException {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public URL getAttributeAsURL(final String arg0) throws AttributeException {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public UUID getAttributeAsUUID(final String arg0) throws AttributeException {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public int getAttributeCount() {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public Attribute getAttributeObject(final String arg0) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @Pure
  public boolean hasAttribute(final String arg0) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void toMap(final Map<String, Object> arg0) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  public void toJson(final JsonBuffer arg0) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
  }
  
  @DefaultValueUse("int,road_elements.Road")
  @SyntheticMember
  public RoadObject(final Road road) {
    this($DEFAULT_VALUE$NEW_0, road);
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
    RoadObject other = (RoadObject) obj;
    if (other.pos1D != this.pos1D)
      return false;
    if (!Objects.equals(this.uuid, other.uuid)) {
      return false;
    }
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + this.pos1D;
    result = prime * result + Objects.hashCode(this.uuid);
    return result;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public RoadObject clone() {
    try {
      return (RoadObject) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 33324063206L;
}
