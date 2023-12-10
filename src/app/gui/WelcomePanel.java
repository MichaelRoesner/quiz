package app.gui;


import app.MainFrame;
import app.data.QuizDataLoader;
import app.logic.DifficultyLevel;
import app.logic.QuizModel;
import app.logic.SoundPlayer;
import app.utility.CloseButton;
import app.utility.ResourcePath;

import javax.swing.*;
import java.awt.*;


public class WelcomePanel extends JPanel {

    private final MainFrame parentFrame;


    /**
     * Create the panel.
     */
    public WelcomePanel(MainFrame mainFrame) {
        this.parentFrame = mainFrame;
        setBackground(new Color(122, 5, 194));
        setLayout(null);


        // Close Button
        CloseButton closeButton = new CloseButton(null);
        Dimension size = closeButton.getPreferredSize();
        closeButton.setBounds(630, 6, size.width + 10, size.height + 10);
        add(closeButton);

        // Welcome Label
        StrokedLabel welcomeLabel = new StrokedLabel("Dark IT-Quiz", new Color(0, 0, 0), 2);
        welcomeLabel.setBounds(146, 50, 396, 138);
        add(welcomeLabel);

        // Difficulty selection buttons
        RoundButton easyDifficultyButton = new RoundButton("Leicht");
        easyDifficultyButton.addActionListener(e -> {
            SoundPlayer.playSound(ResourcePath.SOUND_CLICK_PATH);
            parentFrame.switchToQuestionPanel(new QuizModel(
                    QuizDataLoader.loadQuestions(ResourcePath.QUESTION_EASY_PATH),
                    getTimePerQuestion(DifficultyLevel.EASY)));
        });
        easyDifficultyButton.setBounds(236, 169, 203, 43);
        add(easyDifficultyButton);

        RoundButton middleDifficultyButton = new RoundButton("Mittel");
        middleDifficultyButton.addActionListener(e -> {
            SoundPlayer.playSound(ResourcePath.SOUND_CLICK_PATH);
            parentFrame.switchToQuestionPanel(new QuizModel(
                    QuizDataLoader.loadQuestions(ResourcePath.QUESTION_MEDIUM_PATH),
                    getTimePerQuestion(DifficultyLevel.MEDIUM)));
        });
        middleDifficultyButton.setBounds(236, 238, 203, 43);
        add(middleDifficultyButton);

        RoundButton hardDifficultyButton = new RoundButton("Schwer");
        hardDifficultyButton.addActionListener(e -> {
            SoundPlayer.playSound(ResourcePath.SOUND_CLICK_PATH);
            parentFrame.switchToQuestionPanel(new QuizModel(
                    QuizDataLoader.loadQuestions(ResourcePath.QUESTION_HARD_PATH),
                    getTimePerQuestion(DifficultyLevel.HARD)));
        });
        hardDifficultyButton.setBounds(236, 302, 203, 43);
        add(hardDifficultyButton);
    }


    @Override
    public Dimension getPreferredSize() {
        // Return the preferred size of the panel
        int WIDTH = 655;
        int HEIGHT = 436;
        return new Dimension(WIDTH, HEIGHT);
    }

    // Helper method to get time per question based on difficulty
    private int getTimePerQuestion(DifficultyLevel difficulty) {
        return switch (difficulty) {
            case EASY -> 30;
            case MEDIUM -> 20;
            case HARD -> 10;
        };
    }
}
