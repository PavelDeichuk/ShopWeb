package com.pavel.shopweb.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.BinaryJdbcType;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
@DynamicUpdate
@DynamicInsert
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Lob
    @JdbcType(BinaryJdbcType.class)
    private byte[] image;

    private Long size;

    @OneToOne(mappedBy = "imageEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UsersEntity usersEntity;

    @OneToOne(mappedBy = "imageEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ProductEntity productEntity;

    @OneToMany(mappedBy = "imageEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReviewEntity> reviewEntities;
}
