package thymeleaf.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import thymeleaf.model.Company;
import thymeleaf.model.Course;
import thymeleaf.repositories.CompanyRepository;
import thymeleaf.repositories.CourseRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;
    private final EntityManagerFactory entityManagerFactory;



    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }


    public void save(Long companyId,Course course) {
        System.out.println("WORKING???????");
        Company company = companyRepository.findById(companyId);
        course.setCompany(company);
        company.setCourse(course);
       courseRepository.save(course);

    }


    public void deleteById(Long courseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Course course = entityManager.find(Course.class, courseId);
        entityManager.remove(course);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    public Course findById(Long courseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
        Course course = entityManager.createQuery("select c from Course c where c.id=?1", Course.class).setParameter(1, courseId).getSingleResult();
//        entityManager.getTransaction().commit();
        entityManager.close();
        return  course;

    }



    public void update(long id,Course course){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("update Course c set c.courseName=?1, c.duration=?2,c.company=?3 where c.id=?4")
                .setParameter(1,course.getCourseName())
                .setParameter(2,course.getDuration())
                .setParameter(3,course.getCompany())
                .setParameter(4,id)
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();

    }


    public List<Course> getAll(Long id) {
        return courseRepository.getAll(id);
    }


}
