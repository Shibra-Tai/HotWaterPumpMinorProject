package com.crm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.entities.Project;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>
{

}
