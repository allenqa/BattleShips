package Model;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashSet;

/**
 * Created by allensu on 22/07/2016.
 */
public class Player {

    private Game.TileState[] _tiles;
    private int _locationStorage = -1;
    private static final Logger LOGGER = Logger.getLogger( Game.class.getName() );
    private HashSet<Integer> _shipsToPlace;
    public boolean finishedPlacement = false;
    private HashSet<Ship> _shipsUnsank;

    public Player(){
    	_shipsToPlace = new HashSet<Integer>();
    	for (int i = 5; i > 0; i--){
    		_shipsToPlace.add(i);
    	}
        _tiles = new Game.TileState[Game.SIZESQUARED];
        Arrays.fill(_tiles, Game.TileState.UNTOUCHED);
        _shipsUnsank = new HashSet<Ship>();
    }
    public Game.TileState[] getPlayerBoard(){
        return _tiles;
    }
    public void generateShip(int location){
        /*
         * generateShip is called when a tile is clicked while in the GameState.PLACEMENT
         * The first click will store the location of the tile clicked into the _locationStorage
         * The second click will then check if the location is compatible to place a ship, and
         * if it does is it will place the ship, remove ship from remaining ships
         * If the click is deemed invalid, it will not do anything. Either way the second click
         * resets _locationStorage back to -1.
         */
        LOGGER.log(Level.FINE, () ->
                String.format("Ship generator method is called. Location's %d and %d. -1 indicates" +
                        "that it is the first click.", _locationStorage, location));
        if (_locationStorage == -1){
            _locationStorage = location;
            return;
        }
        boolean sameX = (_locationStorage % Game.SIZE) == (location % Game.SIZE);
        boolean sameY = (_locationStorage / Game.SIZE) == (location / Game.SIZE);

        if (!sameX && !sameY){
            _locationStorage = -1;
            LOGGER.log(Level.WARNING, "Ship Placement Isn't Straight %");
            return;
        }

        int shipLength = sameY ? Math.abs(location - _locationStorage) + 1 :
                Math.abs(_locationStorage - location) / Game.SIZE + 1;

        LOGGER.log(Level.WARNING, () -> String.format("shipLength of ship being placed is %d", shipLength));

        int increment = sameY ? 1:Game.SIZE;
        /*
         * Increment = _gameSize if on same Y axis. Otherwise, it is 1.
         * Run through for loop using Increment to check if existing ship.
         * Otherwise, place ship using Increment (basically x or y axis
         * increase).
         */


        boolean taken = false;
        for (int i = Math.min(_locationStorage, location); i <= Math.max(_locationStorage, location); i += increment){
            if (_tiles[i] != Game.TileState.UNTOUCHED){
                taken = true;
                if (_tiles[i] != Game.TileState.SHIPPLACED){
                    LOGGER.log(Level.SEVERE, "Somehow there is a non-UNTOUCHED/SHIPPLACED Tile....");
                }
            }
        }
        if (!taken){
            if (!_shipsToPlace.contains(shipLength)){
                _locationStorage = -1;
            	LOGGER.log(Level.WARNING, "Invalid Ship Length");
            	return;
            }
            _shipsToPlace.remove(shipLength);
            LOGGER.log(Level.INFO, "Placing Ship");
            for (int i = Math.min(_locationStorage, location); i <= Math.max(_locationStorage, location); i += increment){
                _tiles[i] = Game.TileState.SHIPPLACED;
            }
            _shipsUnsank.add(new Ship(_locationStorage, location));
        }
        else{
            LOGGER.log(Level.WARNING, "Ship has already been placed there.");
        }
        if (_shipsToPlace.isEmpty()){
            finishedPlacement = true;
        }
        _locationStorage = -1;
    }
    public boolean getFiredOn(int location){
        /*
         * getFiredOn checks whether or not the turn should end.
         * returns true only if a shot hits an untouched position.
         */
        switch (_tiles[location]) {
            case UNTOUCHED:
                _tiles[location] = Game.TileState.SHOTMISSED;
                LOGGER.log(Level.INFO, "Shot Missed");
                return true;
            case SHIPPLACED:
                _tiles[location] = Game.TileState.SHOTHIT;
                LOGGER.log(Level.INFO, "Shot Hit");
                for(Ship s: _shipsUnsank){
                	s.firedOn(location);
                	if (s.sank()){
                		_shipsUnsank.remove(s);
                	}
                	if (_shipsUnsank.isEmpty()){
                		LOGGER.log(Level.WARNING, "All ships sank. Game should end.");
                	}
                }
                return false;
            default:
                return false;
        }
    }
}
