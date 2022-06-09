package com.example.lanlineelderdemo.utils;

import java.nio.charset.StandardCharsets;

public class Encoder {
    public static String encodeUtf8(String s) {
        return new String(s.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);

    }
}
