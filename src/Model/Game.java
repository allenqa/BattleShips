package Model;

import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

public class Game {
    /*
     * Game creates two arrays of TileStates, defaulting on TileState.UNTOUCHED
     * This keeps track of how the game is going.
     */
    private static final Logger LOGGER = Logger.getLogger( Game.class.getName() );
    public static final int SIZE = 12;
    public static final int SIZESQUARED = SIZE * SIZE;
    private Player player1;
    private Player player2;
    private GameState _gameState;
    private boolean _p2IsAI = true;
    private boolean _p2Turn = false;

    public enum TileState{
        UNTOUCHED, SHIPPLACED, SHOTMISSED, SHOTHIT
    }

    private enum GameState{
        STARTED, PLACEMENT, ENDED
    }

    public Game(){
        /*
         * Game constructor makes the game, and initializes it.
         * import variables:
         * _playerTiles, _enemyTiles, _gameState, _locationStorage, _enemyTurn, _aiControls, _gameSize.
         * The tile arrays save the condition of player1 and enemy tiles.
         * The gameState saves whether the game is started, in placement, or ended
         * _enemyTurn acts like a lock to make sure Players don't attack on player2's turn.
         * _aiControls is a stack of locations that the player2 pops to decide which tile to attack.
         */
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        Logger.getAnonymousLogger().addHandler(handler);
        LOGGER.setLevel(Level.ALL);
        //Fuck you logger. Fuck you.

        player1 = new AIPlayer();
        player2 = new AIPlayer();
        _gameState = GameState.PLACEMENT;
    }

    public void startGame(){
        _gameState = GameState.STARTED;
    }

    public void interact(int location, boolean p2Location){
        /*
         * interact(int location, boolean p2turn) notifies the game where
         * it is clicked on an outside view.
         */
        if (_gameState == GameState.PLACEMENT) {
            if (p2Location){
                player2.generateShip(location);
            }
            else{
                player1.generateShip(location);
            }
            if (player1.finishedPlacement && player2.finishedPlacement){
                startGame();
            }
        }
        if (_gameState == GameState.STARTED){
            /*
            TODO: Two players. Currently only does vs. AI
             */
            if (p2Location && !_p2Turn) {
                //getFiredOn returns true if you miss.
                _p2Turn = player2.getFiredOn(location);
                if (_p2IsAI){
                    while(_p2Turn){
                        _p2Turn = !player1.getFiredOn(((AIPlayer)player2).aiShoot());
                    }
                }
            }
        }
    }
    public TileState[] getBoard(boolean p2){
        /*
         * getBoard(boolean p2turn) returns the condition of a board for the views/controller.
         * boolean p2turn tells the Game if you want the p2turn board of the player1 board.
         */
        if (p2){
            return player2.getPlayerBoard();
        }
        return player1.getPlayerBoard();
    }
}