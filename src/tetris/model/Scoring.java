package tetris.model;

import java.io.*;

public class Scoring {

    private int level;
    private int score;
    private int highscore = 0;
    private static int SCORE_PER_LEVEL;
    private static int [] SCORE_REWARDS = new int[4];
    private static String HIGH_SCORE_FILE = "C:/Users/marc_/IdeaProjects/Tetris/data/highscore.marc";

    public Scoring () {
        this.level=1;
        this.score=0;
        SCORE_REWARDS = new int[]{0,40,100,300,1200};
        loadHighScore();
    }

    private void loadHighScore() {
        try {
            FileInputStream fis = new FileInputStream(HIGH_SCORE_FILE);
            DataInputStream dis = new DataInputStream(fis);
            this.highscore = dis.read();
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
            dos.write(highscore);
            dos.close();
            }
        catch (IOException e)
            { System.out.println("IOException : " + e);
                System.out.println("Highscore konnte nicht abgespeichert werden.");
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
