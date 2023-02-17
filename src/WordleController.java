import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordleController implements ActionListener {
    public WordleModel model;
    public WordleFrame frame;

    public WordleController(WordleModel model, WordleFrame frame){
        this.model = model;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        model.play(frame.getWord());
    }
}
