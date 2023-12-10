package org.salon.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "appointments")
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long appointmentId;

    @ManyToOne
    @JoinColumn(name = "master_id", nullable = false)
    private Users master;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Users client;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Services service;

    @Column(nullable = false)
    private Date appointmentDate;
}

