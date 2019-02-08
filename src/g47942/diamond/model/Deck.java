package g47942.diamond.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class Deck.
 * contains all tuiles of game.
 * @author 
 */
public class Deck {

    private final List<Tile> tiles;

    /**
     * Create a new deck with 35 cards.
     */
    public Deck() {

        tiles = new ArrayList<>();
        int[] deckOfTreasure = {1,2,3,4,5,5,7,7,9,11,11,13,14,15,17}; //ésrv: constante

        for (int deckValueTreasure : deckOfTreasure) {
            tiles.add(new Treasure(deckValueTreasure));
        }
        
        for (int i = 0; i < 3; i++) {
            Hazard batteringRam = new Hazard();
            batteringRam.setType(HazardType.BATTERING_RAM);
            Hazard giantSpiders = new Hazard();
            giantSpiders.setType(HazardType.GIANT_SPIDERS);
            Hazard lavaField = new Hazard();
            lavaField.setType(HazardType.LAVA_FIELD);
            Hazard snakes = new Hazard();
            snakes.setType(HazardType.SNAKES);
            Hazard stoneBall = new Hazard();
            stoneBall.setType(HazardType.STONE_BALL);
            tiles.add(batteringRam);
            tiles.add(giantSpiders);
            tiles.add(lavaField);
            tiles.add(snakes);
            tiles.add(stoneBall);
        }
        
        for (int i = 0; i < 5; i++) { //@srv: constantes
            tiles.add(new Relic());
        }
    }

    /**
     * Represents the list of Tile. (we use this methode only for test)
     *
     * @return list of Tile.
     */
    List<Tile> getTiles() {
        return tiles;
    }

    /**
     * allows return a random card to the deck.
     *
     * @return return random card to the deck.
     */
    public Tile getTile() {
        Collections.shuffle(tiles);
        Tile randomCard = tiles.get(0);
        tiles.remove(randomCard);
        return randomCard;
    }

    /**
     * allows to put back a card in the deck.
     *
     * @param tile get back tile in deck.
     */
    public void putBack(Tile tile) {
        tiles.add(tile);
    }
}
