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
        return new Member("Bobson Dugnutt", 1);
    }

    public Member setupMemberTwo(){
        return new Member("Sleve McDicheal", 1);
    }

    @Test
    public void add_addMemberSetsId() throws Exception {
        Member member = setupMemberOne();
        int id = member.getId();
        memberDao.add(member);
        assertNotEquals(id, memberDao.findById(member.getId()).getId());
    }

    @Test
    public void findById_findSpecificMemberById() throws Exception{
        Member member = setupMemberOne();
        memberDao.add(member);
        Member foundMember = memberDao.findById(member.getId());
        assertEquals(foundMember, member);
    }

    @Test
    public void getAll_allAddedMemberReturnedFromGetAll() throws Exception{
        Member memTwo = setupMemberTwo();
        Member memOne = setupMemberOne();

        memberDao.add(memOne);
        memberDao.add(memTwo);

        assertEquals(2, memberDao.getAll().size());
    }

    @Test
    public void update_changeNameOfTeamMember() throws Exception{
        String originalName = "Bobson Dugnutt";
        Member mem = setupMemberOne();
        memberDao.add(mem);

        memberDao.update(mem.getId(), "Todd Bonzalez", 1);
        Member newMember = memberDao.findById(mem.getId());
        assertNotEquals(originalName, newMember.getName());
    }

    @Test
    public void deleteById_deletesCorrectMember() throws Exception{
        Member memTwo = setupMemberTwo();
        Member memOne = setupMemberOne();

        memberDao.add(memOne);
        memberDao.add(memTwo);

        memberDao.deleteById(memOne.getId());
        assertEquals(1, memberDao.getAll().size());
    }

    @Test
    public void clearAllMembers_removesAllMembers() throws Exception{
        Member memTwo = setupMemberTwo();
        Member memOne = setupMemberOne();

        memberDao.add(memOne);
        memberDao.add(memTwo);

        memberDao.clearAllMembers();

        assertEquals(0, memberDao.getAll().size());
    }

    @Test
    public void teamIdIsReturnedCorrectly() throws Exception{
        Member mem = setupMemberOne();
        int origTeamId = mem.getTeamId();
        memberDao.add(mem);
        assertEquals(origTeamId, memberDao.findById(mem.getId()).getTeamId());
    }
}