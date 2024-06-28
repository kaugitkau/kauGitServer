package com.example.Kau_Git.service;

import com.example.Kau_Git.dto.CommentResponseDto;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.repository.CommentRepository;
import com.example.Kau_Git.repository.PostingRepository;
import com.example.Kau_Git.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentQueryService {

    private final CommentRepository commentRepository;
    private final PostingRepository postingRepository;

    public List<CommentResponseDto.CommentPreviewDto> showComments(Long postId) {
        Posting byPostId = postingRepository.findByPostingId(postId);
        List<CommentResponseDto.CommentPreviewDto> collect = commentRepository.findAllByPostingOrderByCreatedAtAsc(byPostId)
                .stream()
                .map(c -> CommentResponseDto.CommentPreviewDto.builder()
                        .commentId(c.getCommentId())
                        .content(c.getContent())
                        .createdDate(c.getCreatedAt())
                        .nickName(c.getWriter().getNickname())
                        .writerId(c.getWriter().getId())
                        .build())
                .collect(Collectors.toList());
        return collect;

    }

}
