package com.example.Kau_Git.service;

import com.example.Kau_Git.dto.MyPageDto;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.entity.User;
import com.example.Kau_Git.repository.CommentRepository;
import com.example.Kau_Git.repository.PheedHashtagRepository;
import com.example.Kau_Git.repository.PostingRepository;
import com.example.Kau_Git.repository.UserRepository;
import com.example.Kau_Git.service.posting.PheedQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageService { // 새로 정보 등록하기, 회원 정보 가져오기
//    @Autowired
//    UserRepository ur;

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostingRepository postingRepository;
    private final PheedQueryService pheedQueryService;
    public MyPageDto.MyPageMyInfo getMyInfo(Long userId){
        User me = userRepository.findByUserId(userId);
        Integer commentNum = commentRepository.countByWriter_UserId(userId);
        Integer communityNum = postingRepository.countByWriter_UserId(userId);
        Integer recommendedNum = postingRepository.sumRecommendedCntByUserId(userId);

        return MyPageDto.MyPageMyInfo.builder()
                .name(me.getName())
                .userPoint(me.getUserPoint())
                .introduction(me.getIntroduction())
                .mentorScore(me.getMentoringAvgRated())
                .sharingScore(me.getSharingAvgRated())
                .numComment(commentNum)
                .numCommunity(communityNum)
                .numRecommended(recommendedNum)
                .build();
    }



    public MyPageDto.ListPheedDto getPheeds(Long myId) {
        List<Posting> pheeds = postingRepository.findAllByWriter_UserIdAndClassification(myId, 'P');
        List<MyPageDto.PheedDto> pheedDtoList = pheeds.stream()
                .map(this::convertToPheed)
                .collect(Collectors.toList());

        return MyPageDto.ListPheedDto.builder()
                .pheedDtoList(pheedDtoList)
                .build();
    }
    private MyPageDto.PheedDto convertToPheed(Posting pheed) {
        String content = pheed.getContent();

        // 만약 content의 길이가 20자 이상이라면 처음부터 20자까지를, 그렇지 않다면 content 전체를 사용
        String description = content.length() > 20 ? content.substring(0, 20) : content;

        List<String> fileUrls = pheedQueryService.extractFileUrls(pheed);
        List<String> hashtags = pheedQueryService.extractHashtags(pheed);
        return MyPageDto.PheedDto.builder()
                .postingId(pheed.getPostingId())
                .description(description)
                .fileUrls(fileUrls)
                .hashtags(hashtags)
                .build();
    }



    @Transactional
    public User GetInformation(String email, String name){  // pk기반으로 찾아오고, email과 name을 표시한다.
        //이메일 기반 으로 사용자 찾기
        Optional<User> u1 =userRepository.findByEmail(email); //Optional -> null값일 수 도 있기때문에 감아놓는다.


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
        Optional<User> u1 =userRepository.findByEmail(email);

        if(u1.isPresent()) {

            User s1 = u1.get();

            s1.setAddress(address);
            s1.setGender(gender);
            s1.setNationality(nationality);
            s1.setReligion(religion);



            userRepository.save(s1);
        }


    }


    
}
