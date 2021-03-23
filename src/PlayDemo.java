import java.util.ArrayList;
import java.util.Scanner;

/**
 * @program: Card
 * @description:
 * @author: Siyu Hou, Kicho Yu
 * @create: 2021-03-23 02:54
 **/

    /*
    // What parameter do we need? Hand? Player?
    // score should be fixed
    // Then we can use this method.
    private static boolean isBusted(Hand handOfEitherOne) {
        if (score > WINNING_SCORE) {
            // We need to adjust the value of Ace
            if (Hand.contains(Ace)) {
                score -= 10;
                return false;
            }
            return true;
        }
        return true;
    }
     */


    /*
    13 of 4 = king of clubs.
    Working on a way to better display the cards.
    Cards need to have a value and not use the value in the switch statement int the card class.
    Suits need to have a suit name and ot use the value in the switch statement in the card class.
    working on "bust"

    Kicho
    1. We need to implement the ace case. Aces are worth 1 or 11, whichever makes a better hand.
    [Done] 2. We need to show 2 cards of a player when the game starts, not 1.
    [Done] 3. We need to show 1 card of a DEALER when the game starts. Then PLAYER starts the game as #2 says.
    [Done] 4. Shuwei's idea: We need to use Hand.java.
       This is related to removeCard() in hit(); removeCard should be from Deck.java but not Hand.java.
    5. We are not using getWinner() much. Instead, we are re-inventing the wheel.
     */
public class PlayDemo {
    private static final int WINNING_SCORE = 21;
    private static final int DEALER_MAX = 17;

    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        Deck deck = new Deck();
        deck.shuffleCard();

        Hand playerHand = new Hand(new ArrayList<>());
        Hand computerHand = new Hand(new ArrayList<>());

        Card initCard1 = game.hit(deck);
        Card initCard2 = game.hit(deck);
        System.out.println("Player's cards are " + initCard1.getNameAsStr() + " of " + initCard1.getSuitAsStr() +
                " and " + initCard2.getNameAsStr() + " of " + initCard2.getSuitAsStr());
        System.out.println("Player's score: " + game.getPlayerScore() + "\n");

        // Dealer's initial cards
        // add 1, since the second card is hidden
        game.nextPlayer();
        Card pcInitCard1 = game.hit(deck);
        System.out.println("Computer's cards are " + pcInitCard1.getNameAsStr() + " of " + pcInitCard1.getSuitAsStr() +
                " and another is hidden");
        //game.setComputerScore(game.cardValue(pcInitCard1));
        System.out.println("Computer's score: " + game.getComputerScore() + "\n");
        computerHand.addCard(deck.removeCard());
        computerHand.addCard(deck.removeCard());  // Kicho: why do we need this?

        Scanner keyboard = new Scanner(System.in);

//        // Player got blackjack and the game is over.
//        if (game.getPlayerScore() == WINNING_SCORE){
//            System.out.println("Game is over.\n\nCongrats! You got blackjack");
//            System.exit(0);
//        }

        game.nextPlayer();
        while (!game.isBusted() && game.getPlayerScore() < WINNING_SCORE) {
            System.out.println("Hit or Stand");
            String choice = keyboard.nextLine();
            System.out.println();
            try {
                if (choice.equalsIgnoreCase("hit")) {
                    Card hitCard = game.hit(deck);
                    playerHand.addCard(hitCard);
                    //game.playerScore += game.cardValue(hitCard);
                    System.out.println("You got " + hitCard.getNameAsStr() + " of " + hitCard.getSuitAsStr());
                    System.out.println("Player's score: " + game.getPlayerScore());
                } else if (choice.equalsIgnoreCase("stand")) {
                    game.stand();
                    break;
                }
            } catch (Exception e) {
                System.out.println("Not a valid input.");
            }
        }

        // Player got blackjack and the game is over.
        if (game.getPlayerScore() == WINNING_SCORE) {
            System.out.println("\n\n****Congrats! You got blackjack!****");
            System.exit(0);
        }

        // It's now the dealer's turn.
        if (game.isBusted()) {
            System.out.println("\n****Busted!****\n");
            System.out.print(game.nextPlayer() + " wins!");
        } else {
            // add player's second card here.
            Card pcInitCard2 = game.hit(deck);
            computerHand.addCard(pcInitCard2);
            System.out.println("Hidden card is " + pcInitCard2.getNameAsStr() + " of " + pcInitCard2.getSuitAsStr());
            System.out.println("Computer's score is: " + game.getComputerScore());

            while (game.getComputerScore() < DEALER_MAX) {
                Card newCard = game.hit(deck);
                System.out.println("\nComputer got " + newCard.getNameAsStr() + " of " + newCard.getSuitAsStr());
                System.out.println("Computer's score: " + game.getComputerScore());
            }
            if (game.getWinner() == null) {
                System.out.println("Game is a tie! No winner.");
            } else if (game.getComputerScore() > WINNING_SCORE) {
                System.out.println(game.getPlayer() + " is busted!\n");
                System.out.println(game.nextPlayer()  + " wins!");
            } else System.out.println("\n" + game.getWinner()  + " wins!");
        }

/*
         Pending checks:



        //Solved!
        Error when both scores are less than 21 and computer has score > than players -- result should not be a tie.
        3. Player draws a card and total is now 21 - game should end.
        1. Error when computer has a ace and hidden card is a King, Queen, etc.. value = 11 instead of 21.

         2. Error when computer score is greater than 17 and less than Bust value on initial turn.
         computer adds more cards.
             cards are King of hearts and 10 of diamond = 20 -> computer should hold.
*/
    }
}
