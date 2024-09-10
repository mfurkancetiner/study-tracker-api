package com.furkan.study_tracker_api.repository;

import com.furkan.study_tracker_api.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    AppUser findByEmail(String email);
}
