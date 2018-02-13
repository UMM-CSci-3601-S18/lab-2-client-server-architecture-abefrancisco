// Why do we use the `var getAllUsers = function()` syntax
// for the first definition, and the named function syntax
// for the second definition?

/**
 * Function to get all the todos!
 */
function getAllTodos() {
  console.log("Getting all the todos.");

  var HttpThingfortodo = new HttpClient();
  HttpThingfortodo.get("/api/todos", function(returned_json){
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

/**
 * Wrapper to make generating http requests easier. Should maybe be moved
 * somewhere else in the future!.
 *
 * Based on: http://stackoverflow.com/a/22076667
 * Now with more comments!
 */

function getAllTodosByOwner() {
  console.log("Getting all todos by owner.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?owner=" +  document.getElementById("owner").value, function(returned_json){
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllTodosByLimit() {
  console.log("Get limiting todos.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?limit=" + document.getElementById("limit").value, function(returned_json){
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getTodoById() {
  console.log("Get the todo.");
  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos/" +  document.getElementById("id").value, function(returned_json){
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}

function getAllTodosByCategory() {
  console.log("Get all the todos by category.");
  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos?category=" + document.getElementById("category").value, function(returned_json){
    document.getElementById('jsonDump').innerHTML = returned_json;
  });
}



function HttpClient() {
  this.get = function(aUrl, aCallback){
    var anHttpRequest = new XMLHttpRequest();
    anHttpRequest.onreadystatechange = function(){



      if (anHttpRequest.readyState === 4 && anHttpRequest.status === 200)
        aCallback(anHttpRequest.responseText);
    };

    anHttpRequest.open("GET", aUrl, true);
    anHttpRequest.send(null);
  }
}
