import java.util.ArrayList;
/**
 * @program: BlackJack
 * @description: This class represent BlackJack game
 * @author: Siyu Hou, Kicho Yu
 * @create: 2021-03-16 23:39
 **/
public class BlackJack {
    private static final int WINNING_SCORE = 21;
    private static final int DEALER_MAX = 17;
    private Deck deck;
    Hand playerHand;
    Hand dealerHand;

    // As score is dynamic where the value of Ace varies, we don't keep track of the score.
    // Instead, whenever we want to know that score, we compute it with a given hand

    /**
     * default constructor
     */
    public BlackJack() {
        deck = new Deck();
        playerHand = new Hand();
        dealerHand = new Hand();
    }


    /**
     * hit the card from deck
     * @return one card
     */
    public Card playerHit() {
        Card newCard = deck.removeCard();
        if (newCard != null) {
            playerHand.addCard(newCard);
        }
        return newCard;
    }


    /**
     * hit the card from deck for dealer
     * @return one card
     */
    public Card dealerHit() {
        Card newCard = deck.removeCard();
        if (newCard != null) {
            dealerHand.addCard(newCard);
        }
        return newCard;
    }

    /**
     * count the score of hand
     * @param hand ArrayList of Card object
     * @return int which is score
     */
    public static int countScoreOfHand(ArrayList<Card> hand) {
        int score = 0;
        int countOfAce = 0;
        for (int i = 0; i < hand.size(); i++) {
            score += convertCardToValue(hand.get(i));
            if (hand.get(i).getName() == 1) {
                countOfAce += 1;
            }
        }
        while (isBusted(score) && countOfAce > 0) {
            score -= 10;
            countOfAce -= 1;
        }
        return score;
    }

    /**
     * convert card to value
     * @param c Card object
     * @return int which is card value
     */
    private static int convertCardToValue(Card c) {
        int literalValue = c.getName();
        if (literalValue == 1) {
            // will be adjusted in the countScoreOfHand method
            return 11;
        } else if (literalValue > 10) {
            return 10;
        } else {
            return literalValue;
        }
    }


    /**
     * initialize the game
     */
    private void initializeGame() {
        //shuffle cards
        deck.shuffleCard();
        // two cards for player
        Card p1 = playerHit();
        Card p2 = playerHit();
        System.out.println();
        InAndOut.displayPlayerCard(p1);
        InAndOut.displayPlayerCard(p2);
        InAndOut.displayPlayerScore(this);
        System.out.println();
        //two cards for dealer
        Card d1 = dealerHit();
        InAndOut.displayDealerCard(d1);
        // not displaying the second card
        System.out.println("Second card of dealer is hidden.");
        InAndOut.displayDealerScore(this);
        Card d2 = dealerHit();
    }

    /**
     * check whether busted
     * @param score score in hand
     * @return busted or not
     */
    private static boolean isBusted(int score) {
        if (score > WINNING_SCORE) return true;
        return false;
    }

    /**
     * keep playing the game
     * @param args arrays
     */
    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.initializeGame();

        // player's turn
        boolean playerStand = false;
        while (!playerStand) {
            int hitOrStand = InAndOut.getInput();
            // use while loop to get valid input
            while (hitOrStand < 0) {
                hitOrStand = InAndOut.getInput();
            }
            if (hitOrStand == 1) {
                Card newCard = game.playerHit();
                InAndOut.displayPlayerCard(newCard);
                InAndOut.displayPlayerScore(game);
            } else {
                playerStand = true;
                System.out.println("Player chose to stand");
            }
            // check if the player has busted
            if (isBusted(countScoreOfHand(game.playerHand.hand))) {
                System.out.println("\n*** Player busted! Dealer won! Game over! ***");
                break;
            }
        }


        // dealer's turn if player did not get busted
        if (!isBusted(countScoreOfHand(game.playerHand.hand))) {
            System.out.println("\n*** Dealer's turn now ***");
            Card hidden = game.dealerHand.hand.get(1);
            System.out.println("The hidden card of dealer: " + hidden.getNameAsStr() + " of " +
                    hidden.getSuitAsStr() + " " + hidden.getUnicode());
            InAndOut.displayDealerScore(game);

            while (countScoreOfHand(game.dealerHand.hand) < DEALER_MAX) {
                Card newCard = game.dealerHit();
                InAndOut.displayDealerCard(newCard);
                InAndOut.displayDealerScore(game);
            }
            // check if the dealer has busted
            if (isBusted(countScoreOfHand(game.dealerHand.hand))) {
                System.out.println();
                InAndOut.displayPlayerScore(game);
                InAndOut.displayDealerScore(game);
                System.out.println("\n*** Dealer busted! Player won! Game over! ***");
            } else {
                System.out.println("\nDealer chose to stand\n");
                // Compare the score
                System.out.println("*** Final Score ***");
                int scoreOfPlayer = countScoreOfHand(game.playerHand.hand);
                int scoreOfDealer = countScoreOfHand(game.dealerHand.hand);
                InAndOut.displayPlayerScore(game);
                InAndOut.displayDealerScore(game);

                if (scoreOfDealer == scoreOfPlayer) {
                    System.out.println("\nIt's a tie! Game over!");
                } else if (scoreOfDealer < scoreOfPlayer) {
                    System.out.println("\nPlayer won! Game over!");
                } else {
                    System.out.println("\nDealer won! Game over!");
                }
            }
        }


    }
}