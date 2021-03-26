import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {
    private Card card1;
    private Card card2;

    @Before
    public void setUp() throws Exception {
        card1 = new Card();
        card2 = new Card(2, 2);
    }

    @Test
    public void setSuit() {
        card1.setSuit(3);
        card2.setSuit(4);
        assertEquals(3, card1.getSuit());
        assertEquals(4, card2.getSuit());
    }

    @Test
    public void setName() {
        card1.setName(3);
        card2.setName(4);
        assertEquals(3, card1.getName());
        assertEquals(4, card2.getName());
    }

    @Test
    public void getSuit() {
        assertEquals(1, card1.getSuit());
        assertEquals(2, card2.getSuit());
    }

    @Test
    public void getName() {
        assertEquals(1, card1.getName());
        assertEquals(2, card2.getName());
    }

    @Test
    public void getSuitAsStr() {
        assertEquals("Spade", card1.getSuitAsStr());
        assertEquals("Heart", card2.getSuitAsStr());
    }

    @Test
    public void getNameAsStr() {
        assertEquals("Ace", card1.getNameAsStr());
        assertEquals("2", card2.getNameAsStr());
    }

    @Test
    public void getUnicode() {
        assertEquals("\u2660 \uD83C\uDCA1", card1.getUnicode());
        assertEquals("\u2661 \uD83C\uDCA2", card2.getUnicode());
    }
}