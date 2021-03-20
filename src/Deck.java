import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @program: Deck
 * @description: This class represent the deck that consists of 52 cards
 * @author: Siyu Hou, Kicho Yu
 * @create: 2021-03-16 23:39
 **/
public class Deck {
    private final int NUMBER_OF_CARDS = 52;
    private final int NUMBER_OF_SUITS = 4;
    private final int NUMBER_OF_CARDS_IN_A_SUIT = 13;
    private ArrayList<Card> deck = new ArrayList<>(NUMBER_OF_CARDS);

    /**
     * Default constructor that creates a deck of 52 cards by the order of their names
     */
    public Deck() {
        for (int i = 1; i <= NUMBER_OF_SUITS; i++) {
            for (int j = 1; j <= NUMBER_OF_CARDS_IN_A_SUIT; j++) {
                Card newCard = new Card(i, j);
                deck.add(newCard);
            }
        }
    }

    /**
     * Getter that returns the ArrayList of the deck
     * @return the ArrayList of the deck
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Shuffles the cards in the deck.
     * You can implement this by randomly swapping every card in the deck.
     */
    public void shuffleCard() {
        for (int i = 0; i < NUMBER_OF_CARDS; i++) {
            int rand1 = (int) (Math.random() * NUMBER_OF_CARDS);
            int rand2 = (int) (Math.random() * NUMBER_OF_CARDS);
            Collections.swap(deck, rand1, rand2);
        }
    }

    /**
     * Add a new card to the deck.
     * This method should take a Card object as a parameter and add it to the ArrayList .
     *
     * @param card Card object
     * @throws IllegalArgumentException if to add a card that already in the deck
     */
    public void addCard(Card card) throws IllegalArgumentException{
        if (deck.contains(card)) {
            throw new IllegalArgumentException("Can't add a card that already existed in the deck");
        }
        deck.add(card);
    }

    /**
     * Remove a card from the deck.
     * This removes the first card stored in the ArrayList and returns it.
     *
     * @return Card object
     * @throws IllegalStateException when the deck is empty
     */
    public Card removeCard() throws IllegalStateException {
        if (deck.get(0) == null) {
            throw new IllegalStateException("No more card can be removed from deck");
        }
        return deck.remove(0);
    }

    /**
     * Sorts the cards in the deck ordered by name with selection sort.
     */
    public void sortCard() {
        int min;
        for (int i = 0; i < deck.size() - 1; i++) {
            min = i;
            for (int j = i + 1; j < deck.size(); j++) {
                if (deck.get(j).getName() < deck.get(min).getName()) min = j;
            }
            Collections.swap(deck, i, min);
        }
    }

    /**
     * Prints every card in the deck.
     *
     * @return null
     */
    @Override
    public String toString() {
        for (Card c : deck) {
            System.out.print(c.getNameAsStr() + " " + c.getSuitAsStr() + "\n");
        }
        return null;
    }

    public static void main(String[] args) {
        Deck deck1 = new Deck();
        deck1.shuffleCard();
        deck1.toString();

        deck1.sortCard();
        System.out.println("=========After being sorted=============");
        deck1.toString();
    }
}
