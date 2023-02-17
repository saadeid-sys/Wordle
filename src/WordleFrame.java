import javax.swing.*;
import java.awt.*;

public class WordleFrame extends JFrame implements WordleView{
    private JButton[][] buttons;
    private JTextField textField;
    private JPanel buttonPanel;
    private static final int SIZE = 5;
    private WordleModel model;
    public WordleFrame(){
        super("Wordle");
        String word = JOptionPane.showInputDialog("Please enter the 5-character to be guessed");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        model = new WordleModel(word);
        model.addView(this);
        WordleController controller = new WordleController(model, this);

        buttonPanel = new JPanel(new GridLayout(SIZE, SIZE));

        buttons = new JButton[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++){
            for (int j=0; j < SIZE; j++){
                JButton b = new JButton(" ");
                buttons[i][j] = b;
                buttons[i][j].setEnabled(false);
                b.addActionListener(controller);
                b.setActionCommand(i + " " + j);
                buttonPanel.add(b);
            }
        }

        textField = new JTextField();
        textField.addActionListener(controller);

        this.add(buttonPanel, BorderLayout.CENTER);
        this.add(textField, BorderLayout.SOUTH);
        this.setSize(300, 300);
        this.setVisible(true);
    }


    public String getWord(){
        return textField.getText();
    }

    @Override
    public void update(WordleEvent e) {
        for (int y = 0; y < SIZE; y++) {
            buttons[e.getRow()][y].setText(String.valueOf(getWord().charAt(y)));
            if(Character.toLowerCase(getWord().charAt(y)) == Character.toLowerCase(model.getWord().charAt(y))){
                buttons[e.getRow()][y].setBackground(Color.GREEN);
            } else if(model.getWord().toLowerCase().contains(String.valueOf(getWord().charAt(y)))) {
                buttons[e.getRow()][y].setBackground(Color.YELLOW);
            } else {
                buttons[e.getRow()][y].setBackground(Color.GRAY);
            }
        }
        //Erases previous word from textfield
        textField.setText("");

        if(e.getStatus() != WordleModel.Status.UNDECIDED){
            if (e.getStatus() == WordleModel.Status.WON){
                JOptionPane.showMessageDialog(this, "Congrats, you won!");
                //Exit out of the game
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(this, "Boo, you Lost!");
                //Exit out of the game
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        new WordleFrame();
    }
}
