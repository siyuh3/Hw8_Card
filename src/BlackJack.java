import java.util.ArrayList;
import java.util.Scanner;


public class BlackJack {

    private Deck deck;
    Hand playerHand;
    Hand dealerHand;

    // As score is dynamic where the value of Ace varies, we don't keep track of the score.
    // Instead, whenever we want to know that score, we compute it with a given hand

    private static final int WINNING_SCORE = 21;
    private static final int DEALER_MAX = 17;
    private static final int CARDS_IN_HAND_IN_THE_BEGINNING = 2;

    public BlackJack() {
        deck = new Deck();
        playerHand = new Hand();
        dealerHand = new Hand();
    }

    public Card playerHit() {
        Card newCard = deck.removeCard();
        if (newCard != null) {
            playerHand.addCard(newCard);
        }
        return newCard;
    }

    public Card dealerHit() {
        Card newCard = deck.removeCard();
        if (newCard != null) {
            dealerHand.addCard(newCard);
        }
        return newCard;
    }

    public static int countScoreOfHand(ArrayList<Card> hand) {
        int score = 0;
        int countOfAce =0;
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

    private void initializeGame() {
        Card p1 = playerHit(); //player
        //display
        InAndOut.displayPlayerCard(p1);
        Card p2 = playerHit(); // player
        //display
        InAndOut.displayPlayerCard(p2);
        InAndOut.displayPlayerScore(this);

        Card d1 = dealerHit();
        //display
        InAndOut.displayDealerCard(d1);
        Card d2 = dealerHit();
        // don't display
        System.out.println("Second card of dealer is hidden.");
    }
    private static boolean isBusted(int score) {
        if (score > WINNING_SCORE) return true;
        return false;
    }







    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.deck.shuffleCard();
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
                System.out.println("Player busted. Dealer won. Game over!");
                break;
            }
        }


        // dealer's turn if player did not get busted
        if (!isBusted(countScoreOfHand(game.playerHand.hand))) {

            System.out.println("Dealer's turn now");

            while (countScoreOfHand(game.dealerHand.hand) < DEALER_MAX) {
                Card newCard = game.dealerHit();
                InAndOut.displayDealerCard(newCard);
            }
            // check if the dealer has busted
            if (isBusted(countScoreOfHand(game.dealerHand.hand))) {
                System.out.println("Dealer has busted. Player won. Game over!");
            } else {
                System.out.println("\nDealer chose to stand");
                // Compare the score
                int scoreOfPlayer = countScoreOfHand(game.playerHand.hand);
                int scoreOfDealer = countScoreOfHand(game.dealerHand.hand);
                InAndOut.displayPlayerScore(game);
                InAndOut.displayDealerScore(game);

                if (scoreOfDealer == scoreOfPlayer) {
                    System.out.println("It's a tie! Game over!");
                } else if (scoreOfDealer < scoreOfPlayer) {
                    System.out.println("Player won! Game over!");
                } else {
                    System.out.println("Dealer won! Game over!");
                }
                Card hidden = game.dealerHand.hand.get(1);
                System.out.print("The hidden card of dealer is : " + hidden.getNameAsStr() + " of " + hidden.getSuitAsStr());

            }
        }








    }
}
