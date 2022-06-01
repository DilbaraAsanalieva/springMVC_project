package thymeleaf.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Setter@Getter
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
}
