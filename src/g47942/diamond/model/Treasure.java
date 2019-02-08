package g47942.diamond.model;

import java.util.List;
import g47942.diamond.color.Color;
import java.util.ArrayList;

/**
 * this class create treasure tile with a number of rubies.
 * @author 
 */
public class Treasure implements Tile{

    private List<Gem> gems;
    private final int initNbGems;

    /**
     * create object Treasure and initialize the attributes of the class with a
     * number rubies.
     * @param rubies number of rubies.
     */
    public Treasure(int rubies) {
        gems = new ArrayList<>();
        initNbGems = rubies;
        addGemToList(initNbGems);
    }

    /**
     * create object Treasure and initialize the attributes of the class with a
     * random number of rubies between 1 and 15.
     */
    public Treasure() {
        gems = new ArrayList<>();
        int nbHasard = (int) (Math.random() * 15 + 1);
        initNbGems = nbHasard;
        addGemToList(initNbGems);
    }

    /**
     * represents gems on the Treasure.
     * @return gems types  on the Treasure.
     */
    public List<Gem> getGems() {
        return gems;
    }

    /**
     * represents the number of rubies present during its creation.
     * @return the number of rubies present during its creation.
     */
    public int getInitNbRubies() {
        return initNbGems;
    }

    /**
     * when exploring a treasure tile, the rubies found by the explorers are
     * shared between them fairly. Undelivered rubies are left behind.
     * @param explorers a list containing all players
     */
    @Override
    public void explore(List<Explorer> explorers) {
        int rubiesEachPlayer;
        if (!explorers.isEmpty()) {

            rubiesEachPlayer = (initNbGems / explorers.size());
            for(Explorer explorer : explorers){
                for (int i = 0; i < rubiesEachPlayer; i++) {
                    explorer.addToBag(Gem.RUBY);
                }
            }
            int gemRemaining = initNbGems % explorers.size();
            gems.clear();
            addGemToList(gemRemaining);
        }
    }

    /**
     * Add gem to list of attribu gems
     * @param nbGems number of gems added to list
     */
    private void addGemToList(int nbGems){
        for (int i = 0; i < nbGems; i++) {
            gems.add(Gem.RUBY);
        }
    }
    /**
     * Resets the value of the treasure to its original value.
     */
    public void restore() {
        addGemToList(initNbGems);
    }
    
    /**
     * This method moves rubies from instance "o" to the current tile.
     * @param o tile that we wanna moves rubies.
     */
    public void transferGemsFrom(Treasure o) {          
        if (!this.equals(o)) {
            gems.addAll(o.gems);
            o.gems.clear();
        }
    }
    
    /**
     * return number of rubies found and the remaining rubies after shared
     * between all players.
     * @return number of rubies found and the remaining rubies 
     */
    @Override
    public String toString() {
        int nbRubies = 0;
        for(Gem gem : gems){
            nbRubies += gem.getValue();
        }
        return Color.toBlue("rubies found = " + initNbGems+ ", remaining "
                            + "rubies = " + nbRubies); //@srv: pas de couleur dans le modèle.
    }
    
}
