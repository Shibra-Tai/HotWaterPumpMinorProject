package com.crm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.entities.InstallationDocument;


@Repository
public interface InstallationDocumentRepository extends JpaRepository<InstallationDocument, Integer> {
	Optional<InstallationDocument> findByprojectProjectId(int projectId);

}