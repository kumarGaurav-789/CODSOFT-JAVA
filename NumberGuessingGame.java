import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static int playGame() {
        int number = rand.nextInt(100) + 1; // 1 to 100
        int maxAttempts = 7;
        int attempts = 0;

        System.out.println("\nI have selected a number between 1 and 100.");
        System.out.println("You have " + maxAttempts + " attempts.");

        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            int guess = sc.nextInt();
            attempts++;

            if (guess == number) {
                System.out.println("Correct! You guessed it in " + attempts + " attempts.");
                return maxAttempts - attempts + 1; // score
            } 
            else if (guess < number) {
                System.out.println("Too low!");
            } 
            else {
                System.out.println("Too high!");
            }
        }

        System.out.println("You lost! The correct number was: " + number);
        return 0;
    }

    public static void main(String[] args) {
        int totalScore = 0;
        String choice;

        do {
            totalScore += playGame();
            System.out.print("\nDo you want to play again? (yes/no): ");
            choice = sc.next();
        } while (choice.equalsIgnoreCase("yes"));

        System.out.println("\nFinal Score: " + totalScore);
        System.out.println("Thanks for playing!");
    }
}
