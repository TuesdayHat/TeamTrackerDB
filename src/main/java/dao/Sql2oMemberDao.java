package dao;


import models.Member;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class Sql2oMemberDao implements MemberDao{

    private final Sql2o sql2o;

    public Sql2oMemberDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public Member findById(int id){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM members WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Member.class);
        }
    }

    @Override
    public void add(Member member){

    }
}
