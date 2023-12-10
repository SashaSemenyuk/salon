package org.salon.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Data
@Setter
@NoArgsConstructor
@Table(name = "users")
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Getter
    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // Здесь храните роль (admin, master, client)

    // Общие поля для всех пользователей
    @Column
    private String firstName;

    @Getter
    @Column
    private String lastName;

    @Column(nullable = false)
    private Date regDate = new Date();

    @Column
    private Date lastLogin;

    @Column
    private String mobilePhone;

    // Поля, специфичные для клиентов
    @Column
    private Date birthdate;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Reviews> review;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Appointments> appointment;

    // Поля, специфичные для мастеров
    @Column
    private String specialization;

    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL)
    private List<MasterServices> masterService;

    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL)
    private List<Reviews> masterReview;

    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL)
    private List<Appointments> masterAppointment;

    public Users(String username,  String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public String getFirstname() {
        return firstName;
    }

    // Геттеры и сеттеры
}
