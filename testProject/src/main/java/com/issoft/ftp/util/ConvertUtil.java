package com.issoft.ftp.util;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class contains converters
 */
public class ConvertUtil {
    /**
     * Log4j logger
     */
    private final static Logger LOGGER = Logger.getLogger(ConvertUtil.class);

    /**
     * Converts original string to MD5
     * @param originString String to convert
     * @return MD5 result
     * @throws NoSuchAlgorithmException In case of error
     */
    public static String stringToMD5(String originString) throws NoSuchAlgorithmException {
        String resultString = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(originString.getBytes());
            BigInteger hash = new BigInteger(1, md5.digest());
            resultString = hash.toString(16);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e);
            throw new NoSuchAlgorithmException(e);
        }
        return resultString;
    }
}
