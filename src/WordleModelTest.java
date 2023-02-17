import static org.junit.Assert.*;

public class WordleModelTest {

    @org.junit.Test
    public void playTestWithWordHELLO() {
        WordleModel wordleModel = new WordleModel("HELLO");
        wordleModel.play("MEDAL");
        assertEquals(WordleModel.Status.UNDECIDED, wordleModel.getStatus());
        wordleModel.play("HELLO");
        assertEquals(WordleModel.Status.WON, wordleModel.getStatus());
    }

    @org.junit.Test
    public void playTestWithWordMEDAL() {
        WordleModel wordleModel = new WordleModel("HELLO");
        wordleModel.play("MEDAL");
        assertEquals(WordleModel.Status.UNDECIDED, wordleModel.getStatus());
        wordleModel.play("FRIES");
        assertEquals(WordleModel.Status.UNDECIDED, wordleModel.getStatus());
        wordleModel.play("PLACE");
        assertEquals(WordleModel.Status.UNDECIDED, wordleModel.getStatus());
        wordleModel.play("HEART");
        assertEquals(WordleModel.Status.UNDECIDED, wordleModel.getStatus());
        wordleModel.play("JELLO");
        assertEquals(WordleModel.Status.LOST, wordleModel.getStatus());
    }
}