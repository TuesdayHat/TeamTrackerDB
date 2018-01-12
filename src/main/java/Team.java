import java.util.ArrayList;
import java.util.List;

public class Team {
  List<String> members = new ArrayList<>();
  String description;
  String name;

  public Team(String teamName, List<String> people, String desc){
    name = teamName;
    description = desc;
    members = people;
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
}
