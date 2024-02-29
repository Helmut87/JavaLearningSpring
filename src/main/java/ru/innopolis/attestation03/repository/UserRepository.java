package ru.innopolis.attestation03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.attestation03.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
