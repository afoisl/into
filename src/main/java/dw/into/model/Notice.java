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
@Table(name = "notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long noticeId;

    @ManyToOne
    @JoinColumn
    private User user;

    @Column
    private String title;   

    @Column(length = 65535)
    private String content;

    @Column
    private LocalDate writeDate;
}
