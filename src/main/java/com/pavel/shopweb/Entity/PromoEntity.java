package com.pavel.shopweb.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "promo")
@DynamicUpdate
@DynamicInsert
public class PromoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private int activates;

    @OneToMany(mappedBy = "promoEntity", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ActivateEntity> activateEntities;
}
