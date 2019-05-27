package events;

import events.Perception;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;

/**
 * @author robin
 */
@SarlSpecification("0.9")
@SarlElementType(15)
@SuppressWarnings("all")
public class ArrivedAtEndRoad extends Perception {
  @SyntheticMember
  public ArrivedAtEndRoad() {
    super();
  }
  
  @SyntheticMember
  public ArrivedAtEndRoad(final Address source) {
    super(source);
  }
  
  @SyntheticMember
  private static final long serialVersionUID = -698425157L;
}
