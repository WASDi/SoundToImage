package com.wasd.mains;

import com.wasd.Mp3File;
import com.wasd.Mp3Util;

import java.util.List;

public class ReadRawMp3 {

    public static void main(String[] args) throws Exception {
        Mp3File mp3 = new Mp3File(Mp3Util.TEST_FILENAME);

        mp3.init();

        List<Byte> bytes = Mp3Util.readRawData(mp3.getDecodedInput());

        mp3.close();

        System.out.println(bytes.size());
    }

}
