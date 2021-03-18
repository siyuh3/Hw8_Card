import java.util.ArrayList;
import java.util.Collections;

/**
 * @program: Hand
 * @description:
 * @author: Siyu Hou, Kicho Yu
 * @create: 2021-03-16 23:44
 **/
public class Hand extends Deck{
    private final ArrayList<Card> hand;
    private static int NUMBER_OF_CARDS_IN_HAND = 5;

    public Hand(){
        this.hand = new ArrayList<>(NUMBER_OF_CARDS_IN_HAND);
    }

    @Override
    public void shuffleCard() {
        super.shuffleCard();
    }

    @Override
    public void addCard(Card card) {
        hand.add(card);
    }

    @Override
    public Card removeCard() {
        return hand.remove(0);
    }

    @Override
    public void sortCard() {
        int min;
        for (int i = 0; i < hand.size()-1; i++){
            min = i;
            for (int j = i+1; j < hand.size(); j++){
                if (hand.get(j).getName() < hand.get(min).getName()) min = j;
            }
            Collections.swap(hand, i, min);
        }
    }

    @Override
    public String toString() {
        for (Card e : hand){
            System.out.print(e.getSuitAsStr()+ " "+ e.getName() + "\n");
        }
        return null;
    }

    public static void main(String[] args) {
        Deck deck1 = new Deck();
        deck1.shuffleCard();
        Hand hand1 = new Hand();
        Hand hand2 = new Hand();

        for (int i=0; i<5; i++){
            Card temp1 = deck1.removeCard();
            hand1.addCard(temp1);
            Card temp2 = deck1.removeCard();
            hand2.addCard(temp2);
        }


        hand1.sortCard();
        hand2.sortCard();
        deck1.sortCard();

        System.out.println("===Sorted card in hand1===");
        hand1.toString();
        System.out.println("===Sorted card in hand2===");
        hand2.toString();
        System.out.println("===Sorted card in deck1===");
        deck1.toString();
        System.out.println("===Size should be 42===");
        System.out.println(deck1.getDeck().size());

        for (int i = 0; i < NUMBER_OF_CARDS_IN_HAND; i++){
            Card temp1 = hand1.removeCard();
            deck1.addCard(temp1);
            Card temp2 = hand2.removeCard();
            deck1.addCard(temp2);
        }

        System.out.println("===Sorted card in hand1===");
        hand1.toString();
        System.out.println("===Sorted card in hand2===");
        hand2.toString();
        System.out.println("===Sorted card in deck1===");
        deck1.toString();
        System.out.println("===Size should be 52===");
        System.out.println(deck1.getDeck().size());

    }
}
