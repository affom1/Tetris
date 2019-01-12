package tetris;

import tetris.gui.ActionEvent;
import tetris.gui.ActionHandler;
import tetris.gui.GUI;
import tetris.gui.Block;
import tetris.model.CollisionException;
import tetris.model.Field;
import tetris.model.Scoring;
import tetris.model.figures.*;

import java.lang.management.ManagementFactory;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.Objects;


public class Game {

    //private Block block;
    private final GUI gui; // sollte Konstante sein, damit es nicht ausgewechselt werden kann. darum Final
    private Figure figure;  // die Figur jedoch nicht final --> aber nur ein Figure nicht IFigure oder was auch immer --> Polymorphism
    private FigureController figureController;
    private Field field;
    private final Scoring score;

    public Game (GUI gui, Field field) {
        this.gui = gui;
        this.field = field;
        this.score = new Scoring();
    }  // Ein Konstruktor // Einmal zugewiesen, danach sit GUI fix.

    public void start() {
        figureController = new FigureController();

        figureController.start();
        createFigure(((gui.getFieldWidth() -1)/2),gui.getFieldHeight()-2);
        updateGui();
        gui.setActionHandler(figureController);

        // erzeugen sie in der Klasse Game einen FicureController


        // registrieren sie den FigureController beim GUI. // das GUI ruft uns auf wenn es einen Event gibt
        // darum muss ein Interface, eine Schnittstelle definiert werden
        // wenn eine Klasse ein Interface inmplementiert, dann muss jede Methode des Interface implementiert werden.


    }



    public void updateGui() {
        this.gui.clear();
        Block[] temp = null;

        try {
            temp = figure.getBlocks();
            for (int i = 0; i<figure.getBlocks().length; i++) {
                gui.drawBlock(temp[i]);
            }
        } catch (NullPointerException e) {
            System.out.println("Am Ende nicht mehr möglich.");
        }

        if (field.getBlocks()!=null) {
            LinkedList<Block> tempField = (LinkedList<Block>) field.getBlocks();
            for (Block b : tempField) {
                gui.drawBlock(b);
            }
        }
        gui.setScore(score.getScore());
        gui.setHighScore(score.getHighscore());
        gui.setLevel(score.getLevel());
    }

    public void createFigure(int x, int y) {

        // generiere  Random
        int random = (int) (Math.random()*7);

//
//        random = 0;
        if (figureController!=null) {
            switch (random) {
                case 0:
                    this.figure = new IFigure(x, y);
                    break;
                case 1:
                    this.figure = new JFigure(x, y);
                    break;
                case 2:
                    this.figure = new LFigure(x, y);
                    break;
                case 3:
                    this.figure = new OFigure(x, y);
                    break;
                case 4:
                    this.figure = new SFigure(x, y);
                    break;
                case 5:
                    this.figure = new TFigure(x, y);
                    break;
                case 6:
                    this.figure = new ZFigure(x, y);
                    break;
                default:
                    System.out.println("Zahl nicht zwischen 0 und 6");
                    break;
            }
            try {
                field.detectCollision(figure.getBlocks());
            } catch
            (CollisionException ex) {
                if (ex.getMessage().equals("Berührt an bestehendem Block")) stop();
            }
        }
    }

    public void figureLanded (Block [] blocks) {
        field.addBlocks(figure.getBlocks());
        score.updateScore(field.removeFullRows());
        createFigure(((gui.getFieldWidth() -1)/2),gui.getFieldHeight()-1); // eventuell Minus 2 so läufts suberer
    }


    public void stop () {
        gui.setActionHandler(null);
        figureController.interrupt(); // zuerst den Thread unterbrechen.
        figureController = null;
        this.figure = null;
        score.updateHighScore();
        updateGui();
        System.out.println("Game Over");
    }



    // darum muss ein Interface, eine Schnittstelle definiert werden
    // wenn eine Klasse ein Interface inmplementiert, dann muss jede Methode des Interface implementiert werden
    // private weil, sie nur innerhalb der Klasse gebraucht wird.
    private class FigureController extends Thread implements ActionHandler{

        @Override
        public void moveDown() {
            figure.move(0, -1);
            try {
                field.detectCollision(figure.getBlocks());
            }
            catch (CollisionException c) {
                System.err.println("Error: "+c.getMessage());
                figure.move(0,1);
                // nur wenn es der dritte Fall der DetectCollistion auslöst.
                if (c.getMessage().equals("Berührt an bestehendem Block")) figureLanded(figure.getBlocks());
                if (c.getMessage().equals("Berührt an der Y Achse")) figureLanded(figure.getBlocks());
            }
            updateGui();
        }
        @Override
        public void moveLeft() {
            figure.move(-1, 0);
            try {
                field.detectCollision(figure.getBlocks());
            }
            catch (CollisionException c) {
                System.err.println("Error: "+c.getMessage());
                figure.move(1,0);
            }

            updateGui();

        }
        @Override
        public void moveRight() {
            figure.move(1, 0);
            try {
                field.detectCollision(figure.getBlocks());
            }
            catch (CollisionException c) {
                System.err.println("Error: "+c.getMessage());
                figure.move(-1,0);
            }
            updateGui();
        }
        @Override
        public void rotateLeft() {
            figure.rotate(1);
            try {
                field.detectCollision(figure.getBlocks());
            }
            catch (CollisionException c) {
                System.err.println("Error: "+c.getMessage());
                figure.rotate(-1);
            }
            updateGui();
        }
        @Override
        public void rotateRight() {
            figure.rotate(-1);
            try {
                field.detectCollision(figure.getBlocks());
            }
            catch (CollisionException c) {
                System.err.println("Error: "+c.getMessage());
                figure.rotate(1);
            }
            updateGui();
        }
        @Override
        public void drop() {
           try {
               while (true) {
                   figure.move(0,-1);
                   field.detectCollision(figure.getBlocks());
                   updateGui();
//                   try {
//                       Thread.sleep(200);
//                   } catch (InterruptedException e) {
//                       e.printStackTrace();
//                   }

               }
           } catch (CollisionException c) {
               System.err.println("Error: "+c.getMessage());
               figure.move(0,1);
               // nur wenn es der dritte Fall der DetectCollistion auslöst.
               updateGui();
           }
        }

    public void run () {

        // zuerst Mal schlaffen Todo: Den Schlaf durch das Level verkürzen.
        while (!Thread.interrupted()) { // solange nicht interrupted tu das.
            try {
                float factor = giveFactor(score.getLevel());
                int sleepInMillis = (int) (1000*factor);

                System.out.println("Schlafen in Millisek: "+sleepInMillis);
                Thread.sleep(sleepInMillis);
                //Thread.sleep((long) (1000*Math.exp(-0.1 * score.getLevel())));
                moveDown();
            } catch (InterruptedException e) {
                e.getMessage();
                System.err.println("interrupted? kommts überhaupt");
            }
            // danach move nach unten
//            if (figure!=null) {
//                figure.move(0,-1);
//            }
//            updateGui();
        }

    }

        private float giveFactor(int level) {
            switch (level) {
                case 0: return 1.0f;
                case 1: return 0.8f;
                case 2: return 0.6f;
                case 3: return 0.5f;
                case 4: return 0.4f;
                case 5: return 0.3f;
                case 6: return 0.28f;
                case 7: return 0.26f;
                case 8: return 0.24f;
                case 9: return 0.22f;

                default:return 0.2f;
            }
        }

    }
}
