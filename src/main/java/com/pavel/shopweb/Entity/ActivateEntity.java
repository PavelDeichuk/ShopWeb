package com.pavel.shopweb.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long activates;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UsersEntity usersEntity;

    @ManyToOne
    @JoinColumn(name = "promo_id")
    @JsonIgnore
    private PromoEntity promoEntity;
}
