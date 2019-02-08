package g47942.diamond.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author radoin
 */
public class RelicTest {

    @Test
    public void ValueOfEachDiamondsWhenExplorerTakeMax3RelicTuiles() {
        Cave cave = new Cave();
        List<Relic> listRelic = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            listRelic.add(new Relic());
            listRelic.get(i).convertGemValue(cave.getNbTakenRelics());
            cave.incrementNbTakenRelics();
        }
        
        int valueExcepted = 1;
        
        assertEquals(valueExcepted, listRelic.get(listRelic.size()-1).getValueInDiamonds());
    }

    @Test
    public void ValueOfEachDiamondsWhenExplorerTake4Or5RelicTuiles() {
        Cave cave = new Cave();
        List<Relic> listRelic = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            listRelic.add(new Relic());
            listRelic.get(i).convertGemValue(cave.getNbTakenRelics());
            cave.incrementNbTakenRelics();
        }
        
        int valueExcepted = 2;
        
        assertEquals(valueExcepted, listRelic.get(listRelic.size() - 1).getValueInDiamonds());
    }

    @Test
    public void testCanBeTakenWith1ExplorerLeavingCave() {
        Game game = new Game();
        Explorer e1 = new Explorer("e1");
        game.addExplorer(e1);
        e1.takeDecisionToLeave();
        Relic relic = new Relic();
        assertTrue(relic.canBeTaken(game.getExplorers()));
    }

    @Test
    public void testCannotBeTakenWith2ExplorerLeavingCave() {
        Game game = new Game();
        Explorer e1 = new Explorer("e1");
        Explorer e2 = new Explorer("e2");
        game.addExplorer(e1);
        game.addExplorer(e2);
        e1.takeDecisionToLeave();
        e2.takeDecisionToLeave();
        Relic relic = new Relic();
        assertFalse(relic.canBeTaken(game.getExplorers()));
    }
}
