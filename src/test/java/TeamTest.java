import org.junit.Test;
import static org.junit.Assert.*;

public class TeamTest {
  Team testTeam = new Team();

  @Test
  public void Team_instantiates_bool(){
    assertTrue(testTeam != null);
  }
}
