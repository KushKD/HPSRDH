package in.gov.hp.aadhaar.hpsrdh;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by kush on 17/07/15.
 */
public class EncryptData {
    private static MessageDigest digester;
    static {
        try {
            digester = MessageDigest.getInstance(EConstants.EnycType);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public static String Encrypt_String(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException(EConstants.EnycError);
        }
        digester.update(str.getBytes());
        byte[] hash = digester.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append(EConstants.IMEINumber + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString();
    }
}
