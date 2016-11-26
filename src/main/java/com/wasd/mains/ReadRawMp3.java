package com.wasd.mains;

import com.wasd.Mp3Util;
import com.wasd.Util;

import java.util.List;

public class ReadRawMp3 {

    public static void main(String[] args) throws Exception {

        List<Byte> bytes = Mp3Util.bytesFromFile(Mp3Util.TEST_FILENAME);

        System.out.println("before: " + bytes.size());

        bytes = Util.trimZeroes(bytes);

        System.out.println("after: " + bytes.size());
    }

}
