package org.salon.controllers;

import org.salon.controllers.main.Attributes;
import org.salon.models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
public class RegCont extends Attributes {

    @GetMapping("/salon/registration")
    public String reg() {
        return "reg";
    }

    @PostMapping("/salon/registration")
    public String newUser(Model model, @RequestParam String username, @RequestParam String email, @RequestParam String password) {
        try {
            // Проверка наличия пользователя с ролью "admin"
            List<Users> adminUser = repoUsers.findByRole("admin");
            if (adminUser.isEmpty()) {
                repoUsers.save(new Users(username, email, password, "admin"));
                return "redirect:/login";
            }

            // Проверка наличия пользователя с указанным именем
            Users userFromDB = repoUsers.findByUsername(username);
            if (userFromDB != null) {
                if (Objects.equals(userFromDB.getEmail(), email)) {
                    model.addAttribute("message", "Пользователь с таким же адресом электронной почты уже существует!");
                } else {
                    model.addAttribute("message", "Пользователь с таким именем уже существует!");
                }
                return "reg";
            }

            // Сохранение пользователя с ролью "client"
            Users newUser = new Users(username, email, password, "client");
            repoUsers.save(newUser);
            return "redirect:/login";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Ошибка при сохранении пользователя. Пожалуйста, повторите попытку.");
            return "reg";
        }
    }

}
