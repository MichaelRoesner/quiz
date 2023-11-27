package app.gui;

import app.utility.FontLoader;
import app.utility.ResourcePath;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Custom JLabel class that renders a round label with customizable appearance.
 */
public class RoundLabel extends JLabel {
    private Color borderColor; // Field to keep track of the current border color

    /**
     * Constructor to initialize the RoundLabel with default settings.
     *
     * @param text The text to be displayed on the label.
     */
    public RoundLabel(String text) {
        super(text);
        borderColor = Color.WHITE; // Default border color
        setForeground(Color.WHITE); // Default font color
        setBackground(Color.BLACK); // Default background color
        try {
            Font pixelFont = FontLoader.loadFont(ResourcePath.PIXEL, 25f);
            pixelFont = pixelFont.deriveFont(Font.BOLD);
            setFont(pixelFont);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
        setHorizontalAlignment(SwingConstants.CENTER); // Center the text
        setOpaque(false);
    }

    /**
     * Method to wrap the text in HTML for multiline rendering.
     *
     * @param text The text to be wrapped.
     * @return The HTML-wrapped text.
     */
    private String wrapTextInHtml(String text) {
        return "<html><body style='text-align: center;'>" + text + "</body></html>";
    }

    /**
     * Overridden method to set the text with HTML wrapping for multiline rendering.
     *
     * @param text The text to be set.
     */
    @Override
    public void setText(String text) {
        super.setText(wrapTextInHtml(text)); // Set the text with HTML wrapping
    }

    /**
     * Overridden method to paint the component with a rounded appearance.
     *
     * @param g The Graphics object used for painting.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int h = getHeight();
        int w = getWidth();

        // Draw the background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, w, h, h, h);

        // Draw the border
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(1f));
        g2.drawRoundRect(0, 0, w - 1, h - 1, h, h);

        super.paintComponent(g2);
        g2.dispose();
    }

    /**
     * Overridden method to define the preferred size of the label.
     *
     * @return The preferred size of the label.
     */
    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        return size;
    }

    /**
     * Method to change the background and border color of the label.
     *
     * @param color The color to set as the background and border color.
     */
    public void changeBackgroundColor(Color color) {
        setBackground(color);
        borderColor = color; // Change the border color to match the background
        repaint(); // Repaint the button to show the new color
    }

    /**
     * Method to set the default background and border color of the label.
     */
    public void setDefaultColor() {
        setBackground(Color.BLACK);
        borderColor = Color.WHITE; // Change the border color to match the background
        repaint(); // Repaint the button to show the new color
    }

}
