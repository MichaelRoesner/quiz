package app.gui;

import app.utility.FontLoader;
import app.utility.ResourcePath;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.IOException;

/**
 * Custom JButton class that renders a round button with customizable appearance.
 */
public class RoundButton extends JButton {
    Font pixelFont;

    private final Color borderColor; // Field to keep track of the current border color

    /**
     * Constructor to initialize the RoundButton with default settings.
     *
     * @param text The text to be displayed on the button.
     */
    public RoundButton(String text) {
        super(text);
        borderColor = Color.WHITE; // Default border color
        setForeground(Color.WHITE); // Default font color
        setBackground(Color.BLACK); // Default background color
        try {
            pixelFont = FontLoader.loadFont(ResourcePath.PIXEL, 40f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            System.out.println("RoundButtonFehler");
        }
        setFont(pixelFont);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
    }

    /**
     * Method to customize the font size of the button's text.
     *
     * @param fontSize The desired font size.
     */
    public void customizeFont(int fontSize) {
        setFont(new Font(getFont().getName(), Font.BOLD, fontSize));
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

        // Create a capsule shape
        int h = getHeight();
        int w = getWidth();

        // Draw the background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, w, h, h, h);

        // Draw the border
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(1f));
        g2.drawRoundRect(0, 0, w - 1, h - 1, h, h);

        // Draw the text over the button (centered in the button)
        super.paintComponent(g2);
        g2.dispose();
    }

    /**
     * Overridden method to define the preferred size of the button.
     *
     * @return The preferred size of the button.
     */
    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height); // Ensure the size is suitable for a circular shape
        return size;
    }

    /**
     * Overridden method to check if a point is contained within the button.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @return True if the point is contained within the circular area of the button.
     */
    @Override
    public boolean contains(int x, int y) {
        // Overridden for circular hit detection
        Shape shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        return shape.contains(x, y);
    }

}
