package cn.com.xuxiaowei.crypto;

import org.junit.Test;

/**
 * 测试密码加密
 *
 * @author xuxiaowei
 */
public class BCryptTests {

    @Test
    public void hashpw() {
        String password = "xxw";
        System.out.println("原密码：\t" + password);

        String hashed = org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
        System.out.println("加密后：\t" + hashed);

        boolean checkpw = org.mindrot.jbcrypt.BCrypt.checkpw(password, hashed);
        System.out.println("验证：\t" + checkpw);
    }

}
