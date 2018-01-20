import java.util.HashMap;
import java.util.Map;

import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
import models.Member;
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


    //index page, shows rules of contest
    get("/", ((request, response) -> {
      Map<String, Object> model = new HashMap<>();

      List<Team> allTeams = teamDao.getAll();
      model.put("allTeams", allTeams); //don't think I need this here; testing purposes.

      return new ModelAndView(model, "index.hbs");
    }), new HandlebarsTemplateEngine());

    //show all teams, links to details
    get("/teams", ((request, response) -> {
      Map<String, Object> model = new HashMap<>();

      List<Team> allTeams = teamDao.getAll();
      model.put("allTeams", allTeams);

      return new ModelAndView(model, "teams.hbs");
    }), new HandlebarsTemplateEngine());


    //post: process new team form
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


    //get: show New Team form
    get("/teams/new", ((request, response) -> {
      Map<String, Object> model = new HashMap<>();

      List<Team> allTeams = teamDao.getAll();
      model.put("allTeams", allTeams);

      return new ModelAndView(model, "newTeam-form.hbs");
    }), new HandlebarsTemplateEngine());

    //show a specific team and its members
    get("/teams/:id", ((request, response) -> {
      Map<String, Object> model = new HashMap<>();
      int idOfSpecTeam = Integer.parseInt(request.params("id"));

      List<Team> allTeams = teamDao.getAll();
      model.put("allTeams", allTeams);

      Team currTeam = teamDao.findById(idOfSpecTeam);

      model.put("team", currTeam);
      List<Member> allMembersInTeam = teamDao.getMembersOfTeam(idOfSpecTeam);
      model.put("members", allMembersInTeam);

      return new ModelAndView(model, "team-detail.hbs");
    }), new HandlebarsTemplateEngine());


  }
}
