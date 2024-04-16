package com.example.Kau_Git.service;

import com.example.Kau_Git.dto.roomSharing.PostRoomSharingRequestDto;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomSharingCommandService {
    private final PostingRepository postingRepository;

    private void save(Posting post){ //private 제어자는 해당 멤버를 선언한 클래스 내에서만 접근할 수 있도록 제한한다.
        postingRepository.save(post);
    }

    public Posting registSharing(PostRoomSharingRequestDto.RegistPostDto registPost){
        Posting posting = Posting.builder()
                .content(registPost.getContent())
                .build();
        save(posting);

        return posting;


    }


}
