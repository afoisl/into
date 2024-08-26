package dw.into.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "stream_lecture")
public class StreamLecture {

    @Id
    @GeneratedValue
    private int streamId;

    private String streamTitle;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String videoPath;

}
