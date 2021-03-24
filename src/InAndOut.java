import java.util.Scanner;

public class InAndOut {



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

    static void displayPlayerCard(Card c) {
        System.out.println("Player got a card: " + c.getNameAsStr() + " of " + c.getSuitAsStr());
    }

    static void displayPlayerScore(BlackJack game) {
        int scoreOfPlayer = game.countScoreOfHand(game.playerHand.hand);
        System.out.println("Player's score: " + scoreOfPlayer);
    }

    static void displayDealerScore(BlackJack game) {
        int scoreOfPlayer = game.countScoreOfHand(game.dealerHand.hand);
        System.out.println("Dealer's score: " + scoreOfPlayer);
    }

    static void displayDealerCard(Card c) {
        System.out.println("Dealer got a card: " + c.getNameAsStr() + " of " + c.getSuitAsStr());
    }

}
