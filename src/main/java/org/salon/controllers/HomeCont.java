package org.salon.controllers;

import org.salon.controllers.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeCont extends Attributes {
    @GetMapping("/salon")
    public String Index2(Model model) {
        AddAttributesIndex(model);
        AddAttributesServices(model);
        AddAttributesMasterServices(model);
        return "home";
    }
}
