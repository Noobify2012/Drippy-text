About/Overview: This is my implementation of the Dungeon Model. A player enters the dungeon with
nothing but their empty treasure sack. The player then navigates the dungeon from their start point
to the end. They can either start at a randomly selected point and navigate to a point at least 5
moves away or start at cave 0 and move through all the nodes in the dungeon. Dungeons are
represented as interconnected nodes where position row 0, column 0 also known as index 0 is in the
top left-hand corner and the index increases from left to right in the increasing x direction of the
Cartesian plane(columns). It then drops to the next y position down and all the way to the left like
a carriage return to do the same process for the second row. An example 3 row by 3 columns graph is
below:
        columns
    0       1       2
r
o   3       4       5
w
s   6       7       8

The program will print the final list of edges to the screen after it has generated which will have
the indexes of both caves connected by a bidirectional arrow(18<========>23).

List of features. List all features that are present in your program.
- When the program starts the user is greeted and presented with the criteria for entering proper
command line arguments for starting the dungeon model. The first level of error checking starts
there. The input parser ensures that the user enters at least true or false for the wrapping
requirement and positive integers for rows, columns, interconnectivity, and percentage of caves with
treasure.

-The user has no real interaction with any constructors. The startup parser passes all of those
arguments, once validated at the first level, to the constructor. From there the dungeon and player
are automatically built and run.

-In the dungeon implementation, it can be set to either run automatically as a breadth first path,
meaning from start point to end point as efficiently as possible. Or it can run as a depth first
path starting at node 0 and go to every node. Player navigates the dungeon via the unique index
value of each of the caves or tunnels.

-As the player moves through the dungeon, the program will identify which caves have treasure in
them, display the treasure in the cave and then automatically pick it up before the player moves to
the next cave.

-One unique design feature is the treasure implementation which is an enum and factory pattern all
in one class. This makes the code slightly more compact as a single stop for construction and
validation of treasure. This allows the program to easily identify treasure objects in the players
treasure list and cleanly present them when called.

-Caves and edges have a lot of detail which will help for expanding capability going forward.
Caves and tunnels know who they are neighbors with as well as where they are in the dungeon. Edges
contain the two caves associated with them. They also know which direction to travel to get from
one cave to the other. In the next implementation, this will be used so that the player takes a
direction of travel rather than an index to move through the dungeon. Caves also have a treasure
list in them that if selected by the treasure placement method stores the randomly generated
treasure.

-The Dungeon implementation will let the user try and build the smallest possible graph by allowing
them to build any graph with at least 6 nodes. It may not be able to construct it the first time and
will throw an error if it cannot build a correct start and end point based on the randomly selected
start point.

-The constructor for the dungeon will also check for over interconnectivity, ensures there can't be
more than 100% treasure in caves.


How To Run. Instructions to run the program should include the following:

How to run the jar file
There are no command lind arguments that are required to run the jar file other than
"java -jar Project3_Dungeon_Model.jar"

How to Use the Program. Instructions on how to use functionality in your program. if interactive, how to interact with your program? Pay particular attention to the parts that are not part of the example runs that you provided.
- The user only needs to start the jar file as described above and when prompted enter the dungeon
construction requirements. A boolean value for wrapping or non(true for wrapping, false for non),
a positive integer for the number of rows, a positive integer for the number of columns, zero or
greater for the level of interconnectivity, and an integer between 0 and 100 for the percentage of
caves with treasure.

- The program will do the rest.

Description of Examples. 

Run 1 -- Run 1.txt:
1. Welcome Message printed to user with example for how to enter a valid dungeon request.
2. Generates the dungeon based on the parameters passed(wrapping, 10 row by 10 column dungeon, level
0 interconnectivity, and 20% treasure).
3. It then prints out the final edge list after Kruskal's has been completed. It then announces
where the player is entering the dungeon(ie its randomly selected start point) and what the route
they will follow through the dungeon is.
4. The player then navigates the dungeon. At each step along the way the player's status is printed
out which includes what cave they are in, identified by cave index, what they currently have in
their treasure bag, what direction they can move from that cave(North, South, East, and/or West),
and what treasure is in that cave that they will pick up.
5. After the player has completed their route through the cave, the program prints a final message
announcing the completion of navigating the dungeon and presents a final list of what the player
collected for treasure along the way.


Run 2 -- Run2.txt:
1. Welcome Message printed to user with example for how to enter a valid dungeon request.
2. Generates the dungeon based on the parameters passed(non- wrapping, 10 row by 10 column dungeon,
 level 0 interconnectivity, and 20% treasure).
3. It then prints out the final edge list after Kruskal's has been completed. It then announces
where the player is entering the dungeon(ie its randomly selected start point) and what the route
they will follow through the dungeon is.
4. The player then navigates the dungeon. At each step along the way the player's status is printed
out which includes what cave they are in, identified by cave index, what they currently have in
their treasure bag, what direction they can move from that cave(North, South, East, and/or West),
and what treasure is in that cave that they will pick up.
5. After the player has completed their route through the cave, the program prints a final message
announcing the completion of navigating the dungeon and presents a final list of what the player
collected for treasure along the way.

