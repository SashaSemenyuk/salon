package org.salon.controllers;

import org.salon.controllers.main.Attributes;
import org.salon.models.MasterServices;
import org.salon.models.Services;
import org.salon.models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminCont extends Attributes {
    @GetMapping("/profile/addServiceToMaster")
    public String addServiceToMaster(Model model) {
        List<Users> masters = repoUsers.findByRole("master");
        List<Services> services = repoServices.findAll();

        model.addAttribute("masters", masters);
        model.addAttribute("services", services);

        return "addServiceToMaster";
    }

    @PostMapping("/profile/assignServiceToMaster")
    public String assignServiceToMaster(@RequestParam(name = "master") Long masterId,
                                        @RequestParam(name = "service") Long serviceId) {
        Optional<Users> masterOptional = repoUsers.findById(masterId);
        Optional<Services> serviceOptional = repoServices.findById(serviceId);

        masterOptional.ifPresent(master -> {
            serviceOptional.ifPresent(service -> {
                MasterServices masterService = new MasterServices();
                masterService.setMaster(master);
                masterService.setService(service);
                repoMasterServices.save(masterService);
            });
        });

        return "redirect:/salon/profile";
    }

    @GetMapping("/profile/updateUserRole")
    public String updateUser(Model model) {
        List<Users> users = repoUsers.findByRoleNot("admin");
        model.addAttribute("users", users);
        return "manageUsers";
    }
    @PostMapping("/profile/updateUserRole")
    public String updateUserRole(@RequestParam Long userId, @RequestParam String newRole) {
        Optional<Users> userOptional = repoUsers.findById(userId);
        userOptional.ifPresent(user -> {
            user.setRole(newRole);
            repoUsers.save(user);
        });
        return "redirect:/salon/profile";
    }
}
