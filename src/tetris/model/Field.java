package tetris.model;

import tetris.Figure;
import tetris.gui.Block;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingDeque;

public class Field {

    private final int width;
    private final int height;
    private final LinkedList<Block> blockList = new LinkedList<>(); // somit wird das Objekt beim erzeugen erstellt, sozusagen im Konstruktor

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Field (int width, int height) {
        this.height=height;
        this.width=width;
    }

    public void detectCollision ( Block[] blocks) throws CollisionException {
        for (Block b : blocks) {
            if (b.x < 0 || b.x > width-1) {
                throw new CollisionException("Berührt an X Achse");
            }
            if (b.y < 0 || b.y > height) {
                throw new CollisionException("Berührt an der Y Achse");
            }

            for (Block mein : this.blockList) {
                if (b.x == mein.x && b.y == mein.y) {
                        throw new CollisionException("Berührt an bestehendem Block");
                }
            }
        }
    }


    public void addBlocks(Block [] blocks) {
        for (Block b : blocks) { // nicht mit get i iterieren in einer LInked List!! Das ist teuer!
            blockList.add(b);
        }
    }

    public void removeAllBlocks() {
        blockList.clear();
    }

    public List<Block> getBlocks() {
       return this.blockList;

    }

    public boolean isRowFull(int y) {
        System.out.println("isRowFull() wird ausgeführt");
        int counter = 0;
        for (Block b : blockList) {
            if (b.y == y) {
                counter++;
            }
        }
        // wenn der counter auch 10 = feldhöhe hat --> return true
        if (counter==this.getWidth()) {
            return true;
        }
        return false;
    }
    // tut noch nichts.
    public int removeFullRows () {
        System.out.println("removeFullRows() wird ausgeführt");
        int numberOfRows=0;

        for (int i=this.getHeight()-1; i>=0; i-- ) { // für die ganze Höhe des Spielfeldes
            if (isRowFull(i)) {
                removeRow(i);
                numberOfRows++;
            }
        }
        System.out.println("Es wurden "+numberOfRows+" Rows entfernt.");
        return numberOfRows;
    }

    private void removeRow(int y) {
        System.out.println("removeRow() wird ausgeführt");
     /*
     Drei Varianten for the win:
     1. Liste rückwärts durchgehen
     2. Iterator brauchen    --> diese nutze ich.
     3. evtl. Rekursiv
      */
        Iterator<Block> iter = blockList.iterator();
        while (iter.hasNext()) {
            Block b = iter.next();
            if (b.y == y) {
                iter.remove();
            }  else if (b.y>y) {
                b.y--;
            }
        }
    }
}
