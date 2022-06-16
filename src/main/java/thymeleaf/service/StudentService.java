package thymeleaf.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import thymeleaf.model.Group;
import thymeleaf.model.Student;
import thymeleaf.repositories.GroupRepository;
import thymeleaf.repositories.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final EntityManagerFactory entityManagerFactory;
    private final GroupRepository groupRepository;

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional
    public void save(Long groupId,Student student){
        System.out.println("WORKS?");
        Group group = groupRepository.findById(groupId);
        group.setStudent(student);
        student.setGroup(group);
//        studentRepository.save(student); //
        System.out.println("Student successfully saved!");
    }

    public void deleteById(Long studentId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, studentId);
        entityManager.remove(student);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Student findById(Long studentId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Student student = entityManager.createQuery("select s from Student s where s.id=?1", Student.class).setParameter(1, studentId).getSingleResult();
        entityManager.close();
        return  student;
    }

    public void update(long id, Student student){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("update Student s set s.firstName=?1, s.lastName=?2,s.email=?3 where s.id=?4")
                .setParameter(1,student.getFirstName())
                .setParameter(2,student.getLastName())
                .setParameter(3,student.getEmail())
                .setParameter(4,id)
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();

    }


}
