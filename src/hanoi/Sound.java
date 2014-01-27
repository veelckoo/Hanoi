package hanoi;

import java.net.URL;
import javax.sound.sampled.*;

public class Sound {

    public static synchronized void play(final String fileName) {
        
        new Thread(new Runnable() { 

            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    URL url = this.getClass().getClassLoader().getResource(fileName);
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(url);
                    clip.open(inputStream);
                    clip.start(); 
                } catch (Exception e) {
                    System.out.println("play sound error: " + e.getMessage() + " for " + fileName);
                }
            }

        }).start();

    }

}

