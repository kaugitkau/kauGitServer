package com.example.Kau_Git.repository;

import com.example.Kau_Git.entity.Likes;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    Likes findByuserAndPosting(User user, Posting posting);


    Likes findByUser_UserIdAndPosting_PostingId(Long userId, Long postingId);



}