Run 3 -- Run3.txt:
1. Welcome Message printed to user with example for how to enter a valid dungeon request.
2. Generates the dungeon based on the parameters passed(wrapping, 10 row by 10 column dungeon,
 level 5 interconnectivity, and 20% treasure).
3. It then prints out the final edge list after Kruskal's has been completed. It then announces
where the player is entering the dungeon(ie its randomly selected start point) and what the route
they will follow through the dungeon is.
4. This run has the player navigating the entire dungeon and visiting every cave and tunnel in the
dungeon. At each step along the way the player's status is printed out which includes what cave they
are in, identified by cave index, what they currently have in their treasure bag, what direction
they can move from that cave(North, South, East, and/or West),and what treasure is in that cave that
 they will pick up.
5. After the player has completed their route through the cave, the program prints a final message
announcing the completion of navigating the dungeon and presents a final list of what the player
collected for treasure along the way.

Run 4 -- Run4.txt:
1. Welcome Message printed to user with example for how to enter a valid dungeon request.
2. Generates the dungeon based on the parameters passed(non-wrapping, 5 row by 5 column dungeon,
 level 2 interconnectivity, and 30% treasure).
3. It then prints out the final edge list after Kruskal's has been completed. It then announces
where the player is entering the dungeon(ie its randomly selected start point) and what the route
they will follow through the dungeon is.
4. This run has the player navigating the entire dungeon and visiting every cave and tunnel in the
dungeon. At each step along the way the player's status is printed out which includes what cave they
are in, identified by cave index, what they currently have in their treasure bag, what direction
they can move from that cave(North, South, East, and/or West),and what treasure is in that cave that
 they will pick up.
5. After the player has completed their route through the cave, the program prints a final message
announcing the completion of navigating the dungeon and presents a final list of what the player
collected for treasure along the way.

Run 5 -- Run 5.txt:
1. Welcome Message printed to user with example for how to enter a valid dungeon request.
2. Generates the dungeon based on the parameters passed(wrapping, 6 row by 8 column dungeon, level
8 interconnectivity, and 100% treasure).
3. It then prints out the final edge list after Kruskal's has been completed. It then announces
where the player is entering the dungeon(ie its randomly selected start point) and what the route
they will follow through the dungeon is.
4. The player then navigates the dungeon. At each step along the way the player's status is printed
out which includes what cave they are in, identified by cave index, what they currently have in
their treasure bag, what direction they can move from that cave(North, South, East, and/or West),
and what treasure is in that cave that they will pick up.
5. After the player has completed their route through the cave, the program prints a final message
announcing the completion of navigating the dungeon and presents a final list of what the player
collected for treasure along the way.


Design/Model Changes. It is important to document what changes that you have made from earlier designs. Why were those changes required? You can write these changes in terms of version if you wish.

- I had to change some fields in both the cave and dungeon classes in order to help out with some
functionality. This includes adding a set field in the cave class in order to do help with Kruskal's
algorithm.

-In order to do the depth first search, I had to add a helper class called graph for doing most of
the work. This code was found on geeks for geeks and adapted for my implementation. Citations are
in line.

- lots of private methods were added to handle all the object passing and maintenance.


Assumptions. List what assumptions you made during program development and implementation. Be sure
that these do not conflict with the requirements of the project.

- For the first model version where the driver class runs everything the player will navigate the
dungeon via determined paths. I executed this by using an index look up based on the caves along
shared edges.

- A player will always want to pick up all treasure and doesn't have a limit to how much treasure
they can carry.


Limitations. Limitations of your program if any. This should include any requirements that were not
implemented or were not working correctly (including something that might sometimes work).

-While the program can run through the dungeon as either a breadth first(the shortest path from
start point to end point) or depth first(start at node 0 and run through all the nodes), that has to
be specifically configured in the code. One has to be turned on and the other turned off.

-The player has a move method and there are directions in the enum in the game which are used to
determine direction from one cave to another along an edge object but that is not how the player
currently navigates. In its current implementation, the player moves via a list of indexes instead
of a list of directions. This will be an improved feature that is fixed in the next project
deliverable.

-The program can keep track of the players treasure that they pick up but presents it an ugly
manner. Instead of accumulating it and saying how many of each type of treasure, it just appends
each piece of treasure and prints a large ugly this. This will be improved in the next version if
there is time.

- Due to almost every method being private or package private it is hard to test the functionality
without the testing being done in the same class or package.


Citations. 
I found an example of how to do depth first and breadth first searches on geeks for geeks and
adapted that code for my needs. Madhira Datta walked me through how to calculate the maximum number
of edges in a graph for wrapping and non wrapping dungeons. I also found the method to condense my
treasure enum and factory into one class and adapted that code from
https://connected2know.com/programming/java-factory-pattern/. All citations have in line citations.