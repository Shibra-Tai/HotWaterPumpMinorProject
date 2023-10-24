package com.crm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.entities.Schedule;


@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer>
{
	Optional<Schedule> findByprojectProjectId(int projectId);
}
