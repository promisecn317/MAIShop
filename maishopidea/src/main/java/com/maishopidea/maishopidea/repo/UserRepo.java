package com.maishopidea.maishopidea.repo;
import com.maishopidea.maishopidea.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

//    List<user> findAllByuserEmail();

    public User findByEmail(String userEmail);
    public User findByPassword(String userPassword);
//public User userLogin(String username, String password);
}
