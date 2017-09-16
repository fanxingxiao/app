package com.bigdata.logistic_warehouse.service.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bigdata.logistic_warehouse.service.user.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	User findByName(String name);
}
