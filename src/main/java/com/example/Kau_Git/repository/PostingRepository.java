package com.example.Kau_Git.repository;

import com.example.Kau_Git.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostingRepository extends JpaRepository<Posting, Long> {

    List<Posting> findAllByClassification(char c);

    List<Posting> findAllByWriter_UserIdAndClassification(Long userId, char c);

    Posting findByPostingId(Long postingId);

    Integer countByWriter_UserId(Long userId);

    @Query("SELECT SUM(p.recommendedCnt) FROM Posting p where p.writer.id = :userId" )
    Integer sumRecommendedCntByUserId(@Param("userId" ) Long userId);

    List<Posting> findAllByTitleContaining(String title);


    @Query(value = "SELECT p.* FROM Posting p inner join User u on p.user_id = u.user_id WHERE p.classification = 'S' AND p.is_hide = false ORDER BY (u.sharing_avg_rated * LOG(1 + recommended_cnt)) DESC LIMIT 3", nativeQuery = true)
    List<Posting> findTop3ByWeightedScore();


    //커뮤니티글을 인기순으로 조회
    @Query(value = "SELECT * FROM Posting WHERE classification = 'C' AND is_hide = FALSE " +
            "ORDER BY ((recommended_cnt * 2) + view_cnt + (comment_cnt*2)) " +
            "/ POW((TIMESTAMPDIFF(HOUR, created_at, NOW()) + 2), 1.8)", nativeQuery = true)
    List<Posting> findHotPostings();

    //신고횟수가 일정횟수 이상인 포스틩들을 조회
    @Query("SELECT p FROM Posting p WHERE p.reportCnt > :reportCnt")
    List<Posting> findAllWithValueGreaterThan(@Param("reportCnt") int value);

    //커뮤니티글을 최신순으로 조회
    List<Posting> findByClassificationOrderByCreatedAtDesc(char classification);



}
