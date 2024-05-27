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

    //댓글 작성
    public void addComment(Long postingId, Long writerId, CommentRequestDto.AddCommentDto addCommentDto) {
        Posting byPostingId = postingRepository.findByPostingId(postingId);//게시물의 ID를 가져옴
        User byUserId = userRepository.findByUserId(writerId);//작성자의 ID를 가져옴

        Comment build = Comment.builder()
                .content(addCommentDto.getContent())//댓글 내용
                .posting(byPostingId) //게시물 ID
                .writer(byUserId) //작성자의 ID
                .build(); //Comment Entity 생성
        commentRepository.save(build); //저장

    }

    //게시물의 댓글 보기
    public List<CommentResponseDto.CommentPreviewDto> showComments(Long postId) {
        Posting byPostId = postingRepository.findByPostingId(postId); //게시물의 ID를 가져옴
        //게시물의 ID로 달린 댓글을 모두 가져옴
        List<CommentResponseDto.CommentPreviewDto> collect = commentRepository.findAllByPostingOrderByCreatedAtAsc(byPostId)
                .stream()
                .map(c -> CommentResponseDto.CommentPreviewDto.builder()
                        .content(c.getContent())
                        .createdDate(c.getCreatedAt()) //생성 날짜
                        .nickName(c.getWriter().getNickname())
                        .writerId(c.getWriter().getId())
                        .build())
                .collect(Collectors.toList());
        return collect;

    }

    //댓글을 삭제하는 경우
    public void deleteComment(Long userId, Long commentId){
        commentRepository.deleteById(commentId);
    }
    //commentId
}
