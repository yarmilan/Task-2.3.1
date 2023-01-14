package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "list";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model model) {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/update")
    public String showFormForUpdate(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserId(id));
        return "updateForm";
    }

    @RequestMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String getUserId(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserId(id));
        return "userById";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }


}
