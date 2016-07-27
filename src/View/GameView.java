package View;

import Controller.MainController;
import javax.swing.*;
import java.awt.*;

import Model.Game;
import Model.Game.TileState;

/**
 * Created by allensu on 20/07/2016.
 */
public class GameView {

    private JFrame _frame;
    private Board _playerBoard;
    private Board _enemyBoard;

    public GameView(MainController mainController){
        _frame = new JFrame();
        _frame.setVisible(true);
        _frame.setLayout(new BorderLayout());

        _playerBoard = new Board(Game.SIZE, false, mainController);
        _frame.add(_playerBoard.getPanel(), BorderLayout.WEST);

        _enemyBoard = new Board(Game.SIZE, true, mainController);
        _frame.add(_enemyBoard.getPanel(), BorderLayout.EAST);

        _frame.pack();
    }
    public void redraw(TileState[] tiles, boolean enemy){
        if (enemy){
            _enemyBoard.redraw(tiles);
        }
        else{
            _playerBoard.redraw(tiles);
        }
    }
}
