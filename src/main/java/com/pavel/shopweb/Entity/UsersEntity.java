package com.pavel.shopweb.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
@Table(name = "users")
@DynamicUpdate
@DynamicInsert
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 28, message = "Size username is min 3 max 28!")
    @NotNull(message = "Username cannot be null!")
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$", message = "Min 1 uppercase letter.\n" +
            "Min 1 lowercase letter\n" +
            "Min 1 special character.\n" +
            "Min 1 number.\n" +
            "Min 8 characters.")
    @Size(min = 8, max = 32, message = "Size password is min 8 max 32")
    @NotNull(message = "Password cannot be null!")
    private String password;

    @Transient
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$", message = "Min 1 uppercase letter.\n" +
            "Min 1 lowercase letter.\n" +
            "Min 1 special character.\n" +
            "Min 1 number.\n" +
            "Min 8 characters.")
    @Size(min = 8, max = 32, message = "Size password confirm is min 8 max 32")
    private String password2;

    @Email(message = "Error validation email!")
    @Size(min = 4, max = 42, message = "Size email is min 4 max 42!")
    @NotNull(message = "Email cannot be null!")
    private String email;

    private String role;

    private String activationToken;

    private String resetToken;

    private boolean mfa;

    private boolean mailing;

    private String secret;

    private Long balance;

    @OneToOne(mappedBy = "usersEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private UsersDetailEntity usersDetailEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private ImageEntity imageEntity;

    @OneToOne(mappedBy = "usersEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private BucketEntity bucketEntity;

    @OneToOne(mappedBy = "usersEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private WishEntity wishEntity;

    @OneToMany(mappedBy = "usersEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReviewEntity> reviewEntities;

    @OneToMany(mappedBy = "usersEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuestionEntity> questionEntities;

    @OneToMany(mappedBy = "usersEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderEntity> orderEntities;

    @OneToMany(mappedBy = "usersEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CreditCardEntity> creditCardEntities;

    @OneToMany(mappedBy = "usersEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ActivateEntity> activateEntities;
    @ManyToMany(mappedBy = "usersEntities")
    @JsonIgnore
    private List<QuestionEntity> questionEntityList;

    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;
}
