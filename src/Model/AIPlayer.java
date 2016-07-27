package Model;

import java.util.Stack;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by allensu on 22/07/2016.
 */
public class AIPlayer extends Player {

    private static final Logger LOGGER = Logger.getLogger( Game.class.getName() );
    Stack<Integer> _aiControls;

    public AIPlayer(){
        /**
         * AI players have very similar traits to normal players,
         * but have an aiShoot function and all of the relevant
         * resources.
         */
        super();
        _aiControls = new Stack<>();
        for (int i = 0; i < Game.SIZESQUARED; i++){
            _aiControls.push(i);
        }
        Collections.shuffle(_aiControls);
    }
//    @Override
//    public void generateShip(int location){
//
//    }
    public int aiShoot(){
        /*
         * AI shoots randomly by popping out of a randomized stack
         */
        if (_aiControls.empty()){
            LOGGER.log(Level.SEVERE, "All Player Tiles Shot already");
            return -1;
        }
        return _aiControls.pop();
    }
}
