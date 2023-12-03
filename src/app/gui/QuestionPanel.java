package app.gui;

import app.MainFrame;
import app.logic.Answer;
import app.logic.Question;
import app.logic.QuizModel;
import app.logic.SoundPlayer;
import app.utility.CloseButton;
import app.utility.OptionColor;
import app.utility.ResourcePath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the panel displaying a quiz question with answer options.
 */
public class QuestionPanel extends JPanel {

    // Components for the QuestionPanel
    private final MainFrame parentFrame;
    private final Map<RoundLabel, Answer> labelAnswers;
    private final QuizModel quizModel;
    private final Timer questionTimer;
    private final JLabel timerLabel;
    private final StrokedLabel questionLabel;
    private final RoundLabel optionA;
    private final RoundLabel optionB;
    private final RoundLabel optionC;
    private final RoundLabel optionD;
    private RoundLabel selectedLabel;
    private boolean buttonClicked;

    /**
     * Creates a new instance of the QuestionPanel.
     *
     * @param mainFrame  The main application frame.
     * @param quizModel  The model containing quiz data and logic.
     */
    public QuestionPanel(MainFrame mainFrame, QuizModel quizModel) {
        // Initialization and setup
        this.parentFrame = mainFrame;
        labelAnswers = new HashMap<>();
        this.quizModel = quizModel;
        this.selectedLabel = null;

        // Panel configuration
        setBackground(new Color(122, 5, 194));
        setLayout(null);

        // Components initialization
        questionLabel = new StrokedLabel("", new Color(0, 0, 0), 1, ResourcePath.PIXEL, 60f);
        questionLabel.setForeground(new Color(255, 255, 255));
        questionLabel.setBounds(53, 38, 618, 85);

        // Add components to the panel
        add(questionLabel);

        // Close Button
        CloseButton closeButton = new CloseButton(parentFrame);
        Dimension size = closeButton.getPreferredSize();
        closeButton.setBounds(705, 6, size.width + 10, size.height + 10);
        add(closeButton);


        // Initialize and configure option labels
        optionA = new RoundLabel("New button");
        optionA.setBounds(53, 222, 280, 61);
        optionA.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SoundPlayer.playSound(ResourcePath.SOUND_CLICK_PATH);
                selectedLabel = optionA;
                optionA.changeBackgroundColor(OptionColor.selectedColor);
                optionB.setDefaultColor();
                optionC.setDefaultColor();
                optionD.setDefaultColor();
            }
        });
        add(optionA);

        optionB = new RoundLabel("New button");
        optionB.setBounds(391, 222, 280, 61);
        optionB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SoundPlayer.playSound(ResourcePath.SOUND_CLICK_PATH);
                selectedLabel = optionB;
                optionB.changeBackgroundColor(OptionColor.selectedColor);
                optionA.setDefaultColor();
                optionC.setDefaultColor();
                optionD.setDefaultColor();
            }
        });
        add(optionB);

        optionC = new RoundLabel("New button");
        optionC.setBounds(53, 329, 280, 61);
        optionC.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SoundPlayer.playSound(ResourcePath.SOUND_CLICK_PATH);
                selectedLabel = optionC;
                optionC.changeBackgroundColor(OptionColor.selectedColor);
                optionA.setDefaultColor();
                optionB.setDefaultColor();
                optionD.setDefaultColor();
            }
        });
        add(optionC);

        optionD = new RoundLabel("New button");
        optionD.setBounds(391, 329, 280, 61);
        optionD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SoundPlayer.playSound(ResourcePath.SOUND_CLICK_PATH);
                selectedLabel = optionD;
                optionD.changeBackgroundColor(OptionColor.selectedColor);
                optionA.setDefaultColor();
                optionB.setDefaultColor();
                optionC.setDefaultColor();
            }
        });
        add(optionD);

        // Continue Button
        RoundButton continueButton = new RoundButton("next");
        continueButton.setBounds(585, 413, 85, 31);
        continueButton.customizeFont(25);
        // Add a boolean variable to track whether the button has been clicked
        buttonClicked = false;
        continueButton.addActionListener(e -> {
            // Check if the button has already been clicked
            if (!buttonClicked) {
                if (selectedLabel != null) {
                    checkSelectedOption(selectedLabel);
                }

                // Set the variable to true to indicate that the button has been clicked
                buttonClicked = true;
            }
        });
        add(continueButton);


        timerLabel = new JLabel("29");
        timerLabel.setForeground(new Color(255, 255, 255));
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        timerLabel.setBounds(337, 147, 57, 43);
        add(timerLabel);


        // Timer initialization
        questionTimer = new Timer(1000, e -> updateTimer());

        // Start the quiz
        startQuiz();

    }

    // Method to update the timer label
    private void updateTimer() {
        int currentTime = Integer.parseInt(timerLabel.getText());
        currentTime--;
        if (currentTime <= 0) {
            questionTimer.stop();
            checkSelectedOption(selectedLabel);


        } else {
            timerLabel.setText(String.valueOf(currentTime));
        }
    }

    // Method to check the selected option and handle accordingly
    private void checkSelectedOption(RoundLabel selectedLabel) {
        // Check if no option is selected
        if(selectedLabel == null) {
            quizModel.recordAnswer(null);
        } else {
            // Record the selected answer and provide feedback
            quizModel.recordAnswer(labelAnswers.get(selectedLabel));
            Answer answer = labelAnswers.get(selectedLabel);
            if(answer.isCorrect()) {
                // Play correct sound and change background color to correct color
                SoundPlayer.playSound(ResourcePath.SOUND_RIGHT_PATH);
                selectedLabel.changeBackgroundColor(OptionColor.correctColor);
            } else {
                // Play wrong sound and change background color to wrong color
                SoundPlayer.playSound(ResourcePath.SOUND_WRONG_PATH);
                selectedLabel.changeBackgroundColor(OptionColor.wrongColor);
            }
        }

        // Use Timer to introduce a delay before loading the next question or transitioning to the score panel
        Timer timer = new Timer(2000, e -> operationWithDelay());
        timer.setRepeats(false); // Make sure the timer only runs once
        timer.start();



    }

    // Method to perform operations with a delay (e.g., reset labels, load next question, switch to score panel)
    private void operationWithDelay() {
        // Reset the selected label's color
        if (selectedLabel != null) {
            selectedLabel.setDefaultColor();
            selectedLabel = null;
        }

        // Check if there are more questions
        if (quizModel.hasMoreQuestions()) {
            // Load the next question
            loadNextQuestion();
        } else {
            // Switch to the score panel
            parentFrame.switchToScorePanel(quizModel.getAnsweredQuestion(), quizModel.getScore());
            // Stop the question timer to prevent switching to the ScorePanel again.
            questionTimer.stop();
        }
    }

    // Method to load the next question and update the UI
    private void loadNextQuestion() {
        selectedLabel = null;
        Question currentQuestion = quizModel.getNextQuestion();
        if (currentQuestion != null) {
            // Update components with question details

            // Set the question text and adjust label font size
            questionLabel.setText(currentQuestion.getQuestion());
            questionLabel.adjustLabelFontSize();

            // Populate option labels with answer text and associate answers with labels
            Answer answer = currentQuestion.getAnswers().get(0);
            optionA.setText(answer.getText());
            labelAnswers.put(optionA, answer);

            answer = currentQuestion.getAnswers().get(1);
            optionB.setText(answer.getText());
            labelAnswers.put(optionB, answer);


            answer = currentQuestion.getAnswers().get(2);
            optionC.setText(answer.getText());
            labelAnswers.put(optionC, answer);

            answer = currentQuestion.getAnswers().get(3);
            optionD.setText(answer.getText());
            labelAnswers.put(optionD, answer);

            // Reset the timer label
            timerLabel.setText(String.valueOf(quizModel.getTimePerQuestion()));
            // Start the timer
            questionTimer.start();
            buttonClicked = false;
        }
    }

    // Method to start the quiz by resetting the quiz model and loading the first question
    public void startQuiz() {
        quizModel.resetQuiz();
        loadNextQuestion(); // Load the first question
    }

    // Override the getPreferredSize method to define the preferred size of the panel
    @Override
    public Dimension getPreferredSize() {
        // Return the preferred size of the panel
        return new Dimension(730, 468);
    }

}
