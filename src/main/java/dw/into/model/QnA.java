package dw.into.model;

import jakarta.persistence.*;
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
@Table(name = "QnA")
public class QnA {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnAId;

    @ManyToOne
    @JoinColumn
    private User user;

    @Column
    private String title;

    @Column(length = 65535)
    private String text;

    @Column
    private LocalDate writeDate;

}
