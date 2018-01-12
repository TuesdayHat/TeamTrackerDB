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
      List<Team> teams = Team.getInstances();
      model.put("teams", teams);
      return new ModelAndView(model, "index.hbs");
    }), new HandlebarsTemplateEngine());
  }
}
