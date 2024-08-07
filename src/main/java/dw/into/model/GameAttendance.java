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
@Table(name = "game_attendance")
public class GameAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameAttId;

    @ManyToOne
    @JoinColumn
    private User user;

    @Column
    private LocalDate attDate;
}
