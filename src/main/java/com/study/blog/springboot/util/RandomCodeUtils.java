package com.study.blog.springboot.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomCodeUtils {
    private final static int DEFAULT_CODE_LEN = 14;

    public static String makeCode() {
        return makeCode(DEFAULT_CODE_LEN);
    }

    public static String makeCode(int codeLen) {
        long min = (long) Math.pow(10, codeLen - 1);
        long max = (long) Math.pow(10, codeLen);

        long randomNumber = ThreadLocalRandom.current().nextLong(min, max);
        return String.valueOf(randomNumber);
    }
}
