package org.zerock.partFive.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("비밀번호 인코딩 테스트")
    public void testEncode() {

        String password = "1111";

        String enPW = passwordEncoder.encode(password);
        System.out.println("enPW = " + enPW);

        boolean matchResults = passwordEncoder.matches(password, enPW);

        System.out.println("matchResults = " + matchResults);
    }
}
