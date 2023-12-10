package org.salon.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "master_services")
public class MasterServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long masterServiceId;

    @ManyToOne
    @JoinColumn(name = "master_id", nullable = false)
    @JsonBackReference
    private Users master;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)  // Убедитесь, что имя столбца правильное
    @JsonManagedReference
    private Services service;
}

