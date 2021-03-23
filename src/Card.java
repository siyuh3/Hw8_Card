/**
 * @program: Card
 * @description: This class represents the card that stores the
 * suit (e.g., Clubs, Diamonds, Hearts, Spades) and its name
 * @author: Siyu Hou
 * @create: 2021-03-16 23:39
 **/
public class Card {
    private int suit;
    private int name;

    /**
     * Default constructor that creates a card of Spade Ace
     */
    public Card() {
        setSuit(1);
        setName(1);
    }

    /**
     * Constructor that creates a card of a given suit and a given name
     *
     * @param suit the suit of a card
     * @param name the name of a card
     */
    public Card(int suit, int name) {
        setSuit(suit);
        setName(name);
    }

    /**
     * Setter that sets the suit of a card
     *
     * @param suit the suit of a card
     * @throws IndexOutOfBoundsException if suit is out of the range of [1,4]
     */
    public void setSuit(int suit) throws IndexOutOfBoundsException {
        int NUMBER_OF_SUITS = 4;
        if (suit < 1 || suit > NUMBER_OF_SUITS) {
            throw new IndexOutOfBoundsException("Invalid suit");
        }
        this.suit = suit;
    }

    /**
     * Setter that sets the name of a card
     *
     * @param name the name of a card
     * @throws IndexOutOfBoundsException if name is out of the range of [1,13]
     */
    public void setName(int name) throws IndexOutOfBoundsException {
        int NUMBER_OF_CARDS_IN_A_SUIT = 13;
        if (name < 1 || name > NUMBER_OF_CARDS_IN_A_SUIT) {
            throw new IndexOutOfBoundsException("Invalid name");
        }
        this.name = name;
    }

    /**
     * Getter that gets the suit of the card
     *
     * @return the suit of the card
     */
    public int getSuit() {
        return this.suit;
    }

    /**
     * Getter that gets the name of the card
     *
     * @return the name of the card
     */
    public int getName() {
        return this.name;
    }

    /**
     * Getter that gets the string of the suit of the card
     *
     * @return the string of the suit of the card
     */
    public String getSuitAsStr() {
        return switch (suit) {
            case 1 -> "Spade";
            case 2 -> "Heart";
            case 3 -> "Diamond";
            case 4 -> "Club";
            default -> null;
        };
    }

    /**
     * Getter that gets the string of the name of the card
     *
     * @return the string of the name of the card
     */
    public String getNameAsStr() {
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
