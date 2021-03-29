import java.util.ArrayList;
import java.util.Collections;

/**
 * @program: Hand
 * @description: This is a class representing the cards in a hand
 * @author: Siyu Hou, Kicho Yu
 * @create: 2021-03-16 23:44
 **/
public class Hand extends Deck {
    public final ArrayList<Card> hand;
    private static final int NUMBER_OF_CARDS_IN_HAND = 5;

    /**
     * Default constructor that initializes a hand of empty set of cards
     */
    public Hand() {
        this.hand = new ArrayList<Card>(NUMBER_OF_CARDS_IN_HAND);
    }

    /**
     * Shuffles the cards in a hand
     */
    @Override
    public void shuffleCard() {
        for (int i = 0; i < NUMBER_OF_CARDS_IN_HAND; i++) {
            int rand1 = (int) (Math.random() * NUMBER_OF_CARDS_IN_HAND);
            int rand2 = (int) (Math.random() * NUMBER_OF_CARDS_IN_HAND);
            Collections.swap(hand, rand1, rand2);
        }
    }

    /**
     * Add a new card to the hand
     * This method should take a Card object as a parameter and add it to the ArrayList of Hand .
     *
     * @param card Card object
     *             Note: In the Deck class, we make sure there will be no duplicate in a deck. Therefore,
     *             there won't be any duplicate in the hand whose cards are derived from the deck
     */
    @Override
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Remove a card from the hand
     * This removes the first card stored in the ArrayList of the Hand and returns it
     *
     * @return Card object got removed
     * @throws IllegalStateException when the hand is empty
     */
    @Override
    public Card removeCard() {
        if (hand.get(0) == null) {
            throw new IllegalStateException("No more card can be removed from hand");
        }
        return hand.remove(0);
    }

    /**
     * Sort the cards of the hand using selection sort
     */
    @Override
    public void sortCard() {
        for (int i = 0; i < hand.size() - 1; i++) {
            int min = i;
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.get(j).getName() < hand.get(min).getName()) min = j;
            }
            Collections.swap(hand, i, min);
        }
    }

    /**
     * Print all cards of a hand in strings
     *
     * @return null
     */
    @Override
    public String toString() {
        for (Card c : hand) {
            System.out.print(c.getNameAsStr() + " " + c.getSuitAsStr() + "\n");
        }
        return null;
    }

    /**
     * test the code
     *
     * @param args array
     */
    public static void main(String[] args) {
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();
        deck1.shuffleCard();
        // Testing shuffleCard() in Deck.java
        boolean deskShuffleCardResult = deck1 == deck2;
        System.out.println("deck1 == deck2? Expect FALSE: " + deskShuffleCardResult);
        Hand hand1 = new Hand();
        Hand hand2 = new Hand();

        // Testing addCard() in Hand.java
        // Testing removeCard() in Deck.java
        for (int i = 0; i < NUMBER_OF_CARDS_IN_HAND; i++) {
            Card temp1 = deck1.removeCard();
            hand1.addCard(temp1);
            Card temp2 = deck1.removeCard();
            hand2.addCard(temp2);
        }


        System.out.println("\n===Cards in hand1===");
        // Testing toString() in Hand.java
        hand1.toString();

        System.out.println("\n===Shuffled Cards in hand1===");
        // Testing shuffleCard() in Hand.java
        // This output should be different from the previous output.
        hand1.shuffleCard();
        hand1.toString();

        System.out.println("\n===Sorted cards in hand2===");
        // Testing sortCard() in Hand.java
        hand2.sortCard();
        hand2.toString();

        System.out.println("\n===Sorted cards in deck1===");
        // Testing sortCard() in Deck.java
        deck1.sortCard();
        // Testing toString() in Deck.java
        deck1.toString();

        System.out.println("\n===Size should be 42 (= 52 - 5 - 5)===");
        // Testing getDeck() in Deck.java
        System.out.println(deck1.getDeck().size());


        System.out.println("\n\n===Return the cards===");

        // Testing addCard() in Deck.java
        // Testing removeCard() in Hand.java
        for (int i = 0; i < NUMBER_OF_CARDS_IN_HAND; i++) {
            Card temp1 = hand1.removeCard();
            deck1.addCard(temp1);
            Card temp2 = hand2.removeCard();
            deck1.addCard(temp2);
        }

        System.out.println("\n===Sorted card in hand1 (expecting empty)===");
        hand1.toString();
        System.out.println("\n===Sorted card in hand2 (expecting empty)===");
        hand2.toString();
        System.out.println("\n===Cards in deck1 after adding 10 cards back===");
        deck1.toString();
        System.out.println("\n===Sorted card in deck1===");
        deck1.sortCard();
        deck1.toString();
        System.out.println("\n===Size should be 52===");
        System.out.println(deck1.getDeck().size());
    }
}
