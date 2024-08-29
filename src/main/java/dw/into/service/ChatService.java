package dw.into.service;

import dw.into.model.ChatMessage;
import dw.into.model.ChatUser;
import dw.into.model.StudyRoom;
import dw.into.model.User;
import dw.into.repository.ChatMessageRepository;
import dw.into.repository.ChatUserRepository;
import dw.into.repository.StudyRoomRepository;
import dw.into.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatUserRepository chatUserRepository;

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

    public List<ChatUser> getChatUserByRoomId(StudyRoom studyRoom) {
        return chatUserRepository.findByStudyRoom(studyRoom);
    }

    public void saveChatUser(String sender, StudyRoom studyRoom) {
        User user = userRepository.findById(sender).orElseThrow();
        ChatUser chatUser = new ChatUser();
        chatUser.setUser(user);
        chatUser.setStudyRoom(studyRoom);
        chatUserRepository.save(chatUser);
    }

    public void removeChatUser(String sender, StudyRoom studyRoom) {
        User user = userRepository.findById(sender)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + sender));

        ChatUser chatUser = chatUserRepository.findByUserAndStudyRoom(user, studyRoom)
                .orElseThrow(() -> new EntityNotFoundException("ChatUser not found"));

        chatUserRepository.delete(chatUser);
    }
}