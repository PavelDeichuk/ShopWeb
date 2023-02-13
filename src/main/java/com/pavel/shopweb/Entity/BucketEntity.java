package com.pavel.shopweb.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bucket")
@DynamicUpdate
@DynamicInsert
public class BucketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String session;

    @Transient
    private int quantity;

    @Transient
    private Long price;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UsersEntity usersEntity;
}
