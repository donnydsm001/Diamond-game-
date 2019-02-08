package g47942.diamond.model;

/**
 * Represents the cave with 5 entrances.
 * @author 
 */
public class Cave {
    
    private final int NB_MAX_CAVE_ENTRANCE = 5;
    private int nbExploredEntrance;
    private CaveEntrance currentEntrance;
    private final Deck deck;
    private int nbTakenRelics;

    /**
     * create object Cave.
     */
    public Cave() {
        nbExploredEntrance = 0;
        deck = new Deck();
        nbExploredEntrance = 0;
    }

    /**
     * Represents the number of relic used.
     * @return  the number of relic used.
     */
    public int getNbTakenRelics() {
        return nbTakenRelics;
    }

    
    /**
     * Represents number of explored entrance of cave.
     * @return number of explored entrance of cave.
     */
    public int getNbExploredEntrance() {
        return nbExploredEntrance;
    }

    /**
     * represents the deck of game.
     * @return the deck of game.
     */
    public Deck getDeck() {
        return deck;
    }
    
    /**
     * represents the last open entry.
     * @return the last open entry.
     */
    public CaveEntrance getCurrentEntrance() {
        return currentEntrance;
    }
    
    /**
     * Less than 5 entries have been explored.
     * @return true if less than 5 entries have been explored.
     */
    public boolean hasNewEntranceToExplore() {
        return nbExploredEntrance < NB_MAX_CAVE_ENTRANCE;
    }
    
    /**
     * Opens a new entrance to the cave.
     * @throws GameException if The previous phase of game is not over or 
     * yet opened 5 entrances of cave!
     */
    public void openNewEntrance() {
        if (nbExploredEntrance > 0 && !currentEntrance.isLockedOut()
                || !hasNewEntranceToExplore()) {
            throw new GameException ("The previous phase of game is not over or"
                                     + " yet opened 5 entrances of cave!");
        }
        currentEntrance = new CaveEntrance(this);
    }
    
    /**
     * allows to condemn the current entrance of the cave and to increment 
     * the number of caves explored.
     * @throws GameException If the game phase is not in progress.
     */
    public void lockOutCurrentEntrance() {
        if (currentEntrance.isLockedOut()) {
            throw new GameException ("The game phase is not in progress!");
        }
        currentEntrance.lockOut();
        nbExploredEntrance++;
    }
    
    /**
     * The last entrance is dangerous.
     * @return true if the last entrance is dangerous.
     */
    public boolean isLastEntranceUnsafe() {
        return currentEntrance.isUnsafe();  
    }
    
    /**
     * increment by 1 the number of relics removed of deck.
     */
    public void incrementNbTakenRelics(){
        nbTakenRelics++;
    }
}
