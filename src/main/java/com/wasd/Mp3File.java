package com.wasd;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Mp3File {

    private final String fileName;

    private AudioInputStream rawInput;

    private AudioFormat decodedFormat;
    private AudioInputStream decodedInput;

    public Mp3File(String fileName) {
        this.fileName = fileName;
    }

    public void init() throws IOException, UnsupportedAudioFileException {
        File file = new File(fileName);
        rawInput = AudioSystem.getAudioInputStream(file);
        AudioFormat baseFormat = rawInput.getFormat();
        decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                baseFormat.getSampleRate(),
                16,
                baseFormat.getChannels(),
                baseFormat.getChannels() * 2,
                baseFormat.getSampleRate(),
                false);
        decodedInput = AudioSystem.getAudioInputStream(decodedFormat, rawInput);
    }

    public void close() throws IOException {
        rawInput.close();
    }

    public AudioFormat getDecodedFormat() {
        return decodedFormat;
    }

    public AudioInputStream getDecodedInput() {
        return decodedInput;
    }
}
