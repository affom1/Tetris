package tetris.model;

import tetris.gui.Block;

public class Field {

    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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
        }

    }


}
