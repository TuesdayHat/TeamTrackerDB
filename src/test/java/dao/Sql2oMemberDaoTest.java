package dao;

import models.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;


public class Sql2oMemberDaoTest {
    private Sql2oMemberDao memberDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        memberDao = new Sql2oMemberDao(sql2o);

        conn = sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    public Member setupMemberOne(){
        return new Member("Bobson Dugnutt");
    }

    @Test
    public void add_addMemberSetsId() throws Exception {
        Member member = setupMemberOne();
        int id = member.getId();
        memberDao.add(member);
        assertNotEquals(id, memberDao.findById(member.getId()).getId());
    }

}