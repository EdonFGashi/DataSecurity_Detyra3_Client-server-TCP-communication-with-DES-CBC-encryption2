package DES_Implementimi;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Arrays;
import java.util.Base64;

public class DES {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }


    public static String encryption(String plaintext, String key){
        try {

            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);


            byte[] ivBytes = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 };
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);


            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);


            byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());


            String encryptedBase64 = Base64.getEncoder().encodeToString(encryptedBytes);

//            System.out.println("Encrypted string (Base64): " + encryptedBase64);
            return encryptedBase64;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String decryption(String message, String qelsi) {
        try {
            String encryptedBase64 = message;
            String key = qelsi;

            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);


            byte[] ivBytes = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07};
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);


            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedBase64);

            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            String decryptedText = new String(decryptedBytes);

            return decryptedText;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    private static byte[] removePadding(byte[] data) {
        int padLength = data[data.length - 1];
        return Arrays.copyOfRange(data, 0, data.length - padLength);
    }
}
