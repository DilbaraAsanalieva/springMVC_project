package thymeleaf.service;

import org.springframework.stereotype.Service;
import thymeleaf.model.Company;
import thymeleaf.model.Course;
import thymeleaf.repositories.CompanyRepository;
import thymeleaf.repositories.CourseRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;
    private final EntityManagerFactory entityManagerFactory;

    public CourseService(CourseRepository courseRepository, CompanyRepository companyRepository, EntityManagerFactory entityManagerFactory) {
        this.courseRepository = courseRepository;
        this.companyRepository = companyRepository;
        this.entityManagerFactory = entityManagerFactory;
    }

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
        courseRepository.deleteById(courseId);
    }


    public Course findById(Long courseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Course course = entityManager.createQuery("select c from Course c where c.id=?1", Course.class).setParameter(1, courseId).getSingleResult();
        entityManager.close();
        return  course;
    }



//    public void update(long id,Course course){
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.createQuery("update Course c set c.courseName=?1, c.duration=?2,c.company=?3 where c.id=?4")
//                .setParameter(1,course.getCourseName())
//                .setParameter(2,course.getDuration())
//                .setParameter(3,course.getCompany()) //?
//                .setParameter(4,id)
//                .executeUpdate();
//        entityManager.getTransaction().commit();
//    }

    public void update(Course course, long courseId) {
        Course course1 = findById(courseId);
        course1.setCourseName(course.getCourseName());
        course1.setDuration(course.getDuration());
//        course1.setCompany(course.getCompany());
        courseRepository.update(course1);
    }









}
