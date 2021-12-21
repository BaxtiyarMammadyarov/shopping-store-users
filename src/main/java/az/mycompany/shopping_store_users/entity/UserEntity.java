package az.mycompany.shopping_store_users.entity;

import az.mycompany.shopping_store_users.dto.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name",nullable = false)
    String name;
    @Column(name = "surname",nullable = false)
    String surname;
    @Column(name = "email",nullable = false,unique = true)
    String email;
    @Column(name = "username",nullable = false,unique = true)
    String username;
    @Column(name = "password",nullable = false)
    String password;
    @Column(name = "enable",nullable = false)
    Boolean enable;
    @Column(name = "status",nullable = false)
    Status status;
}
