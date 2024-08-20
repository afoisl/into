package dw.into.controller;

import dw.into.model.StudyRoom;
import dw.into.service.StudyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/studyroom")
public class StudyRoomController {

    @Autowired
    private StudyRoomService studyRoomService;

    @GetMapping
    public List<StudyRoom> getAllStudyRooms() {
        return studyRoomService.getAllStudyRooms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyRoom> getStudyRoomById(@PathVariable int id) {
        Optional<StudyRoom> studyRoom = studyRoomService.getStudyRoomById(id);
        return studyRoom.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}