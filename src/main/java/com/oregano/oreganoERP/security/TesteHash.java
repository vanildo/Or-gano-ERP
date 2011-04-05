package com.oregano.oreganoERP.security;

import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;

public class TesteHash {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    Sha256Hash sha256Hash = new Sha256Hash("vanni1");
	    Sha1Hash sha1 = new Sha1Hash("vanni1");
	    System.out.println("256: " + sha256Hash.toHex());
	    System.out.println("1: " + sha1.toHex());
	}

}
