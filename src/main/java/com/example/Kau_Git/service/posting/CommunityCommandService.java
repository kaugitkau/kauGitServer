package com.example.Kau_Git.service.posting;

import com.example.Kau_Git.dto.community.CommunityRequestDto;
import com.example.Kau_Git.entity.Hashtag;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.entity.PostingHashtag;
import com.example.Kau_Git.entity.User;
import com.example.Kau_Git.repository.*;
import com.example.Kau_Git.service.AbstractPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityCommandService extends AbstractPostingService {
    private final PostingRepository postingRepository;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;
    private final PostingHashtagRepository postingHashtagRepository;

    public void addPosting(Long userId, CommunityRequestDto.AddPostingDto addPostingDto) {
        User writer = userRepository.findByUserId(userId);
        Posting posting = Posting.builder()
                .writer(writer)
                .classification('C')
                .content(addPostingDto.getContent())
                .title(addPostingDto.getTitle())
                .build();
        postingRepository.save(posting);

        List<String> hashtags = addPostingDto.getHashtags();
        if (hashtags!=null) saveHashtag(posting, hashtags);

    }

    private void saveHashtag(Posting posting, List<String> hashtags) {
        hashtags.stream()
                .forEach(tag -> {
                    Hashtag hashtag = Hashtag.builder()
                            .word(tag)
                            .build();
                    Hashtag save = hashtagRepository.save(hashtag);

                    PostingHashtag postingHashtag = new PostingHashtag(posting, save);
                    postingHashtagRepository.save(postingHashtag);
                });

    }



}
