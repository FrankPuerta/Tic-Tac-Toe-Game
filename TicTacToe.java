import java.util.Scanner;

public class TicTacToe {
    // The Tic-Tac-Toe board represented as a 2D array
    private static char[][] board = {
            {'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'}
    };

    // Variables to store the player and computer symbols
    private static char player = 'X';
    private static char computer = 'O';

    // Scanner for user input
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Boolean to track if the game has been won
        boolean gameWon = false;

        // Counter to track the number of moves (used to check for draws)
        int moves = 0;

        // Game loop that runs until there is a winner or the board is full (9 moves)
        while (!gameWon && moves < 9) {
            // Print the current state of the board
            printBoard();

            // If it's the player's turn (even moves), ask for input
            if (moves % 2 == 0) {
                playerMove();
            }
            // If it's the computer's turn (odd moves), let AI make a move
            else {
                computerMove();
            }

            // Increment move count after each turn
            moves++;

            // Check if the game has been won
            gameWon = checkWinner();
        }

        // Print the final board state after the game ends
        printBoard();

        // Display the winner or declare a draw
        if (gameWon) {
            System.out.println("Game Over! " + ((moves % 2 == 1) ? "Player" : "Computer") + " wins!");
        } else {
            System.out.println("It's a draw!");
        }

        // Close the scanner to avoid memory leaks
        scanner.close();
    }

    // Method to print the current state of the board
    private static void printBoard() {
        System.out.println();

        // Loop through each row of the board
        for (char[] row : board) {
            // Loop through each cell in the row
            for (char cell : row) {
                // Print the cell value
                System.out.print(cell + " ");
            }
            // Move to the next line after printing a row
            System.out.println();
        }
        System.out.println();
    }

    // Method for handling the player's move
    private static void playerMove() {
        int move;

        // Keep asking for a valid move until the player enters a correct input
        while (true) {
            System.out.print("Your turn. Select a spot (1-9): ");

            try {
                // Read player's move
                move = scanner.nextInt();

                // If the move is valid, place 'X' and break the loop
                if (makeMove(move, player)) break;

                // If move is invalid, notify the user
                System.out.println("Invalid move! Try again.");
            }
            // Handle invalid input (e.g., letters instead of numbers)
            catch (Exception e) {
                // Clear the invalid input
                scanner.next();
                // Notify the player of the invalid input
                System.out.println("Invalid input! Please enter a number between 1 and 9.");
            }
        }
    }

    // Method for handling the computer's move using a strategy
    private static void computerMove() {
        System.out.println("Computer's turn...");

        // Step 1: Check if the computer can win this turn
        for (int i = 1; i <= 9; i++) {
            if (makeMove(i, computer)) {
                if (checkWinner()) return;
                board[(i - 1) / 3][(i - 1) % 3] = (char) ('0' + i); // Undo move
            }
        }

        // Step 2: Check if the player is about to win and block them
        for (int i = 1; i <= 9; i++) {
            if (makeMove(i, player)) {
                if (checkWinner()) {
                    makeMove(i, computer); // Block the player's winning move
                    return;
                }
                board[(i - 1) / 3][(i - 1) % 3] = (char) ('0' + i); // Undo move
            }
        }

        // Step 3: Take the center if available
        if (makeMove(5, computer)) return;

        // Step 4: Try to take a corner
        int[] corners = {1, 3, 7, 9};
        for (int move : corners) {
            if (makeMove(move, computer)) return;
        }

        // Step 5: If no better options, take a side
        int[] sides = {2, 4, 6, 8};
        for (int move : sides) {
            if (makeMove(move, computer)) return;
        }
    }

    // Method to place a move on the board if valid
    private static boolean makeMove(int move, char symbol) {
        // Ensure that only 'X' or 'O' can be placed
        if (symbol != 'X' && symbol != 'O') {
            System.out.println("Error: Invalid symbol '" + symbol + "'. Only 'X' or 'O' is allowed.");
            return false;
        }

        // Convert move number (1-9) into row and column indices
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;

        // Check if the selected position is empty
        if (move >= 1 && move <= 9 && board[row][col] != 'X' && board[row][col] != 'O') {
            // Place 'X' or 'O' in the chosen position
            board[row][col] = symbol;
            return true; // Move was successful
        }

        // Return false if the move is invalid
        return false;
    }

    // Method to check if there is a winner
    private static boolean checkWinner() {
        // Loop through each row and column to check for a win
        for (int i = 0; i < 3; i++) {
            // Check if all three values in a row are the same
            if ((board[i][0] == board[i][1] && board[i][1] == board[i][2]) ||
                    (board[0][i] == board[1][i] && board[1][i] == board[2][i])) {
                return true;
            }
        }

        // Check both diagonals for a win
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0])) {
            return true;
        }

        // Return false if no winner is found
        return false;
    }
}

