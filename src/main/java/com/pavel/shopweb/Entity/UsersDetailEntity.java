package com.pavel.shopweb.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_detail")
@DynamicInsert
@DynamicUpdate
public class UsersDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 24, message = "Firstname min 3 max 24")
    private String firstname;

    @Size(min = 3, max = 28, message = "Surname min 3 max 28")
    private String surname;

    @PositiveOrZero(message = "The age field cannot be negative")
    private int age;

    private LocalDate birthday;

    @Size(min = 12, max = 574, message = "Description min 12, max 574")
    private String description;

    @OneToOne
    @JoinColumn(name = "users_id")
    private UsersEntity usersEntity;
}
