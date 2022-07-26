package ma.emsi.gestion_depense.controllers;

import lombok.AllArgsConstructor;
import ma.emsi.gestion_depense.security.entities.AppRole;
import ma.emsi.gestion_depense.security.entities.AppUser;
import ma.emsi.gestion_depense.security.repositories.AppUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class userController {
    AppUserRepository appUserRepository;
    @GetMapping("/user/monprofile")
    public String profile(Model model, String username){
        AppUser appUser = appUserRepository.findByUsername(username);
        List<AppRole> roles = new ArrayList<>();
        for (AppRole role: appUser.getAppRoles()) {
            roles.add(role);
        }
        model.addAttribute("listroles",roles);
        model.addAttribute("user",appUser);
        return "monprofile";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
}
