package capacities;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.AgentTrait;
import io.sarl.lang.core.Capacity;

@SarlSpecification("0.9")
@SarlElementType(20)
@SuppressWarnings("all")
public interface Drive extends Capacity {
  public abstract int getSpeed();
  
  /**
   * Returns the new speed after speeding up
   */
  public abstract int speedUp(final int maxSpeed, final int currentSpeed);
  
  /**
   * Returns the new speed after braking
   */
  public abstract int brake(final int currentSpeed);
  
  /**
   * @ExcludeFromApidoc
   */
  public static class ContextAwareCapacityWrapper<C extends Drive> extends Capacity.ContextAwareCapacityWrapper<C> implements Drive {
    public ContextAwareCapacityWrapper(final C capacity, final AgentTrait caller) {
      super(capacity, caller);
    }
    
    public int getSpeed() {
      try {
        ensureCallerInLocalThread();
        return this.capacity.getSpeed();
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public int speedUp(final int maxSpeed, final int currentSpeed) {
      try {
        ensureCallerInLocalThread();
        return this.capacity.speedUp(maxSpeed, currentSpeed);
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public int brake(final int currentSpeed) {
      try {
        ensureCallerInLocalThread();
        return this.capacity.brake(currentSpeed);
      } finally {
        resetCallerInLocalThread();
      }
    }
  }
}
