package com.innova.backend.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.apache.commons.logging.Log;

public class JWTUtils {

    public static void decoded(String JWTEncoded) throws Exception {
        try {
            String[] split = JWTEncoded.split("\\.");
            System.out.println("JWT_DECODED Header: " + getJson(split[0]));
            System.out.println("JWT_DECODED Body: " + getJson(split[1]));
        } catch (UnsupportedEncodingException e) {
            //Error
        }
    }

    private static String getJson(String strEncoded) throws UnsupportedEncodingException{
        byte[] decodedBytes = Base64.getDecoder().decode(strEncoded);
        return new String(decodedBytes, "UTF-8");
    }
}