package View;

import Controller.MainController;
import Controller.TileListener;
import Model.Game;

import java.awt.*;
import javax.swing.*;

/**
 * Created by allensu on 20/07/2016.
 */
public class Board {
    /**
     * Abstract Board class created so it can be inherited to create both boards
     */
    private JPanel _panel;
    private GridTile[] _tiles;
    private MainController _mainController;
    public Board(int size, boolean enemy, MainController mainController){
        _mainController = mainController;
        _panel = new JPanel(new GridLayout(size, size));
        _tiles = new GridTile[size * size];
        for (int i = 0; i < size * size; i++){
            /**
             * Board Creates Tiles and passes it the location as an integer.
             */
            _tiles[i] = new GridTile(enemy);
            _tiles[i].addActionListener(new TileListener(i, enemy, _mainController));
            _panel.add(_tiles[i]);
        }
    }
    public JPanel getPanel(){
        return _panel;
    }
    public void redraw(Game.TileState[] tiles){
        for (int i = 0; i < tiles.length; i++){
            _tiles[i].updateTyle(tiles[i]);
        }
    }
}
