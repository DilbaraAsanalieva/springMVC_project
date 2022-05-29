package thymeleaf.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import thymeleaf.model.Course;
import thymeleaf.repositories.CourseRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;


    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public void save(Course course) {
        System.out.println(course.getCourseName());
        courseRepository.save(course);
        System.out.println("Course successfully saved!");
    }
}
