package com.klef.talentforge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.klef.talentforge.model.JobApplications;
import com.klef.talentforge.model.ViewApplicationStatus;

public interface ViewApplicationStatusRepository extends JpaRepository<ViewApplicationStatus, Integer> {

	
	
	
}
