package com.example.Kau_Git.service;

import com.example.Kau_Git.dto.CommentRequestDto;
import com.example.Kau_Git.dto.CommentResponseDto;
import com.example.Kau_Git.entity.Comment;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.entity.User;
import com.example.Kau_Git.repository.CommentRepository;
import com.example.Kau_Git.repository.PostingRepository;
import com.example.Kau_Git.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentCommandService extends AbstractPostingService{
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostingRepository postingRepository;

    public void addComment(Long postId, Long writerId, CommentRequestDto.AddCommentDto addCommentDto) {
        Posting byPostId = postingRepository.findByPostId(postId);
        User byUserId = userRepository.findByUserId(writerId);
        Comment build = Comment.builder()
                .content(addCommentDto.getContent())
                .posting(byPostId)
                .writer(byUserId)
                .build();
        commentRepository.save(build);

    }

    public List<CommentResponseDto.CommentPreviewDto> showComments(Long postId) {
        Posting byPostId = postingRepository.findByPostId(postId);
        List<CommentResponseDto.CommentPreviewDto> collect = commentRepository.findAllByPostingOrderByCreatedAtAsc(byPostId)
                .stream()
                .map(c -> CommentResponseDto.CommentPreviewDto.builder()
                        .content(c.getContent())
                        .createdDate(c.getCreatedAt())
                        .nickName(c.getWriter().getNickname())
                        .writerId(c.getWriter().getId())
                        .build())
                .collect(Collectors.toList());
        return collect;

    }

    public void deleteComment(Long userId, Long commentId){
        commentRepository.deleteById(commentId);
    }
}
