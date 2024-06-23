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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikesCommandService {
    private final PostingRepository postingRepository;
    private final UserRepository userRepository;
    private final LikesRepository likesRepository;


    public void save(Likes likes) {
        likesRepository.save(likes);
    }


    public void checkLikeStatus(Long userId, Long postingId) {
        Likes like = likesRepository.findByUser_UserIdAndPosting_PostingId(userId, postingId);
        if (like == null) {
            likePosting(userId, postingId);
        } else {
            cancelLike(like);
        }
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
        postingRepository.save(byPostId);  // 변경 사항 저장


    }


    public void cancelLike(Likes like) {
        like.getPosting().decrementRecommendedCnt();

        likesRepository.delete(like);


    }

    public void decrementLikeCount(Long postingId) {
        Posting posting = postingRepository.findById(postingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid posting Id"));
        posting.decrementRecommendedCnt();
        postingRepository.save(posting); // 변경 사항을 저장하여 반영
    }
}