package tetris;

import tetris.gui.Block;

import java.awt.*;

// Linepiece
public abstract class Figure {

    //Alternativ weglassen und erst in der IFigure Klassen implementieren
    protected final int color;

    /**
     *  final weil es nicht ändern soll --> robuste Programmierung. Attribute, wo man weiss, dass sie nicht ändern sollen am besten final deklarieren
     *  jede Figur hat vier Blöcke
     *  somit dürfen abgeleitete Methoden direkt darauf zugreifen
     *  protected weil es zwingend
     *
     */
    protected final Block[] blockarray = new Block[4];  //  //

    public Figure (int color, int x, int y) {
        this.color = color;
    }

//    public Figure (int x, int y) {
//
//    }

    public Block[] getBlocks() {
        return blockarray;
    }

    public void move(int dx, int dy) {
       // schöne for-Schlaufe --> Für jeden Block im BlockArray: mach das!
         /**
         *    diese Schleife ist Ressourcenschonender vor allem wenn LinkedLists verwendet werden
         *     Bei einer Linked List wird immer die gesamte Schlaufe durchlaufen
         *     Somit kann aber nicht ein Teil herausgenommen werden, somti kann aber der Block nicht geändert werden.
         */
        for (Block block : blockarray) {
            block.x +=dx;
            block.y +=dy;
        }

    }

    public void rotate(int d) {

        int cx = blockarray[0].x;   // Vektor! Das Drehzentrum
        int cy = blockarray[0].y;   // Vektor! Das Drehzentrum

        for (Block block : blockarray) {
            int dx = block.x - cx;
            int dy = block.y - cy;
            block.x = cx + d * dy;
            block.y = cy - d * dx;
        }

    }
}