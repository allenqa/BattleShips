package View;

import Model.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by allensu on 20/07/2016.
 */

@SuppressWarnings("serial")
public class GridTile extends JButton{
    private Color _color;
    private boolean _p2;

    public GridTile(boolean p2){
        _p2 = p2;
        this.setOpaque(true);
        this.setBorderPainted(true);
        if (p2){
            _color = Color.BLACK;
        }
        else{
            _color = Color.WHITE;
        }
        this.setPreferredSize(new Dimension(40, 40));
        this.setContentAreaFilled(false);
    }

    public void setColor(Color c){
        if (_color == c){
            return;
        }
        _color = c;
        repaint();
    }

    public void updateTyle(Game.TileState state){
        switch(state){
            case UNTOUCHED:
                if (_p2){
                    setColor(Color.BLACK);
                }
                else{
                    setColor(Color.WHITE);
                }
                break;
            case SHIPPLACED:
                if (_p2){
                    setColor(Color.BLACK);
                }
                else{
                    setColor(Color.CYAN);
                }
                break;
            case SHOTHIT:
                setColor(Color.RED);
                break;
            case SHOTMISSED:
                setColor(Color.ORANGE);
                break;
        }
    }

    public void paintComponent(Graphics g) {
        g.setColor(_color);
        g.fillRect(0, 0, getSize().width, getSize().height);
        super.paintComponent(g);
    }
}
