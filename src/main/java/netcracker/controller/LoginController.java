package netcracker.controller;

import netcracker.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private PersonRepo personRepo;

    @GetMapping("/")
    public String home(@RequestParam String name, Model model){
        model.addAttribute("name", name);
        return "home";
    }

    @GetMapping("/menu")
    public String menu(){
        return "menu";
    }
}
