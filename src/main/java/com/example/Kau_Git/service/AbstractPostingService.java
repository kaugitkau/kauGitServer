package com.example.Kau_Git.service;

import com.example.Kau_Git.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public abstract class AbstractPostingService {

    private PostingRepository postingRepository;

    // 공통 삭제 로직
    public void deletePosting(Long postingId) {

        // 게시물 삭제
        postingRepository.deleteById(postingId);
    }

}
