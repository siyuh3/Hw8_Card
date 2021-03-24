import java.util.ArrayList;
import java.util.Scanner;

// this is the class where we implement the blackjack game.
/*
2 players: computer vs human.
methods:
hit -- add a card to sum
stand -- stop
 */
public class BlackJack {

    //    private Player player;
//    private Player dealer;
    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;
    private int countOfActions;// it is to determine who the next player is


    // As score is dynamic where the value of Ace varies, we don't keep track of the score.
    // Instead, whenever we want to know that score, we compute it with a given hand

    private static final int WINNING_SCORE = 21;
    private static final int DEALER_MAX = 17;
    private static final int CARDS_IN_HAND_IN_THE_BEGINNING = 2;

    public BlackJack() {
//        player = Player.PLAYER;
//        dealer = Player.DEALER;
        deck = new Deck();
        playerHand = new Hand();
        dealerHand = new Hand();
//        countOfActions = 0;
    }

//    public Player getPlayer() {
//        if (countOfActions % 2 == 0) return player;
//        return dealer;
//    }


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

    private boolean isGameOver() {
        // Either player of dealer busted
        int playerScore = countScoreOfHand(playerHand.hand);
        int dealerScore = countScoreOfHand(dealerHand.hand);
        if (isBusted(playerScore)) {
            return true;
        } else if (isBusted(dealerScore)) {
            return true;
        }
        return false;
    }




    private static boolean isBusted(int score) {
        if (score > WINNING_SCORE) return true;
        return false;
    }

    private static int getInput() {
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


    private void initializeGame() {
        Card p1 = playerHit(); //player
        //display
        displayPlayerCard(p1);
        Card p2 = playerHit(); // player
        //display
        displayPlayerCard(p2);
        displayPlayerScore();

        Card d1 = dealerHit();
        //display
        displayDealerCard(d1);
        Card d2 = dealerHit();
        // don't display
        System.out.println("Second card of dealer is hidden.");
    }

    private static void displayPlayerCard(Card c) {
        System.out.println("Player got a card: " + c.getNameAsStr() + " of " + c.getSuitAsStr());
    }

    private void displayPlayerScore() {
        int scoreOfPlayer = countScoreOfHand(playerHand.hand);
        System.out.println("Player's score: " + scoreOfPlayer);
    }

    private void displayDealerScore() {
        int scoreOfPlayer = countScoreOfHand(dealerHand.hand);
        System.out.println("Dealer's score: " + scoreOfPlayer);
    }

    private static void displayDealerCard(Card c) {
        System.out.println("Dealer got a card: " + c.getNameAsStr() + " of " + c.getSuitAsStr());
    }
    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.deck.shuffleCard();
        game.initializeGame();


        // player's turn
        boolean playerStand = false;
        while (!playerStand) {
            int hitOrStand = getInput();
            // use while loop to get valid input
            while (hitOrStand < 0) {
                hitOrStand = getInput();
            }
            if (hitOrStand == 1) {
                Card newCard = game.playerHit();
                displayPlayerCard(newCard);
                game.displayPlayerScore();
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
                displayDealerCard(newCard);
            }
            // check if the dealer has busted
            if (isBusted(countScoreOfHand(game.dealerHand.hand))) {
                System.out.println("Dealer has busted. Player won. Game over!");
            } else {
                System.out.println("\nDealer chose to stand");
                // Compare the score
                int scoreOfPlayer = countScoreOfHand(game.playerHand.hand);
                int scoreOfDealer = countScoreOfHand(game.dealerHand.hand);
                game.displayPlayerScore();
                game.displayDealerScore();

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
