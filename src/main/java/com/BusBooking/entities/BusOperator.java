package com.BusBooking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bus_operators")
public class BusOperator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operator_name")
    private String operatorName;

    @Column(name = "contect_email")
    private String contectEmail;

    @Column(name = "contact_phone")
    private  String contactPhone;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(mappedBy = "busOperator", cascade = CascadeType.ALL)
    private Set<Bus> buses;
}
// 5 no