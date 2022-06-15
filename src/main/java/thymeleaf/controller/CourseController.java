package thymeleaf.controller;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.model.Company;
import thymeleaf.model.Course;
import thymeleaf.service.CompanyService;
import thymeleaf.service.CourseService;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/courses")
@Controller
public class CourseController {

    private final CourseService courseService;
    private final CompanyService companyService;

    @ModelAttribute("courseList")
    public List<Course> findAllCourses() {
        return courseService.findAllCourses();
    }

    @GetMapping
    public String findAll() {
//        model.addAttribute("allCourse",courseService.findAllCourses());
        return "course/allCourses";
    }


    @GetMapping("/save")
    public String saveCoursePage( Model model) {
        model.addAttribute(
                "emptyCourse", new Course());

        return "course/saveCoursePage";
    }

    @PostMapping("/save")
    public String saveCourse(Course course) {

        courseService.save(course.getCompanyId(),course);

        return "redirect:/api/courses";
    }


    @GetMapping("/{id}")
    public String findById(@PathVariable("id")Long id,Model model){
        model.addAttribute("course",courseService.findById(id));
        return "course/findByIdPage";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        courseService.deleteById(id);
        return "redirect:/api/courses";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable Long id){
        model.addAttribute("course",courseService.findById(id));
        return "course/editCourse";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("course")Course course,@PathVariable Long id){
        courseService.update(course,id);
        return "redirect:/api/courses";
    }

    @ModelAttribute("companyList")
    public List<Company> findAllCompanies(){
        return companyService.findAllCompanies();
    }






}
