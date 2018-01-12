import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;
import java.util.List;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");

    get("/", ((request, response) -> {
      Map<String, Object> model = new HashMap<>();
//      List<Team> teams = Team.getInstances();
//      model.put("teams", teams);
      return new ModelAndView(model, "index.hbs");
    }), new HandlebarsTemplateEngine());

    get("/teams", ((request, response) -> {
      Map<String, Object> model = new HashMap<>();

      List<Team> teams = Team.getInstances();
      for(Team team: teams){
        System.out.println(team);
      }
      model.put("teams", teams);

      return new ModelAndView(model, "teams.hbs");
    }), new HandlebarsTemplateEngine());

    get("/teams/new", ((request, response) -> {
      Map<String, Object> model = new HashMap<>();



      return new ModelAndView(model, "newTeam-form.hbs");
    }), new HandlebarsTemplateEngine());

    get("/teams/:id", ((request, response) -> {
      Map<String, Object> model = new HashMap<>();

      int teamId = Integer.parseInt(request.params("id"));
      Team currTeam = Team.findById(teamId);
      model.put("team", currTeam);

      return new ModelAndView(model, "team-detail.hbs");
    }), new HandlebarsTemplateEngine());
  }
}
