package g47942.diamond.model;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TreasureTest {

    @Test
    public void contructorWithoutAttribut() {
        Treasure treasure = new Treasure();
        int initNbRubies = treasure.getInitNbRubies();
        int rubies = 0;
        for (Gem gem : treasure.getGems()){
            rubies += gem.getValue();
        }
        boolean rubiesEqualsInitNbRubies = rubies == initNbRubies;
        boolean inBoundaries = (1 <= rubies && rubies <= 15);

        assertTrue(rubiesEqualsInitNbRubies && inBoundaries);
    }

    @Test
    public void contructorWithAttribut() {
        Treasure treasure = new Treasure(42);
        int initNbRubies = treasure.getInitNbRubies();
        int rubies = 0;
        for (Gem gem : treasure.getGems()){
            rubies += gem.getValue();
        }
        assertTrue(initNbRubies == rubies && rubies == 42);
    }

    @Test
    public void exploredByNoExplorer() {
        Treasure treasure = new Treasure();
        treasure.explore(Arrays.asList());
    }

    @Test
    public void exploredBy1Explorer() {
        Explorer e1 = new Explorer("e1");
        Treasure treasure = new Treasure(4);
        treasure.explore(Arrays.asList(e1));
        int rubies = 0;
        for (Gem gem : treasure.getGems()){
            rubies += gem.getValue();
        }
        assertEquals(0, rubies);
    }

    @Test
    public void exploredBy2Explorers() {
        Explorer e1 = new Explorer("e1");
        Explorer e2 = new Explorer("e2");
        Treasure treasure = new Treasure(3);
        treasure.explore(Arrays.asList(e1, e2));
        int rubies = 0;
        for (Gem gem : treasure.getGems()){
            rubies += gem.getValue();
        }
        assertEquals(1, rubies);
    }
}