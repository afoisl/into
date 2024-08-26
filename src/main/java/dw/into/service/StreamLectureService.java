package dw.into.service;

import dw.into.model.Lecture;
import dw.into.model.StreamLecture;
import dw.into.repository.StreamLectureRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StreamLectureService {

    @Autowired
    StreamLectureRepository streamLectureRepository;

    @Transactional
    public StreamLecture scheduleStream(String title, LocalDateTime startTime, LocalDateTime endTime, String videoPath) {
        StreamLecture streamLecture = new StreamLecture();

        streamLecture.setStreamTitle(title);
        streamLecture.setStartTime(startTime);
        streamLecture.setEndTime(endTime);
        streamLecture.setVideoPath(videoPath);
        return streamLectureRepository.save(streamLecture);
    }

    public Optional<StreamLecture> getStreamStatus(Integer streamId) {
        return streamLectureRepository.findById(streamId);
    }

    public boolean isStreamLive(StreamLecture streamLecture) {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(streamLecture.getStartTime()) && now.isBefore(streamLecture.getEndTime());
    }

    public long getElapsedSeconds(StreamLecture streamLecture) {
        return java.time.Duration.between(streamLecture.getStartTime(), LocalDateTime.now()).getSeconds();
    }

    public List<StreamLecture> getAllStreams() {
        return streamLectureRepository.findAll();
    }
}
