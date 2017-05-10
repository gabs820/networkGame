# networkGame
Mass Chaos: The Game




Task list 4/12/17
Shae - I have been researching the player movement mechanics.  By Friday, I will have the ball moving using the arrow keys.
Gabby - Research connecting players into a shared game arena.
Sofya - Create shooting mechanism, begin work on displaying shooting over server.


Future To Do
Create counters for a player's score.
Create a timer.

Archived tasks

4/5/17
Created MassChaosClient.java
Created MassChaosServer.java
Created MassChaosClientListener.java
Created MassChaosClientHandler.java
Created Game.java
Created FrameDemo.java


DOCUMENTATION

***Compiling***
1. To host a server:
  -Compile and run MassChaosServer.java using the following commands in command prompt:
  -"> javac MassChaosServer.java"
  -"> java MassChaosServer"
2. To connect as a client:
  -Compile and run MassChaosClient.java using the following commands:
    "> javac MassChaosClient.java"
    "> java MassChaosClient"
    

***Overview***
Mass Chaos is a multiplayer game that connects players through a network service. The goal of the game is to remain alive as long as possible, along with eliminating as many players as possible. Currently, there is no limit on the amount of players that can connect.

This game is coded in Java. 
Requires Java SDK installed to compile/execute game


***Server***
Compile MassChaosServer.java and run MassChaosServer.class
Server will open up the Game file and run that for each client that connects

***Client***
Compile MassChaosClient.java and run MassChaosClient.class

//timer
//enemies eliminated

***Player Movement***
To test player movement, compile MoveBall.java and MyKeyListener.java
Run MoveBall.class with java MoveBall
Use the arrow keys to move
Use the spacebar to shoot a ball in the direction of your movement
//each has their own connection etc
