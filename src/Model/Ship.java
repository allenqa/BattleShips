package Model;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by allensu on 20/07/2016.
 */
public class Ship {
    //public
	private HashSet<Integer> _tiles;
    private static final Logger LOGGER = Logger.getLogger( Game.class.getName() );
	
    public Ship(int start, int end){
    	_tiles = new HashSet<>();
    	boolean sameY = (start / Game.SIZE) == (end / Game.SIZE);
    	int increment = sameY ? 1:Game.SIZE;
    	for (int i = Math.min(start, end); i <= Math.max(start, end); i += increment){
            _tiles.add(i);
        }
    }
    public boolean sank(){
    	return _tiles.isEmpty();
    }
    public void firedOn(int location){
    	_tiles.remove(location);
    	if (sank()){
    		LOGGER.log(Level.WARNING, "Ship Sank.");
    	}
    }
}
