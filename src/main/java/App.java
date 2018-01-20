import java.util.HashMap;
import java.util.Map;

import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
import models.Team;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

import java.util.List;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String connectionString = "jdbc:h2:~/teamTracker.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
    Sql2o sql2o = new Sql2o(connectionString, "", "");
    Sql2oMemberDao memberDao = new Sql2oMemberDao(sql2o);
    Sql2oTeamDao teamDao = new Sql2oTeamDao(sql2o);



    get("/", ((request, response) -> {
      Map<String, Object> model = new HashMap<>();
      return new ModelAndView(model, "index.hbs");
    }), new HandlebarsTemplateEngine());

    get("/teams", ((request, response) -> {
      Map<String, Object> model = new HashMap<>();

      List<Team> allTeams = teamDao.getAll();
      model.put("allTeams", allTeams);

      return new ModelAndView(model, "teams.hbs");
    }), new HandlebarsTemplateEngine());

    post("/teams", ((request, response) -> {
      Map<String, Object> model = new HashMap<>();
      String newTeamName = request.queryParams("name");
      String newTeamDesc = request.queryParams("desc");
      String newTeamMembers = request.queryParams("members");

      Team newTeam = new Team(newTeamName, newTeamMembers, newTeamDesc); // note: rebuild logic for taking in multiple members at once
      teamDao.add(newTeam);

      List<Team> allTeams = teamDao.getAll();
      model.put("allTeams", allTeams);

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
