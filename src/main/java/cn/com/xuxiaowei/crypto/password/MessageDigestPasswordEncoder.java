package cn.com.xuxiaowei.crypto.password;

import cn.com.xuxiaowei.crypto.codec.Hex;
import cn.com.xuxiaowei.crypto.codec.Utf8;
import cn.com.xuxiaowei.crypto.keygen.Base64StringKeyGenerator;
import cn.com.xuxiaowei.crypto.keygen.StringKeyGenerator;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Objects;

/**
 * This {@link PasswordEncoder} is provided for legacy purposes only and is not considered
 * secure.
 *
 * Encodes passwords using the passed in {@link MessageDigest}.
 *
 * The general format of the password is:
 *
 * <pre>
 * s = salt == null ? "" : "{" + salt + "}"
 * s + digest(password + s)
 * </pre>
 *
 * Such that "salt" is the salt, digest is the digest method, and password is the actual
 * password. For example when using MD5, a password of "password", and a salt of
 * "thisissalt":
 *
 * <pre>
 * String s = salt == null ? "" : "{" + salt + "}";
 * s + md5(password + s)
 * "{thisissalt}" + md5(password + "{thisissalt}")
 * "{thisissalt}2a4e7104c2780098f50ed5a84bb2323d"
 * </pre>
 *
 * If the salt does not exist, then omit "{salt}" like this:
 *
 * <pre>
 * digest(password)
 * </pre>
 *
 * If the salt is an empty String, then only use "{}" like this:
 *
 * <pre>
 * "{}" + digest(password + "{}")
 * </pre>
 *
 * The format is intended to work with the DigestPasswordEncoder that was found in the
 * Spring Security core module. However, the passwords will need to be migrated to include
 * any salt with the password since this API provides Salt internally vs making it the
 * responsibility of the user. To migrate passwords from the SaltSource use the following:
 *
 * <pre>
 * String salt = saltSource.getSalt(user);
 * String s = salt == null ? null : "{" + salt + "}";
 * String migratedPassword = s + user.getPassword();
 * </pre>
 *
 * @author Ray Krueger
 * @author Luke Taylor
 * @author Rob Winch
 * @since 5.0 deprecated Digest based password encoding is not considered secure. Instead
 * use an adaptive one way function like BCryptPasswordEncoder, Pbkdf2PasswordEncoder, or
 * SCryptPasswordEncoder. Even better use {@link DelegatingPasswordEncoder} which supports
 * password upgrades. There are no plans to remove this support. It is deprecated to
 * indicate that this is a legacy implementation and using it is considered insecure.
 */
public class MessageDigestPasswordEncoder implements PasswordEncoder {

	private static final String PREFIX = "{";

	private static final String SUFFIX = "}";

	private StringKeyGenerator saltGenerator = new Base64StringKeyGenerator();

	private boolean encodeHashAsBase64;

	private Digester digester;

	private boolean enableSalt = true;

	/**
	 * The digest algorithm to use Supports the named
	 * <a href="https://java.sun.com/j2se/1.4.2/docs/guide/security/CryptoSpec.html#AppA">
	 * Message Digest Algorithms</a> in the Java environment.
	 * @param algorithm
	 */
	public MessageDigestPasswordEncoder(String algorithm) {
		if ("".equals(algorithm)) {
			this.digester = null;
			return;
		}
		this.digester = new Digester(algorithm, 1);
	}

	public MessageDigestPasswordEncoder(Algorithm algorithm) {
		if (Algorithm.NOOP.equals(algorithm)) {
			this.digester = null;
			return;
		}
		this.digester = new Digester(algorithm.getValue(), 1);
	}

	public void setEncodeHashAsBase64(boolean encodeHashAsBase64) {
		this.encodeHashAsBase64 = encodeHashAsBase64;
	}

	/**
	 * Encodes the rawPass using a MessageDigest. If a salt is specified it will be merged
	 * with the password before encoding.
	 * @param rawPassword The plain text password
	 * @return Hex string of password digest (or base64 encoded string if
	 * encodeHashAsBase64 is enabled.
	 */
	@Override
	public String encode(CharSequence rawPassword) {
		if (digester == null) {
			return rawPassword.toString();
		}
		String salt;
		if (enableSalt) {
			salt = PREFIX + this.saltGenerator.generateKey() + SUFFIX;
		}
		else {
			salt = "";
		}
		return digest(salt, rawPassword);
	}

	private String digest(String salt, CharSequence rawPassword) {
		String saltedPassword = rawPassword + salt;
		byte[] digest = this.digester.digest(Utf8.encode(saltedPassword));
		String encoded = encode(digest);
		return salt + encoded;
	}

	private String encode(byte[] digest) {
		if (this.encodeHashAsBase64) {
			return Utf8.decode(Base64.getEncoder().encode(digest));
		}
		return new String(Hex.encode(digest));
	}

	/**
	 * Takes a previously encoded password and compares it with a rawpassword after mixing
	 * in the salt and encoding that value
	 * @param rawPassword plain text password
	 * @param encodedPassword previously encoded password
	 * @return true or false
	 */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (digester == null) {
			return Objects.equals(rawPassword, encodedPassword);
		}
		String salt = extractSalt(encodedPassword);
		String rawPasswordEncoded = digest(salt, rawPassword);
		return PasswordEncoderUtils.equals(encodedPassword.toString(), rawPasswordEncoded);
	}

	/**
	 * Sets the number of iterations for which the calculated hash value should be
	 * "stretched". If this is greater than one, the initial digest is calculated, the
	 * digest function will be called repeatedly on the result for the additional number
	 * of iterations.
	 * @param iterations the number of iterations which will be executed on the hashed
	 * password/salt value. Defaults to 1.
	 */
	public void setIterations(int iterations) {
		this.digester.setIterations(iterations);
	}

	private String extractSalt(String prefixEncodedPassword) {
		int start = prefixEncodedPassword.indexOf(PREFIX);
		if (start != 0) {
			return "";
		}
		int end = prefixEncodedPassword.indexOf(SUFFIX, start);
		if (end < 0) {
			return "";
		}
		return prefixEncodedPassword.substring(start, end + 1);
	}

	public boolean isEnableSalt() {
		return enableSalt;
	}

	public void setEnableSalt(boolean enableSalt) {
		this.enableSalt = enableSalt;
	}

}
