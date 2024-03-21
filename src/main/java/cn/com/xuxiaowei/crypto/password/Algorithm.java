package cn.com.xuxiaowei.crypto.password;

/**
 * @author xuxiaowei
 * @since 0.0.1
 */
public enum Algorithm {

	NOOP("NOOP"),

	MD5("MD5"),

	SHA_1("SHA-1"),

	SHA_256("SHA-256"),

	SHA_384("SHA-384"),

	SHA_512("SHA-512");

	private final String value;

	Algorithm(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
