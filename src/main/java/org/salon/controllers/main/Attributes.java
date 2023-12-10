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

    protected void AddAttributesServices(Model model) {
        AddAttributes(model);
        model.addAttribute("services", repoServices.findAll());
    }

    protected void AddAttributesMasterServices(Model model) {
        AddAttributes(model);
        model.addAttribute("masterServices", repoMasterServices.findAll());
    }

    protected void AddAttributesProductAdd(Model model) {
        AddAttributes(model);
    }

//    protected void AddAttributesProductEdit(Model model, Long id) {
//        AddAttributes(model);
//        model.addAttribute("product", repoProducts.getReferenceById(id));
//    }

    protected void AddAttributesProfile(Model model) {
        AddAttributes(model);
        Users user = getUser();
        model.addAttribute("user", user);
//        model.addAttribute("allDevice", user.getDevices().size());
//        model.addAttribute("right", user.getDevices());
    }

//    protected void AddAttributesProfiles(Model model) {
//        AddAttributes(model);
//        model.addAttribute("usersList", repoUsers.findAllByOrderByRole());
//        model.addAttribute("roles", Arrays.asList(Role.values()));
//    }

//    protected void AddAttributesService(Model model) {
//        AddAttributes(model);
//        List<Devices> devices = new ArrayList<>();
//
//        if (getUserRole().equals(Role.TECHNICIAN.name())) {
//            devices.addAll(repoMasterServices.findByStatus(Status.WAITING));
//            devices.addAll(repoMasterServices.findByStatus(Status.ASSEMBLING));
//        } else if (getUserRole().equals(Role.TESTER.name())) {
//            devices.addAll(repoMasterServices.findByStatus(Status.TESTED));
//        }
//
//        model.addAttribute("devices", devices);
//    }

//    protected void AddAttributesStats(Model model, Select select, Status status, DeviceType type, Role role) {
//        AddAttributes(model);
//        if (select == Select.OFFICE_EQUIPMENT) {
//            List<Devices> devices;
//            if (status == Status.ALL && type == DeviceType.ALL) devices = repoMasterServices.findAll();
//            else if (status == Status.ALL) devices = repoMasterServices.findByDeviceType(type);
//            else if (type == DeviceType.ALL) devices = repoMasterServices.findByStatus(status);
//            else devices = repoMasterServices.findByStatusAndDeviceType(status, type);
//            model.addAttribute("devices", devices);
//        } else {
//            model.addAttribute("users", repoUsers.findByRole(role));
//        }
//        model.addAttribute("roles", Role.values());
//        model.addAttribute("selects", Select.values());
//        model.addAttribute("statuses", Status.values());
//        model.addAttribute("types", DeviceType.values());
//        model.addAttribute("roleSelected", role);
//        model.addAttribute("deviceStatusSelected", status);
//        model.addAttribute("deviceTypeSelected", type);
//    }

//    protected void AddAttributesStatsProducts(Model model) {
//        AddAttributes(model);
//        model.addAttribute("incomes", repoIncomes.findAll());
//    }

    protected void AddAttributesActionsList(Model model) {
        AddAttributes(model);
        model.addAttribute("users", repoUsers.findAllByOrderByRole());
    }

//    protected void AddAttributesActions(Model model, Long userId, String date) {
//        AddAttributes(model);
//        model.addAttribute("user", repoUsers.getReferenceById(userId));
//        if (date == null || date.equals("")) {
//            date = DateNow();
//        }
//
//        List<Actions> list = getUser().getActions();
//        String finalDate = date;
//        List<Actions> actions = list.stream().filter(action -> action.getDate().startsWith(finalDate)).toList();
//
//        model.addAttribute("actions", actions);
//        model.addAttribute("date", date);
//    }

//    protected void AddAttributesDeviceAdd(Model model) {
//        AddAttributes(model);
//        model.addAttribute("types", Arrays.asList(DeviceType.values()));
//        model.addAttribute("DeviceTypeAll", DeviceType.ALL);
//    }

//    protected void AddAttributesDeviceEdit(Model model, Long idDevice) {
//        AddAttributes(model);
//        model.addAttribute("types", Arrays.asList(DeviceType.values()));
//        model.addAttribute("device", repoMasterServices.getReferenceById(idDevice));
//        model.addAttribute("DeviceTypeAll", DeviceType.ALL);
//    }

//    protected void AddAttributesMyDevices(Model model) {
//        AddAttributes(model);
//        model.addAttribute("devices", getUser().getDevices());
//    }

//    protected void AddAttributesSearch(Model model, Status status, DeviceType type) {
//        AddAttributes(model);
//        List<Devices> devices;
//        if (status == Status.ALL && type == DeviceType.ALL) devices = repoMasterServices.findAll();
//        else if (status == Status.ALL) devices = repoMasterServices.findByDeviceType(type);
//        else if (type == DeviceType.ALL) devices = repoMasterServices.findByStatus(status);
//        else devices = repoMasterServices.findByStatusAndDeviceType(status, type);
//        model.addAttribute("devices", devices);
//        model.addAttribute("statuses", Status.values());
//        model.addAttribute("types", DeviceType.values());
//        model.addAttribute("deviceStatusSelected", status);
//        model.addAttribute("deviceTypeSelected", type);
//    }

//    protected void AddAttributesSearch(Model model, String search) {
//        AddAttributes(model);
//        List<Devices> temp = new ArrayList<>();
//        for (Devices i : repoMasterServices.findAll()) if (i.getName().contains(search)) temp.add(i);
//        model.addAttribute("devices", temp);
//        model.addAttribute("statuses", Status.values());
//        model.addAttribute("deviceStatusSelected", Status.ALL);
//        model.addAttribute("types", DeviceType.values());
//        model.addAttribute("deviceTypeSelected", DeviceType.ALL);
//    }
}
