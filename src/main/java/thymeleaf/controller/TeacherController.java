package thymeleaf.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.model.Group;
import thymeleaf.model.Teacher;
import thymeleaf.service.TeacherService;

import java.util.List;

@RequestMapping("/api/teacher")
@Controller
@AllArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @ModelAttribute("/teacherList")

    public List<Teacher> findAllTeachers(){
        return teacherService.findAllTeachers();
    }

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("allTeachers",teacherService.findAllTeachers());
        return "teacher/allTeachers";
    }

    @GetMapping("/save")
    public String saveTeacherPage(Model model) {

        model.addAttribute(
                "emptyTeacher", new Teacher());

        return "teacher/saveTeacherPage";
    }

    @PostMapping("/save")
    public String saveTeacher(Teacher teacher) {

        System.out.println(teacher);

        teacherService.save(teacher);

        return "redirect:/api/teacher";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id")Long id, Model model){
        model.addAttribute("teacher",teacherService.findById(id));
        return "teacher/findByIdPage";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable Long id){
        teacherService.deleteById(id);
        return "redirect:/api/teacher";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable Long id){
        model.addAttribute("teacher",teacherService.findById(id));
        return "teacher/editTeacher";
    }
    @PatchMapping("{id}")
    public String update(@ModelAttribute("teacher") Teacher teacher, @PathVariable Long id){
        teacherService.update(id,teacher);
        return "redirect:/api/group";
    }


}
