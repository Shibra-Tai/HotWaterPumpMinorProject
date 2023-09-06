package com.crm.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
	Optional<User> findByuserName(String userName);
}
