package Jeremy.Griffin;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    //Runs the game
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        SoundHandler.RunBGMMusic("src/Resources/BGM2.wav");
        new PlayScreen().PlayScreen();











    }
}
