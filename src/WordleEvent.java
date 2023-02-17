import java.util.EventObject;

public class WordleEvent extends EventObject {
    private String word;
    private int row;
    private WordleModel.Status status;

    public WordleEvent(WordleModel model, int row, String word, WordleModel.Status status){
        super(model);
        this.row = row;
        this.word = word;
        this.status = status;
    }

    public int getRow(){
        return row;
    }
    public String getWord(){return word;}
    public WordleModel.Status getStatus() {
        return status;
    }
}
