package events;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;

/**
 * @author robin
 */
@SarlSpecification("0.9")
@SarlElementType(15)
@SuppressWarnings("all")
public class Perception extends Event {
  @SyntheticMember
  public Perception() {
    super();
  }
  
  @SyntheticMember
  public Perception(final Address source) {
    super(source);
  }
  
  @SyntheticMember
  private static final long serialVersionUID = 588368462L;
}
