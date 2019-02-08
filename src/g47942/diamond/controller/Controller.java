package g47942.diamond.controller;

import g47942.diamond.model.Explorer;
import g47942.diamond.model.Model;
import g47942.diamond.view.View;

/**
 * game dynamics.
 *
 * @author 
 */
public class Controller {

    private final Model game;
    private final View view;

    /**
     * Create a object who control game dynamics.
     *
     * @param game facade of game Diamond.
     * @param view interact with user's.
     */
    public Controller(Model game, View view) {
        this.game = game;
        this.view = view;
    }

    /**
     * This methode allows start the game.
     */
    public void startGame() { //déocuper en plusieurs méthodes
        boolean isThereNewExplorersToAdd = true;
        while (isThereNewExplorersToAdd) {
            game.addExplorer(view.askExplorer());
            if (game.isThereEnoughExplorer()) {
                game.start();
                isThereNewExplorersToAdd = view.isThereNewExplorerToAdd();
            }
        }

        while (!game.isOver()) {
            game.startNewExplorationPhase();
            view.displayNbCaveEntrance();
            while (!game.isExplorationPhaseOver()) {
                game.moveForward();
                view.displayGame();
                for (Explorer explorer : game.getExploringExplorers()) {
                    if (!view.askExplorerChoiceToContinue(explorer)) {
                        game.handleExplorerDecisionToLeave(explorer);
                    }
                }
                game.makeExplorersLeave();
                view.turnResumeDisplay();
            }
            game.endExplorationPhase();
            view.displayRunAway();
        }
        view.displayWinner();
    }
}
