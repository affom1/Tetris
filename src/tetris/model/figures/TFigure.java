package tetris.model.figures;

import tetris.Figure;
import tetris.gui.Block;

public class TFigure extends Figure {
    public TFigure (int x, int y) {
        super(6,x, y);
        System.out.println("TFigure");
        blockarray[0] = new Block(x,y,color);
        blockarray[1] = new Block(x-1,y,color);
        blockarray[2] = new Block(x+1,y,color);
        blockarray[3] = new Block(x,y-1,color);
    }

}
