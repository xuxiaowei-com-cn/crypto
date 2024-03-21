package cn.com.xuxiaowei.crypto.password;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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
	void disableSalt() {
		String txt = "徐晓伟";
		// @formatter:off
		String[] result = new String[] {
				"徐晓伟",
				"d4f9f4089a08b0b33174d23523d4ab94",
				"4e966342cc1cc491e57f3be025364be9922826bf",
				"cf19c752331cf7ce40760b00d7bfec852ef583e2c14a13a7bb314bad6a71f53a",
				"a6ec18fd63edb96514ccf00a4901849e2ae5ad13d89cdf0ca5c2689e6e833491deb9697b4d1e691549efd0470292fc7e",
				"9c3ebb33a52766f49310655e7d9fff0cff0c61df8b7f7c0c4df542896f4f48b4e524dd99486996583938c23be507235ece916eed99fc7ad28ee2790cbca11146" };
		// @formatter:on
		Algorithm[] values = Algorithm.values();
		for (int i = 0; i < values.length; i++) {
			MessageDigestPasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder(values[i]);
			passwordEncoder.setEnableSalt(false);
			String encode = passwordEncoder.encode(txt);
			assertNotNull(encode);
			boolean matches = passwordEncoder.matches(txt, encode);
			assertTrue(matches);
			assertEquals(encode, result[i]);
		}
	}

	@Test
	void noop() {
		String uuid = UUID.randomUUID().toString();
		MessageDigestPasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("");
		String encode = passwordEncoder.encode(uuid);
		assertNotNull(encode);
		boolean matches = passwordEncoder.matches(uuid, encode);
		assertTrue(matches);
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
