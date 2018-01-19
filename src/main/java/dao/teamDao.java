package dao;

import models.*;

import java.util.List;

public interface teamDao {

    //create
    void add (Team team);

    //read
    List<Team> getAll();
//    List<Member> getMembersOfTeam(int teamId);

    Team findById(int id);

    //update
    void update(int id, String name, String desc);

    //delete
    void deleteById(int id);
    void clearAllTeams();
}
