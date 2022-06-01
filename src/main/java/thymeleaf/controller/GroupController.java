package thymeleaf.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.model.Group;
import thymeleaf.service.GroupService;

import java.util.List;

@RequestMapping("/api/group")
@AllArgsConstructor
@Controller
public class GroupController {
    private final GroupService groupService;

    @ModelAttribute("/groupList")
    public List<Group> findAllGroups() {
        return groupService.findAllGroups();
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("allGroups",groupService.findAllGroups());
        return "group/allGroups";
    }

    @GetMapping("/save")
    public String saveGroupPage(Model model) {

        model.addAttribute(
                "emptyGroup", new Group());

        return "group/saveGroupPage";
    }

    @PostMapping("/save")
    public String saveGroup(Group group) {

        System.out.println(group);

        groupService.save(group);

        return "redirect:/api/group";
    }


    @GetMapping("/{id}")
    public String findById(@PathVariable("id")Long id, Model model){
        model.addAttribute("group",groupService.findById(id));
        return "group/findByIdPage";
    }

    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable Long id){
        groupService.deleteById(id);
        return "redirect:/api/group";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable Long id){
        model.addAttribute("group",groupService.findById(id));
        return "group/editGroup";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute("group")Group group,@PathVariable Long id){
        groupService.update(id,group);
        return "redirect:/api/group";
    }


}
