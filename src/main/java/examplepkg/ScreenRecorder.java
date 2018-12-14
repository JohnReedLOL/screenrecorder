package examplepkg;

import org.jcodec.api.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class ScreenRecorder {

    public static void record() throws Exception {
        final Robot robot = new Robot();
        final File file = new File("outputVideo.mp4");
        final int frameRate = 4;
        final AWTSequenceEncoder sequenceEncoder = AWTSequenceEncoder.createSequenceEncoder(file, frameRate);
        JFrame frame = new JFrame("Window Listener");
        WindowListener listener = new WindowAdapter() {
            public void windowClosing(WindowEvent w) {
                ScreenRecorder.terminate(sequenceEncoder);
                System.exit(0);
            }
        };
        frame.addWindowListener(listener);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setVisible(true);
        while (true) {
            final Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            final BufferedImage image = robot.createScreenCapture(screenRect);
            try {
                sequenceEncoder.encodeImage(image);
            } catch (Exception e) {
                System.err.println("Caught exception of type: " + e.getClass().getName()); // Caught exception of type: java.lang.IllegalStateException
                System.err.println("Exception message: " + e.getMessage()); // Exception message: The muxer track has finished muxing
                Thread.sleep(300);
                System.exit(0); // java.lang.InterruptedException
            }
        }
    }

    public static void terminate(final AWTSequenceEncoder sequenceEncoder) {
        try {
            sequenceEncoder.finish();
        } catch (Exception e) {
            System.err.println("Caught exception of type: " + e.getClass().getName());
            System.err.println("Exception message: " + e.getMessage());
        }
        System.err.println("sequenceEncoder.finish done!");
    }
}
