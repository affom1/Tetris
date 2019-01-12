package tetris.model.figures;

import tetris.Figure;
import tetris.gui.Block;

import java.awt.*;

public class IFigure extends Figure {

    // private final static int COLOR =1;

    public IFigure(int x, int y) {
        super(1, x, y); // rot
        System.out.println("IFigure Marc");
        blockarray[0] = new Block(x,y,color);
        blockarray[1] = new Block(x-1,y,color);
        blockarray[2] = new Block(x+1,y,color);
        blockarray[3] = new Block(x+2,y,color);


    }

    @Override
    public String toString() {
        return "IFigure{}";
    }
}
