package thymeleaf.controller;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import thymeleaf.model.Course;
import thymeleaf.service.CourseService;

import java.util.List;


@AllArgsConstructor
@RequestMapping("/api/courses")
@Controller
public class CourseController {

    private final CourseService courseService;

    @ModelAttribute("/courseList")
    public List<Course> findAllCourses() {
        return courseService.findAllCourses();
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("allCourse",courseService.findAllCourses());
        return "allCourses";
    }


    @GetMapping("/save")
    public String saveCoursePage(Model model) {

        model.addAttribute(
                "emptyCourse", new Course());

        return "saveCoursePage";
    }

    @PostMapping("/save")
    public String saveCourse(Course course) {

        System.out.println(course);

        courseService.save(course);

        return "redirect:/api/courses";
    }


}
