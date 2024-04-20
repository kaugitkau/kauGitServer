package com.example.Kau_Git.service;

import com.example.Kau_Git.dto.roomSharing.PostRoomSharingRequestDto;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.entity.User;
import com.example.Kau_Git.repository.PostingRepository;
import com.example.Kau_Git.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomSharingCommandService {
    private final PostingRepository postingRepository;
    private final UserRepository userRepository;

    private void save(Posting post){ //private 제어자는 해당 멤버를 선언한 클래스 내에서만 접근할 수 있도록 제한한다.
        postingRepository.save(post);
    }

    final
    public Posting registSharing(PostRoomSharingRequestDto.RegistPostDto registPost, Long userId){
        User user = userRepository.findByUserId(userId);
        Posting posting = Posting.builder()
                .title(registPost.getTitle())
                .content(registPost.getContent())
                .writer(user)
                .build();
        save(posting);

        return posting;
    }


}
