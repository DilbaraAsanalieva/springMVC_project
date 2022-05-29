package thymeleaf.model;

import lombok.Getter;
import lombok.Setter;
import thymeleaf.model.Course;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Setter@Getter
@Table( name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
   private String firstName;
   private String email;
   private String lastName;
   @OneToOne
    private Course course;

}
