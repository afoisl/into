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
@Table(name = "game_point")
public class GamePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gamePointId;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Game game;

    @Column
    private int point;

    @Column
    private LocalDate pointDate;
}
