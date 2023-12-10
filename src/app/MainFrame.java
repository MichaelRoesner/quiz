package app;

import app.gui.QuestionPanel;
import app.gui.ScorePanel;
import app.gui.WelcomePanel;
import app.logic.Answer;
import app.logic.Question;
import app.logic.QuizModel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainFrame extends JFrame {


    // Application entry point
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                // Create and display the main frame
                MainFrame frame = new MainFrame();
                frame.setFocusTraversalPolicy(
                        new LayoutFocusTraversalPolicy() {
                            @Override
                            protected boolean accept(Component c) {
                                // Ensure all components are focusable
                                return c.isFocusable() || super.accept(c);
                            }
                        }
                );
                frame.setVisible(true);
            } catch (Exception e) {
                // Print the stack trace in case of an exception
                e.printStackTrace();
            }
        });
    }

    // Constructor for the MainFrame
    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        switchToWelcomePanel(); // Initialize with WelcomePanel
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Switch to the WelcomePanel
    public void switchToWelcomePanel() {
        // Remove other panels before switching
        removeOtherPanels();


        // Create and set the new content pane
        JPanel welcomePanel = new WelcomePanel(this);
        setContentPane(welcomePanel);

        // Revalidate, repaint, and pack
        revalidate();
        repaint();
        pack();
    }

    // Switch to the QuestionPanel with a specified QuizModel
    public void switchToQuestionPanel(QuizModel quizModel) {
        JPanel questionPanel = new QuestionPanel(this, quizModel);
        setContentPane(questionPanel);
        revalidate();
        repaint();
        pack();
    }

    // Switch to the ScorePanel with answered questions and the final score
    public void switchToScorePanel(Map<Question, Answer> answeredQuestion, int score) {
        JPanel scorePanel = new ScorePanel(this, answeredQuestion, score);
        setContentPane(scorePanel);
        revalidate();
        repaint();
        pack();
    }

    /**
     * Removes any existing panels from the frame before switching to the WelcomePanel.
     * This ensures a clean transition without accumulating unnecessary components.
     */
    private void removeOtherPanels() {
        // Iterate through components in the frame's content pane
        Component[] components = getContentPane().getComponents();

        // Remove any JPanel components to maintain a clean transition
        for (Component component : components) {
            if (component instanceof JPanel) {
                remove(component);
            }
        }


    }
}

