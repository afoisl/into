package dw.into.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.Store;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "lecture")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeItemId;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "lecture_name")
    private String lectureName;

    @Column(name = "lecture_image")
    private String lectureImage;

    @Column
    private String subject;

    @Column
    private int LecPrice;

    @Column
    private String lectureClass;

    @Column(length = 65535)
    private String lecContent;
}