package models;


public class Member {
    private String name;
    private int id;
    private int teamId;

    public Member(String name, int... teamId){
        this.name = name;
        this.teamId = teamId[0];
    }

    public void setName(String newName){
        this.name = newName;
    }
    public void setId(int newId){
        this.id = newId;
    }
    public void setTeamId(int newTeamId){
        this.teamId = newTeamId;
    }

    //GETTERS
    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }
    public int getTeamId(){
        return teamId;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return false;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (id != member.id) return false;
        if (teamId != member.teamId) return false;
        return name.equals(member.name);
    }

    @Override
    public int hashCode(){
        int result = name.hashCode();
        result = 31*result+id;
        result = 31*result+teamId;

        return result;
    }

}
