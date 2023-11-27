package com.klef.talentforge.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.talentforge.model.ViewApplicationStatus;

@Repository
public interface ViewApplicationStatusRepository extends JpaRepository<ViewApplicationStatus, Integer>
{
	
	@Query("select s from ViewApplicationStatus s where s.id=?1 and s.applicationstatustittle=?2")
	public List<ViewApplicationStatus> getStatusByIDAndTitle(int id, String jobtitle);

	    
	    
}
