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
public class ArrivedAtEndRoad extends Event {
  @SyntheticMember
  public ArrivedAtEndRoad() {
    super();
  }
  
  @SyntheticMember
  public ArrivedAtEndRoad(final Address source) {
    super(source);
  }
}
