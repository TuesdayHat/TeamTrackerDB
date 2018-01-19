import models.Team;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamTest {
  private String name = "Team";
  private String desc = "The Best Team";
  private String members = "Bobson Dugnutt, Todd Bonzalez, Mike Truk, Willie Dustice";

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
    assert(testTeam.getMembers().size() > 4);
  }

  @Test
  public void findById_findIndividualTeamById_Team(){
    Team team = new Team(name, members, desc);
    assertEquals(1, Team.findById(testTeam.getId()).getId());
  }
}
