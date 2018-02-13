package umm3601.todo;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

/**
 * A fake "database" of user info
 *
 * Since we don't want to complicate this lab with a real database,
 * we're going to instead just read a bunch of user data from a
 * specified JSON file, and then provide various database-like
 * methods that allow the `UserController` to "query" the "database".
 */
public class Database {

  private Todo[] allTodos;

  public Database(String userDataFile) throws IOException {
    Gson gson = new Gson();
    FileReader reader = new FileReader(userDataFile);
    allTodos = gson.fromJson(reader, Todo[].class);
  }

  /**
   * Get the single user specified by the given ID. Return
   * `null` if there is no user with that ID.
   *
   * @param id the ID of the desired user
   * @return the user with the given ID, or null if there is no user
   * with that ID
   */
  public Todo getTodo(String id) {
    return Arrays.stream(allTodos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }

  /**
   * Get an array of all the users satisfying the queries in the params.
   *
   * @param queryParams map of required key-value pairs for the query
   * @return an array of all the users matching the given criteria
   */
  public Todo[] listTodos(Map<String, String[]> queryParams) {
    Todo[] filteredTodos = allTodos;
    //String message = "User with ID " + id + " wasn't found.";

    // by category
    if (queryParams.containsKey("category")) {
      String targetCategory = queryParams.get("category")[0];
      filteredTodos = filterTodosByCategory(filteredTodos, targetCategory);
    }

    // by content
    if (queryParams.containsKey("content")) {
      String targetContent = queryParams.get("content")[0];
      filteredTodos = filterTodosByContent(filteredTodos, targetContent);
    }

    // for limiting todos
    if (queryParams.containsKey("limit")) {
      int targetLimit = Integer.parseInt(queryParams.get("limit")[0]);
      filteredTodos = limitTodos(filteredTodos, targetLimit);
    }

    // for ordering by
    if (queryParams.containsKey("orderBy")) {
      String targetOrder = queryParams.get("orderBy")[0];
      filteredTodos = orderTodos(filteredTodos, targetOrder);
    }

    // by owner
    if (queryParams.containsKey("owner")) {
      String targetOwner = queryParams.get("owner")[0];
      filteredTodos = filterTodosByOwner(filteredTodos, targetOwner);
    }

    // by status
    if (queryParams.containsKey("status")) {
      Boolean targetStatus = Boolean.parseBoolean(queryParams.get("status")[0]);
      filteredTodos = filterTodosByStatus(filteredTodos, targetStatus);
    }

    return filteredTodos;
  }

  // filter by category
  public Todo[] filterTodosByCategory(Todo[] todos, String targetCategory) {
    return Arrays.stream(todos).filter(x -> x.category.equals(targetCategory)).toArray(Todo[]::new);
  }

  // filter by content
  public Todo[] filterTodosByContent(Todo[] todos, String targetContent) {
    return Arrays.stream(todos).filter(x -> x.body.contains(targetContent)).toArray(Todo[]::new);
  }

  // limit
  public Todo[] limitTodos(Todo[] todos, int targetLimit) {
    if (targetLimit < 0) {
      targetLimit = 0;
    }

    Todo[] limtodos = new Todo[targetLimit];

    if (targetLimit < 1) {
      return limtodos;
    } else if (targetLimit > todos.length) {
      return todos;
    } else {
      System.arraycopy(todos, 0, limtodos, 0, targetLimit);
      return limtodos;
    }
  }

  public Todo[] orderTodos(Todo[] todos, String order) {

    switch (order) {
      case "body":
        Arrays.sort(todos, new bodyComparator());
        break;

      case "owner":
        Arrays.sort(todos, new ownerComparator());
        break;

      case "status":
        Arrays.sort(todos, new statusComparator());
        break;

      default:
        Arrays.sort(todos, new categoryComparator());
        break;
    }
    return todos;
  }

  // comparator for owner
  public class ownerComparator implements Comparator<Todo> {
    @Override
    public int compare(Todo td1, Todo td2) {
      return td1.owner.compareTo(td2.owner);
    }
  }

  // comparator for status
  public class statusComparator implements Comparator<Todo> {
    @Override
    public int compare(Todo td1, Todo td2) {
      return Boolean.compare(td1.status, td2.status);
    }
  }

  // comparator for body
  public class bodyComparator implements Comparator<Todo> {
    @Override
    public int compare(Todo td1, Todo td2) {
      return td1.body.compareTo(td2.body);
    }
  }

  // comparator for category
  public class categoryComparator implements Comparator<Todo> {
    //@Override
    public int compare(Todo td1, Todo td2) {
      return td1.category.compareTo(td2.category);
    }
  }

  // filter by owner
  public Todo[] filterTodosByOwner(Todo[] todos, String targetOwner) {
    return Arrays.stream(todos).filter(x -> x.owner.equals(targetOwner)).toArray(Todo[]::new);
  }

  // get all todos by 'status' and filter
  public Todo[] filterTodosByStatus(Todo[] todos, boolean targetStatus) {
    return Arrays.stream(todos).filter(x -> x.status == targetStatus).toArray(Todo[]::new);
  }
}
