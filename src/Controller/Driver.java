package Controller;

import javax.swing.*;
/**
 * Created by allensu on 20/07/2016.
 */
public class Driver {
    public static void main(String[] args){
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }

        Model.Game game = new Model.Game();
        Controller.MainController mainController = new MainController(game);
        View.GameView gameView = new View.GameView(mainController);
        mainController.addView(gameView);
        //game.startGame();
    }
}
