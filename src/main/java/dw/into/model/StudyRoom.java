package dw.into.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "study_room")
public class StudyRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int StRoomId;

    @ManyToOne
    @JoinColumn
    private MockGrade mockGrade;

    @OneToOne
    @JoinColumn
    private StudyBoard studyBoard;
}
