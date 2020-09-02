package cn.com.xuxiaowei.crypto;

import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

/**
 * 测试密码加密
 *
 * @author xuxiaowei
 */
public class BCryptTests {

    /**
     * BCrypt 加密及验证
     */
    @Test
    public void hashpw() {
        String password = "xxw";
        System.out.println("原密码：\t" + password);

        String hashed = BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
        System.out.println("加密后：\t" + hashed);

        boolean checkpw = BCrypt.checkpw(password, hashed);
        System.out.println("验证：\t" + checkpw);
    }

    /**
     * 验证带有前缀的 BCrypt 加密结果
     */
    @Test
    public void securityBcrypt() {
        String password = "xxw";
        System.out.println("原密码：\t\t\t\t\t\t\t" + password);

        String prefixHashed = "{bcrypt}$2a$10$bCMT61vI.XEYZnTILyqKq.nce8a3B1WUHOIJMZUru2ducZytnIRDi";
        System.out.println("带有前缀的 BCrypt 加密后：\t\t\t" + prefixHashed);

        String hashed = "$2a$10$bCMT61vI.XEYZnTILyqKq.nce8a3B1WUHOIJMZUru2ducZytnIRDi";
        System.out.println("BCrypt 加密后：\t\t\t\t\t" + hashed);

        boolean prefixCheckpw = BCryptPasswordEncoder.checkpw(password, prefixHashed);
        System.out.println("验证带有前缀的 BCrypt 加密后：\t\t" + prefixCheckpw);

        boolean checkpw = BCrypt.checkpw(password, hashed);
        System.out.println("验证 BCrypt 加密后：\t\t\t\t" + checkpw);
    }

}
