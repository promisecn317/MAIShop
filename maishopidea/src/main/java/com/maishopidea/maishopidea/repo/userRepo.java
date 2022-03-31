package com.maishopidea.maishopidea.repo;
import com.maishopidea.maishopidea.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepo extends JpaRepository<user,Integer> {

//    List<user> findAllByuserEmail();
//
//    List<user> findAllByuserPasswoed();
}
