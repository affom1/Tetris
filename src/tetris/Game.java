package tetris;

import tetris.gui.ActionEvent;
import tetris.gui.ActionHandler;
import tetris.gui.GUI;
import tetris.gui.Block;
import tetris.model.figures.*;


public class Game {

    //private Block block;
    private final GUI gui; // sollte Konstante sein, damit es nicht ausgewechselt werden kann. darum Final
    private Figure figure;  // die Figur jedoch nicht final --> aber nur ein Figure nicht IFigure oder was auch immer --> Polymorphism
    private Game.FigureController figureController;

    public Game (GUI gui) {
        this.gui = gui;
    }  // Ein Konstruktor // Einmal zugewiesen, danach sit GUI fix.

    public void start() {
        createFigure(((gui.getFieldWidth() -1)/2),gui.getFieldHeight()-1);
        updateGui();

        // erzeugen sie in der Klasse Game einen FicureController
        figureController = new FigureController();

        // registrieren sie den FigureController beim GUI.
        gui.setActionHandler(figureController);

    }

    public void updateGui() {
        this.gui.clear();
        Block [] temp;
        temp = figure.getBlocks();
        for (int i = 0; i<figure.getBlocks().length; i++) {
            gui.drawBlock(temp[i]);
        }
    }

    public void createFigure(int x, int y) {

        // generiere  Random
        int random = (int) (Math.random()*7);

//
//        int random = 2;

        switch (random) {
            case 0: this.figure = new IFigure(x, y); break;
            case 1: this.figure = new JFigure(x, y); break;
            case 2: this.figure = new LFigure(x, y); break;
            case 3: this.figure = new OFigure(x, y); break;
            case 4: this.figure = new SFigure(x, y); break;
            case 5: this.figure = new TFigure(x, y); break;
            case 6: this.figure = new ZFigure(x, y); break;
            default:
                System.out.println("Zahl nicht zwischen 0 und 6");
                break;
        }

    }
    private class FigureController implements ActionHandler {

        public void moveDown() {
            figure.move(0, -1);
            updateGui();
        }

        public void moveLeft() {
            figure.move(-1, 0);
            updateGui();

        }

        public void moveRight() {
            figure.move(1, 0);
            updateGui();
        }

        public void rotateLeft() {
            figure.rotate(1);
            updateGui();
        }

        public void rotateRight() {
            figure.rotate(-1);
            updateGui();
        }

        public void drop() {
            // TODO: 06.11.2018  noch leer lassen
        }

    }


}
