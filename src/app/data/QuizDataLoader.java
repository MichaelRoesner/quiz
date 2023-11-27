package app.data;

import app.logic.Answer;
import app.logic.Question;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class QuizDataLoader {

    // Method to load questions from a JSON file
    public static List<Question> loadQuestions(String filename) {
        // List to store loaded questions
        List<Question> questions = new ArrayList<>();

        try {
            // Read the content of the file into a string
            String content = new String(Files.readAllBytes(Paths.get(filename)));

            // Convert the string content into a JSON array
            JSONArray jsonArray = new JSONArray(content);

            // Iterate through each JSON object in the array
            for (int i = 0; i < jsonArray.length(); i++) {
                // Get the current JSON object representing a question
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                // Extract question text from the JSON object
                String questionText = jsonObj.getString("question");

                // Extract the array of answers for the current question
                JSONArray jsonAnswers = jsonObj.getJSONArray("answers");

                // List to store answers for the current question
                List<Answer> answers = new ArrayList<>();

                // Iterate through each JSON object in the answers array
                for (int j = 0; j < jsonAnswers.length(); j++) {
                    // Get the current JSON object representing an answer
                    JSONObject jsonAns = jsonAnswers.getJSONObject(j);

                    // Extract answer text and correctness from the JSON object
                    String answerText = jsonAns.getString("answer");
                    boolean isCorrect = jsonAns.getBoolean("correct");

                    // Create an Answer object and add it to the list of answers
                    answers.add(new Answer(answerText, isCorrect));
                }

                // Create a Question object with the extracted question text and answers
                questions.add(new Question(questionText, answers));
            }
        } catch (IOException | JSONException e) {
            // Handle exceptions by printing the stack trace
            e.printStackTrace();
        }

        // Return the list of loaded questions
        return questions;
    }
}
