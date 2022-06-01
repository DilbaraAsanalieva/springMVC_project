package thymeleaf.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.model.Company;
import thymeleaf.service.CompanyService;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/company")
@Controller

public class CompanyController {
    private final CompanyService companyService;


    @ModelAttribute("/companyList")
    public List<Company> findAllCompanies() {
        return companyService.findAllCompanies();
    }


    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("allCompanies",companyService.findAllCompanies());
        return "company/allCompanies";
    }



    @GetMapping("/save")
    public String saveCompanyPage(Model model) {

        model.addAttribute(
                "emptyCompany", new Company());

        return "company/saveCompanyPage";
    }

    @PostMapping("/save")
    public String saveCompany(Company company) {

        System.out.println(company);

        companyService.save(company);

        return "redirect:/api/company";
    }


    @GetMapping("/{id}")
    public String findById(@PathVariable("id")Long id,Model model){
        model.addAttribute("company",companyService.findById(id));
        return "company/findByIdPage";
    }


    @GetMapping("/delete/{id}")
    public String deleteCompany(@PathVariable Long id){
        companyService.deleteById(id);
        return "redirect:/api/company";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable Long id){
        model.addAttribute("company",companyService.findById(id));
        return "company/editCompany";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute("company") Company company, @PathVariable Long id){
        companyService.update(id,company);
        return "redirect:/api/company";
    }



}
