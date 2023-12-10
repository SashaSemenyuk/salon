package org.salon.controllers;

import org.salon.controllers.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileCont extends Attributes {
    @GetMapping("/salon/profile")
    public String profile(Model model) {
        AddAttributesProfile(model);
        return "profile";
    }
}
