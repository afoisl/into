package dw.into.service;

import dw.into.model.ChatMessage;
import dw.into.model.StudyRoom;
import dw.into.repository.ChatMessageRepository;
import dw.into.repository.StudyRoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private StudyRoomRepository studyRoomRepository;

    public ChatMessage saveMessage(StudyRoom studyRoom, ChatMessage chatMessage) {
        chatMessage.setStudyRoom(studyRoom);
        return chatMessageRepository.save(chatMessage);
    }

    public StudyRoom findStudyRoomById(Integer roomId) {
        return studyRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("StudyRoom with id " + roomId + " not found"));
    }

    public List<ChatMessage> findMessagesByRoomId(int roomId) {
        StudyRoom studyRoom = studyRoomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("StudyRoom not found"));
        return chatMessageRepository.findByStudyRoom(studyRoom);
    }
}