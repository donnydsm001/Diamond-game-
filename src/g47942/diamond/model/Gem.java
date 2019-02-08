package g47942.diamond.model;

/**
 * Type of rubies.
 * 
 * @author 
 */
public enum Gem {
    DIAMOND(5), RUBY(1);
    
    private final int value;

    /**
     * Constructor Gem 
     * @param value value of gem.
     */
    private Gem(int value) {
        this.value = value;
    }
    
    /**
     * represents the value of gem
     * @return value of gem
     */
    public int getValue() {
        return value;
    }
    
}
