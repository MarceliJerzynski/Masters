# Masters

In this project implemented 6 algorithms: 
1. Fibbonacci number - comparing recurvise and dynamic programming approaches
2. Knapsack problem using dynamic programming.
3. Matrix chain ordering problem
4. Dijkstra's algorithm
5. A* search algorithm
6. Bellman–Ford algorithm

Launching application can be started with following command (while being in directory with jar file):\
java -jar Masters-1.0-SNAPSHOT-jar-with-dependencies.jar [MODE] [INPUT]

If no arguments are given, application is going to run all of arguments with hardcoded input.

Following modes are defined:

1. FIBONACCI\
  Following inputs are defined:\
    None - Application will calculate following Fibonacci numbers {10,11,12,13,14,15,16,17,18,19,20} 10000 times and compare efficency between recursive and dynamic programming approach.\
    N1 -  Application will calculate N1 Fibonacci number 10000 and compare efficency between recursive and dynamic programming approach.\
    N1 N2 - Application will calculate Fibonacci numbers in range of {N1, N2} with step of 1 10000 times and compare efficency between recursive and dynamic programming approach\
    N1 N2 N3 - Application will calculate Fibonacci numbers in range of {N1, N2} with step of 1 N3 times and compare efficency between recursive and dynamic programming approach\
    N1 N2 N3 N4 - Application will calculate Fibonacci numbers in range of {N1, N2} with step of N4 N3 times and compare efficency between recursive and dynamic programming approach\
  If any input data will not be integer value, application is going to throw exception\

2. KNAPSACK\
   Following inputs are defined:\
    None - Application will solve knapsack problem with given values:\
      Capacity of knapsack: 7\
      Items:\
        Weight  | Value | Index\
        2       | 3     | 1\
        3       | 1     | 2\
        3       | 3     | 3\
        5       | 4     | 4\
        4       | 2     | 5\
    FILENAME - Application will solve knapsack problem with values defined in file FILENAME\
    Layout of file is following:\
      First line - capacity of backpack\
      Each next line - 3 numbers: Weight, Value, Index\
        Example file content:\
          7\
          1	2	1\
          1	1	2\
          2	3 3\
          3	7	4\
          3	6	5\
    If FILENAME file does not follow layout, application will throw exception\

3. MATRIXES\
   Following inputs are defined:\
    None - Application will solve Matrix chain ordering problem with given values: {5, 10, 15, 5, 30, 10}\
    N1 N2 ... NN - Application will solve Matrix chain ordering problem with given values: {N1 N2 ... NN}\
      Minimal amount of N{i} values is 2.\
    If values N1 N2 ... NN will not be integer values, or amount of integer values will be equal to 1, application will throw exception\

4. DIJKSTRA\
   Following inputs are defined:\
    None - Application will calculate shortest paths between nodes in a graph using Dijktra's algorithms with following adjacency matrix:\
            {null, 2   , 3   , null, null},\
            {null, null, null, 10  , null},\
            {null, null, null, 7   , 13  },\
            {null, null, null, null, 1   },\
            {null, null, null, null, null}\
      Start node index: 0\
      End node index: 4\
     FILENAME - Application will calculate shortest paths between nodes in a graph using Dijktra's algorithms with values defined in FILENAME file.\
     Layout of file is following:\
      First line - start node index\
      Second line - end node index\
      Next lines - adjacency matrix. If there is no edge between nodes, application expects "null" text\
      Example file content:\
        0\
        3\
        null	1		  null	null	1		  null\
        1		  null	1		  null	1		  null\
        null	1		  null	1		  null	null\
        null	null	1		  null	1		  1	\
        1		  1		  null	1		  null	null\
        null	null	null	1		  null	null\
      If FILENAME file does not follow layout, application will throw exception\

5. ASTAR\
   None - Application will calculate shortest paths between nodes in a graph using A* algorithms with following adjacency matrix:\
            {null, 2   , null, null, 3   },\
            {null, null, 6   , null, null},\
            {null, null, null, 7   , null},\
            {null, null, null, null, null},\
            {null, null, null, 8   , null}\
      Start node index: 0\
      End node index: 4\
      Heuristics: {13, 12, 1, 0, 11}\
   FILENAME - Application will calculate shortest paths between nodes in a graph using A* algorithms with values defined in FILENAME file.\
    Layout of file is following:\
      First line - start node index\
      Second line - end node index\
      Next line - Values of heuristics for each node\
      Next lines - adjacency matrix. If there is no edge between nodes, application expects "null" text\
      Example file content:\
        0\
        3\
        1 	2		3		4		5		6\
        null	1		  null	null	1		  null\
        1		  null	1		  null	1		  null\
        null	1		  null	1		  null	null\
        null	null	1		  null	1		  1	\
        1		  1		  null	1		  null	null\
        null	null	null	1		  null	null\

      If FILENAME file does not follow layout, application will throw exception\
 6. BELLMANFORD\
       Following inputs are defined:\
       None - Application will calculate shortest paths between nodes in a graph using Bellman-Ford algorithms with following adjacency matrix:\
            {null, 6   , 3   , null, null},\
            {null, null, null, 10  , null},\
            {null, null, null, null, 4   },\
            {null, null, null, null, null},\
            {null, -3  , null, null, null}\
          Start node index: 0\
          End node index: 3\
       FILENAME - Application will calculate shortest paths between nodes in a graph using Bellman-Ford algorithms with values defined in FILENAME file.\
        Layout of file is following:\
        First line - start node index\
        Second line - end node index\
        Next lines - adjacency matrix. If there is no edge between nodes, application expects "null" text\
        Example file content:\
        0\
        3\
        null	1		  null	null	1		  null\
        1		  null	1		  null	1		  null\
        null	1		  null	1		  null	null\
        null	null	1		  null	1		  1	\
        1		  1		  null	1		  null	null\
        null	null	null	1		  null	null\
      If FILENAME file does not follow layout, application will throw exception\
