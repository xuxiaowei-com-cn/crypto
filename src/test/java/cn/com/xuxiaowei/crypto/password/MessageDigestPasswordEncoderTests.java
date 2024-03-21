package cn.com.xuxiaowei.crypto.password;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MessageDigestPasswordEncoderTests {

	@Test
	void algorithm() {
		Algorithm[] values = Algorithm.values();
		for (Algorithm value : values) {
			String uuid = UUID.randomUUID().toString();
			MessageDigestPasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder(value);
			String encode = passwordEncoder.encode(uuid);
			assertNotNull(encode);
			boolean matches = passwordEncoder.matches(uuid, encode);
			assertTrue(matches);
		}
	}

	@Test
	void md5() {
		String uuid = UUID.randomUUID().toString();
		MessageDigestPasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("MD5");
		String encode = passwordEncoder.encode(uuid);
		assertNotNull(encode);
		boolean matches = passwordEncoder.matches(uuid, encode);
		assertTrue(matches);
	}

	@Test
	void sha1() {
		String uuid = UUID.randomUUID().toString();
		MessageDigestPasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("SHA-1");
		String encode = passwordEncoder.encode(uuid);
		assertNotNull(encode);
		boolean matches = passwordEncoder.matches(uuid, encode);
		assertTrue(matches);
	}

	@Test
	void sha256() {
		String uuid = UUID.randomUUID().toString();
		MessageDigestPasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("SHA-256");
		String encode = passwordEncoder.encode(uuid);
		assertNotNull(encode);
		boolean matches = passwordEncoder.matches(uuid, encode);
		assertTrue(matches);
	}

	@Test
	void sha384() {
		String uuid = UUID.randomUUID().toString();
		MessageDigestPasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("SHA-384");
		String encode = passwordEncoder.encode(uuid);
		assertNotNull(encode);
		boolean matches = passwordEncoder.matches(uuid, encode);
		assertTrue(matches);
	}

	@Test
	void sha512() {
		String uuid = UUID.randomUUID().toString();
		MessageDigestPasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("SHA-512");
		String encode = passwordEncoder.encode(uuid);
		assertNotNull(encode);
		boolean matches = passwordEncoder.matches(uuid, encode);
		assertTrue(matches);
	}

}
