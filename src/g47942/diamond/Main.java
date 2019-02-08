package g47942.diamond;

import g47942.diamond.controller.Controller;
import g47942.diamond.model.Game;
import g47942.diamond.model.Model;
import g47942.diamond.view.View;

/**
 * Start the game.
 * @author 
 */
public class Main {
    
    /**
     * Create the model, view and controller by tying them together and start 
     * the game.
     * @param args not use
     */
    public static void main(String[] args) {
        Model game = new Game();
        View view = new View(game);
        Controller controller = new Controller(game, view);
        controller.startGame();
    }
}