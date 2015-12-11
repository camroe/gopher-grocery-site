package com.gophergroceries.services;

import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/root-context.xml")
public class EncryptionDecryptionTest {
	private final String SESSION_ID = "TestSessionID";
	private final String ENCRYPTED_STRING = "HQeGUxUQ36dI+hpjxwiqoA==";

	@Autowired
	EncryptionDecryptionService edService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEncryptWithSessionID() {
		String encrypted = "";
		try {
			System.out.println("Original: " + SESSION_ID);
			encrypted = edService.encrypt(SESSION_ID);
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | ShortBufferException | IllegalBlockSizeException
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Encrypted: " + encrypted);
		String msg = "Encrypted value should be " + ENCRYPTED_STRING + ": Actual value is "
				+ encrypted;
		assertTrue(msg, ENCRYPTED_STRING.equals(encrypted));
	}

	@Test
	public void testBothSessionID() {
		String encrypted = "";
		String decrypted = "";
		try {
			System.out.println("Original: " + SESSION_ID);
			encrypted = edService.encrypt(SESSION_ID);
			System.out.println("Encrypted: " + encrypted);
			decrypted = edService.decrypt(encrypted);
			System.out.println("Decrypted: " + decrypted);
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | ShortBufferException | IllegalBlockSizeException
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDecryptWithSessionID() {
		String decrypted = "";
		try {
			System.out.println("Original: " + SESSION_ID);
			decrypted = edService.decrypt(ENCRYPTED_STRING);
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | ShortBufferException | IllegalBlockSizeException
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Decrypted: " + decrypted);
		String msg = "Decrypted value should be " + SESSION_ID + ": Actual value is "
				+ decrypted;
		assertTrue(msg, SESSION_ID.equals(decrypted));
	}

}
