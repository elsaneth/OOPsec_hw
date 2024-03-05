import java.util.Random;
import java.util.Scanner;

public class SuitsKodutoo2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int moves;
        int maxMovesPerParticipant;

        // check total moves/matches input
        do {
            System.out.println("Insert number of 'matches': ");
            notInteger(scan);
            moves = scan.nextInt();
            // check if is positive
            if (moves <= 0) {
                System.out.println("Invalid input. Enter a positive integer.");
            }
        } while (moves <= 0);

        // check max moves per participant input
        do {
            System.out.println("Insert maximum move: ");
            notInteger(scan);
            maxMovesPerParticipant = scan.nextInt();
            // check if is positive integer
            if (maxMovesPerParticipant <= 0) {
                System.out.println("Invalid input. Enter a positive integer.");
            // check if is smaller than moves
            } else if (maxMovesPerParticipant > moves) {
                System.out.println("Invalid input. Maximum move can't exceed the total number of 'matches'");
            }
        } while (maxMovesPerParticipant <= 0 || maxMovesPerParticipant > moves);

        // start new game
        newGame(moves, maxMovesPerParticipant, scan);
    }

    public static void newGame(int moves, int maxMovesPerParticipant, Scanner scan) {
        // save total moves/matches so user can start new game with same value
        int moves1 = moves;
        while (moves > 0) {
            moves = makeHumanMove(moves, maxMovesPerParticipant);
            if (moves > 0) {
                moves = makeComputerMove(moves, maxMovesPerParticipant);
                // it is computer turn so if after computer turn, moves are zero --> computer won
                if (moves == 0) {
                    System.out.println("Computer won!");
                }
            }
            // if moves are not bigger than 0 then moves are 0, and since it is human's turn --> you won
            else {
                System.out.println("You won!");
                break;
            }
        }
        System.out.println();
        int count = 1;
        System.out.println("New game with same rules? If yes then type y.");
        String choice = scan.next();
        // if user wants to play again with same rules
        if (choice.equalsIgnoreCase("y")) {
            count ++;
            System.out.println("GAME NR " + count);
            newGame(moves1, maxMovesPerParticipant, scan);
        }
    }
    public static int makeHumanMove(int moves, int maxMovesPerParticipant){
        Scanner scan = new Scanner(System.in);
        int humanMove;
        do {
            System.out.println("Insert your move: 1) at least 1 2) less than or equal to possible moves " +
                                "3) less than or equal to max moves");
            notInteger(scan);
            humanMove = scan.nextInt();
            // check if is valid move: can't be less than 1, has to be smaller than left moves, has to be smaller than
            // max move per participant
            if (humanMove < 1 || humanMove > moves || humanMove > maxMovesPerParticipant) {
                System.out.println("Invalid move. Please try again.");
            }
        } while (humanMove < 1 || humanMove > moves || humanMove > maxMovesPerParticipant);
        System.out.println("Your move is: " + humanMove);
        System.out.println("Total nr of matches: " + moves);
        System.out.println();
        return  moves - humanMove;
    }
    public static int makeComputerMove(int moves, int maxMovesPerParticipant) {
        Random random = new Random();
        int computerMove;
        // if moves is bigger than max moves per participant then produce random
        if (moves > maxMovesPerParticipant) {
            computerMove = random.nextInt(maxMovesPerParticipant) +1;
        // if it possible to win with one move then computer move is moves, so no moves are left and computer wins
        } else {
            computerMove = moves;
        }
        System.out.println("Computer move: " + computerMove);
        System.out.println("Total nr of matches: " + moves);
        System.out.println();
        return moves - computerMove;
    }

    // used mutltiple times so made it into function
    public static void notInteger(Scanner scan) {
        while (!scan.hasNextInt()) {
            System.out.println("Invalid input. Enter a valid integer.");
            scan.next();
        }
    }
}