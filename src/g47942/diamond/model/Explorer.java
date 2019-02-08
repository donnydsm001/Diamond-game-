package g47942.diamond.model;

import g47942.diamond.color.Color;
import java.util.Objects;

/**
 * this class create players with his characteristic.
 *
 * @author 
 */
public class Explorer {

    private final String pseudonym;
    private final Bag bag;
    private State state;
    private final Chest chest;

    /**
     * creation object explorer contains name of player with his bag and his
     * state.
     *
     * @param pseudonym name of player
     */
    public Explorer(String pseudonym) {
        this.pseudonym = pseudonym;
        this.bag = new Bag();
        this.state = State.CAMPING;
        chest = new Chest();
    }

    /**
     * represents the bag of player.
     *
     * @return bag of player
     */
    public Bag getBag() {
        return bag;
    }

    /**
     * Represents the chest of explorer. (Use only for test)
     * @return chest of explorer.
     */
    public Chest getChest() {
        return chest;
    }
    
    /**
     * represent the pseudonym of player.
     *
     * @return his pseudonym.
     */
    public String getPseudonym() {
        return pseudonym;
    }

    /**
     * represent the state of player.
     *
     * @return his state
     */
    public State getState() {
        return state;
    }

    /**
     * represent number of rubies added in bag.
     *
     * @param gem number of rubies added in bag.
     */
    public void addToBag(Gem gem) {
        bag.addGem(gem);
    }

    /**
     * player take decision to leave cave.
     */
    public void takeDecisionToLeave() {
        this.state = State.LEAVING;
    }

    /**
     * number of rubies in the bag of an explorer.
     *
     * @return the number of rubies in the bag.
     */
    public int getFortune() {
        return chest.getValue();
    }

    /**
     * Explorer is going to camp.
     */
    public void reachCamp() {
        this.state = State.CAMPING;
        chest.saveBag(bag);
    }

    /**
     * Allows the explorer start exploration.
     */
    public void startExploration() {
        state = State.EXPLORING;
    }

    /**
     * Explorer arrive in camp after losing all his rubies.
     */
    public void runAway() {
        bag.loseContent();
        state = State.CAMPING;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.pseudonym);
        return hash;
    }

    /**
     * allows to compare 2 objects
     *
     * @param obj it is an other type object
     * @return return true if 2 objects are same.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Explorer other = (Explorer) obj;
        return pseudonym.equals(other.pseudonym);
    }

    /**
     * Transform a object to string
     *
     * @return the pseudonym of explorer.
     */
    @Override
    public String toString() {
        return Color.toGreen("Explorer{" + pseudonym + "}");
    }
}
