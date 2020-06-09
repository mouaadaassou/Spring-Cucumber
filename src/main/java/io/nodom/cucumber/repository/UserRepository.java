package io.nodom.cucumber.repository;

import io.nodom.cucumber.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
