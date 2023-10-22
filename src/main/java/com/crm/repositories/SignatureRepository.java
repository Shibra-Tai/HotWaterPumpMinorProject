package com.crm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.entities.Signature;


@Repository
public interface SignatureRepository extends JpaRepository<Signature,Integer>{
	Optional<Signature> findByprojectProjectId(int projectId);

}