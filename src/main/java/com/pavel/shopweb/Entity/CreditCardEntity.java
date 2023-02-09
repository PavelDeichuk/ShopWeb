package com.pavel.shopweb.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credit_card")
@DynamicInsert
@DynamicUpdate
public class CreditCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private String fullName;

    private LocalDateTime validity;

    private String cvv;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity usersEntity;
}
