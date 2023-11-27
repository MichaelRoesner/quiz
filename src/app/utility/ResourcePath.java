package app.utility;

// Utility class for storing resource file paths
public class ResourcePath {
    // File paths for easy, medium, and hard difficulty level questions
    public static final String QUESTION_EASY_PATH = "resources/data/questions_easy.json";
    public static final String QUESTION_MEDIUM_PATH = "resources/data/questions_medium.json";
    public static final String QUESTION_HARD_PATH = "resources/data/questions_hard.json";

    // File paths for sound effects
    public static final String SOUND_CLICK_PATH = "resources/sounds/click.wav";
    public static final String SOUND_WRONG_PATH = "resources/sounds/wrong.wav";
    public static final String SOUND_RIGHT_PATH = "resources/sounds/correct.wav";
    public static final String SOUND_WIN_PATH = "resources/sounds/win.wav";
    public static final String SOUND_LOSE_PATH = "resources/sounds/lose.wav";

    // File paths for fonts
    public static final String CRUISER = "resources/fonts/2015 Cruiser.ttf";
    public static final String PIXEL = "resources/fonts/advanced_pixel-7.ttf";


    // Private constructor to prevent instantiation
    private ResourcePath() {
        // This constructor is empty to prevent instantiation of the utility class
        // All methods and fields are static, and the class is not meant to be instantiated
    }
}
