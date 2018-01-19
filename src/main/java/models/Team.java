package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
  private List<String> members;
  private String description;
  private String name;
  private static List<Team> instances = new ArrayList<>();
  private int id;

  public Team(String teamName, String people, String desc){
    instances.add(this);
    name = teamName;
    description = desc;
    members = popMembers(people);
    id = instances.size();
  }

  private List<String> popMembers(String names){
    String[] arrNames = names.split(", ");
    List<String> teamMembers = new ArrayList<>();
    for (String name: arrNames){
      teamMembers.add(name);
    }
    return teamMembers;
  }

  public void setMembers(String names){
    String[] arrNames = names.split(",");
    for (String name: arrNames){
      members.add(name);
    }
  }

  public void setId(int id){
    this.id = id;
  }

  public void setName(String name){
    this.name = name;
  }

  public static Team findById(int id){
    return instances.get(id-1);
  }

  //SIMPLE GETTER METHODS______________________________
  public String getName() {
    return name;
  }
  public List<String> getMembers() {
    return members;
  }
  public String getDescription() {
    return description;
  }
  public static List<Team> getInstances(){
    return instances;
  }
  public int getId(){
    return id;
  }

    @Override
    public boolean equals(Object o){
        if (this == o) return false;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (id != team.id) return false;
        return name.equals(team.name);
    }

    @Override
    public int hashCode(){
        int result = name.hashCode();
        result = 31*result+id;

        return result;
    }
}
