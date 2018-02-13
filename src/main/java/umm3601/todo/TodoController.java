package umm3601.todo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;
import static umm3601.Util.buildFailJsonResponse;
import static umm3601.Util.buildSuccessJsonResponse;

/**
 * Controller that manages requests for info about users.
 */
public class TodoController {

  private final Gson gson;
  private Database database;

  /**
   * Construct a controller for users.
   *
   * This loads the "database" of user info from a JSON file and
   * stores that internally so that (subsets of) users can be returned
   * in response to requests.
   *
   * @param database the database containing user data
   */
  public TodoController(Database database) {
    gson = new Gson();
    this.database = database;
  }

  /**
   * Get the single user specified by the `id` parameter in the request.
   *
   * @param req the HTTP request
   * @param res the HTTP response
   * @return a success JSON object if the user with that ID is found, a fail
   * JSON object if no user with that ID is found
   */
  public JsonObject getTodo(Request req, Response res) {
    res.type("application/json");
    String id = req.params("id");
    Todo todo = database.getTodo(id);
    if (todo != null) {
      return buildSuccessJsonResponse("todo", gson.toJsonTree(todo));
    } else {
      String message = "User with ID " + id + " wasn't found.";
      return buildFailJsonResponse("id", message);
    }
  }

  /**
   * Get a JSON response with a list of all the users in the "database".
   *
   * @param req the HTTP request
   * @param res the HTTP response
   * @return a success JSON object containing all the users
   */
  public JsonObject getTodos(Request req, Response res) {
    res.type("application/json");
    Todo[] todos = database.listTodos(req.queryMap().toMap());
    return buildSuccessJsonResponse("users", gson.toJsonTree(todos));
  }

}
