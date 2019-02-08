package g47942.diamond.model;

import java.util.List;
import g47942.diamond.color.Color;
import java.util.Objects;

/**
 * Class Hazard
 * Danger room
 * @author 
 */
public class Hazard implements Tile {

    private HazardType type;
    private boolean exlorersEscapeReason;

    /**
     * Represents the type of danger of the tile.
     *
     * @return the type of danger of the tile.
     */
    public HazardType getType() {
        return type;
    }

    /**
     * Allow identify the tiles that cause explorers to escape
     *
     * @return true if the tiles correspend of the tiles that cause explorers to
     * escape.
     */
    public boolean isExlorersEscapeReason() {
        return exlorersEscapeReason;
    }

    /**
     * Edit type of danger
     *
     * @param type type of danger
     */
    public void setType(HazardType type) {
        this.type = type;
    }

    /**
     * This methode make anything. If there are a danger card, the explorers
     * don't win rubies.
     *
     * @param explorers Explorer who have state exploration.
     */
    @Override
    public void explore(List<Explorer> explorers) {
    }

    /**
     * cause explorers to escape.
     */
    public void escape() {
        exlorersEscapeReason = true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.type);
        return hash;
    }

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
        final Hazard other = (Hazard) obj;
        return this.type.equals(other.type);

    }

    /**
     * Can show to users what is the type of card.
     *
     * @return type of card.
     */
    @Override
    public String toString() {
        return Color.toRed("Danger : " + type);
    }

}
