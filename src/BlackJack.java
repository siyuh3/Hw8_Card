import java.util.ArrayList;
import java.util.Scanner;

/**
 * @program: BlackJack
 * @description: This class represents the BlackJake game play
 * @author: Siyu Hou, Kicho Yu
 * @create: 2021-03-20 23:39
 **/
public class BlackJack {
    private Player player;
    private int playerScore;
    private int computerScore;
    private static final int WINNING_SCORE = 21;
    private static final int CARDS_IN_HAND_IN_THE_BEGINNING = 2;

    /**
     * Default constructor.
     */
    public BlackJack() {
        this.player = Player.PLAYER;
        setPlayerScore(0);
        setComputerScore(0);

    }

    /**
     * get current player
     *
     * @return current player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return next player
     */
    public Player nextPlayer() {
        if (this.player == Player.PLAYER) {
            this.player = Player.DEALER;
        } else if (this.player == Player.DEALER) {
            this.player = Player.PLAYER;
        }
        return this.player;
    }

    public Card hit(Deck deck) {
        Card newCard = deck.removeCard(); // card value must translate to an int.
        if (this.player == Player.PLAYER) {
            this.playerScore += cardValue(newCard);
        } else {
            this.computerScore += cardValue(newCard);
        }
        return newCard;
    }

    public void stand() {
        nextPlayer();
    }

    public Player getWinner() {
//        if (playerScore == WINNING_SCORE)
//            return Player.PLAYER;
//        else if (computerScore == WINNING_SCORE) return Player.DEALER;
//        else if (playerScore > WINNING_SCORE) return Player.DEALER;
//        else if (playerScore > computerScore) return Player.PLAYER;
//        else if (computerScore > playerScore) return Player.DEALER;

        // when both players have the same score and are less than 21.
//        if(playerScore == computerScore && computerScore < WINNING_SCORE && playerScore < WINNING_SCORE){
//            return null;
//        }
        // Computer busts and Player is less than or equal to 21.
        if(computerScore > WINNING_SCORE && playerScore <= WINNING_SCORE){
            return Player.PLAYER;
        }
        // player busts
        else if(playerScore > WINNING_SCORE){
            return Player.DEALER;
        }
        // Both players are less than 21 and player is greater than computer.
        else if(computerScore < WINNING_SCORE && computerScore < playerScore){
            return Player.PLAYER;
        }
        // Both players are less than 21 and computer score is greater than players.
        else if(computerScore < WINNING_SCORE && computerScore > playerScore){
            return Player.DEALER;
        }
        else if(computerScore == WINNING_SCORE){
            return Player.DEALER;
        }
        return null;
    }

    public boolean gameOver() {
        if (playerScore == computerScore && playerScore <= WINNING_SCORE) return true;
        return getWinner() != null;
        //else if (computerScore >= 17) return true;
    }

    public boolean isBusted() {
        if (player == Player.PLAYER) return (playerScore > WINNING_SCORE);
        else return computerScore > WINNING_SCORE;
    }

    public int cardValue(Card card) {
        int literalValue = card.getName();
        if (literalValue == 1 && player == Player.PLAYER) {
            playerScore += 11;
            if (isBusted()) {
                playerScore -= 11;
                return 1;
            } else {
                playerScore -= 11;
                return 11;
            }
        } else if (literalValue == 1 && player == Player.DEALER) {
            computerScore += 11;
            if (isBusted()) {
                computerScore -= 11;
                return 1;
            } else {
                computerScore -= 11;
                return 11;
            }
        } else return Math.min(literalValue, 10);
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public void setComputerScore(int computerScore) {
        this.computerScore = computerScore;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getComputerScore() {
        return computerScore;
    }
}
