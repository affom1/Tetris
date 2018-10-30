package tetris;

import tetris.gui.Block;

// Linepiece
public class Figure {

    public static int color;
    private Block[] blockarray = new Block[4];  // jede Figur hat vier Blöcke
    private boolean isTurned = false;           // BILLLLLLIG!!!

    public Figure (int color, int x, int y) {
        this.color = color;

        // auch möglich und schöner:
        // Achtunng: dann muss rotate umgebaut werden!
        blockarray[0] = new Block(x,y,color);
        blockarray[1] = new Block(x-1,y,color);
        blockarray[2] = new Block(x+1,y,color);
        blockarray[3] = new Block(x+2,y,color);

        // Achtung, somit ist es nicht in der MItte!
//        for (int i = 0;i<blockarray.length;i++) {
//            blockarray[i]= new Block(x, y, color);
//            x++;
//        }
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

       // hässliche for Schlaufe

//        for (int i = 0;i<blockarray.length;i++) {
//            blockarray[i].x +=dx;
//            blockarray[i].y +=dy;
//        }
    }

    public void rotate(int d) {
        // 1 == rechts 0 == links
        // hässssslich!!!!!

        int cx = blockarray[0].x;   // Vektor! Das Drehzentrum
        int cy = blockarray[0].y;   // Vektor! Das Drehzentrum

        for (Block block : blockarray) {
            int dx = block.x - cx;
            int dy = block.y - cy;
            block.x = cx + d * dy;
            block.y = cy - d * dx;

        }

//        if (i==1 && !isTurned) {
//            blockarray[0].y += 2;
//            blockarray[0].x += 2;
//
//            blockarray[1].y += 1;
//            blockarray[1].x += 1;
//
//            blockarray[3].y += -1;
//            blockarray[3].x += -1;
//            this.isTurned = true;
//        } else {
//            blockarray[3].y += 1;
//            blockarray[3].x += 1;
//
//            blockarray[0].y -= 2;
//            blockarray[0].x -= 2;
//
//            blockarray[1].y -= 1;
//            blockarray[1].x -= 1;
//            this.isTurned = false;
//        }
    }
}