package com.maishopidea.maishopidea.repo;

import com.maishopidea.maishopidea.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User findByEmail(String userEmail);
    User findByPassword(String userPassword);
}
