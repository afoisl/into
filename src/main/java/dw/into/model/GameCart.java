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
@Table(name = "game_cart")
public class GameCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameCartId;

    @ManyToOne
    @JoinColumn
    private User user;

}
