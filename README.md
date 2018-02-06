# CSCI 3601 Lab #2 - JavaScript and Spark Lab

<!--[![Build Status](https://travis-ci.org/UMM-CSci-3601/3601-lab2_client-server.svg?branch=master)](https://travis-ci.org/UMM-CSci-3601/3601-lab2_client-server)
-->
[![Build Status](https://travis-ci.org/UMM-CSci-3601-S18/lab-2-client-server-architecture-abefrancisco.svg?branch=master)](https://travis-ci.org/UMM-CSci-3601-S18/lab-2-client-server-architecture-abefrancisco)

Here you will explore serving up a simple website that you create, 
using a [Java Spark][spark] server. Spark is a micro framework for 
creating web applications in Java. You will be using Spark to create 
the server component (back-end) of your website.

The client component (front-end) of your website will user JavaScript
to allow you to accept and process user input. We will use JUnit to test
the server code and introduce continuous integration using [Travis CI][travis].

Your specific tasks for this lab can be found in the [LABTASKS.md][labtasks] file in this repository.

## Overview of the project

You'll be building parts of a simple to-do list using a
client-server architecture. The server will be able to handle
simple HTTP GET requests, where a client (or a user) can
visit a URL such as `http://localhost:4567/api/users` and the server
will respond with JSON-formatted text (following the [JSend][jsend] 
guidelines) containing
all the users the server knows about, e.g.,
```
{
  "status": "success",
  "data": {
    "users": [
      {
        "_id": "588935f57546a2daea44de7c",
        "name": "Connie Stewart",
        "age": 25,
        "company": "OHMNET",
        "email": "conniestewart@ohmnet.com"
      },
      {
        "_id": "588935f5597715f06f3e8f6c",
        "name": "Lynn Ferguson",
        "age": 25,
        "company": "NIQUENT",
        "email": "lynnferguson@niquent.com"
      },
      {
        "_id": "588935f51c55b55c75a84848",
        "name": "Roseann Roberson",
        "age": 23,
        "company": "GINKLE",
        "email": "roseannroberson@ginkle.com"
      },
      ...
    ]
  }
}
```

The client will be a combination of HTML, CSS, and JavaScript
that runs in the browser and converts user actions (such as
clicking a button) into requests to the server. In a "real"
system you'd want to display the results nicely as part of
the application web interface (like the nicely formatted
list of e-mails in GMail).
To keep this lab simple, however, you'll just display the "raw" JSON
that the client receives from the server.

This lab has two components:

* Implement the desired server functionality
* Implement a simple web client that allows users to 
access that server functionality through HTML forms

The details of both of these components are in [LABTASKS.md](./LABTASKS.md).

## Setup

Open up [IntelliJ IDEA][intellij-idea] and make sure that Java is set up correctly
* From the welcome screen of IntelliJ, in the lower right corner-ish area, click on Configure.
* Choose "Project Defaults"
* Choose "Project Structure"
* In the Project SDK section, there is a dropdown menu for the SDK to select. If there is no SDK, it will say so in red "<No SDK>". There is a button to the right of that that says "New...". Click that button. In our lab, you can find Java in /usr/lib/jvm/java

Clone your Github classroom
fork of this repository from GitHub. When prompted if you would like create an IntelliJ project for the sources you've checked out, **select yes**.

Then, select **import project from existing model** and select **Gradle**. Make sure **Use default gradle wrapper** is selected on the next screen, and click **OK**.

You'll also want the JSONView extension for either Firefox or Chrome installed. This will make JSON in the browser look pretty and actually be readable.

* Firefox: [JSONView][jsonview-firefox]
* Chrome: [JSONView][jsonview-chrome]


## Running Your project

We use the [Gradle][gradle] build tool to build and run our web application.
Gradle is a powerful system for defining, managing, and running tasks
that allows us to easily build and test our full web application.

Open the Gradle tool window in IntelliJ by going to: `View -> Tool Windows -> Gradle`. From here, open up the `Tasks` section. Gradle tasks run things like the development server, production build, and tests. Open up the `application` task category and double click `run`.

Your server should now be running on port 4567, the default Spark port. Visit it at [http://localhost:4567][local].

## Testing Your Project

There's very little meaningful logic in the client component of this
project so we're not going to worry about testing the client here.
We'll begin testing the client when we introduce Angular in subsequent 
labs.

The server-side portion of this project will be tested using JUnit.
Server-sided tests are located in the `src/test/java` directory.

To run your server-side tests, let's practice creating an IntelliJ run configuration. We need to do this because there is no default task for running only server-side tests. Open `Run -> Edit configurations`. Click the green `+` arrow and choose `Gradle`.

- Name: "Run Server Tests"
- Gradle project: Click on the folder with the blue square icon and choose your project from the dropdown.
- Tasks: test
- Arguments: `-x karmaRun`

Then, you can run it by selecting the run configuration
`Run -> Run... -> Run Server Tests`. The `-x karmaRun` script
parameters specify that you want to run all the tests
_except_ the `karma` tests (which will be used to run the
client tests later) so you just get the server tests.

(In IntelliJ you can also run all the JUnit tests in the project
by right-clicking on `test/java` in the `Project` view and choose
`Run All Tests...`. It's useful to know how to create a run
configuration, though, and they can be important for automating
processing like Travis-CI.)


## Continuous Integration with Travis CI

[Travis CI][travis] is a Continuous Integration tool that performs 
builds of your project every time you push to GitHub. 
This is very helpful, as it makes keeping track of your testing 
over the lifetime of a project very easy. Having a build/test 
history makes it easy to find where, or when, your project broke,
which makes it a lot easier to figure out _why_ it broke and get
it fixed and building successfully again.

Any open-source, public project on GitHub can use Travis CI for
free whereas people normally need to pay for the ability to use
Travis to build private repositories. Through your GitHub
Student pack, you get free private builds on Travis while you're
a student.

> Protip: The GitHub Student pack has a ton of really awesome stuff in it, including $100 of credit to Digital Ocean! https://education.github.com/pack/offers

We've done the hard part of setting up the [.travis.yml][travis-yml] 
file. You can look at it by clicking the previous link or 
[read about setting up a Java Gradle project with 
Travis][travis-java].

What you need to do:
- Sign into [Travis-CI.org][travis] with your GitHub account.

> Protip: We'd recommend having everyone in your group do this.

> :bangbang: Make sure you go to *.org* and _not_ *.com*. Travis-CI
has both a `.org` and a `.com` version for their free and paid
customers, respectively. You will be a non-paying customer, so you
_have_ to use the `.org` site; if you attempt to use the `.com`
site you won't see any of your projects.

- Authorize the application to access your GitHub account.
  - Read through the "First Time Here?" instructions.
- Click on the "+" on the left side of the page near "My Repositories".
- Find your fork for this lab in the list of repositories.
- Enable continuous integration for it by clicking the big toggle switch beside it.
- Then, click on the gear icon (settings) directly next to the previous toggle switch.
- From here, you can see various pieces of information and
settings regarding the builds of your project. You don't need
to change anything at the moment, but it's good to see what's
there

At this point, make some change to your forked project and add those changes to GitHub (It doesn't really matter what you do, this is needed to trigger a Travis build).

After you committed and pushed those changes, you'll want to update
the Travis-CI badge link (at the top of this document) so it points
to _your_ project. (Otherwise it will always show you the status of
the project you forked, which won't be very useful.) 

On the Travis-CI web site, click on
the `Build Status Image` button to the right of the name of the
repository.
  - Select "Master" for the Branch.
  - Select "Markdown" for the drop-down.
  - Copy the markdown it provides.
  - Update this README.md file to swap out the build status image at the top with your own.

Your own forked project is now ready for the magic of continuous integration!

## Resources

##### Running in the command line

We include a Gradle wrapper which lets you run gradle tasks from the command line. So, you can run tasks like:

```
./gradlew test
```

##### Handling requests in Spark

- [Using Spark to create APIs in Java][spark-api]
- [Response handling with Spark][spark-response]
- [Spark documentation][spark-documentation]
- [Example of a nicely structured, more complex Spark project][spark-structure]
- [JSend "standard" for JSON responses][jsend]
- [Best practices for REST interface design][rest-best-practices]
- [HTTP Status Codes][status-codes]

[gradle]: https://gradle.org/
[intellij-idea]: https://www.jetbrains.com/idea/
[jasmine]: https://jasmine.github.io/
[jasmine-introduction]: http://jasmine.github.io/2.0/introduction.html
[jsend]: http://labs.omniti.com/labs/jsend
[jsonview-chrome]: https://chrome.google.com/webstore/detail/jsonview/chklaanhfefbnpoihckbnefhakgolnmc?hl=en
[jsonview-firefox]: https://addons.mozilla.org/en-us/firefox/addon/jsonview/
[karma]: https://karma-runner.github.io/1.0/index.html
[labtasks]: LABTASKS.md
[local]: http://localhost:4567/
[rest-best-practices]: https://medium.com/@mwaysolutions/10-best-practices-for-better-restful-api-cbe81b06f291
[spark]: http://sparkjava.com/
[spark-api]: http://nordicapis.com/using-spark-to-create-apis-in-java/
[spark-documentation]: http://sparkjava.com/documentation.html
[spark-response]: http://sparkjava.com/documentation.html#response
[spark-structure]: http://sparkjava.com/tutorials/application-structure
[travis]: https://travis-ci.org/
[travis-java]: https://docs.travis-ci.com/user/languages/java/
[travis-yml]: .travis.yml
[status-codes]: https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
