package com.test.controller;


import com.test.alljava.DaoService;
import com.test.alljava.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DaoController {
    private DaoService daoService;

    @Autowired(required = true)
    @Qualifier(value = "daoService")
    public void setDaoService(DaoService daoService) {
        this.daoService = daoService;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String listUsers(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.daoService.listUsers());
        return "index";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        if (user.getId() == 0) {
            this.daoService.addUser(user);
        } else {
            this.daoService.updateUser(user);
        }
        return "redirect:/index";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id) {
        this.daoService.removeUser(id);
        return "redirect:/index";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", this.daoService.getUserById(id));
        model.addAttribute("listUsers", this.daoService.listUsers());
        return "index";
    }

    @RequestMapping("userdata/{id}")
    public String userdata(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", this.daoService.getUserById(id));
        return "userdata";
    }
}