package com.example.Kau_Git.service;

import com.example.Kau_Git.dto.community.CommunityRequestDto;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityCommandService extends AbstractPostingService{
    private final PostingRepository postingRepository;

    public void addPosting(Long userId, CommunityRequestDto.AddPostingDto addPostingDto) {
        Posting build = Posting.builder()
                .classification('C')
                .content(addPostingDto.getContent())
                .title(addPostingDto.getTitle())
                .build();
        postingRepository.save(build);

    }

}
