package org.salon.controllers;

import org.salon.controllers.main.Attributes;
import org.salon.models.Services;
import org.salon.models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Controller
public class ProfileCont extends Attributes {
    @GetMapping("/salon/profile")
    public String profile(Model model) {
        AddAttributesProfile(model);
        AddAttributesAppointments(model);
        return "profile";
    }
    @PostMapping("/personalMasterInfo/{id}")
    public String updateMasterInfo(@PathVariable long id, @ModelAttribute Users user){
        Users existingMaster = repoUsers.findById(id).orElseThrow(() -> new RuntimeException("Master not found"));
        existingMaster.setFirstName(user.getFirstName());
        existingMaster.setLastName(user.getLastName());
        existingMaster.setMobilePhone(user.getMobilePhone());
        existingMaster.setSpecialization(user.getSpecialization());
        repoUsers.save(existingMaster);

        return "redirect:/salon/profile";
    }
    @PostMapping("/personalClientInfo/{id}")
    public String updateClientInfo(@PathVariable long id, @ModelAttribute Users user){
        Users existingClient = repoUsers.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
        existingClient.setFirstName(user.getFirstName());
        existingClient.setLastName(user.getLastName());
        existingClient.setMobilePhone(user.getMobilePhone());
        existingClient.setBirthdate(user.getBirthdate());

        repoUsers.save(existingClient);

        return "redirect:/salon/profile";
    }
    @PostMapping("/salon/profile/{id}")
    public String updateUserInfo(@PathVariable long id, @ModelAttribute Users user){
        Users existingUser = repoUsers.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());

        repoUsers.save(existingUser);

        return "redirect:/salon/profile";
    }
}
