package com.klef.talentforge.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import com.klef.talentforge.model.Applicant;
import com.klef.talentforge.model.ViewApplicationStatus;


public interface ViewApplicationStatusRepository extends JpaRepository<ViewApplicationStatus, Integer> {

	
	@Query("from ViewApplicationStatus where id=?1 and Applicationstatustittle=?2")
	public ViewApplicationStatus getbyidofstatus(int id,String tittle);
	
	    
	    
}
