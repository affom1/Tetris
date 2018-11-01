package tetris.model.figures;

import tetris.Figure;
import tetris.gui.Block;

import java.awt.*;

public class IFigure extends Figure {

    public IFigure(int x, int y) {
        super(1, x, y); // rot
        System.out.println("IFigure");
        blockarray[0] = new Block(x,y,color);
        blockarray[1] = new Block(x-1,y,color);
        blockarray[2] = new Block(x+1,y,color);
        blockarray[3] = new Block(x+2,y,color);
    }

//    public IFigure () {
//        super();
//        // auch möglich und schöner:
//        // Achtunng: dann muss rotate umgebaut werden!
//        blockarray[0] = new Block(x,y,color);
//        blockarray[1] = new Block(x-1,y,color);
//        blockarray[2] = new Block(x+1,y,color);
//        blockarray[3] = new Block(x+2,y,color);
//
//    }
}
