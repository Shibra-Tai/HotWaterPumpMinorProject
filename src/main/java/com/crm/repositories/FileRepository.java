package com.crm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.entities.FileEntity;


@Repository
public interface FileRepository extends JpaRepository<FileEntity, Integer>{
	Optional<FileEntity> findByprojectProjectId(int projectId);

}