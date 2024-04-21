package com.example.Kau_Git.service;

import com.example.Kau_Git.entity.Likes;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.entity.User;
import com.example.Kau_Git.repository.LikesRepository;
import com.example.Kau_Git.repository.PostingRepository;
import com.example.Kau_Git.repository.UserRepository;
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
        Posting byPostId = postingRepository.findByPostId(postId);
        User byUserId = userRepository.findByUserId(userID);
        Likes build = Likes.builder()
                .user(byUserId)
                .posting(byPostId)
                .build();
        save(build);


    }


}
