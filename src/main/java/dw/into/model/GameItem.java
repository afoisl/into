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
@Table(name = "gameItem")
public class GameItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long gameItemId;

    @ManyToOne
    @JoinColumn
    private GameCart gameCart;

    @Column
    private String gameItemName;

    @Column
    private int gameItemPrice;
}
