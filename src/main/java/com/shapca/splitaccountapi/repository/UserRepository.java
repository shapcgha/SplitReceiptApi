package com.shapca.splitaccountapi.repository;

import com.shapca.splitaccountapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginAndPasswordSha(String login, String passwordSha);

    int countByLogin(String login);
}
