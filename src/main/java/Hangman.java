import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("/Users/rosaTome/Desktop/words.txt"));
        Scanner keyboard = new Scanner(System.in);

        List<String> words = new ArrayList<>();
        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }

//      picks random word from words.txt
        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));
        System.out.println(word);

//      List of characters
        List<Character> playerGuesses = new ArrayList<>();

//      if printWordState methods returns as true, then it will break out of while loop and user wins the game
        int wrongCount = 0;
        while (true) {

//            hangman - 7 lives
            if (wrongCount >= 2) {
                System.out.println("   -------");
                System.out.println("   |/     |");
            } else if (wrongCount >= 1) {
                System.out.println("   |");
                System.out.println("   |");
                System.out.println("   |");
                System.out.println("   |");
                System.out.println("   |");
                System.out.println("   |");
                System.out.println("___|___");
            } else {
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("___|___");
            }

            if (wrongCount >= 3) {
                System.out.println("   |     (_)");
            } else if (wrongCount >= 2) {
                System.out.println("   |");
            }

            if (wrongCount >= 4) {
                System.out.print("   |     \\");
                if (wrongCount >= 5) {
                    System.out.print("|");
                    if (wrongCount >= 6) {
                        System.out.println("/");
                    } else {
                        System.out.println();
                    }
                } else {
                    System.out.println();
                }
            } else if (wrongCount >= 3) {
                System.out.println("   |");
            }

            if (wrongCount >= 5) {
                System.out.println("   |      |");
            } else if (wrongCount >= 4) {
                System.out.println("   |");
            }

            if (wrongCount >= 7) {
                System.out.println("   |     / \\");
            } else if (wrongCount >= 6) {
                System.out.println("   |     /");
            } else if (wrongCount >= 5) {
                System.out.println("   |");
            }

            if (wrongCount >= 2) {
                System.out.println("   |");
                System.out.println("   |");
                System.out.println("   |");
                System.out.println("   |");
                System.out.println("   |");
                System.out.println("___|___");
            }

            System.out.println();

            //      Look at the word and put dashes except where the player has guessed thw letter correctly
            printWordState(word, playerGuesses);

//          if guess not contained, wrong count implemented
            if (!getPlayerGuess(keyboard, playerGuesses, word)) {
                wrongCount++;
            }

            if (printWordState(word, playerGuesses)) {
                System.out.println("You win!");
                break;
            }

            if (wrongCount >= 7) {
                System.out.println("Game over! The word was: " + word);
            }

            System.out.println("Please enter your guess for the word:");
            if (keyboard.nextLine().equals(word)) {
                System.out.println("You win!");
                break;
            } else {
                System.out.println("Nope! Try again.");
            }
        }
    }

    private static boolean getPlayerGuess(Scanner keyboard, List<Character> playerGuesses, String word) {
        System.out.println();
        System.out.println("Please enter a letter:");
//      gets the next string input from the user
        String letterGuess = keyboard.nextLine();
//      This will get the first character inputted by user and then added to the list
        playerGuesses.add(letterGuess.charAt(0));

        return word.contains(letterGuess);
    }

//    prints out the current state of the players guesses in relation to the word
    private static boolean printWordState (String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            } else {
                System.out.print("_");
            }
        }

        System.out.println();
//        returning true if the player has guessed all the letters in thw word
        return (word.length() == correctCount);
    }
}


