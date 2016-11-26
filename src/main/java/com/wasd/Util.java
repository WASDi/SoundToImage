package com.wasd;

import java.util.List;

public class Util {

    public static List<Byte> trimZeroes(List<Byte> bytes) {
        int fromIndex = 0;
        int toIndex = bytes.size();

        for (int i = 0; i < bytes.size(); i++) {
            if (bytes.get(i) != 0) {
                break;
            }
            fromIndex++;
        }

        for (int i = bytes.size() - 1; i >= 0; i--) {
            if (bytes.get(i) != 0) {
                break;
            }
            toIndex--;
        }

        return bytes.subList(fromIndex, toIndex);
    }

}
