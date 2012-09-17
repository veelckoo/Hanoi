package hanoi;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.*;

//TODO rewrite completely
public class Sound {
    private AudioData audiodata;
    private AudioDataStream audiostream;
    private ContinuousAudioDataStream continuousaudiostream;

    public Sound (URL url) throws java.io.IOException {
        audiodata = new AudioStream (url.openStream()).getData();
        audiostream = null;
        continuousaudiostream = null;
    }

    public Sound(String filename) {
        try {
            FileInputStream fis = new FileInputStream (filename);
            AudioStream audioStream = new AudioStream (fis);
            audiodata = audioStream.getData();
            audiostream = null;
            continuousaudiostream = null;
        } catch (IOException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void play () {
        audiostream = new AudioDataStream (audiodata);
        AudioPlayer.player.start (audiostream);
    }

}
