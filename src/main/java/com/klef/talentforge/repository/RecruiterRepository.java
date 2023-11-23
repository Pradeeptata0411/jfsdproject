package com.klef.talentforge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.talentforge.model.Recruiter;

import jakarta.transaction.Transactional;



@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter,Integer>{

	@Query("select s from Recruiter s where s.email=?1 and s.password=?2 and s.loginstatus=true")
	public Recruiter checkRecruiterlogin(String email,String pwd);
	
	
	@Modifying
    @Transactional  //commit changes
	@Query("update Recruiter s set s.loginstatus=?2 where s.id=?1")
	public int recruiteracceptance(int cid, boolean acceptance);

	
}
