package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.model.Group;
import thymeleaf.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

//    @PersistenceContext /////
    private final EntityManager entityManager;

    public StudentRepository(EntityManagerFactory entityManager){
        this.entityManager = entityManager.createEntityManager();
    }

    @Transactional
    public void save(Student student){
        entityManager.persist(student);
    }

    List<Student> students = new ArrayList<Student>();


    public List<Student> findAll(){
        entityManager.getTransaction().begin();

        students = entityManager
                .createQuery("select s from Student s",Student.class)
                .getResultList();
        entityManager.getTransaction().commit();
        return students;
    }

    public Student findById(long studentId){

        entityManager.getTransaction().begin();

        Student student = entityManager.find(Student.class, studentId);

        entityManager.getTransaction().commit();

        return student;
    }

    public Student show(long id){
        return students.stream().filter(student -> student.getId()==id).findAny().orElse(null);

    }

    public void update(long studentId,Student student){

        entityManager.getTransaction().begin();

        Student student1 = show(studentId);

        student1.setFirstName(student.getFirstName());

        student1.setLastName(student.getLastName());

        student1.setEmail(student.getEmail());

        entityManager.getTransaction().commit();

    }

    public void deleteById(long studentId){

        entityManager.getTransaction().begin();

        entityManager.remove(entityManager.find(Student.class,studentId));

        entityManager.getTransaction().commit();
    }

}
