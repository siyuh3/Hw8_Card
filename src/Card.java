/**
 * @program: Card
 * @description: This class represent the card that stores the
 * suit (e.g., Clubs, Diamonds, Hearts, Spades)
 * @author: Siyu Hou
 * @create: 2021-03-16 23:39
 **/
public class Card {
    private int suit;
    private int name;

    public Card(){
        setSuit(1);
        setName(1);
    }

    public Card(int suit, int name){
        setSuit(suit);
        setName(name);
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getSuit() {
        return this.suit;
    }

    public int getName() {
        return this.name;
    }

    public String getSuitAsStr(){
        return switch (suit) {
            case 1 -> "Spades";
            case 2 -> "Hearts";
            case 3 -> "Diamonds";
            case 4 -> "Clubs";
            default -> null;
        };
    }

    public String getNameAsStr(){
        return switch (name) {
            case 1 -> "Ace";
            case 2 -> "2";
            case 3 -> "3";
            case 4 -> "4";
            case 5 -> "5";
            case 6 -> "6";
            case 7 -> "7";
            case 8 -> "8";
            case 9 -> "9";
            case 10 -> "10";
            case 11 -> "Jack";
            case 12 -> "Queen";
            case 13 -> "King";
            default -> null;
        };
    }
}
