package thymeleaf.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;

@Entity
@Setter@Getter
@ToString
@NoArgsConstructor

@Table(name = "groups")
public class Group {

    @Id
    @SequenceGenerator(
            name = "group_sequence",
            sequenceName = "group_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "group_sequence")

    private Long id;
    private String groupName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfFinish;
    @ManyToOne (cascade = MERGE) //orphanRemoval = true
    private Course course;

    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Student> students = new ArrayList<>();
    @Transient
    private Long courseId; //LIST


    public void setStudent(Student student){
        this.students.add(student);
    }


}
