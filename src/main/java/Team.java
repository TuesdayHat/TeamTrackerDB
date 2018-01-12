import java.util.ArrayList;
import java.util.List;

public class Team {
  private List<String> members;
  private String description;
  private String name;
  private static List<Team> instances = new ArrayList<>();
  private int id;

  Team(String teamName, List<String> people, String desc){
    instances.add(this);
    name = teamName;
    description = desc;
    members = people;
    id = instances.size();
  }

  public void setMembers(String names){
    String[] arrNames = names.split(", ");
    for (String name: arrNames){
      members.add(name);
    }
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
}
