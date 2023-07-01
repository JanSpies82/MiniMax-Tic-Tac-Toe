# MiniMax Tic-Tac-Toe
This is a simple command line game in Java where the player can play against an agent that uses the [MiniMax algorithm](https://en.wikipedia.org/wiki/Minimax) to determine the best move

## Pre-requisites
- Java 8 or higher
- Linux to run the makefile if you want to compile the source code
## Installation
1. Clone the repository
2. Execute the exe file in the dist folder OR
3. Run the jar file in the root folder using the command `java -jar TicTacToe.jar` in the command line OR
4. Compile the source code using the command `make` in the terminal at the root of the project directory.

## How to play
- When the game is started the player can select whether to play against another player, against the agent or to watch the agent play against itself.
- The player then enters their name
- Finally the player can select who goes first (the player is always X)
- The game starts and the player chooses where to place their X by entering the block number of the position, with the blocks being numbered from 1 to 9 from left to right and top to bottom
- The game continues until either the player or the agent wins or the game is a draw

## How the agent works
The agent uses the MiniMax algorithm to determine the best move. The algorithm works by recursively searching through the game tree to find the best move. The agent assumes that the player will always make the best move for them and the agent will always make the best move for itself. The agent will then choose the move that will lead to the best outcome for itself. The agent will always choose the move that will lead to the fastest win, or the slowest loss. The agent will never choose a move that will lead to a loss. The way this is represented is that for any position, if it is a loss it is awarded a value of -1, if it is a draw it is awarded a value of 0 and if it is a win it is awarded a value of 1. The agent then tries to maximize its score. The agent generates the entire game tree at the start of the game after the starting party has been selected. If it finds itself in a position that is not in its game tree, the tree is regenerated from its current position. Other than the hard value of the outcome of the game, the agent also uses a heuristic defined as the "probability of success" which is the number of success states in the subtree divided by the number of nodes in the subtree. The heuristic is used in the case where all child states have an equal value, to then choose the child which will have more success states in its subtree.