package org.salon.controllers.main;


import org.salon.models.Users;
import org.salon.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

public class Main {
    @Autowired
    protected RepoUsers repoUsers;
    @Autowired
    protected RepoMasterServices repoMasterServices;
    @Autowired
    protected RepoAppointments repoAppointments;
    @Autowired
    protected RepoReviews repoReviews;
    @Autowired
    protected RepoServices repoServices;

    @Value("${upload.img}")
    protected String uploadImg;

    protected Users getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
            Object principal = auth.getPrincipal();

            if (principal instanceof UserDetails userDetail) {
                Users user = repoUsers.findByEmail(userDetail.getUsername());

                if (user != null) {
                    System.out.println("User found in the repository: " + user.getUsername() + ", " + user.getEmail()); // или другие поля пользователя
                } else {
                    System.out.println("User not found in the repository");
                }

                return repoUsers.findByEmail(userDetail.getUsername().trim());
            }
        }

        System.out.println("Authentication or principal is null");
        return null;
    }


    protected String getUserRole() {
        Users user = getUser();
        if (user != null) return String.valueOf(user.getRole());
        return "NOT";
    }

    protected Long getUserID() {
        Users user = getUser();
        if (user != null) return user.getId();
        return 0L;
    }

    protected String getFirstnameLastname() {
        Users user = getUser();
        if (user != null) return user.getFirstName() + " " + user.getLastName();
        return "Добро пожаловать";
    }
}
