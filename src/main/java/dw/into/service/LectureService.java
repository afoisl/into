package dw.into.service;

import dw.into.model.Lecture;
import dw.into.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }
    public Lecture getLectureById (int id) {
        Optional<Lecture> lecture = lectureRepository.findById(id);
        return lecture.orElse(null);
    }
}
