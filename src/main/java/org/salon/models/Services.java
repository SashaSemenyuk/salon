package org.salon.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import org.springframework.web.multipart.MultipartFile;


import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "services")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long serviceId;

    @Column(nullable = false)
    private String serviceName;

    @Column(columnDefinition = "TEXT")
    private String description;


    @Column(nullable = false)
    private double price;


    @Column(nullable = false)
    private int duration;


    @Column
    private String category;

    @Column(length = 10)
    private String genderSpecific;

    @Column
    private String availability;

    @Column
    private double discount;

    @Column(columnDefinition = "TEXT")
    private String addOns;

    @Column
    private String serviceImage;

    @Transient
    private MultipartFile serviceImageFile;

    @Column
    private double averageRating;

    @JsonIgnore
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<MasterServices> masterServices;

    @JsonIgnore
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<Reviews> review;

    @JsonIgnore
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<Appointments> appointment;

    @Column
    private byte[] serviceImageBytes;

    public Services(String serviceName, String description, double price, int duration, String category, String genderSpecific, String serviceImage) {
        this.serviceName = serviceName;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.category = category;
        this.genderSpecific = genderSpecific;
        this.serviceImage = serviceImage;
    }
}

