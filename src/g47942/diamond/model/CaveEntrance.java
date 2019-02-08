package g47942.diamond.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manage an entrance to the cave.
 *
 * @author 
 */
public class CaveEntrance {

    private Tile lastDiscoveredTile;
    private final List<Tile> path;
    private boolean lockedOut;
    private final Cave cave;
    private boolean unsafe;
    private boolean treasureFound;
    private Treasure firstTreasureTile;

    /**
     * Create object CaveEntrace.
     *
     * @param cave cave of caveEntrance.
     */
    public CaveEntrance(Cave cave) {
        path = new ArrayList<>();
        this.cave = cave;
        treasureFound = false;
    }

    /**
     * Can change value of attribut. (Use only for test)
     *
     * @param firstTreasureTile first Treasure Tile in path
     */
    public void setFirstTreasureTile(Treasure firstTreasureTile) {
        this.firstTreasureTile = firstTreasureTile;
    }

    /**
     * Represents last tile treasure discovery.
     *
     * @return Last tile treasure discovered.
     */
    public Tile getLastDiscoveredTile() {
        return lastDiscoveredTile;
    }

    /**
     * the path is unsafe?
     *
     * @return true if the path is unsafe.
     */
    public boolean isUnsafe() {
        return unsafe;
    }

    /**
     * Represents list of treasure discovered (path of cave).
     *
     * @return list of treasure discovered.
     */
    public List<Tile> getPath() {
        return path;
    }

    /**
     * Represents if an entry is condemned or not.
     *
     * @return true if an entry is condemned.
     */
    public boolean isLockedOut() {
        return lockedOut;
    }

    /**
     * Declare the entry condemned.
     */
    public void lockOut() {
        lockedOut = true;
    }

    /**
     * The explorers discover a new Tile room.
     *
     * @param explorers The explorer who discover a new tile room.
     */
    public void discoverNewTile(List<Explorer> explorers) {
        Tile newTile = cave.getDeck().getTile();
        if (newTile instanceof Hazard) {
            for (Tile tile : path) {
                if (tile.equals(newTile)) {
                    unsafe = true;
                    ((Hazard) newTile).escape();
                }
            }
        }
        if (newTile instanceof Treasure && !treasureFound) {
            treasureFound = true;
            firstTreasureTile = (Treasure) newTile;
        }
        if (newTile instanceof Relic) {
            int nbRelicInPath = cave.getNbTakenRelics();
            ((Relic) newTile).convertGemValue(nbRelicInPath);
            cave.incrementNbTakenRelics();
        }
        newTile.explore(explorers);
        lastDiscoveredTile = newTile;
        path.add(newTile);
    }

    /**
     * Explorers who toke decision to leave cave are browse the route to camp.
     * This explorers toke also the rubies found when they come back and it is
     * shared between them.
     *
     * @param explorers Explorers who toke decision to leave
     */
    public void returnToCamp(List<Explorer> explorers) { //@srv: découper en plusieurs méthodes.
        if (!explorers.isEmpty()) {
            for (int i = 0; i < path.size(); i++) {
                if (path.get(i) instanceof Relic) {
                    if (((Relic) path.get(i)).canBeTaken(explorers)) {
                        int valueDiamonds = ((Relic) path.get(i)).getValueInDiamonds();
                        for (int j = 1; j <= valueDiamonds; j++) {
                            explorers.get(0).addToBag(Gem.DIAMOND);
                        }
                        path.remove(path.get(i));
                        i--;
                    }
                }
            }
            if (firstTreasureTile != null) {
                firstTreasureTile.explore(explorers);
            }
            
            for (Explorer explorer : explorers) {
                explorer.reachCamp();                
            }
        }
    }

    /**
     * This methode help only for test.
     *
     * @param treasure treasure added to list of path.
     */
    public void addTileToPath(Treasure treasure) {
        path.add(treasure);
    }

    /**
     * Transfer gems of current tile from the first tile Tresure
     */
    public void makeLastTileExplored() {
        if (path.get(path.size() - 1) instanceof Treasure) {
            firstTreasureTile.transferGemsFrom((Treasure) path.get(path.size() - 1));
        }
    }
}
