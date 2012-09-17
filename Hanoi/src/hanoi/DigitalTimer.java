package hanoi;
import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class DigitalTimer extends JLabel {

	private static final long serialVersionUID = -955989559217859007L;
	private static volatile String timeText;
    private Thread internalThread;
    private volatile boolean noStopRequested;
    
    public DigitalTimer() {
        //setBorder(BorderFactory.createLineBorder(Color.black));
        Color myColor = new Color(192,192,192);
        setForeground(myColor);
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(new Font("Trebuchet MS", Font.PLAIN, 36));
        setText("00000.0"); // use to size component
        setMinimumSize(getPreferredSize());
        setPreferredSize(getPreferredSize());
        setSize(getPreferredSize());
        timeText = "0.0";
        setText(timeText);

        noStopRequested = true;
        Runnable r = new Runnable() {
            public void run() {
                try {
                    runWork();
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        };
        internalThread = new Thread(r, "DigitalTimer");
        internalThread.start();
    }

    private void runWork() {
        long startTime = System.currentTimeMillis();
        int tenths = 0;
        long normalSleepTime = 100;
        long nextSleepTime = 100;
        DecimalFormat fmt = new DecimalFormat("0.0");

        Runnable updateText = new Runnable() {
            public void run() {
                setText(timeText);
            }
        };

        while (noStopRequested) {
            try {
                Thread.sleep(nextSleepTime);

                tenths++;
                long currTime = System.currentTimeMillis();
                long elapsedTime = currTime - startTime;

                nextSleepTime = normalSleepTime
                    + ((tenths * 100) - elapsedTime);

                if (nextSleepTime < 0) {
                    nextSleepTime = 0;
                }

                timeText = fmt.format(elapsedTime / 1000.0);
                SwingUtilities.invokeAndWait(updateText);
            } catch (InterruptedException ix) {
                // stop running
                return;
            } catch (InvocationTargetException x) {
                x.printStackTrace();
            }
        }
  }
    
    public void stopRequest() {
        noStopRequested = false;
        internalThread.interrupt();
    }

    public boolean isAlive() {
        return internalThread.isAlive();
    }
  
    public static String getTimeText(){
        return timeText;
    }

}