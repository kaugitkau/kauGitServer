package com.example.Kau_Git.service.posting;

import com.example.Kau_Git.dto.roomSharing.PostRoomSharingResponseDto;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomSharingQueryService {

    private final PostingRepository postingRepository;

    public PostRoomSharingResponseDto.PostResultDto showPosting(Long postId) {
        //DB에서 postId로 검색해서 게시물을 찾음
        Posting byPostId = postingRepository.findByPostingId(postId);
        //
        return PostRoomSharingResponseDto.PostResultDto.builder()
                .title(byPostId.getTitle()) //
                .content(byPostId.getContent()) //내용
                .writer(byPostId.getWriter().getNickname()) //작성자
                .viewCnt(byPostId.getViewCnt()) //조회수
                .recommendedCnt(byPostId.getRecommendedCnt()) //추천(좋아요)수
                .build();
        //오류발생: Resolved [org.springframework.web.HttpMediaTypeNotAcceptableException: No acceptable representation]
        //해결법: dto에 getter를 넣어야한다!

    }

    //모든 게시물 조회하기
    public PostRoomSharingResponseDto.AllPostDto showAllPosting(){
        List<Posting> allPosting = postingRepository.findAll();

        List<PostRoomSharingResponseDto.SimplePostDto> simplePostDtos=new ArrayList<>();

        for (Posting posting : allPosting) {
            PostRoomSharingResponseDto.SimplePostDto build = PostRoomSharingResponseDto.SimplePostDto.builder()
                    .title(posting.getTitle())
                    .postId(posting.getPostingId())
                    .build();
            simplePostDtos.add(build);

        }

        return PostRoomSharingResponseDto.AllPostDto.builder()
                .list(simplePostDtos)
                .build();

//        List<PostRoomSharingResponseDto.SimplePostDto> simplePostDtos = postingRepository.findAll().stream()
//                .map(posting -> PostRoomSharingResponseDto.SimplePostDto.builder()
//                        .title(posting.getTitle())
//                        .build())
//                .collect(Collectors.toList());
//
//        return PostRoomSharingResponseDto.AllPostDto.builder()
//                .posts(simplePostDtos)
//                .build();  //java8 이상 stream 이용한 버전.

    }
}
