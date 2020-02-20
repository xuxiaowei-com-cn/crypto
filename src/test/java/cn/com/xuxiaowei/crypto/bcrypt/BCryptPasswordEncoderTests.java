/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.com.xuxiaowei.crypto.bcrypt;

import cn.com.xuxiaowei.crypto.MessageDigestPasswordEncoder;
import cn.com.xuxiaowei.crypto.PasswordEncoder;
import cn.com.xuxiaowei.crypto.PasswordEncoderFactories;
import cn.com.xuxiaowei.crypto.StandardPasswordEncoder;
import org.junit.Test;

/**
 * @author xuxiaowei
 * @since 0.0.1
 */
public class BCryptPasswordEncoderTests {

    private final String rawPassword = "123";

    @Test
    public void encode() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(rawPassword);
        System.out.println(encode);
        boolean matches = passwordEncoder.matches(rawPassword, encode);
        System.out.println(matches);
    }

    @Test
    public void createDelegatingPasswordEncoder() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encode = passwordEncoder.encode(rawPassword);
        System.out.println(encode);
        boolean matches = passwordEncoder.matches(rawPassword, encode);
        System.out.println(matches);
    }

    @Test
    public void md5() {
        PasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("MD5");
        String encode = passwordEncoder.encode(rawPassword);
        System.out.println(encode);
        boolean matches = passwordEncoder.matches(rawPassword, encode);
        System.out.println(matches);
    }

    @Test
    public void SHA1() {
        PasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("SHA-1");
        String encode = passwordEncoder.encode(rawPassword);
        System.out.println(encode);
        boolean matches = passwordEncoder.matches(rawPassword, encode);
        System.out.println(matches);
    }

    @Test
    public void SHA256() {
        PasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("SHA-256");
        String encode = passwordEncoder.encode(rawPassword);
        System.out.println(encode);
        boolean matches = passwordEncoder.matches(rawPassword, encode);
        System.out.println(matches);
    }

    @Test
    public void sha256() {
        PasswordEncoder passwordEncoder = new StandardPasswordEncoder();
        String encode = passwordEncoder.encode(rawPassword);
        System.out.println(encode);
        boolean matches = passwordEncoder.matches(rawPassword, encode);
        System.out.println(matches);
    }

}