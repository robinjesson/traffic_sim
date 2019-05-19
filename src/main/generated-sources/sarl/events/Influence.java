package events;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;

/**
 * @author robin
 */
@SarlSpecification("0.8")
@SarlElementType(15)
@SuppressWarnings("all")
public class Influence extends Event {
  @SyntheticMember
  public Influence() {
    super();
  }
  
  @SyntheticMember
  public Influence(final Address source) {
    super(source);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 588368462L;
}
