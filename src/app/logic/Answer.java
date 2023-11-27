package app.logic;

// The Answer class represents an answer to a question.
public class Answer {
    // Private field to store the text of the answer.
    private final String text;

    // Private field to indicate whether the answer is correct.
    private final boolean correct;

    // Constructor to initialize the Answer object with text and correctness.
    public Answer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    // Getter method to retrieve the text of the answer.
    public String getText() {
        return text;
    }

    // Getter method to check if the answer is correct.
    public boolean isCorrect() {
        return correct;
    }
}
