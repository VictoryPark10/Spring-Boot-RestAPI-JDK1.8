package com.vicsoft.basicapi8.repository.user;

import com.vicsoft.basicapi8.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByIdentifier(String identifier);

    void deleteByIdentifier(String identifier);

}
