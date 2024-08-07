package dw.into.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cache.annotation.CacheConfig;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "lec_content")
public class LecContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contentId;

    @ManyToOne
    @JoinColumn
    private Lecture lecture;

    @Column
    private String contentName;

    @Column
    private String videoPath;

    @Column
    private int playTime;
}
