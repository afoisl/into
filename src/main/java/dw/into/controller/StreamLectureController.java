package dw.into.controller;

import dw.into.model.StreamLecture;
import dw.into.service.StreamLectureService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/stream")
public class StreamLectureController {
    @Autowired
    private StreamLectureService streamLectureService;

    @PostMapping("/schedule")
    public ResponseEntity<String> scheduleStream(@RequestBody ScheduleRequest request) {
        streamLectureService.scheduleStream(request.getStreamTitle(), request.getStartTime(), request.getEndTime(), request.getVideoPath());
        return ResponseEntity.ok("Stream Scheduled");
    }

    public ResponseEntity<StreamStatus> getStreamStatus(@PathVariable Integer streamId) {
        return streamLectureService.getStreamStatus(streamId).map(streamLecture -> {
                    boolean isLive = streamLectureService.isStreamLive(streamLecture);
                    long elapsedSeconds = streamLectureService.getElapsedSeconds(streamLecture);
                    StreamStatus status = new StreamStatus(isLive, elapsedSeconds);
                    return ResponseEntity.ok(status);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/lectures")
    public List<StreamLecture> getAllStreams() {
        return streamLectureService.getAllStreams();
    }


    @Getter
    @Setter
    public static class ScheduleRequest {
        private String streamTitle;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private String videoPath;
    }

    public static class StreamStatus {
        public boolean isLive;
        public long elapsedSeconds;

        public StreamStatus(boolean isLive, long elapsedSeconds) {
            this.isLive = isLive;
            this.elapsedSeconds = elapsedSeconds;
        }
    }
}
