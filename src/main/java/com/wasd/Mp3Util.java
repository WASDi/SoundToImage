package com.wasd;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mp3Util {

    public static final String TEST_FILENAME = "nuclear-siren.mp3";

    public static List<Byte> readRawData(AudioInputStream decodedInput) throws IOException, LineUnavailableException {
        List<Byte> bytes = new ArrayList<>(1024*1024);
        byte[] data = new byte[4096];
        int nBytesRead = 0;

        while (nBytesRead != -1) {
            nBytesRead = decodedInput.read(data, 0, data.length);
            if (nBytesRead != -1) {
                //HERE data has the bytes we want
                for (int i = 0; i < nBytesRead; i++) {
                    bytes.add(data[i]);
                }
            }
        }

        decodedInput.close();

        return bytes;
    }

    public static void rawplay(AudioFormat targetFormat, AudioInputStream decodedInput) throws IOException, LineUnavailableException {
        byte[] data = new byte[4096];
        SourceDataLine dataLine = getDataLine(targetFormat);
        if (dataLine != null) {
            // Start
            dataLine.start();
            int nBytesRead = 0;
            int nBytesWritten = 0;
            int totBytesRead = 0;

            while (nBytesRead != -1) {
                nBytesRead = decodedInput.read(data, 0, data.length);
                totBytesRead += nBytesRead;
                if (nBytesRead != -1) {
                    nBytesWritten = dataLine.write(data, 0, nBytesRead);
                }
            }
            System.out.println(totBytesRead);
            // Stop
            dataLine.drain();
            dataLine.stop();
            dataLine.close();
            decodedInput.close();
        }
    }

    private static SourceDataLine getDataLine(AudioFormat audioFormat) throws LineUnavailableException {
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        SourceDataLine dataLine = (SourceDataLine) AudioSystem.getLine(info);
        dataLine.open(audioFormat);
        return dataLine;
    }
}
