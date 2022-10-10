##PagoNxt
###Reqres Rest-API task

In the following project you'll find two feature files, 
in which include negative and positive test scenarios.

In the task, it is mentioned that I need to create a report file,
which include `Data, Test Name, Test result status, Last update date and
Failure counter`. But even if it seems successful to create a user, it was 
not successful. There are no connections between requests and database. It
only shows me existing data, nothing more. Because there is no retrievable
data on database side, you won't see any test report as a consequence.
You can find simple `HTML` report, but this is not what you wanted
I suppose.

At the beginning, I made simple tasks like creating user, updating and 
deleting with dynamic id, but couldn't retrieve specific user information
or didn't show up on `list of user` request. Therefore, I tried to do it
with registration which brought me also no help. But I kept it, so you 
can inspect my whole framework. I also created some more overloading method,
which can be helpful to use same method with different inputs.

### File Structure
In the task, I implemented Page Object Model. I think, it's a good model, 
if you want to scale it later and to make some improvements.
####- pages
It can be used later, if UI testing is also included. So three layer
verification can be executed.
####- runners
Inside `CukesRunner.class` I added for getting `HTML` report and `rerun`
file in order to run again if any test fails. Since this is like e2e test
it's of no use at the moment.
####- stepDefinitions
All test related methods are stored here.
####- utilities
Under this package there are some commonly used methods I created.
Some are for related to only this project, some others are in general;
can be used any project.

Inside `ReqresAPI`, you can find all methods regarding `GET, POST, PUT, PATCH, DELETE` etc.
So it's really helpful to me. Only thing I need to do is calling methods
with given inputs.
####- resources
Under this folder there are two package; `environments` and `features`
I didn't go so deep for environment, because there were no task specified for me
Under feature file there are valid and invalid test scenarios.
`Also I left some notes on some scenarios`, because nothing worked 
as it should be or supposed to be.


I hope you enjoy, while inspecting my project. Have a good day!

