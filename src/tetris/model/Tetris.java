package tetris.model;

import tetris.Game;
import tetris.gui.GUI;




public class Tetris {

    public static void main(String[] args ) {
        System.out.println("Hello World");

        int width = 10;
        int height = 20;


        // somit kann auch args reingenommen werden
        try {
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);

        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {

        }
        Field field = new Field(width, height);
        GUI  myGui = new GUI(field.getWidth(), field.getHeight());
        Game game = new Game(myGui, field);
        game.start();


    }


}
