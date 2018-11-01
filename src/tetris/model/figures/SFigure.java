package tetris.model.figures;

import tetris.Figure;
import tetris.gui.Block;

public class SFigure extends Figure {

    public SFigure (int x, int y) {
        super(5,x, y);
        System.out.println("SFigure");
        blockarray[0] = new Block(x,y,color);
        blockarray[1] = new Block(x,y+1,color);
        blockarray[2] = new Block(x+1,y,color);
        blockarray[3] = new Block(x+1,y-1,color);
    }
}
