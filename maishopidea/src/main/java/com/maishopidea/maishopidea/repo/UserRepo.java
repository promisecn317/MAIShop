package com.maishopidea.maishopidea.repo;
import com.maishopidea.maishopidea.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

//    List<user> findAllByuserEmail();
//
//    List<user> findAllByuserPasswoed();
}
