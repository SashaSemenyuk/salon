package org.salon.controllers;

import java.io.IOException;
import org.salon.controllers.main.Attributes;
import org.salon.models.Services;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
public class ServicesCont extends Attributes {
    @GetMapping("/salon/services")
    public String services(Model model) {
        AddAttributesServices(model);
        return "services";
    }
    @GetMapping("/services/add")
    public String addServices(Model model){
        return "addService";
    }
    @GetMapping("/services/delete/")
    public String deleteServices(Model model){
        AddAttributesServices(model);
        return "deleteService";
    }
    @GetMapping("/services/update/")
    public String showUpdateServiceForm(Model model) {
        AddAttributesServices(model);
        return "updateService";
    }
    @GetMapping("/salon/services/filter")
    public String getFilteredServices(@RequestParam(name = "min_price", required = false) Double minPrice,
                                      @RequestParam(name = "max_price", required = false) Double maxPrice,
                                      Model model) {
        if (minPrice != null && maxPrice != null && minPrice > maxPrice) {
            return "redirect:/error";
        }

        List<Services> filteredServices = repoServices.findByPriceBetween(minPrice, maxPrice);
        model.addAttribute("services", filteredServices);
        return "services";
    }
    @GetMapping("/salon/services/sort")
    public String getSortedServices(@RequestParam(name = "sort", required = false, defaultValue = "name") String sort,
                                    Model model) {
        List<Services> sortedServices;

        switch (sort) {
            case "name":
                sortedServices = repoServices.findAll(Sort.by(Sort.Direction.ASC, "serviceName"));
                break;
            case "asc_price":
                sortedServices = repoServices.findAll(Sort.by(Sort.Direction.ASC, "price"));
                break;
            case "desc_price":
                sortedServices = repoServices.findAll(Sort.by(Sort.Direction.DESC, "price"));
                break;
            default:
                sortedServices = repoServices.findAll(Sort.by(Sort.Direction.ASC, "serviceName"));
                break;
        }

        model.addAttribute("services", sortedServices);
        return "services";
    }
    @GetMapping("/salon/services/search")
    public String searchServices(@RequestParam(name = "title", required = false) String title,
                                 @RequestParam(name = "category", required = false) String category,
                                 @RequestParam(name = "searchType", required = false) String searchType,
                                 Model model) {
        List<Services> searchResults;

        if ("title".equals(searchType)) {
            searchResults = repoServices.findByServiceNameContaining(title);
        } else if ("category".equals(searchType)) {
            searchResults = repoServices.findByCategoryContaining(category);
        } else {
            // Handle default case or return an error message
            searchResults = new ArrayList<>();
        }

        model.addAttribute("services", searchResults);
        return "services";
    }
    @PostMapping("/services/add")
    public String serviceAdd(Model model, @RequestParam String serviceName, @RequestParam String description, @RequestParam double price, @RequestParam int duration,  @RequestParam String category, @RequestParam String genderSpecific, @RequestParam MultipartFile serviceImageFile) {
        String res = "";
        if (serviceImageFile != null && !Objects.requireNonNull(serviceImageFile.getOriginalFilename()).isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = "services/" + uuidFile + "_" + serviceImageFile.getOriginalFilename();
                    serviceImageFile.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (IOException e) {
                model.addAttribute("message", "Слишком большой размер аватарки");
                AddAttributesServiceAdd(model);
                return "addService";
            }
        }

        Services service = new Services(serviceName, description, price, duration, category, genderSpecific, res);
        repoServices.save(service);
        return "redirect:/salon/services";
    }
    @PostMapping("/services/delete/{id}")
    public String serviceDelete(@PathVariable Long id) {
        Services service = repoServices.getReferenceById(id);
        repoServices.delete(service);
        return "redirect:/salon/services";
    }

    @PostMapping("/services/update/{serviceId}")
    public String updateService(@PathVariable long serviceId, Model model, @ModelAttribute Services updatedService, @RequestParam MultipartFile serviceImageFile) {
        Services existingService = repoServices.findById(serviceId).orElseThrow(() -> new RuntimeException("Service not found"));
        existingService.setPrice(updatedService.getPrice());
        double discountedPrice;
        if (updatedService.getDiscount() != 0) {
            discountedPrice = calculateDiscountedPrice(updatedService.getPrice(), updatedService.getDiscount());
        } else {
            discountedPrice = 0.00;
        }
        existingService.setDiscount(discountedPrice);
        existingService.setAddOns(updatedService.getAddOns());
        String res = "";
        if (serviceImageFile != null && !Objects.requireNonNull(serviceImageFile.getOriginalFilename()).isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = "services/" + uuidFile + "_" + serviceImageFile.getOriginalFilename();
                    serviceImageFile.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (IOException e) {
                model.addAttribute("message", "Слишком большой размер фотографии");
                AddAttributesServiceAdd(model);
                return "updateService";
            }
        }

        // Save the updated service
        repoServices.save(existingService);

        return "redirect:/salon/services";
    }

    public double calculateDiscountedPrice(double originalPrice, double discount) {
        // Calculate the discounted price
        return originalPrice * (1 - discount / 100);
    }
}
