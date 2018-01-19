package dao;

import models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

/**
 * Created by Guest on 1/19/18.
 */
public class Sql2oTeamDaoTest {
    private Sql2oTeamDao teamDao;
//    private Sql2oMemberDao memberdao;
    private Connection conn;

    @Before
    public void setUp() throws Exception{
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        teamDao = new Sql2oTeamDao(sql2o);
//        memberDao = new Sql2oMemberDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    public Team setupTeamOne(){
        String name = "Team One";
        String desc = "The Teamiest Team That Ever Teamed Up";
        String members = "Bobson Dugnutt, Todd Bonzalez, Mike Truk, Willie Dustice";
        return new Team(name, members, desc );
    }

    @Test
    public void add_addingNewTeamSetsId_int(){
        Team team = setupTeamOne();
        int initialId = team.getId();
        teamDao.add(team);
        Team foundTeam = teamDao.findById(team.getId());
        assertEquals(team, foundTeam);
    }
}