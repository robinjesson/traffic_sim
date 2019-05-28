import application.Simulation;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import javafx.application.Application;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author tiantian
 */
@SarlSpecification("0.9")
@SarlElementType(10)
@SuppressWarnings("all")
public class Main {
  @Pure
  public static void main(final String[] args) {
    Application.launch(Simulation.class);
  }
  
  @SyntheticMember
  public Main() {
    super();
  }
}
