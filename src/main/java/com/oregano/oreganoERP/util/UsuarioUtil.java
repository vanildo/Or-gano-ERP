package com.oregano.oreganoERP.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.jboss.seam.solder.log.Category;

@Stateless
public class UsuarioUtil {

	@Inject @Category("oreganoERP :: UserUtil")
	private Logger log;
	
	public String generateSHA1Hash(String word) {

        String hashedWord = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(word.getBytes("UTF-8"));
            BigInteger hash = new BigInteger(1, md.digest());
            hashedWord = hash.toString(16);
        } catch (NoSuchAlgorithmException e) {
            log.error("Erro recuperando o algoritmo de criptografia SHA-1.", e);
        } catch (UnsupportedEncodingException e) {
            log.error("Erro criando o hash da senha: encoding nao suportado.", e);
        }

        return hashedWord;
    }

    public String generatePassword(int passLength) {

        SecureRandom wheel;
        StringBuffer sb = new StringBuffer();
        char[] printableAscii = new char[] { '!', '\"', '#', '$', '%', '(', ')', '*', '+', '-', '.', '/', '\'', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', '0', ':', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~', 'a',
                'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

        try {
            wheel = SecureRandom.getInstance("SHA1PRNG");

            for (int i = 0; i < passLength; i++) {
                int random = wheel.nextInt(printableAscii.length);
                sb.append(printableAscii[random]);
            }
        } catch (NoSuchAlgorithmException e) {
            log.error("Erro ao criar a senha.", e);
            sb.append("abcd1234");
        }

        return sb.toString();

    }
}
