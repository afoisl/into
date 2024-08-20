package dw.into.repository;

import dw.into.model.ChatMessage;
import dw.into.model.StudyRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {
    List<ChatMessage> findByStudyRoom(StudyRoom studyRoom);
}