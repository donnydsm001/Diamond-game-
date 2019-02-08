package g47942.diamond.model;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class CaveTest {

    @Test
    public void discoverNewTreasureSameSharing() {                     
        Explorer e1 = new Explorer("e1");
        Explorer e2 = new Explorer("e2");
        CaveEntrance cave = new CaveEntrance(new Cave());
        e1.startExploration();
        e2.startExploration();
        cave.discoverNewTile(Arrays.asList(e1, e2));
        assertTrue(e1.getBag().equals(e2.getBag()));
    }

    @Test
    public void addTreassureToPath() {
        CaveEntrance cave = new CaveEntrance(new Cave());
        cave.addTileToPath(new Treasure());
        cave.addTileToPath(new Treasure());
        cave.addTileToPath(new Treasure());

        assertEquals(3, cave.getPath().size());
    }

    @Test
    public void returnToCampWith1Explorer() {
        Explorer e1 = new Explorer("e1");
        CaveEntrance cave = new CaveEntrance(new Cave());
        Treasure treasure = new Treasure(4);
        cave.setFirstTreasureTile(treasure);
        cave.addTileToPath(treasure);
        cave.returnToCamp(Arrays.asList(e1));
        assertEquals(4, e1.getFortune());
    }

    @Test
    public void returnToCampWith2Explorer() {
        Explorer e1 = new Explorer("e1");
        Explorer e2 = new Explorer("e2");
        CaveEntrance cave = new CaveEntrance(new Cave());
        Treasure treasure = new Treasure(5);
        cave.setFirstTreasureTile(treasure);
        cave.addTileToPath(treasure);
        cave.returnToCamp(Arrays.asList(e1, e2));
        assertEquals(2, e1.getFortune());
    }
    
    @Test
    public void returnToCampByNoExplorer() {
        CaveEntrance cave = new CaveEntrance(new Cave());
        cave.returnToCamp(Arrays.asList());
    }
    
    @Test
    public void nb1ExploredEntrance() {
        Cave cave = new Cave();
        cave.openNewEntrance();
        cave.lockOutCurrentEntrance();
        assertEquals(1, cave.getNbExploredEntrance());
    }
    
    @Test
    public void nb2ExploredEntrance() {
        Cave cave = new Cave();
        cave.openNewEntrance();
        cave.lockOutCurrentEntrance();
        cave.openNewEntrance();
        cave.lockOutCurrentEntrance();
        assertEquals(2, cave.getNbExploredEntrance());
    }
    
    @Test(expected = GameException.class)
    public void phasIsNotRogress() {
        Cave cave = new Cave();
        cave.openNewEntrance();
        cave.lockOutCurrentEntrance();
        cave.lockOutCurrentEntrance();
    }
    
    @Test(expected = GameException.class)
    public void canNotOpenNewEntranceCauseOfNbMaxEntraceIs5() {
        Cave cave = new Cave();
        cave.openNewEntrance();
        cave.lockOutCurrentEntrance();
        cave.openNewEntrance();
        cave.lockOutCurrentEntrance();
        cave.openNewEntrance();
        cave.lockOutCurrentEntrance();
        cave.openNewEntrance();
        cave.lockOutCurrentEntrance();
        cave.openNewEntrance();
        cave.lockOutCurrentEntrance();
        cave.openNewEntrance();
    }
    
    @Test(expected = GameException.class)
    public void canNotOpenNewEntranceCauseOfPreviousPhaseNotEnd() {
        Cave cave = new Cave();
        cave.openNewEntrance();
        cave.lockOutCurrentEntrance();
        cave.openNewEntrance();
        cave.openNewEntrance();
    }
}
