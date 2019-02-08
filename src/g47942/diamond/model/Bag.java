package g47942.diamond.model;

import g47942.diamond.color.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class creates a bag whit a number of rubies in the bag.
 * @author 
 */
public class Bag {
    
    protected List<Gem> gems;     
    
    /**
     * creation object bag.
     */
    public Bag() {
        gems = new ArrayList<>();
    }
    /**
     * Add gems in the bag.
     * @param gem type of rubies added in the bag.
     */
    public void addGem(Gem gem) {
        gems.add(gem);
    }
    /**
     * Getteur of gems
     * @return list of gems
     */
    public List<Gem> getGems() {
        return gems;
    }
    
    /**
     * represents the gems in bag
     * @return  number total of gems in bag.
     */
    public int getValue() {
        int nbRubies = 0;
        for(Gem gem: gems){
            nbRubies += gem.getValue();
        }
        return nbRubies;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.gems);
        return hash;
    }
    
    /**
     * lose all rubies that explorers found.
     */
    public void loseContent() {
        gems.clear();
    }
    
    /**
     * allows to compare 2 objects
     * @param obj it is an other type object
     * @return return true if 2 objects are same.
     */
    @Override
    public boolean equals( Object obj) {   
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bag other = (Bag) obj;
        return this.gems.equals(other.gems);
    }

    /**
     * Transform the object on string.
     * @return  string with number of rubies in the bag
     */
    @Override
    public String toString() {
        return Color.toBlue("number of rubies = "  + getValue());
    }
}
