package com.pavel.shopweb.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@DynamicInsert
@DynamicUpdate
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long price;

    @Transient
    private Long totalPrice;

    @OneToOne
    @JoinColumn(name = "image_id")
    private ImageEntity imageEntity;

    @OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY)
    private List<ReviewEntity> reviewEntities;

    @OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY)
    private List<QuestionEntity> questionEntities;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetailEntity> orderDetailEntities;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "discount_id")
    private DiscountEntity discountEntity;

    @ManyToMany(mappedBy = "productEntities")
    @JsonIgnore
    private List<CategoryEntity> categoryEntities;

    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;
}
