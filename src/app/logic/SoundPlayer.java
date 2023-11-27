package app.logic;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

// Class for playing sounds
public class SoundPlayer {

    // Method to play a sound from a given path
    public static void playSound(String soundFilePath) {
        new Thread(() -> {
            try {
                // Open an audio input stream
                File url = new File(soundFilePath).getAbsoluteFile();
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);

                // Get a sound clip resource
                Clip clip = AudioSystem.getClip();

                // Open the audio clip and load samples from the audio input stream
                clip.open(audioIn);

                // Play the audio clip
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
