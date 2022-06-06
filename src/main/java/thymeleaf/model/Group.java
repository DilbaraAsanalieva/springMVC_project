package thymeleaf.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Setter@Getter
@ToString

@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String groupName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfFinish;
    @ManyToMany
    private List<Course> courses;
    @OneToMany(mappedBy = "group")
    private List<Student> students;
    @Transient
    private Long courseId; //LIST


    public Group(Long id, String groupName,
                 Date dateOfStart, Date dateOfFinish,
                 List<Course> courses, List<Student> students,
                 Long courseId) {
        this.id = id;
        this.groupName = groupName;
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
        this.courses = courses;
        this.students = students;
        this.courseId = courseId;
    }

    public Group() {
    }


}
