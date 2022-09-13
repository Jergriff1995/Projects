package Jeremy.Griffin;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundHandler {
    public static void RunBGMMusic(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
        Clip clip = AudioSystem.getClip();
        clip.open(inputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }
    public static void RunMusic2(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
        Clip clip = AudioSystem.getClip();
        clip.open(inputStream);
        clip.loop(0);

    }
}
