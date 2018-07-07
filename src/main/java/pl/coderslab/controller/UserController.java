package pl.coderslab.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.coderslab.bean.LoginData;
import pl.coderslab.bean.SessionManager;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepo;

    @GetMapping("/register")
    public String register(Model m) {
        m.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "register";
        }
        User existingUser = userRepo.findFirstByEmail(user.getEmail());
        if (existingUser != null) {
           FieldError error = new FieldError("user", "email", "Email musi być unikalny");
           bindingResult.addError(error);
            return "register";
        }



            userRepo.save(user);
            return "redirect:/";

    }

    @GetMapping("/login")
    public String login(Model m) {
        m.addAttribute("loginData", new LoginData());
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute LoginData loginData,
                            Model m,
                            RedirectAttributes ra) {
        User u =this.userRepo.findFirstByEmail(loginData.getEmail());

        if(u != null && u.isPasswordCorrect(loginData.getPassword())) {
            HttpSession s = SessionManager.session();
            s.setAttribute("user", u);
            ra.addFlashAttribute("msg", "Jestes zalogowany!");
            return "redirect:/";
        }

        m.addAttribute("msg", "Wprowadz poprawne dane");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model m) {
        m.addAttribute("loginData", new LoginData());
        HttpSession s = SessionManager.session();
        s.invalidate();

        return "redirect:/";
    }
}