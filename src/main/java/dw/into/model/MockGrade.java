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
@Table(name = "mock_grade")
public class MockGrade {
    @Id
    @Column
    private String mockGradeName;

    @Column
    private int highLimit;

    @Column
    private int lowLimit;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "mock_id")
    private Mock mock; // 어떤 시험의 등급인지
}
