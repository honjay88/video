package honjay.common.utils;

import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by honjayChen on 2017/4/11.
 */

public class CryptoEngine {

    public static String AESDecrypt(String strEncrypt,String strKey) throws IOException {
        try {
            String strIV ="8080808080808080";
            byte[] ivByte = strIV.getBytes("UTF-8");
            AlgorithmParameterSpec mAlgorithmParameterSpec = new IvParameterSpec(ivByte);

            byte[] keyByte = strKey.getBytes("UTF-8");
            SecretKeySpec secretKey = new SecretKeySpec(keyByte, "AES");

            Cipher decipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            decipher.init(Cipher.DECRYPT_MODE, secretKey, mAlgorithmParameterSpec);

            byte[] byteToEncrypt = Base64.decode(strEncrypt,Base64.DEFAULT);//Base64.decode(strEncrypt.getBytes(), Base64.DEFAULT);
            byte[] decryptByte = decipher.doFinal(byteToEncrypt);

            String decryptString = Base64.encodeToString(decryptByte, Base64.DEFAULT);
            return decryptString;
        } catch (NoSuchAlgorithmException e) {
            //handle exception
            // ex: e.printStackTrace(); System.exit(1);
            throw new RuntimeException(e.getMessage());
        } catch (NoSuchPaddingException e) {
            //handle exception
            throw new RuntimeException(e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            //handle exception
            throw new RuntimeException(e.getMessage());
        } catch (InvalidKeyException e) {
            //handle exception
            throw new RuntimeException(e.getMessage());
        } catch (IllegalBlockSizeException e) {
            //handle exception
            throw new RuntimeException(e.getMessage());
        } catch (BadPaddingException e) {
            //handle exception
            throw new RuntimeException(e.getMessage());
        }
    }

    public static byte[] AESEncrypt(byte[] byteToDecrypt, String strKey) throws IOException {
        try {
            String strIV ="8080808080808080";
            byte[] ivByte = strIV.getBytes("UTF-8");
            AlgorithmParameterSpec mAlgorithmParameterSpec = new IvParameterSpec(ivByte);

            byte[] keyByte = strKey.getBytes("UTF-8");
            SecretKeySpec mSecretKeySpec = new SecretKeySpec(keyByte, "AES");

            Cipher mCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            mCipher.init(Cipher.ENCRYPT_MODE, mSecretKeySpec, mAlgorithmParameterSpec);

           // byte[] byteToDecrypt = Base64.decode(strToDecrypt, Base64.DEFAULT);
            byte[] encryptByte = mCipher.doFinal(byteToDecrypt);

           // String decryptString = new String(decryptByte,"UTF-8");
            return encryptByte;
        } catch (UnsupportedEncodingException e) {
            //handle exception
            // ex: e.printStackTrace(); System.exit(1);
            throw new RuntimeException(e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            //handle exception
            throw new RuntimeException(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            //handle exception
            // ex: e.printStackTrace(); System.exit(1);
            throw new RuntimeException(e.getMessage());
        } catch (NoSuchPaddingException e) {
            //handle exception
            throw new RuntimeException(e.getMessage());
        } catch (InvalidKeyException e) {
            //handle exception
            throw new RuntimeException(e.getMessage());
        } catch (IllegalBlockSizeException e) {
            //handle exception
            throw new RuntimeException(e.getMessage());
        } catch (BadPaddingException e) {
            //handle exception
            throw new RuntimeException(e.getMessage());
        }
    }
}