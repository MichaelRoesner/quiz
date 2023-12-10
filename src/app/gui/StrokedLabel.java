package app.gui;

import app.utility.FontLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.io.IOException;

public class StrokedLabel extends JLabel {
    private final Color strokeColor;
    private final int strokeThickness;


    // Constructor
    public StrokedLabel(String text, Color strokeColor, int strokeThickness, String fontPath, float fontSize) {
        super(text);
        this.strokeColor = strokeColor;
        this.strokeThickness = strokeThickness;
        Font customFont;
        try {
            customFont = FontLoader.loadFont(fontPath, fontSize);
            setFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            System.out.println("StrokedLabelFehler");
        }


        setForeground(Color.WHITE);
    }


    // Override paintComponent to customize rendering
    @Override
    protected void paintComponent(Graphics g) {
        String text = getText();
        if (text == null || text.isEmpty()) {
            return;
        }

        // Create a Graphics2D object
        Graphics2D g2 = (Graphics2D) g.create();
        // Enable anti-aliasing for smoother text rendering
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Calculate text position
        FontMetrics fm = g2.getFontMetrics();
        int textX = (getWidth() - fm.stringWidth(text)) / 2;
        int textY = fm.getAscent() + (getHeight() - fm.getHeight()) / 2;

        // Set font and color
        g2.setFont(getFont());

        // Create a TextLayout from the text
        FontRenderContext frc = g2.getFontRenderContext();
        TextLayout textLayout = new TextLayout(text, getFont(), frc);
        AffineTransform textTransform = new AffineTransform();
        textTransform.setToTranslation(textX, textY);

        // Get the outline of the text
        Shape outline = textLayout.getOutline(textTransform);

        // Draw the outline (stroke) of the text
        g2.setColor(strokeColor);
        g2.setStroke(new BasicStroke(strokeThickness));
        g2.draw(outline);

        // Fill the text (this is the text content itself)
        g2.setColor(getForeground());
        g2.fill(outline);

        // Dispose the Graphics2D object to free resources
        g2.dispose();
    }

    // Method to adjust the label font size to fit within the label's dimensions
    public void adjustLabelFontSize() {
        Font font = getFont();
        int labelWidth = getWidth() - strokeThickness * 2; // Consider stroke thickness
        int labelHeight = getHeight() - strokeThickness * 2; // Consider stroke thickness

        if (labelWidth <= 0 || labelHeight <= 0) {
            return; // Skip if the label doesn't have a proper size
        }

        float fontSize = font.getSize2D();
        FontMetrics metrics = getFontMetrics(font);
        int textWidth = metrics.stringWidth(getText());
        int textHeight = metrics.getHeight();

        // Reduce font size if necessary to fit within the label's dimensions
        while ((textWidth > labelWidth || textHeight > labelHeight) && fontSize > 0) {
            fontSize--;
            font = font.deriveFont(fontSize);
            metrics = getFontMetrics(font);
            textWidth = metrics.stringWidth(getText());
            textHeight = metrics.getHeight();
        }

        setFont(font); // Apply the adjusted font
        repaint(); // Repaint the label to reflect the change
    }
}
