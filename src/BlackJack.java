
import java.util.Scanner;


// this is the class where we implement the blackjack game.
/*
2 players: computer vs human.
methods:
hit -- add a card to sum
stand --
 */

public class BlackJack extends Deck {
    private Player player;

    private int playerScore;
    private int computerScore;
    private static final int WINNING_SCORE = 21;
    private static final int DEALER_MAX = 17;

    /**
     * Default constructor.
     */
    public BlackJack(){
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



    /**
     *
     * @return
     */
    public Player nextPlayer(){
        /*
        Added nextPlayer method because there are only two players in the game, but having this
        method allows us to use the same methods for each player.
     */
        if(this.player == Player.PLAYER){
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

    public Card hit(){
        // made this return a card so the player can see what card he or she added to their score.
        Card cardValue = removeCard(); // card value must translate to an int.
        if(this.player == Player.PLAYER){
            this.playerScore += cardValue; // <-- this must be an int so we can add a value to the players score.
            return cardValue;
        }
        this.computerScore += cardValue; // <-- this must be an int so we can add a value to the computer score.
        return cardValue;


    }

    public static void main(String[] args) {
        BlackJack game1 = new BlackJack();
        game1.shuffleCard();
        Scanner keyboard = new Scanner(System.in);
        while(game1.playerScore < WINNING_SCORE)
        {
            System.out.println("Hit or Hold");
            String choice = keyboard.nextLine();
            try{
                if(choice.equals("Hit")) {
                    game1.hit();
                }
                else if (choice.equals("hold")){
                    break;
                }

            }
            catch(Exception e){
                System.out.println("Not a valid input.");
            }
        }
        if(game1.playerScore == 21){
            System.out.println("Player 1 wins!"); // dealer max is 17 so player will always win if he or she gets 21
        }

        game1.nextPlayer();
        while(game1.computerScore < DEALER_MAX){
            game1.hit();
        }

    }
}
