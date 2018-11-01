package tetris.model.figures;

import tetris.Figure;
import tetris.gui.Block;

public class JFigure extends Figure {

    public JFigure (int x, int y) {
        super(2,x, y);
        System.out.println("JFigure");
        blockarray[0] = new Block(x,y,color);
        blockarray[1] = new Block(x-1,y,color);
        blockarray[2] = new Block(x,y+1,color);
        blockarray[3] = new Block(x,y+2,color);
    }

    // todo but Why?
    // public void rotate(int d);


}
