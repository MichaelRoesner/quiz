//removed due to unpredicted behavior in runnable jar

//package app.utility;
//
//import java.awt.*;
//import java.io.File;
//import java.io.IOException;
//
//// Utility class for loading and registering a custom font
//public class FontLoader {
//    private static Font customFont;
//
//    // Method to load a custom font from the specified path and set its size
//    public static Font loadFont(String path, float fontSize) throws IOException, FontFormatException {
//        customFont = Font.createFont(Font.TRUETYPE_FONT, new File(path));
//        customFont = customFont.deriveFont(fontSize); // Set the font size to 40 points
//        registerFont(); // Register the loaded font
//        return customFont; // Return the loaded font
//    }
//
//    // Method to register the loaded custom font with the local graphics environment
//    public static void registerFont() {
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        ge.registerFont(customFont);
//    }
//}
