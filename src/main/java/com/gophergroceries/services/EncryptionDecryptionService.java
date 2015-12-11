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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EncryptionDecryptionService {
	private static final Logger logger = LoggerFactory.getLogger(EncryptionDecryptionService.class);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MessageDigest sha = null;
		try {
			sha = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
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

	// public String encrypt(String stringToEncrypt)
	// throws UnsupportedEncodingException, NoSuchAlgorithmException,
	// NoSuchPaddingException, InvalidKeyException,
	// InvalidAlgorithmParameterException, ShortBufferException,
	// IllegalBlockSizeException, BadPaddingException {
	// byte[] input = stringToEncrypt.getBytes("UTF-8");
	// cypher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
	// byte[] encrypted = new byte[cypher.getOutputSize(input.length)];
	// int enc_len = cypher.update(input, 0, input.length, encrypted, 0);
	// enc_len += cypher.doFinal(encrypted, enc_len);
	// logger.info("Original: " + stringToEncrypt + "\n"
	// + "Encrypted: \"" + encrypted.toString() + "\"\n"
	// + "Encrypted Length: " + enc_len);
	// return encrypted.toString();
	// }
	//
	// public String decrypt(String stringToDecrypt)
	// throws UnsupportedEncodingException, NoSuchAlgorithmException,
	// NoSuchPaddingException, InvalidKeyException,
	// InvalidAlgorithmParameterException, ShortBufferException,
	// IllegalBlockSizeException, BadPaddingException {
	// byte[] input = stringToDecrypt.getBytes("UTF-8");
	// logger.info("Decrypt String Length: " + input.length);
	// cypher.init(Cipher.DECRYPT_MODE, key, ivSpec);
	// byte[] decrypted = new byte[cypher.getOutputSize(input.length)];
	// int dec_len = cypher.update(input, 0, input.length, decrypted, 0);
	// dec_len += cypher.doFinal(decrypted, dec_len);
	// logger.info("Original: " + stringToDecrypt + "\n"
	// + "Decrypted: " + decrypted.toString() + "\n"
	// + "Decrypted Length: " + dec_len);
	// return decrypted.toString();
	// }
	//
	// private Cipher getEnccypherryptionCypher()
	// throws UnsupportedEncodingException, NoSuchAlgorithmException,
	// NoSuchPaddingException {
	// byte[] keyBytes;
	// byte[] ivBytes = "Initialization Vector".getBytes();
	//
	// keyBytes = (SALT2 + this.getClass().getName()).getBytes("UTF-8");
	// MessageDigest sha = MessageDigest.getInstance("SHA-1");
	// keyBytes = sha.digest(keyBytes);
	// keyBytes = Arrays.copyOf(keyBytes, 8); // use only first 56 bits
	// ivBytes = Arrays.copyOf(ivBytes, 8);// use only first 56 bits
	//
	// // wrap key data in Key/IV specs to pass to cipher
	// key = new SecretKeySpec(keyBytes, "DES");
	// ivBytes = Arrays.copyOf(ivBytes, 8);
	// ivSpec = new IvParameterSpec(ivBytes);
	// // create the cipher with the algorithm you choose
	// // see javadoc for Cipher class for more info, e.g.
	// Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
	// return cipher;
	// }

}
