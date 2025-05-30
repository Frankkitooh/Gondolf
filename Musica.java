package juego;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Musica {
    private Clip clip;

    public Musica(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Repetir música en bucle
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void restart() {
        if (clip != null) {
            clip.setFramePosition(0); // Reinicia desde el inicio
            clip.start();
        }
    }
}
