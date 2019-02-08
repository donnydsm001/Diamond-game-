package g47942.diamond.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Facade of Diamond.
 *
 * @author 
 */
public class Game implements Model {

    private final List<Explorer> explorers;
    private final Cave cave;
    private final int NB_MIN_EXPLORER = 3;
    private final int NB_MAX_EXPLORER = 8;

    /**
     * Create a new game.
     */
    public Game() {
        this.explorers = new ArrayList<>();
        this.cave = new Cave();
    }

    /**
     * This methode can be used to add new player (explorer) in the game.
     *
     * @param explorer the explorer to add.
     * @throws GameException If it isn't possible to add a new player in the
     * game!
     */
    @Override
    public void addExplorer(Explorer explorer) {

        if (!isItPossibleToAddExplorer()) {
            throw new GameException("Max 8 players !!");
        } else {
            explorers.add(explorer);
        }
    }

    /**
     * Make all exploring explorers move forward in the cave. The explorers
     * share what they found.
     */
    @Override
    public void moveForward() {
        cave.getCurrentEntrance().discoverNewTile(getExploringExplorers());
        if (isExplorationPhaseAborted()) {
            for (Explorer explorer : getExploringExplorers()) {
                explorer.runAway();
            }
        }
    }

    /**
     * Declares if it's the end of the exploration phase.
     *
     * @return true if it's the end of the exploration phase.
     */
    @Override
    public boolean isExplorationPhaseOver() {
        return getExploringExplorers().isEmpty();
    }

    /**
     * Declares if it's the end of the game.
     *
     * @return true if it's the end of the game.
     */
    @Override
    public boolean isOver() {
        return isExplorationPhaseOver() && !cave.hasNewEntranceToExplore();
    }

    /**
     * Return the cave of the game.
     *
     * @return the game's cave.
     */
    @Override
    public Cave getCave() {
        return cave;
    }

    /**
     * Give all explorers of the game. They could be exploring or leaving.
     *
     * @return all the explorers of the game.
     */
    @Override
    public List<Explorer> getExplorers() {
        return explorers;
    }

    /**
     * Give all explorers which are exploring.
     *
     * @return explorers in the cave.
     */
    @Override
    public List<Explorer> getExploringExplorers() {
        List<Explorer> list = new ArrayList<>();
        for (Explorer exp : explorers) {
            if (exp.getState() == State.EXPLORING) {
                list.add(exp);
            }
        }
        return list;
    }

    /**
     * Consider the choice of the explorer to leave the cave.
     *
     * @param explorer The explorer who make the choice to leave.
     * @throws GameException If the explorer is not in the current game.
     */
    @Override
    public void handleExplorerDecisionToLeave(Explorer explorer) {
        if (!getExploringExplorers().contains(explorer)) {
            throw new GameException("The explorer is not in the cave !");//ésrv: + explorer name.
        } else {
            explorer.takeDecisionToLeave();
        }
    }

    /**
     * Check the number of registered explorers respects the rules (between 3
     * and 8 players).
     *
     * @throws GameException If the number of players is not correct.
     */
    @Override
    public void start() {
        if (explorers.size() < NB_MIN_EXPLORER
                || explorers.size() > NB_MAX_EXPLORER) {
            throw new GameException("The number of players is not correct !"   + " (between 3 and 8 players)"); //@srv: utiliser les constantes
        }
    }

    /**
     * return true if there are at least 3 explorers.
     *
     * @return true if there are at least 3 explorers.
     */
    @Override
    public boolean isThereEnoughExplorer() {
        return explorers.size() >= NB_MIN_EXPLORER;
    }

    /**
     * return true if it is possible to add a new player in the game.
     *
     * @return true if it is possible to add a new player in the game.
     */
    @Override
    public boolean isItPossibleToAddExplorer() {
        return explorers.size() < NB_MAX_EXPLORER;
    }

    /**
     * The explorer with the greatest number of rubies.
     *
     * @return Explorer with the greatest number of rubies.
     * @throws GameException If this method is called before the end of the game
     */
    @Override
    public Explorer getWinner() {
        Explorer theWinner = new Explorer("");
        if (isExplorationPhaseOver()) {
            int winnerNbRubies = -1;
            for (Explorer explorer : explorers) {
                if (winnerNbRubies < explorer.getFortune()) {
                    theWinner = explorer;
                    winnerNbRubies = explorer.getFortune();
                }
            }
        } else {
            throw new GameException("The game is not end");
        }
        return theWinner;
    }

    /**
     * allows all explorers wishing to leave the cave. Create a new list with
     * those players.
     */
    @Override
    public void makeExplorersLeave() {
        cave.getCurrentEntrance().makeLastTileExplored();
        List<Explorer> explorersLeaveCave = new ArrayList<>();
        for (Explorer exp : explorers) {  
            if (exp.getState() == State.LEAVING) {
                explorersLeaveCave.add(exp);
            }
        }
        cave.getCurrentEntrance().returnToCamp(explorersLeaveCave);
    }

    /**
     * open a new entrance and the statu of explorer change to EXPLORING
     */
    @Override
    public void startNewExplorationPhase() {
        cave.openNewEntrance();
        for (Explorer exp : explorers) {
            exp.startExploration();
        }
    }

    /**
     * The current entrance is finish. Resets the value of the treasure to its
     * original value and allows to put back a card in the deck.
     */
    @Override
    public void endExplorationPhase() {
        cave.lockOutCurrentEntrance();
        for (Tile tile : cave.getCurrentEntrance().getPath()) {
            if (tile instanceof Treasure) {
                ((Treasure) tile).restore();
                cave.getDeck().putBack(tile);
            } else {
                if (tile instanceof Hazard && !((Hazard) tile).isExlorersEscapeReason()) {
                    cave.getDeck().putBack(tile);
                }
            }
        }
    }

    /**
     * The current entrance is dangerous.
     *
     * @return true if the current entrance is dangerous.
     */
    public boolean isExplorationPhaseAborted() {
        return cave.isLastEntranceUnsafe();
    }
}
