package media;

import java.awt.*;
import java.io.File;
import media.AudioPlayer;
import controller.GameLoop;

import static media.AudioPlayer.addAudioByFilePath;

public class BackgroundMusic {
    public static final String BM = "background";

    public BackgroundMusic(){

    }

    public void start(){
        addAudioByFilePath(BackgroundMusic.BM, new File("assets/audio/background.wav"));
        while(true){
            AudioPlayer.BplaySounds(BM);
            try 
            {
                Thread.sleep(49000);
            } 
            catch(InterruptedException e)
            {
                 // this part is executed when an exception (in this example InterruptedException) occurs
            }
        }
    }

}
