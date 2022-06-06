package thymeleaf.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import thymeleaf.model.Course;
import thymeleaf.model.Teacher;
import thymeleaf.repositories.CourseRepository;
import thymeleaf.repositories.TeacherRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final EntityManagerFactory entityManagerFactory;
    private final CourseRepository courseRepository;

    public List<Teacher> findAllTeachers(){
        return teacherRepository.findAll();
    }

    public void save(Teacher teacher) {
        System.out.println(teacher.getFirstName());

        Course course = courseRepository.findById(teacher.getCourseId());

        teacher.setCourse(course);

        course.setTeacher(teacher);

        teacherRepository.save(teacher);

        System.out.println("Teacher successfully saved!");
    }

    public void deleteById(Long teacherId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Teacher teacher = entityManager.find(Teacher.class, teacherId);
        entityManager.remove(teacher);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Teacher findById(Long teacherId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Teacher teacher = entityManager.createQuery("select t from Teacher t where t.id=?1", Teacher.class).setParameter(1, teacherId).getSingleResult();
        entityManager.close();
        return  teacher;

    }

    public void update(long id, Teacher teacher){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("update Teacher t set t.firstName=?1, t.lastName=?2,t.email=?3,t.course=?4 where t.id=?5")
                .setParameter(1,teacher.getFirstName())
                .setParameter(2,teacher.getLastName())
                .setParameter(3,teacher.getEmail())
                .setParameter(4,teacher.getCourseId())
                .setParameter(5,id)
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();

    }



}
