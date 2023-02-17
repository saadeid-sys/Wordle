import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class WordleModel {
    private String[][] grid;
    private final int SIZE = 5;
    public enum Status {WON, UNDECIDED, LOST};
    private Status status;
    private String word;

    public ArrayList<WordleView> views;

    public WordleModel(String word){
        this.word = word.toLowerCase();
        views = new ArrayList<WordleView>();
        grid = new String[SIZE][SIZE];
        status = Status.UNDECIDED;

        for (int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                grid[i][j] = " ";
            }
        }
    }

    public Status getStatus(){
        return status;
    }

    public String getWord(){
        return word;
    }

    public void addView(WordleView view){
        views.add(view);
    }

    private boolean isGameOver(){
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if (grid[i][j].equals(" ")){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isWinner(){
        String tempWord = "";

        //check every row
        for (int i = 0; i < SIZE; i++, tempWord = "") {
            for (int j = 0; j < SIZE; j++) {
                tempWord += grid[i][j];
            }
            if (tempWord.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }

    public void updateStatus(){
        if(isWinner()){
            status = Status.WON;
        } else if (isGameOver()){
            status = Status.LOST;
        }
    }

    public int findNextRow(String[][] wordPlayed){
        for(int i = 0; i < SIZE ; i++){
            if(wordPlayed[i][0] == " "){
                return i; //returns the next available row
            }
        }
        return -1; //returns -1 if there are no more rows
    }
    public void play(String wordPlayed){
        System.out.println("The word: " + word);
        int x = findNextRow(grid);
        if(x != -1){
            for (int y = 0; y < SIZE; y++) {
                grid[x][y] = String.valueOf(wordPlayed.charAt(y));
            }
        }
        updateStatus();
        System.out.println(status);
        for(WordleView v: views){
            v.update(new WordleEvent(this, x, wordPlayed, status));
        }
    }
}
