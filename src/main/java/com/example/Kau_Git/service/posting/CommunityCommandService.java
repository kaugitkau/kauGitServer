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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommunityCommandService extends AbstractPostingService {
    private final PostingRepository postingRepository;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;
    private final PostingHashtagRepository postingHashtagRepository;

    //게시글 작성하기
    public void addPosting(Long userId, CommunityRequestDto.AddPostingDto addPostingDto) {
        //작성자 정보 가져오기
        User writer = userRepository.findByUserId(userId);
        //게시물 Entity 생성
        Posting build = Posting.builder()
                .writer(writer) //작성자 정보
                .classification('C') //분류 설정
                .content(addPostingDto.getContent()) //내용 가져오기
                .title(addPostingDto.getTitle()) //제목 설정
                .build();//Entity 생성
        List<String> hashtags = addPostingDto.getHashtags();
        if (hashtags!=null) saveHashtag(build, hashtags);
        //DB에 저장
        postingRepository.save(build);

    }
    private void saveHashtag(Posting posting, List<String> hashtags) {
        hashtags.stream()
                .forEach(tag -> {
                    Optional<Hashtag> existingHashtag = hashtagRepository.findByWord(tag);
                    Hashtag hashtag;
                    if (existingHashtag.isPresent()) {
                        hashtag = existingHashtag.get();
                    } else {
                        hashtag = Hashtag.builder()
                                .word(tag)
                                .build();
                        hashtag = hashtagRepository.save(hashtag);
                    }
                    PostingHashtag pheedHashtag = new PostingHashtag(posting, hashtag);
                    postingHashtagRepository.save(pheedHashtag);
                });

    }

}
