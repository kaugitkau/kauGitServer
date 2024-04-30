package com.example.Kau_Git.service;

import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteService {
    private final PostingRepository postingRepository;

    public void deletePosting(Long postingId) {
        postingRepository.deleteById(postingId);
    }
}
