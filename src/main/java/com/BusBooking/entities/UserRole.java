package com.BusBooking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_roles")
public class UserRole implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName ="id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "role_id",referencedColumnName ="id")
    private Role role;
}
// dev 3rd no