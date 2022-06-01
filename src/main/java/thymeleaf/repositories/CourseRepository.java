package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.model.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CourseRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

//    public CourseRepository(EntityManagerFactory entityManager) {
//        this.entityManager = entityManager.createEntityManager();
//    } //??????????????????????????????


    public List<Course> findAll(){
        return entityManager
                .createQuery("select c from Course c",Course.class)
                .getResultList();
    }

    @Transactional
    public void save(Course course){
//        entityManager.getTransaction().begin();
        entityManager.persist(course);
//        entityManager.getTransaction().commit();
    }

    public void deleteById(Long courseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Course course = entityManager.find(Course.class, courseId);
        entityManager.remove(course);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Course findById(long courseId){
        return entityManager.find(Course.class,courseId);
    }



    }
