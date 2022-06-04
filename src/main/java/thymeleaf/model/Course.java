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
    @ManyToOne(cascade = CascadeType.MERGE)
    private Company company;
    @OneToOne(mappedBy = "course")
    private Teacher teacher;
    @ManyToMany(mappedBy = "courses")
    private List<Group> group;

    @Transient
    private Long companyId;

    public Course(Long id, String courseName, Date duration,Company company) {
        this.id = id;
        this.courseName = courseName;
        this.duration = duration;
        this.company = company;
    }

    public Course() {

    }

    public void assignCompany(Company company){
        this.company = company;
    }



}
