import java.util.ArrayList;
import java.util.Scanner;

/**
 * @program: Card
 * @description:
 * @author: Siyu Hou
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
    2. We need to show 2 cards of a player when the game starts, not 1.
    3. We need to show 1 card of a DEALER when the game starts. Then PLAYER starts the game as #2 says.
    4. Shuwei's idea: We need to use Hand.java.
       This is related to removeCard() in hit(); removeCard should be from Deck.java but not Hand.java.

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
        System.out.println("Player's card are " + initCard1.getNameAsStr() + " of " + initCard1.getSuitAsStr()  + " and " +
                initCard2.getNameAsStr() + " of " + initCard2.getSuitAsStr());
        System.out.println("Player's score: " + game.getPlayerScore());
        // add 1, since 2 is hidden
        game.nextPlayer();
        Card pcInitCard1 = game.hit(deck);

        System.out.println("\nComputer's card are " + pcInitCard1.getNameAsStr() + " of " +  pcInitCard1.getSuitAsStr() +
                " and another is hidden");
        //game.setComputerScore(game.cardValue(pcInitCard1));
        System.out.println("Computer's score: " + game.getComputerScore());
        computerHand.addCard(deck.removeCard());
        computerHand.addCard(deck.removeCard());

        Scanner keyboard = new Scanner(System.in);

        if (game.getPlayerScore() == WINNING_SCORE) System.out.println("Congrats! You got blackjack");
        /*
        It's now the dealers turn.
         */
        game.nextPlayer();
        while (!game.isBusted()) {
            System.out.println("Hit or Stand");
            String choice = keyboard.nextLine();
            System.out.println("\n");
            try {
                if (choice.equalsIgnoreCase("hit")) {
                    Card hitCard = game.hit(deck);
                    playerHand.addCard(hitCard);
                    //game.playerScore += game.cardValue(hitCard);
                    System.out.println("You got " + hitCard.getNameAsStr() + " of " + hitCard.getSuitAsStr());
                    System.out.println("player score: " + game.getPlayerScore());
                } else if (choice.equalsIgnoreCase("stand")) {
                    game.stand();
                    break;
                }
            } catch (Exception e) {
                System.out.println("Not a valid input.");
            }
        }

        // Busted --> User win
        // no Busted --> keep playing
        if (game.isBusted()) {
            System.out.println("\n****Bust!****\n");
            System.out.print("Winner is " + game.nextPlayer());
        } else {
            while (game.getComputerScore() < DEALER_MAX) {
                // reveal the second init card
                Card pcInitCard2 = game.hit(deck);
                computerHand.addCard(pcInitCard2);
                System.out.println("Hidden card is " + pcInitCard2.getNameAsStr() + " of " + pcInitCard2.getSuitAsStr());
                Card newCard = game.hit(deck);
                computerHand.addCard(newCard);


                System.out.println("\nComputer got " + newCard.getNameAsStr() + " of " + newCard.getSuitAsStr());
                System.out.println("Computer score: " + game.getComputerScore());
            }
            if (game.getWinner() == null) System.out.println("Game is a tie! No winner");
            else if (game.getComputerScore() > WINNING_SCORE) {
                System.out.println("Dealer busted!\n");
                System.out.print(game.nextPlayer() + " is the winner!");
            }
            // added this because if Player's score = 21 and Dealer's score = 21 -> result = tie.
            else if((game.getComputerScore() == WINNING_SCORE) == (game.getPlayerScore() == WINNING_SCORE)){
                System.out.println("Game is a tie");
            }
            else System.out.println(game.getWinner() + " is the winner!");
        }


        // Error when computer has a ace and hidden card is a King, Queen, etc..
        // Error when computer score is greater than 17 on initial turn
            // cards are King of hearts and 10 of diamond = 20 -> computer should hold.

    }
}
