package dao;

import models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oTeamDaoTest {
    private Sql2oTeamDao teamDao;
    private Sql2oMemberDao memberDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception{
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        teamDao = new Sql2oTeamDao(sql2o);
        memberDao = new Sql2oMemberDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    public Team setupTeamOne(){
        String name = "Team One";
        String desc = "The Teamiest Team That Ever Teamed Up";
//        String members = "Bobson Dugnutt, Todd Bonzalez, Mike Truk, Willie Dustice";
        String members = "";
        return new Team(name, members, desc );
    }

    public Team setupTeamTwo(){
        String name = "Team Two";
        String desc = "The Second Teamiest Team But Still Pretty Teamy";
//        String members = "Sleve McDicheal, Glenallen Mixon, Kevin Noginly, Darryl Archideld";
        String members = "";
        return new Team(name, members, desc );
    }
    public Team setupTeamThree(){
        String name = "Team Two";
        String desc = "The Second Teamiest Team But Still Pretty Teamy";
        String members = "";
        return new Team(name, members, desc );
    }

    @Test
    public void add_addingNewTeamSetsId_int(){
        Team team = setupTeamOne();
        int initialId = team.getId();
        teamDao.add(team);
        assertNotEquals(initialId, team.getId());
    }

    @Test
    public void findById_teamsCanBeFoundById_team(){
        Team team = setupTeamOne();
        teamDao.add(team);
        Team foundTeam = teamDao.findById(team.getId());
        assertEquals(team, foundTeam);
    }

    @Test
    public void getAll_allAddedTeamsReturnedByGetAll_int(){
        teamDao.add(setupTeamOne());
        teamDao.add(setupTeamTwo());
        assertEquals(2, teamDao.getAll().size());
    }

    @Test
    public void update_updateChangesTeamContent(){
        Team team = setupTeamOne();
        String originalName = "Team One";
        teamDao.add(team);

        teamDao.update(team.getId(), "Teamination", "update Description");
        Team teamUpdate = teamDao.findById(team.getId());
        assertNotEquals(originalName, teamUpdate.getName());

    }

    @Test
    public void deleteById_deleteSpecificTeamById(){
        Team teamOne = setupTeamOne();
        Team teamTwo = setupTeamTwo();
        teamDao.add(teamOne);
        teamDao.add(teamTwo);

        teamDao.deleteById(teamOne.getId());
        assertEquals(1, teamDao.getAll().size());
        assertEquals(teamTwo, teamDao.getAll().get(0));
    }

    @Test
    public void clearAllTeams_wipeAllTeamsFromDatabase(){
        teamDao.add(setupTeamOne());
        teamDao.add(setupTeamTwo());
        teamDao.clearAllTeams();
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void getMembersOfTeam_returnsAllMembersInTeam(){
        Team teamOne = setupTeamOne();
        teamDao.add(teamOne);

        int teamOneId = teamOne.getId();
        Member memOne = new Member("Bobson Dugnutt", teamOneId);
        Member memTwo = new Member("Sleve McDicheal", teamOneId);
        Member memThree = new Member("Glenallen Mixon", teamOneId);

        memberDao.add(memOne);
        memberDao.add(memTwo);

        System.out.println(teamDao.getMembersOfTeam(teamOneId).size());
        assertTrue(teamDao.getMembersOfTeam(teamOneId).size() == 2);
        assertTrue(teamDao.getMembersOfTeam(teamOneId).contains(memOne));
        assertTrue(teamDao.getMembersOfTeam(teamOneId).contains(memTwo));
        assertFalse(teamDao.getMembersOfTeam(teamOneId).contains(memThree));
    }
}