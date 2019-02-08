package g47942.diamond.model;

import java.util.ArrayList;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void addAndGetExplorerGoodNumberOfExplorers() {
        Game game = new Game();
        Explorer e1 = new Explorer("sdr");
        Explorer e2 = new Explorer("mcd");

        game.addExplorer(e1);
        game.addExplorer(e2);

        assertEquals(2, game.getExplorers().size());
    }

    @Test
    public void addAndGetExplorerGoodExplorers() {
        Game game = new Game();
        Explorer e1 = new Explorer("sdr");
        Explorer e2 = new Explorer("mcd");

        game.addExplorer(e1);
        game.addExplorer(e2);

        /*
         * If this test is false, check if you implement the
         * Explorer's equals method...
         */
        assertTrue(game.getExplorers().contains(e1)
                && game.getExplorers().contains(e2));
    }

    @Test
    public void treatChoiceToLeave() {
        Game game = new Game();
        Explorer e1 = new Explorer("pbt");
        game.addExplorer(e1);
        game.startNewExplorationPhase();
        game.handleExplorerDecisionToLeave(e1);
        assertTrue(e1.getState() == State.LEAVING);
    }

    @Test(expected = GameException.class)
    public void treatChoiceToLeaveException() {
        Game game = new Game();
        Explorer e1 = new Explorer("pbt");
        game.handleExplorerDecisionToLeave(e1);
    }

    @Test
    public void moveForwardExploringExplorerGetRubies() {
        Game game = new Game();
        Explorer e1 = new Explorer("sdr");
        Explorer e2 = new Explorer("pbt");
        game.addExplorer(e1);
        game.addExplorer(e2);
        game.startNewExplorationPhase();
        game.handleExplorerDecisionToLeave(e1);
        game.moveForward();
        assertTrue(e2.getBag().getValue() >= 0);
        /* if rubies = 0 that means 
                                                     * that means that the first 
                                                     * card is a dangerous card.
         */
    }

    @Test
    public void moveForwardLeavingExplorerDoNotGetRubies() {
        Game game = new Game();
        Explorer e1 = new Explorer("sdr");
        Explorer e2 = new Explorer("pbt");
        game.addExplorer(e1);
        game.addExplorer(e2);
        game.startNewExplorationPhase();
        game.handleExplorerDecisionToLeave(e1);
        game.moveForward();
        assertTrue(e1.getBag().getValue() == 0);
    }

    @Test
    public void isExplorationPhaseOverNoExplorers() {
        Game game = new Game();
        assertTrue(game.isExplorationPhaseOver());
    }

    @Test
    public void isOverExploringExplorer() {
        Game game = new Game();
        Explorer e1 = new Explorer("sdr");
        game.addExplorer(e1);
        assertFalse(game.isOver());
    }

    @Test
    public void isOverExplorerIsLeaving() {
        Game game = new Game();
        Explorer e1 = new Explorer("sdr");
        game.addExplorer(e1);
        game.startNewExplorationPhase();
        game.handleExplorerDecisionToLeave(e1);
        assertTrue(game.isExplorationPhaseOver());
    }

    @Test
    public void getExploringExplorers() {
        Game game = new Game();
        Explorer e1 = new Explorer("pbt");
        Explorer e2 = new Explorer("sdr");
        e1.startExploration();
        e2.startExploration();
        game.addExplorer(e1);
        game.addExplorer(e2);
        game.handleExplorerDecisionToLeave(e1);
        List<Explorer> exploringExplorers = game.getExploringExplorers();
        assertTrue(exploringExplorers.size() == 1
                && exploringExplorers.contains(e2));
    }

    @Test(expected = GameException.class)
    public void canNotStartWith2Players() {
        Game game = new Game();
        Explorer e1 = new Explorer("aa");
        Explorer e2 = new Explorer("bb");

        game.addExplorer(e1);
        game.addExplorer(e2);
        game.start();
    }

    @Test(expected = GameException.class)
    public void canNotStartOrAddWith9OrMoreplayers() {
        Game game = new Game();
        Explorer e1 = new Explorer("aa");
        Explorer e2 = new Explorer("bb");
        Explorer e3 = new Explorer("cc");
        Explorer e4 = new Explorer("dd");
        Explorer e5 = new Explorer("ee");
        Explorer e6 = new Explorer("ff");
        Explorer e7 = new Explorer("gg");
        Explorer e8 = new Explorer("hh");
        Explorer e9 = new Explorer("ii");

        game.addExplorer(e1);
        game.addExplorer(e2);
        game.addExplorer(e3);
        game.addExplorer(e4);
        game.addExplorer(e5);
        game.addExplorer(e6);
        game.addExplorer(e7);
        game.addExplorer(e8);
        game.addExplorer(e9);

        game.start();
    }

    @Test
    public void thereIsEnoughExplorer() {
        boolean yes = true;
        boolean maybe;
        Game game = new Game();
        Explorer e1 = new Explorer("aa");
        Explorer e2 = new Explorer("bb");
        Explorer e3 = new Explorer("cc");
        Explorer e4 = new Explorer("dd");

        game.addExplorer(e1);
        game.addExplorer(e2);
        game.addExplorer(e3);
        game.addExplorer(e4);

        maybe = game.isThereEnoughExplorer();

        assertEquals(yes, maybe);
    }

    @Test
    public void canAddOtherExplorer() {
        boolean yes = true;
        boolean maybe;
        Game game = new Game();
        Explorer e1 = new Explorer("aa");
        Explorer e2 = new Explorer("bb");
        Explorer e3 = new Explorer("cc");
        Explorer e4 = new Explorer("dd");
        Explorer e5 = new Explorer("ee");
        Explorer e6 = new Explorer("ff");
        Explorer e7 = new Explorer("gg");

        game.addExplorer(e1);
        game.addExplorer(e2);
        game.addExplorer(e3);
        game.addExplorer(e4);
        game.addExplorer(e5);
        game.addExplorer(e6);
        game.addExplorer(e7);

        maybe = game.isItPossibleToAddExplorer();

        assertEquals(yes, maybe);
    }

    @Test
    public void getWinnerByE2() {
        Game game = new Game();
        Explorer e1 = new Explorer("pbt");
        Explorer e2 = new Explorer("sdr");
        game.addExplorer(e1);
        game.addExplorer(e2);
        game.startNewExplorationPhase();
        game.getExplorers().get(0).getChest().gems.add(Gem.RUBY);
        game.getExplorers().get(1).getChest().gems.add(Gem.DIAMOND);
        game.handleExplorerDecisionToLeave(e1);
        game.handleExplorerDecisionToLeave(e2);
        assertEquals(game.getExplorers().get(1), game.getWinner());
    }

    @Test
    public void getWinnerByE1() {
        Game game = new Game();
        Explorer e1 = new Explorer("pbt");
        Explorer e2 = new Explorer("sdr");
        game.addExplorer(e1);
        game.addExplorer(e2);
        game.startNewExplorationPhase();
        game.getExplorers().get(0).getChest().gems.add(Gem.DIAMOND);
        game.getExplorers().get(1).getChest().gems.add(Gem.RUBY);
        game.handleExplorerDecisionToLeave(e1);
        game.handleExplorerDecisionToLeave(e2);
        assertEquals(game.getExplorers().get(0), game.getWinner());
    }

    @Test
    public void listSizeOfExplorersLeavingCave() {
        List<Explorer> explorersLeaveCave = new ArrayList<>();
        explorersLeaveCave.add(new Explorer("e1"));
        explorersLeaveCave.add(new Explorer("e2"));
        explorersLeaveCave.add(new Explorer("e3"));

        assertEquals(3, explorersLeaveCave.size());
    }

    @Test(expected = GameException.class)
    public void explorerNotInCaveCanNotDecideToLeave() {
        Game game = new Game();
        Explorer e1 = new Explorer("e1");
        game.addExplorer(e1);
        game.handleExplorerDecisionToLeave(e1);
    }
    
    @Test (expected = GameException.class)
    public void canNotWinnerIfGameNotEnd (){
        Game game = new Game();
        Explorer e1 = new Explorer("e1");
        game.addExplorer(e1);
        game.startNewExplorationPhase();
        game.getWinner();
    }
}
