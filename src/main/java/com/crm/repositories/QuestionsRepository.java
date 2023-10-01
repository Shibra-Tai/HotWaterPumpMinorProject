package com.crm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.entities.Questions;
import com.crm.entities.User;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Integer>{
	Optional<Questions> findByprojectProjectId(int userName);

}
