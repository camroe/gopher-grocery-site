package com.gophergroceries.services;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class EncryptionDecryptionService {
	// private static final Logger logger =
	// LoggerFactory.getLogger(EncryptionDecryptionService.class);
	private final static String SALT1 = "I82frogsI82frogsI82frogs";
	private final static String SALT2 = "nice and salty";
	private static SecretKeySpec key;
	private static IvParameterSpec ivSpec;
	private static Cipher ecipher;
	private static Cipher dcipher;

	static {
		byte[] keyBytes = (SALT1 + SALT2).getBytes();
		byte[] ivBytes = "Initialization Vector".getBytes();

		try {
			keyBytes = (SALT2 + SALT1).getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		MessageDigest sha = null;
		try {
			sha = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		keyBytes = sha.digest(keyBytes);
		keyBytes = Arrays.copyOf(keyBytes, 8); // use only first 56 bits
		ivBytes = Arrays.copyOf(ivBytes, 8);// use only first 56 bits

		// wrap key data in Key/IV specs to pass to cipher
		key = new SecretKeySpec(keyBytes, "DES");
		ivBytes = Arrays.copyOf(ivBytes, 8);
		ivSpec = new IvParameterSpec(ivBytes);
		// create the cipher with the algorithm you choose
		// see javadoc for Cipher class for more info, e.g.
		try {
			ecipher = Cipher.getInstance("DES");
			dcipher = Cipher.getInstance("DES");
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			dcipher.init(Cipher.DECRYPT_MODE, key);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String encrypt(String str) throws Exception {
		// Encode the string into bytes using utf-8
		byte[] utf8 = str.getBytes("UTF8");

		// Encrypt
		byte[] enc = ecipher.doFinal(utf8);

		// Encode bytes to base64 to get a string
		return Base64.getEncoder().encodeToString(enc);
	}

	public String decrypt(String str) throws Exception {
		// Decode base64 to get bytes
		byte[] dec = Base64.getDecoder().decode(str);

		byte[] utf8 = dcipher.doFinal(dec);

		// Decode using utf-8
		return new String(utf8, "UTF8");
	}
}
