package g47942.diamond.model;

import java.util.List;

/**
 * Interface Tile.
 * @author 
 */
public interface Tile {
    
    /**
     * when exploring a treasure tile, the rubies found by the explorers are
     * shared between them fairly. Undelivered rubies are left behin.
     * If exploring a Hasard or Relic tile, nothing happens.
     * @param explorers a list containing all players who are exploring.
     */
    void explore(List<Explorer> explorers);
}
