package com.fmi.javaee.vertex.security;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class VertexEncryptor {

	private static final String SECRET_KEY = "VeRtExVertexVERT";

	private static final String INIT_VECTOR = "secret123secret1";

	public static String encrypt(String plainText) throws Exception {
		IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
		SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(plainText.getBytes());
		return new String(Base64.getEncoder().encode(encrypted));
	}

	public static String decrypt(String encryptedText) throws Exception {
		IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
		SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		byte[] resultText = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
		return new String(resultText);
	}

}
