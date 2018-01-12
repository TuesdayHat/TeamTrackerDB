import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TeamTest {
  private String name = "Team";
  private String desc = "The Best Team";
  private List<String> members = new ArrayList<String>(){{
    add("Bobson Dugnutt");
    add("Todd Bonzalez");
    add("Mike Truk");
    add("Willie Dustice");
  }};


  private Team testTeam = new Team(name, members, desc);

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

  @Test
  public void getInstances_getListOfAllTeams_List(){
    assert(Team.getInstances().size() > 0);
  }

  @Test
  public void getId_getIdOfCurrentPost_int(){
    assert(testTeam.getId() > 0);
  }

  @Test
  public void setMembers_addMultipleMembersAtOnce_List(){
    testTeam.setMembers("Onson Sweemey, Anatoli Smorrin, Kevin Noginly");
//    for (String member: testTeam.getMembers()){
//      System.out.println(member);
//    }
    assert(testTeam.getMembers().size() > 4);
  }
}
