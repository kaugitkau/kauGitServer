package com.example.Kau_Git.service.posting;

import com.example.Kau_Git.apiPayload.code.status.ErrorStatus;
import com.example.Kau_Git.dto.community.CommunityResponseDto;
import com.example.Kau_Git.entity.Hashtag;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.repository.CommentRepository;
import com.example.Kau_Git.repository.PostingHashtagRepository;
import com.example.Kau_Git.repository.PostingRepository;
import com.nimbusds.oauth2.sdk.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityQueryService {

    private final PostingRepository postingRepository;
    private final CommentRepository commentRepository;
    private final PostingHashtagRepository postingHashtagRepository;

    //세부 게시물 내용 보여주기
    public CommunityResponseDto.PostingDto showPosting(Long postId) {
        Posting byPostId = postingRepository.findByPostingId(postId);
        CommunityResponseDto.PostingDto build = CommunityResponseDto.PostingDto.builder()
                .postingId(byPostId.getPostingId())
                .title(byPostId.getTitle())
                .content(byPostId.getContent())
                .region(byPostId.getWriter().getAddress())
                .viewCnt(byPostId.getViewCnt())
                .recommendedCnt(byPostId.getRecommendedCnt())
                .createdAt(byPostId.getCreatedAt())
                .hashTags(extractHashtags(byPostId))
                .build();
        return build;

    }
    //Community 게시물 내용 보여주기
    public CommunityResponseDto.ListDto showList(String sortType) throws GeneralException {

        List<Posting> postings;
        if (sortType == "popularity") {
            postings = postingRepository.findAllByClassification('C');
        }
        else if (sortType == "latest") {
            postings = postingRepository.findByClassificationOrderByCreatedAtDesc('C');
        }
        else{
            throw new GeneralException(ErrorStatus._BAD_REQUEST.getMessage());
        }
        List<CommunityResponseDto.PreviewDto> collect = postings.stream()
                .map(posting ->
                        CommunityResponseDto.PreviewDto.builder()
                                .postingId(posting.getPostingId())
                                .title(posting.getTitle())
                                .createdAt(posting.getCreatedAt())
                                .commentCount(commentRepository.countByPosting_PostingId(posting.getPostingId()))
                                .recommendedCount(posting.getRecommendedCnt())
                                .region(posting.getWriter().getAddress())
                                .description(makeDescription(posting.getContent()))
                                .build())
                .toList();
        CommunityResponseDto.ListDto build = CommunityResponseDto.ListDto.builder()
                .previewDtoList(collect)
                .build();
        return build;
    }


    //게시물 내용 요약 - 20자 이내로
    public String makeDescription(String content){
        // 만약 content의 길이가 20자 이상이라면 처음부터 20자까지를, 그렇지 않다면 content 전체를 사용
        String description = content.length() > 20 ? content.substring(0, 20) : content;
        return description;
    }

    public List<String> extractHashtags(Posting community){
        List<Hashtag> hashtagsByPosting = postingHashtagRepository.findHashtagsByPosting(community);
        return hashtagsByPosting.stream()
                .map(Hashtag::getWord)
                .collect(Collectors.toList());
    }


}
