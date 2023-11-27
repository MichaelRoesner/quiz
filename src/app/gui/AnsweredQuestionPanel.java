package app.gui;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class AnsweredQuestionPanel extends JPanel {


    /**
     * Constructor to create the AnsweredQuestionPanel.
     *
     * @param questionNumber The number of the question.
     * @param question       The text of the question.
     * @param selectedAnswer The answer selected by the user.
     * @param correctAnswer  The correct answer to the question.
     */
    public AnsweredQuestionPanel(int questionNumber, String question, String selectedAnswer, String correctAnswer) {
        // Set panel properties
        setBorder(new LineBorder(new Color(255, 255, 255))); // White border
        setBackground(new Color(75, 0, 130)); // Background color
        setLayout(null); // Use absolute layout

        // Label for displaying question number
        JLabel questionNLable = new JLabel("Question " + questionNumber);
        questionNLable.setFont(new Font("Arial", Font.BOLD, 20));
        questionNLable.setForeground(new Color(255, 255, 255)); // White text

        questionNLable.setBounds(28, 10, 114, 37); // Position
        add(questionNLable);

        // Label for displaying "Selected Answer"
        JLabel lblSelected = new JLabel("Selected Answer: ");
        lblSelected.setFont(new Font("Arial", Font.BOLD, 15));
        lblSelected.setForeground(Color.WHITE); // White text

        lblSelected.setBounds(28, 57, 157, 37); // Position
        add(lblSelected);

        // Label for displaying "Correct Answer"
        JLabel lblCorrectAnswer = new JLabel("Correct Answer: ");
        lblCorrectAnswer.setFont(new Font("Arial", Font.BOLD, 15));
        lblCorrectAnswer.setForeground(Color.WHITE); // White text

        lblCorrectAnswer.setBounds(28, 111, 142, 37); // Position
        add(lblCorrectAnswer);

        // Label for displaying the question text
        JLabel questionLabel = new JLabel(question);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        questionLabel.setForeground(Color.WHITE); // White text

        questionLabel.setBounds(182, 10, 442, 37); // Position
        add(questionLabel);

        // Label for displaying the selected answer
        JLabel selectedLabel = new JLabel(selectedAnswer);
        selectedLabel.setFont(new Font("Arial", Font.BOLD, 15));
        selectedLabel.setForeground(Color.WHITE); // White text

        selectedLabel.setBounds(182, 57, 442, 37); // Position
        add(selectedLabel);

        // Label for displaying the correct answer
        JLabel correctLabel = new JLabel(correctAnswer);
        correctLabel.setFont(new Font("Arial", Font.BOLD, 15));
        correctLabel.setForeground(Color.WHITE); // White text

        correctLabel.setBounds(182, 111, 442, 37); // Position
        add(correctLabel);
    }

    /**
     * Overrides the getPreferredSize method to specify the preferred size of the panel.
     *
     * @return The preferred size of the panel.
     */
    @Override
    public Dimension getPreferredSize() {
        // Return the preferred size of the panel
        return new Dimension(660, 167);
    }
}
