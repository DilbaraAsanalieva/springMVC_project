package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;
    public EntityManagerFactory entityManagerFactory;



    public List<Student> findAll(){
        return entityManager
                .createQuery("select s from Student s",Student.class)
                .getResultList();
    }

//    public void save(Student student){
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(student);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }

    @Transactional
    public void save(Student student){
        entityManager.persist(student);
    }
}
