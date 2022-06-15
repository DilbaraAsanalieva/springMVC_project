package thymeleaf.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter@Getter
@Table( name = "teachers")
public class Teacher {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)

      @Id
        @SequenceGenerator(
                name = "teacher_sequence",
                sequenceName = "teacher_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                generator = "teacher_sequence")
   private Long id;
   private String firstName;
   private String email;
   private String lastName;
   @OneToOne
   private Course course;
   @Transient
   private Long courseId;
}
