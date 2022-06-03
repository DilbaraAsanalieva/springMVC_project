package thymeleaf.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter@Setter
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String companyName;
    private String locatedCountry;
    @OneToMany(mappedBy = "company", cascade = CascadeType.MERGE)
    private List<Course> courses;
    public void setCourse(Course course){
        this.courses.add(course);
    }
}
