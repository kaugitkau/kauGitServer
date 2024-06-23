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
        //DB에 저장
        postingRepository.save(build);

    }

}
