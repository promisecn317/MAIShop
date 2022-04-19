package com.maishopidea.maishopidea.repo;


import com.maishopidea.maishopidea.entity.VerifyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifyStatusRepository extends JpaRepository<VerifyStatus, String> {
    VerifyStatus findVerifyStatusByEmail(String address);
}
