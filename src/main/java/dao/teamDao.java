package dao;

import models.*;

public interface teamDao {

    //create
    void add (Team team);

    //read

    Team findById(int id);
}
