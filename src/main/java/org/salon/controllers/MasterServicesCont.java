package org.salon.controllers;

import org.salon.controllers.main.Attributes;
import org.salon.models.MasterServices;
import org.salon.models.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MasterServicesCont extends Attributes {
    @GetMapping("/getMastersByService/{serviceId}")
    @ResponseBody
    public List<Users> getMastersByService(@PathVariable Long serviceId) {
        List<MasterServices> masterServicesList = repoMasterServices.findByService_ServiceId(serviceId);

        // Извлекаем мастеров из списка MasterServices
        List<Users> masters = masterServicesList.stream()
                .map(MasterServices::getMaster)
                .collect(Collectors.toList());

        return masters;
    }
}
