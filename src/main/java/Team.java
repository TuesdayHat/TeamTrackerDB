import java.util.ArrayList;
import java.util.List;

public class Team {
  private List<String> members = new ArrayList<>();
  private String description;
  private String name;
  private static List<Team> instances = new ArrayList<>();
  private int id;

  public Team(String teamName, List<String> people, String desc){
    instances.add(this);
    name = teamName;
    description = desc;
    members = people;
    id = instances.size();
  }

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
}
