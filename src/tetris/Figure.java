package tetris;

import tetris.gui.Block;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

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

    @Override
    public String toString() {
//        return "Figure{" + "Die Figure "+getClass().getSimpleName()+
//                " color=" + color +
//                ", blockarray=" + Arrays.toString(blockarray) +
//
//                '}';

        String neu= "Die Figur: ";
        neu = neu+ getClass().getSimpleName().toString();
        for (int i = 0; i<blockarray.length;i++) {
            neu = neu+"Block "+i+ " hat Koordinaten "+convert(blockarray[i]);

        }
        return neu;
    }
    private String convert (Block block) {
        return "(" + block.x+")"+ " ("+block.y+")";
    }

    @Override
    public boolean equals(Object o) {

        // 1. return true, wenn es das selbe Objekt ist (== Prüfung)
        if (this == o) return true;
        // 2. return false, wenn es keine Figur ist oder wenn es null ist
        if (o==null || getClass() != o.getClass()) return false;

        //3. Caste das Objekt in eine Figur
        Figure figure = (Figure) o;
        // vergleiche alle Attribute miteinander und returne true, wenn sie stimmen, oder false wenn sie nicht gleich sind.
        return color == figure.color &&
                Arrays.equals(blockarray, figure.blockarray);
    }



    @Override
    public int hashCode() {
        int result = Objects.hash(color);
        result = 31 * result + Arrays.hashCode(blockarray);
        return result;
    }

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