package com.example.Kau_Git.repository;

import com.example.Kau_Git.entity.ApplicantRespondent;
import com.example.Kau_Git.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuideMatchingRepository extends JpaRepository<ApplicantRespondent, Long> {

    List<ApplicantRespondent> findAllByRespondent(User guide);

}
