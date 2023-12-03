package com.klef.talentforge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.klef.talentforge.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer>{
    
	@Query("from Job where jobtitle=?1 and description=?2")
	public Job viewJobByTitleAndDescription(String title, String description);
	
	
	@Query("from Job where companyname=?1 ")
	public List<Job> viewalljobsbycompanyname(String companyname);
	
	
	@Query("FROM Job WHERE companyname = ?1 AND salary >= ?2")
	public List<Job> searchBasedOnSalaryAndCompanyName(String companyname, int salary);

	 @Query("select DISTINCT j.companyname from Job j")
	 public  List<String> findAllDistinctCompanyNamescheck();
	
	 
	 
	 @Query("FROM Job WHERE STR_TO_DATE(posteddate, '%d-%m-%Y') >= STR_TO_DATE(:fromdate, '%d-%m-%Y')")
	  public List<Job> viewallJobsByDate( @Param("fromdate") String fromdate);

	 
	 
	 
	 
}
