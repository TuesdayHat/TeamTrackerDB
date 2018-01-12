import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TeamTest {
  String name = "Team";
  String desc = "The Best Team";
  List<String> members = new ArrayList<String>(){{
    add("Bobson Dugnutt");
    add("Todd Bonzalez");
    add("Mike Truk");
    add("Willie Dustice");
  }};


  Team testTeam = new Team(name, members, desc);

  @Test
  public void Team_instantiates_bool(){
    assertTrue(testTeam != null);
  }

  @Test
  public void getName_getsName_string(){
    assertEquals("Team", testTeam.getName());
  }

  @Test
  public void getDescription_getsDescription_string(){
    assertEquals("The Best Team", testTeam.getDescription());
  }

  @Test
  public void getMembers_getListOfMembers_List(){
    assert(testTeam.getMembers().size() > 2);
  }
}
