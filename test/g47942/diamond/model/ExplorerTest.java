package g47942.diamond.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExplorerTest {

    @Test
    public void getPseudonyme() {
        Explorer explorer = new Explorer("Sdr");
        assertEquals("Sdr", explorer.getPseudonym());
    }

    @Test
    public void getStateBeforeTakeDecisionToLeave() {
        Explorer explorer = new Explorer("Sdr");
        explorer.startExploration();
        assertEquals(State.EXPLORING, explorer.getState());
    }

    @Test
    public void getStateAftertakeDecisionToLeave() {
        Explorer explorer = new Explorer("Sdr");
        explorer.takeDecisionToLeave();
        assertEquals(State.LEAVING, explorer.getState());
    }

    @Test
    public void getBagBeforAddingToBag() {
        Explorer explorer = new Explorer("Sdr");
        assertEquals(0, explorer.getBag().getValue());
    }

    @Test
    public void addToBag1Times() {
        Explorer explorer = new Explorer("Sdr");
        explorer.addToBag(Gem.RUBY);
        assertEquals(1, explorer.getBag().getValue());
    }

    @Test
    public void addToBag2Times() {
        Explorer explorer = new Explorer("Sdr");
        explorer.addToBag(Gem.RUBY);
        explorer.addToBag(Gem.DIAMOND);
        assertEquals(6, explorer.getBag().getValue());
    }
    
    @Test
    public void addToBag3Times() {
        Explorer explorer = new Explorer("Sdr");
        explorer.addToBag(Gem.RUBY);
        explorer.addToBag(Gem.RUBY);
        explorer.addToBag(Gem.DIAMOND);
        explorer.addToBag(Gem.DIAMOND);
        
        assertEquals(12, explorer.getBag().getValue());
    }

    @Test
    public void equalsTrue() {
        Explorer e1 = new Explorer("mcd");
        Explorer e2 = new Explorer("mcd");
        assertTrue(e1.equals(e2));
    }

    @Test
    public void equalsFalseDifferent() {
        Explorer e1 = new Explorer("mcd");
        Explorer e2 = new Explorer("pbt");
        assertFalse(e1.equals(e2));
    }

    @Test
    public void equalsFalseOtherObject() {
        Explorer e1 = new Explorer("mcd");
        String e2 = "mcd";
        assertFalse(e1.equals(e2));
    }

    @Test
    public void equalsFalseNull() {
        Explorer e1 = new Explorer("mcd");
        assertFalse(e1.equals(null));
    }
    
    @Test
    public void getStateWhenArriveInCamp() {
        Explorer explorer = new Explorer("e1");
        explorer.takeDecisionToLeave();
        explorer.reachCamp(); 
        assertEquals(State.CAMPING, explorer.getState());
    }
}