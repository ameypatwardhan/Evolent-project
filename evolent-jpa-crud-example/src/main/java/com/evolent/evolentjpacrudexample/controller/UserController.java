package com.evolent.evolentjpacrudexample.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.evolent.evolentjpacrudexample.exception.ResourceNotFoundException;
import com.evolent.evolentjpacrudexample.model.User;
import com.evolent.evolentjpacrudexample.service.UserService;

@Controller
@RequestMapping("/")
public class UserController 
{
    @Autowired
    UserService service;
 
    @RequestMapping
    public String getAllEmployees(Model model) 
    {
        List<User> list = service.getAllUsers();
 
        model.addAttribute("users", list);
        return "list-users";
    }
 
    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editUserById(Model model, @PathVariable("id") Optional<Long> id) 
                            throws ResourceNotFoundException 
    {
    	if (id.isPresent()) {
            User user = service.getUserById(id.get());
            model.addAttribute("user", user);
        } else {
            model.addAttribute("user", new User());
            
        }
       
        return "add-edit-user";
    }
     
    @RequestMapping(path = "/delete/{id}")
    public String deleteEmployeeById(Model model, @PathVariable("id") Long id) 
                            throws ResourceNotFoundException 
    {
        service.deleteUserById(id);
        return "redirect:/";
    }
 
    @RequestMapping(path = "/createUser", method = RequestMethod.POST)
    public String createOrUpdateUser(User user) 
    {
    	service.createOrUpdateUser(user);
        return "redirect:/";
    }
}