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
@Table(name = "mock_question")
public class MockQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content; // 문제 내용
    private String correctAnswer; // 정답

    @ManyToOne
    @JoinColumn(name = "mock_id")
    private Mock mock; // 관련된 시험 정보
}

