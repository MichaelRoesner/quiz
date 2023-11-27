package app.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Class representing the model for a quiz
public class QuizModel {
    private final List<Question> questions;   // List of quiz questions
    private final Map<Question, Answer> answeredQuestion;  // Map to store answered questions and their corresponding answers
    private int currentQuestionIndex = 0;   // Index to track the current question
    private final int timePerQuestion;      // Time allocated per question
    private int score = 0;   // Current score

    // Constructor
    public QuizModel(List<Question> questions, int time) {
        this.questions = questions;
        this.timePerQuestion = time;
        this.answeredQuestion = new HashMap<>();
    }

    // Method to get the next question in the quiz
    public Question getNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex++);
        }
        return null;
    }

    // Method to record an answer for the current question
    public void recordAnswer(Answer answer) {
        if(answer!=null)
            if (answer.isCorrect())
                score++;
        answeredQuestion.put(questions.get(currentQuestionIndex-1), answer);
    }

    // Method to get the current score
    public int getScore() {
        return score;
    }

    // Method to check if there are more questions in the quiz
    public boolean hasMoreQuestions() {
        return currentQuestionIndex < questions.size();
    }

    // Method to reset the quiz (reset question index and score)
    public void resetQuiz() {
        currentQuestionIndex = 0;
        score = 0;
    }

    // Method to get the time allocated per question
    public int getTimePerQuestion() {
        return timePerQuestion;
    }

    // Method to get the map of answered questions and their corresponding answers
    public Map<Question, Answer> getAnsweredQuestion() {
        return answeredQuestion;
    }
}
