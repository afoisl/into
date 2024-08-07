package dw.into.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "study_board")
public class StudyBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studyBoardId;

    @ManyToOne
    @JoinColumn
    private User user;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDate writeDate;
}
