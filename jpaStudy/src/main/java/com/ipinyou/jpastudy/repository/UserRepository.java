package com.ipinyou.jpastudy.repository;

import com.ipinyou.jpastudy.entity.UserBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserBaseEntity, Long> {
}
