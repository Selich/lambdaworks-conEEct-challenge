# LambdaWorks Connect4 challenge ( Scala ) :star:


##### How to run: 
1.  Run the sbt:

         sbt run
         
2. Choose an option in the menu ( defaults are shown ):

        Number of rows: 6
        Number of columns: 6
        Choose your symbol: o
        Choose bots symbol: x
        
3. Enjoy!


#### Languaged used:
First time writting Scala so forgive me for any mistakes based on conventions, but it was fun using it for this project.
    
#### Resources used:
   Alpha-beta pruning [(link)](https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-4-alpha-beta-pruning/)

   It's a type of minmax algorithm that tries and finds all available moves and outcomes from a given move. 

   If the leafs in the decision tree return a FourInLine state, then we return the winning score and compare it with the other posible outcomes.

   The move with the best calculated score is then executed.

   
   I must admit, when there was something I didn't know how to write in Scala,I looked at [this](https://github.com/kristofa/connect4) implementation on Github. :innocent:
   
##### Additional notes:
Was really short on time. Had plans to open a socket communication with Scala app by importing Java libraries and hosting a React app as a client.

Also, with limited amount of time I at least got familiar with Scala

All the best, and thanks for the challenge!

    
   
