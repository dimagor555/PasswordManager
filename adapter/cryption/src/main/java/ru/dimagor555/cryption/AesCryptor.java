package ru.dimagor555.cryption;

import ru.dimagor555.domain.port.Decryptor;
import ru.dimagor555.domain.port.Encryptor;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AesCryptor implements Encryptor, Decryptor {
    private final Cipher cipher;
    private final IvParameterSpec ivParameterSpec;
    private SecretKey key;

    public AesCryptor() {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        } finally {
            this.cipher = cipher;
        }
        ivParameterSpec = new IvParameterSpec(new byte[16]);
    }

    public void setKey(String key) {
        byte[] keyBytes = Arrays.copyOf(key.getBytes(StandardCharsets.UTF_8), 32);
        this.key = new SecretKeySpec(keyBytes, "AES");
    }

    @Override
    public String encrypt(String data) {
        try {
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
            byte[] result = cipher.doFinal(dataBytes);
            return Base64.getEncoder().encodeToString(result);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException
                | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String decrypt(String data) {
        try {
            byte[] dataBytes = Base64.getDecoder().decode(data);
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
            byte[] result = cipher.doFinal(dataBytes);
            return new String(result, StandardCharsets.UTF_8);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException
                | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }
}
