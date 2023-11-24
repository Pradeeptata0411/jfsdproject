package com.klef.talentforge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.talentforge.model.Applicant;

import jakarta.transaction.Transactional;


@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {

	
	@Query("select s from Applicant s where s.email=?1 and s.password=?2 and s.loginstatus=true")
	public Applicant checkApplicantlogin(String email,String pwd);
	
	
	
	@Query("select email from Applicant")
	public List<String> getAllEmails();
	
	
	
	
	@Modifying
    @Transactional  //commit changes
	@Query("update Applicant s set s.loginstatus=?2 where s.id=?1")
	public int applicantacceptance(int cid, boolean acceptance);

	
	
	
	
}
