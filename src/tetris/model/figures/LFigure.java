package tetris.model.figures;

import tetris.Figure;
import tetris.gui.Block;

public class LFigure extends Figure {

    public LFigure (int x, int y) {
        super(3,x, y);
        System.out.println("LFigure");
        blockarray[0] = new Block(x,y,color);
        blockarray[1] = new Block(x+1,y,color);
        blockarray[2] = new Block(x,y+1,color);
        blockarray[3] = new Block(x,y+2,color);
    }


}
