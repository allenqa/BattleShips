package Controller;

/**
 * Created by allensu on 20/07/2016.
 */
public class MainController {
    /**
     * This class is created just to pass information between all the
     * ActionListeners and the Model.Game.
     */
    Model.Game _game;
    View.GameView _view;
    public MainController(Model.Game game){
        _game = game;
    }
    public void send(int location, boolean p2Location){
        _game.interact(location, p2Location);
        _view.redraw(_game.getBoard(p2Location), p2Location);
        _view.redraw(_game.getBoard(!p2Location), !p2Location);
    }
    public void addView(View.GameView view) {
        _view = view;
    }
}
