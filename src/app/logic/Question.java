package app.logic;

import java.util.List;

// Define a record named Question
public class Question {
    // Private field to store the text of the question.
    private final String question;

    // Private field to store a list of answers associated with the question.
    private final List<Answer> answers;

    // Constructor to initialize the Question object with a question and a list of answers.
    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    // Getter method to retrieve the text of the question.
    public String getQuestion() {
        return question;
    }

    // Getter method to retrieve the text of the correct answer.
    // This method iterates through the list of answers and returns the text of the first correct answer found.
    // If no correct answer is found, it returns null.
    public String getCorrectAnswer() {
        for (Answer answer : answers) {
            if (answer.isCorrect()) {
                return answer.getText();
            }
        }
        return null;
    }

    // Getter method to retrieve the list of answers associated with the question.
    public List<Answer> getAnswers() {
        return answers;
    }
}
