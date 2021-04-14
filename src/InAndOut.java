import java.util.Scanner;

/**
 * @program: BlackJack
 * @description: This class represent the input and output
 * @author: Siyu Hou, Kicho Yu, Chance Lamberth, Shuwei Wang
 * @create: 2021-03-16 23:39
 **/
public class InAndOut {

    /**
     * get the user input
     *
     * @return user decision
     */
    static int getInput() {
        int decision = -1;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Hit or Stand");
        String choice = keyboard.nextLine();
        System.out.println("\n");
        try {
            if (choice.equalsIgnoreCase("hit")) {
                decision = 1;
            } else if (choice.equalsIgnoreCase("stand")) {
                decision = 0;
            }
        } catch (Exception e) {
            System.out.println("Not a valid input.");
        }
        return decision;
    }

    /**
     * display the player card
     *
     * @param c object Card c
     */
    static void displayPlayerCard(Card c) {
        System.out.println("Player got a card: " + c.getNameAsStr() + " of " + c.getSuitAsStr() +
                " " + c.getUnicode());
    }

    /**
     * display the dealer card
     *
     * @param c object Card c
     */
    static void displayDealerCard(Card c) {
        System.out.println("Dealer got a card: " + c.getNameAsStr() + " of " + c.getSuitAsStr() +
                " " + c.getUnicode());
    }

    /**
     * display the player score
     *
     * @param game The main game
     */
    static void displayPlayerScore(BlackJack game) {
        int scoreOfPlayer = game.countScoreOfHand(game.playerHand.hand);
        System.out.println("Player's score: " + scoreOfPlayer);
    }

    /**
     * display the dealer score
     *
     * @param game the main game
     */
    static void displayDealerScore(BlackJack game) {
        int scoreOfPlayer = game.countScoreOfHand(game.dealerHand.hand);
        System.out.println("Dealer's score: " + scoreOfPlayer);
    }

}
