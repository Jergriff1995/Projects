package Jeremy.Griffin;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class PlayScreen {
    public JFrame PlayScreen() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        JFrame playScreen = new JFrame();
        Game game = new Game();
        playScreen.setBounds(10,10,700,600);
        playScreen.setTitle("Jimmy's Game");
        playScreen.setResizable(false);
        playScreen.setVisible(true);
        playScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playScreen.add(game);
        return playScreen;

    }
}
