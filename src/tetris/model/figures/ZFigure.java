package tetris.model.figures;

import tetris.Figure;
import tetris.gui.Block;

public class ZFigure extends Figure {
    public ZFigure (int x, int y) {
        super(7,x, y);
        System.out.println("ZFigure");
        blockarray[0] = new Block(x,y,color);
        blockarray[1] = new Block(x,y+1,color);
        blockarray[2] = new Block(x-1,y+1,color);
        blockarray[3] = new Block(x+1,y,color);
    }
}
