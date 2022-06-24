package com.awedzonka.bhpbackend.repository;


import com.awedzonka.bhpbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    long countByLogin(String login);

    long countBySuperUser(boolean check);

    User findUserByLogin(String login);

    List<User> findUserByExamPassed(Boolean passed);

    List<User> findUserByLastNameContainingOrFirstNameContainingOrLoginContaining(String lastName, String firstName, String login);
}
