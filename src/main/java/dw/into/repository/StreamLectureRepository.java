package dw.into.repository;

import dw.into.model.StreamLecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface StreamLectureRepository extends JpaRepository<StreamLecture, Integer> {

}
