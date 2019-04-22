/**
 *
 */
package com.br.alldreams.socialmidia.manager.utils;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.alldreams.socialmidia.conf.beans.SegurancaConfBeans;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jorge Demetrio
 * @since 20 de abr de 2019 01:18:16
 * @version 1.0
 */
@Slf4j
@AllArgsConstructor
@Component
public class CriptografiaUtils {

	public static byte[] toByteArray(String s) {
		return DatatypeConverter.parseHexBinary(s);
	}

	public static String toHexString(byte[] array) {
		return DatatypeConverter.printHexBinary(array);
	}

	@Autowired
	private SegurancaConfBeans conf;

	public String criptografia(final String valor) {
		try {
			Key aesKey = new SecretKeySpec(conf.getChave().getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			return DatatypeConverter.printBase64Binary(cipher.doFinal(valor.getBytes()));
		} catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			log.error(e.getMessage(), e);
		}
		return "";
	}

	public String decriptografia(final String valor) {
		try {
			Key aesKey = new SecretKeySpec(conf.getChave().getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			return new String(cipher.doFinal(DatatypeConverter.parseBase64Binary(valor)));
		} catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			log.error(e.getMessage(), e);
		}
		return "";
	}
}
