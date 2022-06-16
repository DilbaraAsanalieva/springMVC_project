package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.model.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public CourseRepository(EntityManagerFactory entityManager) {
        this.entityManager = entityManager.createEntityManager();
    }


    public List<Course> findAll(){
        return entityManager
                .createQuery("select c from Course c",Course.class)
                .getResultList();
    }

    @Transactional
    public void save(Course course){
//        entityManager.getTransaction().begin();
        System.out.println("REPO SAVE");
        entityManager.persist(course);
        System.out.println(course);
//        entityManager.getTransaction().commit();
    }

    List<Course> courses =new ArrayList<>();


    public void deleteById(Long courseId) {
        entityManager.createQuery("delete from Course c where c.id = :id")
                        .setParameter("id", courseId).executeUpdate();
    }

    public Course findById(long courseId){
        return entityManager.find(Course.class,courseId);
    }



    public Course show(long id) {
        return courses.stream().filter(course -> course.getId() == id).findAny().orElse(null);

    }

    public void update(Course course){
        entityManager.merge(course);

//        entityManager.getTransaction().begin();
//
//        Course course1 = show(courseId);
//
//        course1.setCourseName(course.getCourseName());
//
//        course1.setDuration(course.getDuration());
//
//        course1.setCompany(course.getCompany());
//
//        entityManager.getTransaction().commit();

    }

//
//
////    public List<Course> getAll(Long id){
////        return entityManager.createQuery("select c from Course c",Course.class)
////                .getResultList();
////    }
//
//
//    public Course findByGroupId(List<Course> coursesId){ //FOR GROUP
//        return entityManager.find(Course.class,coursesId);
//    }


}
