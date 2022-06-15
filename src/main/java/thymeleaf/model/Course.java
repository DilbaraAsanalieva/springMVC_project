package thymeleaf.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Setter@Getter
@ToString
@Table(name = "courses")
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "course_sequence")

    private Long id;
    private String courseName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date duration;

    @ManyToOne//(cascade = {DETACH,REFRESH,MERGE})    //(cascade = {CascadeType.MERGE})//(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE}) ,CascadeType.REFRESH,CascadeType.DETACH
    private Company company;

    @OneToOne(mappedBy = "course",cascade = {REMOVE}, orphanRemoval = true ,fetch = FetchType.EAGER)//EAGER
    private Teacher teacher;

    //@OneToOne(mappedBy = "course",
    //            cascade = {REMOVE},
    //            orphanRemoval = true)
    //    private Teacher teacher;


    @OneToMany(mappedBy = "course",cascade = {DETACH, MERGE, REFRESH},
            orphanRemoval = true,fetch = FetchType.EAGER)//fetch = FetchType.EAGER without delete
    private List<Group> groups = new ArrayList<>();

    @Transient
    private Long companyId;

    @Transient
    private Long groupId;

    public Course(Long id, String courseName, Date duration,Company company) {
        this.id = id;
        this.courseName = courseName;
        this.duration = duration;
        this.company = company;
    }

    public Course() {

    }
    public void setGroup(Group group){
        this.groups.add(group);
    }





}
