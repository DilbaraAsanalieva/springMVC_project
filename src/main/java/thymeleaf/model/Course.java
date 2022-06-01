package thymeleaf.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Setter@Getter
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String courseName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date duration;

    @ManyToMany(mappedBy = "courses")
    private List<Group> groups;
    @ManyToOne
    private Company company;
    @OneToOne(mappedBy = "course")
    private Teacher teacher;
    @ManyToMany(mappedBy = "courses")
    private List<Group> group;

    public Course(Long id, String courseName, Date duration) {
        this.id = id;
        this.courseName = courseName;
        this.duration = duration;
    }

    public Course() {

    }
}
