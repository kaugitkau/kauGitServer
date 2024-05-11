package com.example.Kau_Git.service;

import com.example.Kau_Git.entity.User;
import com.example.Kau_Git.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class MyPageServiceTest {

    @Autowired
    private com.example.Kau_Git.service.MyPageService ms;

    @Autowired
    private UserRepository ur;

    @Test
    public void testGetInformation() {

        Optional<User> s1 = Optional.ofNullable(ms.GetInformation("mcrohmh@naver.com", "곽홍주"));

        System.out.println(s1.get().getEmail()+" "+s1.get().getName());
    }

    @Test
    public void testSetInformation() {

        ms.SetInformation("mcrohmh@naver.com","서울시 은평구 신사동","불교","한국",0);

    }
}
