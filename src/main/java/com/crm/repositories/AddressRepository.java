package com.crm.repositories;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.entities.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>
{
	Optional<Address> findByprojectProjectId(int projectId);
}
