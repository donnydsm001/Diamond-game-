package g47942.diamond.model;

import java.util.List;

/**
 * Facade of Diamant.
 * @see <a href="https://en.wikipedia.org/wiki/Facade_pattern">
 *          Facade pattern
 *      </a>
 * @see <a href="https://fr.wikipedia.org/wiki/Fa%C3%A7ade_(patron_de_conception)">
 *          FaÃ§ade (patron de conception)
 *      </a>
 * @author EsiProf
 */
public interface Model {
    /**
     * This methode can be used to add new player (explorer) in the game.
     * @param explorer the explorer to add.
     * @throws GameException If it is not possible to add a new explorer.
     */
    void addExplorer(Explorer explorer);

    /**
     * Make all exploring explorers move forward in the cave.
     * The explorers share what they found.
     */
    void moveForward();

    /**
     * Declares if it's the end of the game.
     * @return true if it's the end of the game.
     */
    boolean isOver();
    
    /**
     * Declares if it's the end of the exploration phase.
     *
     * @return true if it's the end of the exploration phase.
     */
    boolean isExplorationPhaseOver();

    /**
     * Return the cave of the game.
     * @return the game's cave.
     */
    Cave getCave();

    /**
     * Give all explorers of the game. They could be exploring or leaving.
     * @return all the explorers of the game.
     */
    List<Explorer> getExplorers();

    /**
     * Give all explorers which are exploring.
     * @return explorers in the cave.
     */
    List<Explorer> getExploringExplorers();

    /**
     * Consider the choice of the explorer to leave the cave.
     * @param explorer The explorer who make the choice to leave.
     * @throws GameException If the explorer is not in the current game.
     */
    void handleExplorerDecisionToLeave(Explorer explorer);
    
    /**
     * check the number of registered explorers respects the rules.
     * @throws GameException If the number of players is not correct.
     */
    void start();
    
    /**
     * return true if there are at least 3 explorers.
     * @return true if there are at least 3 explorers.
     */
    boolean isThereEnoughExplorer();
    
    /**
     * return true if it is possible to add a new player in the game.
     * @return true if it is possible to add a new player in the game.
     */
    boolean isItPossibleToAddExplorer();
    
    /**
     * The explorer with the greatest number of rubies. 
     * @return Explorer with the greatest number of rubies. 
     * @throws GameException If this method is called before the end of the game
     */
    Explorer getWinner();
    
    /**
     * allows all explorers wishing to leave the cave.
     */
    void makeExplorersLeave();
    
    /**
     * open a new entrance and the statu of explorer change to EXPLORING
     */
    void startNewExplorationPhase();
    
    /**
     * The current entrance is finish.
     */
    void endExplorationPhase();
}
