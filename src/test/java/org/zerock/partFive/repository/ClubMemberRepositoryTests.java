package org.zerock.partFive.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.partFive.entity.ClubMember;
import org.zerock.partFive.entity.ClubMemberRole;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ClubMemberRepositoryTests {

    @Autowired
    private ClubMemberRepository clubMemberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("더미테이더 100개 삽입")
    void insertDummies(){

        IntStream.rangeClosed(1,100).forEach(i->{
            ClubMember clubMember = ClubMember.builder()
                    .email("jyyoun"+i+"@naver.com")
                    .password(passwordEncoder.encode("1111"))
                    .name("user"+i)
                    .fromSocial(false)
                    .build();

            clubMember.addMemberRole(ClubMemberRole.USER);
            if(i>80){
                clubMember.addMemberRole(ClubMemberRole.MANAGER);
            }
            if(i>90){
                clubMember.addMemberRole(ClubMemberRole.ADMIN);
            }
            clubMemberRepository.save(clubMember);
        });
    }
    @Test
    @DisplayName("이메일로 조회")
    void testRead(){
        Optional<ClubMember> result = clubMemberRepository.findByEmail("jyyoun90@naver.com", false);

        if(result.isPresent()){
            ClubMember clubMember = result.get();
            System.out.println("clubMember = " + clubMember);
        }
    }
}
