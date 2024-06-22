package com.example.Kau_Git.service;

import com.example.Kau_Git.entity.Likes;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.entity.User;
import com.example.Kau_Git.repository.LikesRepository;
import com.example.Kau_Git.repository.PostingRepository;
import com.example.Kau_Git.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesCommandService {
    private final PostingRepository postingRepository;
    private final UserRepository userRepository;
    private final LikesRepository likesRepository;

    //Likes Entity(postingId, userId)를 저장
    public void save(Likes likes) {
        likesRepository.save(likes);
    }

    //글 좋아요 하기, 취소하기
    public void checkLikeStatus(Long userId, Long postingId) {
        //게시물(postingId)의 사용자(userId)가 좋아요를 했는지 확인 - Likes Entity를 가져옴
        Likes like = likesRepository.findByUser_UserIdAndPosting_PostingId(userId, postingId);
        if (like != null){
            //like 객체가 존재하는 경우 -> 해당 게시물에 유저가 좋아요를 누른 것
            likePosting(userId, postingId);
        }
        else{
            //like 객체가 존재하지 않는 것
            cancelLike(like);
        }
    }
    //좋아요를 누르는 경우
    public void likePosting(Long userID, Long postId) {
        Posting byPostId = postingRepository.findByPostingId(postId); //좋아요를 누른 게시물 찾기
        User byUserId = userRepository.findByUserId(userID); //현재 유저(좋아요를 누른 유저) 정보 찾기
        //Entity 생성
        Likes build = Likes.builder()
                .user(byUserId) //좋아요를 누른 사람
                .posting(byPostId) //좋아요를 누른 게시물
                .build(); //Likes생성
        save(build); //생성한 Likes를 저장

        //Posting Entity의 좋아요수 필드 1증가
        byPostId.incrementRecommendedCnt();


    }

    //좋아요를 취소하는 경우
    public void cancelLike(Likes likes) {
        //게시물을 가져옴
        Posting posting = likes.getPosting();
        //게시물의 추천 수를 하나 감소 시킴
        posting.decrementRecommendedCnt();
        //좋아요 정보를 삭제 시킴
        likesRepository.delete(likes);


    }


}
