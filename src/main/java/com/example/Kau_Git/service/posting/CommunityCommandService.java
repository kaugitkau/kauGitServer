package com.example.Kau_Git.service.posting;

import com.example.Kau_Git.dto.community.CommunityRequestDto;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.entity.User;
import com.example.Kau_Git.repository.CommentRepository;
import com.example.Kau_Git.repository.PostingRepository;
import com.example.Kau_Git.repository.UserRepository;
import com.example.Kau_Git.service.AbstractPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityCommandService extends AbstractPostingService {
    private final PostingRepository postingRepository;
    private final UserRepository userRepository;

    public void addPosting(Long userId, CommunityRequestDto.AddPostingDto addPostingDto) {
        User writer = userRepository.findByUserId(userId);
        Posting build = Posting.builder()
                .writer(writer)
                .classification('C')
                .content(addPostingDto.getContent())
                .title(addPostingDto.getTitle())
                .build();
        postingRepository.save(build);

    }

}
