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
@Table(name = "game_grade")
public class GameGrade {
    @Id
    @Column
    private String gameGradeName;

    @Column
    private int highLimit;

    @Column
    private int lowLimit;
}
