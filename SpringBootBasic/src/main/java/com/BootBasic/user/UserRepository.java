package com.BootBasic.user;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//DB와 관련된 형태의 Bean
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{



}


