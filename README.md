# User sessions playground

This is a play repository for doing data related excercises.
Below is a couple of ideas for quests.

### Db console
For iterating faster, once the application is running, there's a web console to run SQL and see results available at
http://localhost:8080/h2-console
you need to input the JDBC url `jdbc:h2:file:~/db/sessions` and login without entering a password

## Group sessions by user

Produce a new table, `useractivity` showing the total number of sessions for each user.
Make the table's count automatically reflect when new sessions are added to the `sessions` table.

## Calculate session average length

Output a number saying what the length of the average session is.

## Find highest number of concurrent sessions

Find the highest number of overlapping sessions. 

Optionally find the point in time it happened.

Optionally output the highest number of concurrent sessions of each hour.
