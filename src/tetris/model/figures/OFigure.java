package tetris.model.figures;

import tetris.Figure;
import tetris.gui.Block;

public class OFigure extends Figure {

    public OFigure (int x, int y) {
        super(4,x, y);
        System.out.println("OFigure");
        blockarray[0] = new Block(x,y,color);
        blockarray[1] = new Block(x+1,y,color);
        blockarray[2] = new Block(x,y-1,color);
        blockarray[3] = new Block(x+1,y-1,color);
    }

    public void rotate (int i) {
        System.out.println("nothing happens");
    }


}
