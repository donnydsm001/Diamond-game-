package g47942.diamond.model;

/**
 * Class chest.
 * This class extends to class Bag.
 * @author 
 */
public class Chest extends Bag{

    /**
     * Constructor of Chest.
     * Call the contructor of Bag.
     */
    public Chest() {
        super();
    }
    
    /**
     * Transfer all gems in the bag to the chest of explorer.
     * @param bag bag of explorer.
     */
    public void saveBag(Bag bag) {
        this.gems.addAll(bag.gems);
        bag.gems.clear();
    }
}
