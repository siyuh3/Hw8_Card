import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @program: Deck
 * @description:
 * @author: Siyu Hou, Kicho Yu
 * @create: 2021-03-16 23:39
 **/
public class Deck {
    private ArrayList<Card> deck = new ArrayList<>(52);

    public Deck(){
        for (int i = 1; i <= 4; i++){
            for (int j = 1; j <= 13; j++){
                Card newCard = new Card(i, j);
                deck.add(newCard);
            }
        }
    }

    public ArrayList<Card> getDeck(){
        return deck;
    }

    /**
     * Shuffle the cards in the deck.
     * You can implement this by randomly swapping every card in the deck.
     */
    public void shuffleCard(){
        int min = 0;
        int max = 51;
        int range = max - min + 1;
        for (int i = 0; i < 52; i++){
            int rand1 = (int) (Math.random() * range) + min;
            int rand2 = (int) (Math.random() * range) + min;
            Collections.swap(deck, rand1, rand2);
        }
    }

    /**
     * Add a new card to the deck.
     * This method should take a Card object as a parameter and add it to the ArrayList .
     * @param card Card object
     */
    public void addCard(Card card){
        deck.add(card);
    }

    /**
     * Remove a card from the deck.
     * This removes the first card stored in the ArrayList and returns it.
     * @return Card object
     */
    public Card removeCard(){
        return deck.remove(0);
    }

    /**
     * Sort the cards in the deck ordered by name.
     */
    public void sortCard(){
        int min;
        for (int i = 0; i < deck.size()-1; i++){
            min = i;
            for (int j = i+1; j < deck.size(); j++){
                if (deck.get(j).getName() < deck.get(min).getName()) min = j;
            }
            Collections.swap(deck, i, min);
        }
    }

    /**
     * Print every card in the deck.
     * @return null
     */
    @Override
    public String toString() {
        for (Card e : deck){
            System.out.print(e.getNameAsStr()+ " "+ e.getSuitAsStr() + "\n");
        }
        return null;
    }

    public static void main(String[] args) {
        Deck deck1 = new Deck();
        deck1.shuffleCard();
        deck1.toString();

        deck1.sortCard();
        System.out.println("=========After sorted=============");
        deck1.toString();
    }
}