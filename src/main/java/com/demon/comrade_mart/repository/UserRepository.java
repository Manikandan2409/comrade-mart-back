package com.demon.comrade_mart.repository;

import com.demon.comrade_mart.dto.DispatchDTO;
import com.demon.comrade_mart.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    //Optional<Users> findByUsernameAndPassword(String username, String password);
    Users findByUsernameAndPassword(String username, String password);
    List<Users> findByUsertype(String usertype);
}
