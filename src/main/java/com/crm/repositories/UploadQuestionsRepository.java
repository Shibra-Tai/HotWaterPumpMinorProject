package com.crm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.entities.Questions;
import com.crm.entities.UploadQuestions;

@Repository
public interface UploadQuestionsRepository extends JpaRepository<UploadQuestions, Integer>{
	Optional<UploadQuestions> findByprojectProjectId(int projectId);

}