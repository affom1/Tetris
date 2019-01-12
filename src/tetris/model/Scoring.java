package tetris.model;

import java.io.*;

public class Scoring {

    private int level;
    private int score;
    private int highscore = 0;
    private static int SCORE_PER_LEVEL = 1500;
    private static int [] SCORE_REWARDS = new int[4];
    private static final String HIGH_SCORE_FILE = "C:/Users/marc_/IdeaProjects/Tetris/data/highscore.marc";
    /*
    Wenn als String HIGH_SCORE_FILE nur ein Name angegeben würde, würde es im package src tetris landen.
    Das ist das current Working directory. Jedes Programm hat ein "Start in:"
    Es gibt auch immer ein User: Home. um dort zu speichern könnte man:
    System.getProperty("user.home")+"/highscore.marc"; // so wird im User Home gespeichert.
     */

    public Scoring () {
        this.level=0;
        this.score=0;
        SCORE_REWARDS = new int[]{0,40,100,300,1200};
        loadHighScore();
    }

    private void loadHighScore() {
        try {
            FileInputStream fis = new FileInputStream(HIGH_SCORE_FILE);
            DataInputStream dis = new DataInputStream(fis);
            this.highscore = dis.readInt();
            dis.close();
        } catch (IOException e){
            System.out.println("IOException : "+e);
            System.out.println("Highscore konnte nicht geladen werden und wird auf 0 gesetzt.");
            highscore = 0;
        }
    }
    private void saveHighscore() {
        try {
            FileOutputStream fos = new FileOutputStream(HIGH_SCORE_FILE);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeInt(highscore);
            dos.close();
            }
        catch (IOException e)
            { System.out.println("IOException : " + e);
                System.err.println("Highscore konnte nicht abgespeichert werden.");
        }

    }


    public void updateHighScore () {
        if (this.score > this.getHighscore()){
            this.highscore = this.score;
            System.out.println("highscore geschlagen, Gratulation");
            saveHighscore();
        } else {
            System.out.println("Highscore nicht geschlagen, bad luck");
        }


    }
    public void updateScore(int rows) {
        score = score + SCORE_REWARDS[rows];
        level = (int) score / SCORE_PER_LEVEL;
        System.out.println("level "+ level);
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }


    public int getHighscore() {
        return highscore;
    }
    public void reset() {
        this.score=0;
        this.level=1;
    }

}
