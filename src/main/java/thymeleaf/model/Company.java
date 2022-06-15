package thymeleaf.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
@Table(name = "companies")
public class Company {

    @Id
    @SequenceGenerator(
            name = "company_sequence",
            sequenceName = "company_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "company_sequence")

    private Long id;
    private String companyName;
    private String locatedCountry;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Course> courses = new ArrayList<>();

    public void setCourse(Course course){
        this.courses.add(course);
    }
}
