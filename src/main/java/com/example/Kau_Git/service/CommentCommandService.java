package com.example.Kau_Git.service;

import com.example.Kau_Git.dto.CommentRequestDto;
import com.example.Kau_Git.dto.CommentResponseDto;
import com.example.Kau_Git.entity.Comment;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.entity.User;
import com.example.Kau_Git.repository.CommentRepository;
import com.example.Kau_Git.repository.PostingRepository;
import com.example.Kau_Git.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentCommandService extends AbstractPostingService{
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostingRepository postingRepository;

    @Transactional
    public void addComment(Long postingId, Long writerId, CommentRequestDto.AddCommentDto addCommentDto) {
        Posting byPostingId = postingRepository.findByPostingId(postingId);
        User byUserId = userRepository.findByUserId(writerId);
        Comment build = Comment.builder()
                .content(addCommentDto.getContent())
                .posting(byPostingId)
                .writer(byUserId)
                .build();

        byPostingId.incrementCommentCnt();  //포스팅의 댓글수 1 증가


        commentRepository.save(build);

    }


    @Transactional
    public void deleteComment(Long userId, Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id " + commentId));

        Posting posting = comment.getPosting(); // Comment로부터 Posting ID를 얻는다.

        // 댓글을 ID로 삭제
        commentRepository.deleteById(commentId);
        
        posting.decrementCommentCnt();

    }
}
