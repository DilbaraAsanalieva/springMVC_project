package thymeleaf.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Setter@Getter
@Table(name = "students")

public class Student {

    @Id
    private UUID id;
    private String firstName;
    private String email;
    private String lastName;
    private StudyFormat studyFormat;

    @ManyToOne
    private Group group;
}
