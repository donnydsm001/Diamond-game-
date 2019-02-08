package g47942.diamond.view;

import g47942.diamond.model.Explorer;
import g47942.diamond.model.Model;
import java.util.Scanner;
import g47942.diamond.color.Color;
import g47942.diamond.model.GameException;
import g47942.diamond.model.Tile;

/**
 * This class can interact with user's.
 *
 * @author 
 */
public class View {

    private final Scanner in;
    private final Model game;

    /**
     * create a object Vieuw for interact with the user.
     *
     * @param game new game
     */
    public View(Model game) {
        this.game = game;
        this.in = new Scanner(System.in);
    }

    /**
     * ask user enter his pseudonym of his explorer.
     *
     * @return pseudonym of user.
     */
    public Explorer askExplorer() {
        System.out.println(Color.toYellow("Enter your pseudonym : "));
        Explorer exp = new Explorer(this.in.next());
        return exp;
    }

    /**
     * Ask if have an author player.
     *
     * @return true if there are an author player.
     */
    public boolean isThereNewExplorerToAdd() {
        System.out.println(Color.toCyan("Is there  new explorer to add?"));
        return charToBoolean();
    }

    /**
     * Ask to explorer if he wanna continue exploring.
     *
     * @param explorer explorer who take decision.
     * @return true if he wanna continue exploring.
     */
    public boolean askExplorerChoiceToContinue(Explorer explorer) {
        System.out.println(Color.toGreen(explorer.getPseudonym())
                + Color.toCyan(", do you want to continue?"));
        return charToBoolean();
    }

    /**
     * translate char to boolean.
     * @return true if the first char matches of first char of positif response.
     * @throws GameException if the first char not matches of first char of 
     * positif or negatif response.
     */
    private  boolean charToBoolean() {
        char firstChar = 'z';
        while(firstChar != 'o'){
            firstChar = in.next().charAt(0);
            try{
                switch (firstChar) {
                    case 'y': case 'Y': case 'T': case 't':
                        return true;
                    case 'n':case 'N': case 'f': case 'F':
                        return false;
                    default:
                        throw new GameException("The first char not matches of"
                               + " first char of positif or negatif response.");
                }
            }
            catch (GameException e) {
                System.out.println("I don't understand, can you repeat?");
            }
        }
        return false;
    }
    
    /**
     * show the number of cave opene.
     */
    public void displayNbCaveEntrance(){
        System.out.println("Cave : " + (game.getCave().getNbExploredEntrance() 
                            + 1 )+ " /5");
    }
    
    /**
     * show the state of the game during the game.
     */
    public void displayGame() {
        System.out.println(Color.toPurple("------Display-game------"));
        System.out.println(
                game.getCave().getCurrentEntrance().getLastDiscoveredTile());
        for (Explorer explorer : game.getExploringExplorers()) {
            System.out.println(explorer + ": " + explorer.getBag());
        }
    }

    /**
     * show the winner of the game.
     */
    public void displayWinner() {
        if (this.game.isExplorationPhaseOver()) {
            System.out.println(Color.toBlue("\n \n ***---------THE-END---------"
                                                 + "*** ") + '\n');
            System.out.println("The winner is " + game.getWinner() + " with "
                    + Color.toGreen(game.getWinner().getFortune()
                            + " rubies."));
        }
    }

    /**
     * Show turn resume of display.
     */
    public void turnResumeDisplay() {
        System.out.println(Color.toYellow("------Resume-Display------"));
        for (Tile tile : game.getCave().getCurrentEntrance().getPath()) {
            System.out.println(tile);
        }
        
        for (Explorer explorer : game.getExploringExplorers()) {
            System.out.println(explorer + " " + explorer.getState());
        }
    }

    /**
     * show to users that the exploration is finished.
     */
    public void displayRunAway() {
        System.out.println(Color.toRed(" \n ****The-exploration-is-finished****"));
        System.out.println();
    }
}
