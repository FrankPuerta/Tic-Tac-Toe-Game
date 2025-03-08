# Tic-Tac-Toe Game (Java)

## Overview:
This is a console-based Tic-Tac-Toe game written in Java, where a player competes against a strategic computer AI. The game ensures smooth gameplay by handling invalid inputs and checking for valid moves.

---------------------------------------

## How to Play:

1. Run the Java program.

2. The player will be prompted to enter a move (1-9) corresponding to a position on the board.

3. The AI will respond with a strategic move.

The game alternates turns until a player wins or the board is full (resulting in a draw).

---------------------------------------

## Rules:

The player always plays 'X'.

The computer plays 'O'.

The player and computer take turns making a move on a 3x3 grid.

The first player to get three marks in a row (horizontal, vertical, or diagonal) wins.

If all spaces are filled and no winner is found, the game results in a draw.

---------------------------------------

## Features implemented:

* User Input Handling: Prevents crashes by catching invalid inputs.
* Move Validation: Ensures moves are within range and prevents overwriting occupied spots.
* Smart computer choices (AI): The computer strategically selects moves to win or block the player.
* Game Status Check: Continuously checks for a winner or a draw.
* Board Updates in Real-Time: The board refreshes after every move.
