package g47942.diamond.model;

import g47942.diamond.color.Color;
import java.util.List;

/**
 * Class Relic
 * @author 
 */
public class Relic implements Tile{

    private int valueInDiamonds = 1;

    /**
     * represent the value on diamonds
     * @return number of diamonds
     */
    public int getValueInDiamonds() {
        return valueInDiamonds;
    }
    
    /**
     * returns true if the list contains only one explorer and this one 
     * left the cave, false otherwise.
     * @param explorers List of explorers who are exploring cave
     * @return true if the list contains only one explorer and this one 
     *         left the cave.
     */
    public boolean canBeTaken(List<Explorer> explorers) {
        boolean explorersSize1 = explorers.size() == 1;
        boolean explorerLeaving = explorers.get(0).getState() == State.LEAVING; //@srv attention si size == 0  tu auras une erreur
        return explorersSize1 && explorerLeaving;
    }
    
    /**
     * If exploring Relic tile, nothing happens.
     * @param explorers list of explorers who are exploring
     */
    @Override
    public void explore(List<Explorer> explorers) {}
    
    public void convertGemValue(int nbTakenRelics) {
        if (nbTakenRelics >= 3) {      //ésrv: utiliser des constantes
            valueInDiamonds = 2;
        }
    }

    @Override
    public String toString() {
        return Color.toBlue("Relic : " + valueInDiamonds + " Diamond");
    }
}