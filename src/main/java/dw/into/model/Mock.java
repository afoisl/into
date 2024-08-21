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
@Table(name = "mock")
public class Mock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mockId;

    @Column
    private String mockImg;
}
