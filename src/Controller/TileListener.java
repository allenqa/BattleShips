package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by allensu on 20/07/2016.
 */
public class TileListener implements ActionListener{

    private int _tileLocation;
    private boolean _enemy;
    private MainController _mainController;
    public TileListener(int tileLocation, boolean enemy, MainController mainController){
        _tileLocation = tileLocation;
        _mainController = mainController;
        _enemy = enemy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        _mainController.send(_tileLocation, _enemy);
    }
}
