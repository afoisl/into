package dw.into.repository;

import dw.into.model.ChatUser;
import dw.into.model.StudyRoom;
import dw.into.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatUserRepository extends JpaRepository<ChatUser, Integer> {

    Optional<ChatUser> findByUserAndStudyRoom(User user, StudyRoom studyRoom);

    List<ChatUser> findByStudyRoom(StudyRoom studyRoom);
}