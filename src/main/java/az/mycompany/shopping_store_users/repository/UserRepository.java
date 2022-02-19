package az.mycompany.shopping_store_users.repository;


import az.mycompany.shopping_store_users.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<UserEntity> findAllByEnable(boolean b);

   Optional<UserEntity> findByUsername(String username);

    UserEntity findByEmail(String email);
}
