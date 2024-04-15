package com.example.Kau_Git.Service;

import com.example.Kau_Git.Entity.User;
import com.example.Kau_Git.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MyPageService { // 새로 정보 등록하기, 회원 정보 가져오기
    @Autowired
    UserRepository ur;



    @Transactional
    public User GetInformation(String email, String name){  // pk기반으로 찾아오고, email과 name을 표시한다.
        //이메일 기반 으로 사용자 찾기
        Optional<User> u1 =ur.findByEmail(email); //Optional -> null값일 수 도 있기때문에 감아놓는다.


        if(u1.isPresent()){

            User s1 = u1.get();

            String un =s1.getName();
            String ue =s1.getEmail();
            return s1;

        }else{
            return null;
        }


    }

    @Transactional
    public void SetInformation(String email, String address, String religion, String nationality, int gender){ //
        Optional<User> u1 =ur.findByEmail(email);

        if(u1.isPresent()) {

            User s1 = u1.get();

            s1.setAddress(address);
            s1.setGender(gender);
            s1.setNationality(nationality);
            s1.setReligion(religion);



            ur.save(s1);
        }


    }

    
}
