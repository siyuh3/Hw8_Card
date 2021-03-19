
// this is the class where we implement the blackjack game.
/*
2 players: computer vs human.
methods:
hit -- add a card to sum
stand --
 */

public class BlackJack extends Deck {
    private String player;
    private int score;
    private final int WINNING_SCORE = 21;
    private final int DEALER_MAX = 17;

    public BlackJack(){
        this.player = "Computer";
        this.score = 0;
    }
}
