package org.salon.controllers;

import org.salon.controllers.main.Attributes;
import org.salon.models.MasterServices;
import org.salon.models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MasterServicesCont extends Attributes {
    @GetMapping("/getMastersByService/{serviceId}")
    @ResponseBody
    public List<Map<String, Object>> getMastersByService(@PathVariable Long serviceId) {
        List<MasterServices> masterServicesList = repoMasterServices.findByService_ServiceId(serviceId);

        // Извлекаем мастеров из списка MasterServices и создаем список Map
        List<Map<String, Object>> masters = masterServicesList.stream()
                .map(masterService -> {
                    Map<String, Object> masterMap = new HashMap<>();
                    masterMap.put("id", masterService.getMaster().getId());
                    masterMap.put("username", masterService.getMaster().getUsername());
                    masterMap.put("firstName", masterService.getMaster().getFirstName());
                    masterMap.put("lastName", masterService.getMaster().getLastName());
                    // Добавьте другие поля мастера по необходимости
                    return masterMap;
                })
                .collect(Collectors.toList());

        return masters;
    }
}
