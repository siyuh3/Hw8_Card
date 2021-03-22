
import java.util.Scanner;

/**
 * @program: BlackJack
 * @description: This class represents the BlackJake game play
 * @author: Siyu Hou, Kicho Yu
 * @create: 2021-03-20 23:39
 **/

// this is the class where we implement the blackjack game.
/*
2 players: computer vs human.
methods:
hit -- add a card to sum
stand -- stop
 */

public class BlackJack extends Deck {
    private Player player;
    private int playerScore;
    private int computerScore;
    private static final int WINNING_SCORE = 21;
    private static final int DEALER_MAX = 17;
    private static final int CARDS_IN_HAND_IN_THE_BEGINNING = 2;

    /**
     * Default constructor.
     */
    public BlackJack() {
        this.player = Player.PLAYER;
        this.playerScore = 0;
        this.computerScore = 0;

    }

//    /**
//     * Constructor that takes a player as an argument.
//     *
//     */

//     i dont think we need this.
//    public BlackJack(String player1){
//        this.player = Player.PLAYER;
//        this.playerScore = 0;
//        this.computerScore = 0;
//    }


    public Player getPlayer() {
        return player;
    }

    /**
     * @return
     */
    public Player nextPlayer() {
        /*
        Added nextPlayer method because there are only two players in the game, but having this
        method allows us to use the same methods for each player.
     */
        if (this.player == Player.PLAYER) {
            return this.player = Player.DEALER;
        }
        /*
        figure out error handling when the game is over.
        We don't need to toggle back to the dealer because the game is over. However,
        as a design choice we can switch back to the dealer so we can print out
        this player won.

         */
        return null;
    }


    public Card hit() {
        // made this return a card so the player can see what card he or she added to their score.
        Card newCard = removeCard(); // card value must translate to an int.
        if (this.player == Player.PLAYER) {
            this.playerScore += convertCardToValue(newCard);
            return newCard;
        }
        this.computerScore += convertCardToValue(newCard);
        return newCard;
    }

    private int convertCardToValue(Card c) {
        int literalValue = c.getName();
        if (literalValue == 1) {
            // will be adjusted in the isBusted method
            return 11;
        } else if (literalValue > 10) {
            return 10;
        } else {
            return literalValue;
        }
    }

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
    2. We need to show 2 cards of a player when the game starts, not 1.
    3. We need to show 1 card of a DEALER when the game starts. Then PLAYER starts the game as #2 says.
    4. Shuwei's idea: We need to use hand.java.

     */
    public static void main(String[] args) {
        BlackJack game1 = new BlackJack();
        game1.shuffleCard();
//        Deck deck1 = new Deck();
//        deck1.shuffleCard();
        Scanner keyboard = new Scanner(System.in);
        while (game1.playerScore < WINNING_SCORE) {
            System.out.println("Hit or Stand");
            String choice = keyboard.nextLine();
            System.out.println("\n");
            try {
                if (choice.equalsIgnoreCase("hit")) {
                    Card playing = game1.hit();
                    System.out.println(playing.getNameAsStr() + " of " + playing.getSuitAsStr());
                    System.out.println("player score: " + game1.playerScore);
                } else if (choice.equalsIgnoreCase("stand")) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Not a valid input.");
            }
        }
        if (game1.playerScore > WINNING_SCORE) {
            System.out.println("\n****Bust!****\n");
        }

        /*
        It's now the dealers turn.
         */
        game1.nextPlayer();
        System.out.println("\nIt's now the " + game1.getPlayer() + " turn");
        while (game1.computerScore < DEALER_MAX) {
            Card playing = game1.hit();
            System.out.println(playing.getNameAsStr() + " of " + playing.getSuitAsStr());
            System.out.println("computer score: " + game1.computerScore);
        }
        if (game1.computerScore > WINNING_SCORE) {
            System.out.println("\n****Bust!****\n");

        /*
        Winning logic for player:
        Player must have a score <= WINNING_SCORE && Computer > WINNING SCORE
        or
        Player's score > Computer's score
        tie occurs when both player and dealer values are the same and less than 21
         */
        }
        // when both players have the same score and are less than or equal to 21 -- tie.
        if (game1.playerScore == game1.computerScore &&
                game1.computerScore <= WINNING_SCORE && game1.playerScore <= WINNING_SCORE) {
            // Why is it called "push"? Is it a typo?
            System.out.println("\n***push -- no one wins.***");
        }
        // when computer busts and player is less than or equal to 21. Player wins.
        if (game1.computerScore > WINNING_SCORE && game1.playerScore <= WINNING_SCORE) {
            System.out.println("\n***Player wins!!***");
        }
        // both players are less than or equal to 21 and player is greater than computer -- Player wins.
        else if (game1.computerScore <= WINNING_SCORE && game1.playerScore <= WINNING_SCORE &&
                game1.computerScore < game1.playerScore) {
            System.out.println("\n***Player wins!!***");
        }

        // otherwise computer wins.
        /* Kicho
          When both players go over 21, then that is actually when a dealer wins.
          Here is a rule: If you go over 21 you bust, and the dealer wins regardless of the dealer's hand.
          This rule actually gives an advantage to casino.
        */
        else {
            System.out.println("\n***Computer wins ***");
        }

    }
}
