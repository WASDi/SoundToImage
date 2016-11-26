package com.wasd.mains;

import com.wasd.Mp3File;
import com.wasd.Mp3Util;

public class TestPlayMp3 {

    public static void main(String[] args) throws Exception {
        Mp3File mp3 = new Mp3File(Mp3Util.TEST_FILENAME);

        mp3.init();

        Mp3Util.rawplay(mp3.getDecodedFormat(), mp3.getDecodedInput());

        mp3.close();
    }

}
