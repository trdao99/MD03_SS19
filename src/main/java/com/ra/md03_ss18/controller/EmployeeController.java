package com.ra.md03_ss18.controller;

import com.ra.md03_ss18.DAO.impl.EmployeeDAO;
import com.ra.md03_ss18.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDAO employeeDAO;

    @RequestMapping(value = {"/", "/list"})
    public String list(Model model) {
        List<Employee> employees = employeeDAO.getEmployees();
        model.addAttribute("list", employees);
        return "list";
    }

    @RequestMapping("/editinit/{id}")
    public String editinit(@PathVariable("id") Integer id,Model model) {
        Employee employee = employeeDAO.getEmployeeByID(id);
        model.addAttribute("e", employee);
        return "edit";
    }

    @RequestMapping("/update")
    public String update(@Valid @ModelAttribute("e") Employee e, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        boolean bl = employeeDAO.updateEmployee(e);
        if (bl) {
            return "redirect:/list"; //Gọi tiếp tới method trên controller
        } else {
            model.addAttribute("err", "Insert failed!");
            model.addAttribute("e", e);
            return "edit";
        }
    }

    @RequestMapping("/initiatedEmployee")
    public String initiatedEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("e", employee);
        return "add";
    }

    @RequestMapping("/addEmployee")
    public String addEmployee(@Valid @ModelAttribute("e") Employee e, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add";
        }
        boolean bl = employeeDAO.insertEmployee(e);
        if (bl) {
            return "redirect:/list"; //Gọi tiếp tới method trên controller
        } else {
            model.addAttribute("err", "Insert failed!");
            model.addAttribute("e", e);
            return "add";
        }
    }

    @RequestMapping("/del/{id}")
    public String delEmployee(@PathVariable("id") int id) {
        employeeDAO.deleteEmployee(employeeDAO.getEmployeeByID(id));
        return "redirect:/list";
    }
}
