package app.utility;

import java.awt.*;

// Utility class for defining constant colors for different states
public class OptionColor {
    // Constant color for a correct answer
    public static final Color correctColor = Color.ORANGE;

    // Constant color for a wrong answer
    public static final Color wrongColor = Color.RED;

    // Constant color for a selected answer
    public static final Color selectedColor = Color.YELLOW;

    // Private constructor to prevent instantiation of the class
    private OptionColor() {
        // This constructor is empty to prevent instantiation of the utility class
        // All methods and fields are static, and the class is not meant to be instantiated
    }
}
