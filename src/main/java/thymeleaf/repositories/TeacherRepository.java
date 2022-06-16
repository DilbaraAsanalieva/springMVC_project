package thymeleaf.repositories;


import org.springframework.stereotype.Repository;
import thymeleaf.model.Course;
import thymeleaf.model.Student;
import thymeleaf.model.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public TeacherRepository(EntityManagerFactory entityManager) {
        this.entityManager = entityManager.createEntityManager();
    }
    public List<Teacher> findAll(){
        return  entityManager.createQuery("select t from Teacher t",Teacher.class)
                .getResultList();
    }

    @Transactional
    public void save(Teacher teacher) {
        entityManager.persist(teacher);
    }


    public void deleteById(long teacherId){

        entityManager.getTransaction().begin();

        entityManager.remove(entityManager.find(Teacher.class,teacherId));

        entityManager.getTransaction().commit();
    }

    public Teacher findById(long teacherId){
        return entityManager.find(Teacher.class,teacherId);
    }


    List<Teacher> teachers =new ArrayList<>();


    public void update(Teacher teacher,long teacherId){

        entityManager.getTransaction().begin();

        Teacher teacher1 = show(teacherId);

        teacher1.setFirstName(teacher.getFirstName());

        teacher1.setLastName(teacher.getLastName());

        teacher1.setEmail(teacher.getEmail());

        entityManager.getTransaction().commit();

    }
    public Teacher show(long id) {
        return teachers.stream().filter(teacher -> teacher.getId() == id).findAny().orElse(null);

    }

}
