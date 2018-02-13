package todo;

import org.junit.Test;
import umm3601.todo.Database;
import umm3601.todo.Todo;
import java.io.IOException;
import java.util.HashMap;
import static junit.framework.TestCase.assertEquals;

public class FilterByCategory {

  @Test
  public void filterByCategory() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] categoryVG = db.filterTodosByCategory(allTodos, "video games");
    assertEquals(71, categoryVG.length);
  }
}
