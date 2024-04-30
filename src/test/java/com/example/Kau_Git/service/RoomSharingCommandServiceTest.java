package com.example.Kau_Git.service;

import com.example.Kau_Git.dto.roomSharing.PostRoomSharingRequestDto;

import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.repository.PostingRepository;
import com.example.Kau_Git.service.posting.RoomSharingCommandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class RoomSharingCommandServiceTest {


    @Autowired
    RoomSharingCommandService roomSharingCommandService;

    @MockBean//실제 리포지토리에 반영되지않게한다.  없거나 autowired로 달면 실제 db에 들어가있음.
            // transactional을 애초에 달아서 롤백해줘도 되긴할듯? transactional은 테스트에서는 롤백기능한다.
    PostingRepository postingRepository;



    @Test
    public void addSharingPostTest() throws Exception {
        PostRoomSharingRequestDto.RegistPostDto posting = PostRoomSharingRequestDto.RegistPostDto.builder()
                .title("제목입니다")
                .content("내용이다")
                .build();
        Posting serviceResult = roomSharingCommandService.registSharing(posting, 1L);

        assertNotNull(serviceResult);
        assertEquals("내용이다", serviceResult.getContent());

    }


}