package thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.model.Group;
import thymeleaf.model.Student;
import thymeleaf.service.GroupService;
import thymeleaf.service.StudentService;
import java.util.List;


@Controller

@RequestMapping("/api/student")
public class StudentController {
    public final StudentService studentService;
    private final GroupService groupService;

    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @ModelAttribute("/studentList")
    public List<Student> findAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("allStudents",studentService.findAllStudents());
        return "student/allStudents";
    }


    @GetMapping("/save")
    public String saveStudentPage(Model model) {

        model.addAttribute(
                "emptyStudent", new Student());

        return "student/saveStudentPage";
    }

    @PostMapping("/save")
    public String saveStudent(Student student) {

        System.out.println(student);

        studentService.save(student.getGroupId(),student);

        return "redirect:/api/student";
    }




    @GetMapping("/{id}")
    public String findById(@PathVariable("id")Long id, Model model){
        model.addAttribute("student",studentService.findById(id));
        return "student/findByIdPage";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteById(id);
        return "redirect:/api/student";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable Long id){
        model.addAttribute("student",studentService.findById(id));
        return "student/editStudent";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute("student")Student student,@PathVariable Long id){
        studentService.update(id,student);
        return "redirect:/api/student";
    }

    @ModelAttribute("groupList")
    public List<Group> findAllGroups(){
        return groupService.findAllGroups();
    }


}
