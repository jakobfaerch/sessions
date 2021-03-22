# User sessions playground

This is a play repository for doing data related excercises.
Below is a couple of ideas for quests.

### Db console
For iterating faster, once the application is running, there's a web console to run SQL and see results available at
http://localhost:8080/h2-console
you need to input the JDBC url `jdbc:h2:file:~/db/sessions` and login without entering a password

## Group sessions by user

Show  the total number of sessions for each user.

### Scaling

Estimate how much the dataset could grow before the method implemented would start falling apart.
What would ways to make it scale beyond that be?

## Calculate session average length

Output a number saying what the length of the average session is.

## Find highest number of concurrent sessions

Find the highest number of overlapping sessions. 

Optionally find the point in time it happened.

Optionally output the highest number of concurrent sessions of each hour.
