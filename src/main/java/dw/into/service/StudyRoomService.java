package dw.into.service;

import dw.into.model.StudyRoom;
import dw.into.repository.StudyRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyRoomService {
    @Autowired
    private StudyRoomRepository studyRoomRepository;

    public List<StudyRoom> getAllStudyRooms() {
        return studyRoomRepository.findAll();
    }

    public Optional<StudyRoom> getStudyRoomById(int id) {
        return studyRoomRepository.findById(id);
    }
}
