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

        } catch (ArrayIndexOutOfBoundsException ex) {

        }

        GUI  myGui = new GUI(width, height);
        Game game = new Game(myGui);
        game.start();


    }







}
