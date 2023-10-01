package com.crm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.entities.RefreshToken;
import com.crm.entities.User;
import com.google.common.base.Optional;


@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer>
{

	Optional<RefreshToken> findByUuid(String uuid);
	
	Optional<RefreshToken> findByUser(User user);

	

}
