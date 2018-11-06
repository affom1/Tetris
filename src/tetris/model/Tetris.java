package tetris.model;



import javazoom.jl.player.Player;
import tetris.Game;
import tetris.gui.GUI;


import java.io.FileInputStream;
import java.io.IOException;


public class Tetris {

    public static void main(String[] args ) {
        System.out.println("Hello World");
//        play();

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
    public static void play () {
        String file = "C:/Users/marc_/OneDrive/Shared Shit/Tetris.mp3";
        try{
            FileInputStream fis = new FileInputStream(file);
            Player playMP3 = new Player(fis);
            playMP3.play();
        }
        catch(Exception exc){
            exc.printStackTrace();
            System.out.println("Failed to play the file.");
        }

    }

}
