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


    public void save(Likes likes) {
        likesRepository.save(likes);
    }


    public void likePosting(Long userID, Long postId) {
        Posting byPostId = postingRepository.findByPostingId(postId);
        User byUserId = userRepository.findByUserId(userID);
        Likes build = Likes.builder()
                .user(byUserId)
                .posting(byPostId)
                .build();
        save(build);

        byPostId.incrementRecommendedCnt(); //포스팅 엔티티의 좋아요수 필드 1 증가


    }

    public void cancelLike(Long userID, Long postId) {
        // 사용자 ID와 게시글 ID를 기반으로 좋아요 객체를 조회합니다.

        User byUserId = userRepository.findByUserId(userID);
        Posting byPostId = postingRepository.findByPostingId(postId);
        Likes like = likesRepository.findByuserAndPosting(byUserId, byPostId);

        // 해당 좋아요 객체가 존재한다면, 데이터베이스에서 삭제합니다.
        if (like != null) {
            likesRepository.delete(like);
        } else throw new EntityNotFoundException("Like not found.");
        byPostId.decrementRecommendedCnt();
    }


}
