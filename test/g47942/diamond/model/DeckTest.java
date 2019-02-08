package g47942.diamond.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class DeckTest {

    @Test
    public void nbCardInDeckAfter0TurnPlay() {
        Deck deck = new Deck();
        assertEquals(35, deck.getTiles().size());
    }
    
    @Test
    public void nbCardInDeckAfter1TurnPlay() {
        Deck deck = new Deck();
        deck.getTile();
        assertEquals(34, deck.getTiles().size());
    }
    
    @Test
    public void nbCardInDeckAfter2TurnPlay() {
        Deck deck = new Deck();
        deck.getTile();
        deck.getTile();
        assertEquals(33, deck.getTiles().size());
    }
}
