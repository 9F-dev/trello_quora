# trello_quora
A website similar to quora.

The trello_quora project aims to develop REST API endpoints of various functionalities required for a website similar to Quora from scratch. In order to observe the functionality of the endpoints, you will use the Swagger user interface and store the data in the PostgreSQL database. Also, the project has to be implemented using Java Persistence API (JPA).


**Git Best Practices**

•	Commit often
•	Make small, incremental commits
•	Write good commit messages
•	Make sure your code works before committing it

**Using GitHub with IntelliJ in GUI**
    **Pull - Commit - Push**

1. Init git

2. Right click the project name --> Git --> Repository --> Pull

3. Choose your branch and the master and click pull button from the dialogue box

4. Right click the project name --> Git --> Commit (or commit directory)

5. Uncheck . idea files and Commit

6. Right click the project name --> Git --> Repository --> Push

7. Go to your GitHub profile, refresh the page and check your branch

8. Create pull request

You can include verifiers for your pull request. You can either wait for the moderator/ team leader to merge it with master or if you are very much sure of functionality, you can also merge. 
It's always good to pull and then start working, and before pushing too, do pull to check for any conflicts.

**Using GitHub by command line**
(some useful commands)

1. git init

2. git add <filename> or git add .
  
3. git pull origin master

4. git commit -m "commit message"

5. git push

Frequently used git commands are,

git fetch

git status

git log

To shift to a branch: git checkout <branchname>

To roll back a commit: git revert <commit-ID / SHA>

Always create branches to implement different functionalities and then merge it with the master.


**Setup Instructions**
(For Windows)

1. Install IntelliJ

2. Install Postgres (pgAdmin4)

3. Install JDK (1.8) from Java SE development

4. Install Maven

5. Setup environmental variables for JDK, and Maven

6. Download PostgreSQL JDBC 4.2 driver and add it as external jar file to the IntelliJ project

7. Implement the project

8. Build it using the mvn commands shown below

9. Run the application/project and open it on the localhost


**Building the project**

Commands to be used in terminal are;

mvn clean install -DskipTests

mvn clean install -Psetup


**To run the application in localhost**

localhost:8081/api/swagger-ui.html

[Note: Here, the port 8081 is used to run the localhost]



