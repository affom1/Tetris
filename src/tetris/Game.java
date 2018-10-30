package tetris;

import tetris.Figure;
import tetris.gui.ActionEvent;
import tetris.gui.GUI;
import tetris.gui.Block;



public class Game {

    //private Block block;
    private final GUI gui; // sollte Konstante sein, damit es nicht ausgewechselt werden kann. darum Final
    private Figure figure;  // die Figur jedoch nicht final

    public Game (GUI gui) {
        this.gui = gui;
    }  // Ein Konstruktor // Einmal zugewiesen, danach sit GUI fix.

    public void start() {
        //createBlock();
        // bereits einmal Aufrufen, falls es erwünscht ist, dass der Punkt bereits im GUI erscheint.

        createFigure(((gui.getFieldWidth() -1)/2),gui.getFieldHeight()-1);
        updateGui();
        while (true) {
            ActionEvent e = gui.waitEvent();
            handleEvent (e);
            updateGui();
        }

    }

//    public void createBlock() {
//        this.block = new Block(4,18,18);
//
//    }
    public void updateGui() {
        this.gui.clear();
        Block [] temp;
        temp = figure.getBlocks();
        for (int i = 0; i<figure.getBlocks().length; i++) {
            gui.drawBlock(temp[i]);
        }

//        this.gui.drawBlock(this.block);
    }

    public void handleEvent(ActionEvent e) {
        // schöner wäre es mit Switch Case
//        switch (e) {
//            case MOVE_DOWN:
//                figure.move(0, -1);
//                break;  // schöner mit break;
//            case MOVE_LEFT:
//                figure.move(-1, 0);
//                break;
//            default:
//                break;
//        }

        if (e == ActionEvent.MOVE_DOWN) figure.move(0, -1);
        if (e == ActionEvent.MOVE_LEFT) figure.move(-1, 0);
        if (e == ActionEvent.MOVE_RIGHT) figure.move(1, 0);
        if (e == ActionEvent.ROTATE_LEFT) figure.rotate(1);
        if (e== ActionEvent.ROTATE_RIGHT) figure.rotate(-1);
    }

    public void createFigure(int x, int y) {
        this.figure = new Figure(20, x,y);
    }


}
