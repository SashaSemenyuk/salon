package org.salon.controllers.main;

import org.salon.models.Users;
import org.springframework.ui.Model;

public class Attributes extends Main {
    protected void AddAttributes(Model model) {
        model.addAttribute("firstNameLastname", getFirstnameLastname());
        model.addAttribute("role", getUserRole());
    }

    protected void AddAttributesIndex(Model model) {
        String userRole = getUserRole();
    }

    public void AddAttributesServices(Model model) {
        AddAttributes(model);
        model.addAttribute("services", repoServices.findAll());
    }
    protected void AddAttributesAppointments(Model model) {
        AddAttributes(model);
        model.addAttribute("appoints", repoAppointments.findAll());
    }
    protected void AddAttributesMasterServices(Model model) {
        AddAttributes(model);
        model.addAttribute("masterServices", repoMasterServices.findAll());
    }

    protected void AddAttributesServiceAdd(Model model) {
        AddAttributes(model);
    }

    protected void AddAttributesProfile(Model model) {
        AddAttributes(model);
        Users user = getUser();
        model.addAttribute("user", user);
    }
    protected void AddAttributesReviews(Model model){
        AddAttributes(model);
        model.addAttribute("reviews", repoReviews.findAll());
    }
    protected void AddAttributesValidAppointments(Model model){
        AddAttributes(model);
        model.addAttribute("appoints", repoAppointments.findByStatus("Действительна"));
    }
}
