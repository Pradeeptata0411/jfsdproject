package com.klef.talentforge.service;

import com.klef.talentforge.model.Applicant;
import com.klef.talentforge.model.Recruiter;

public interface RecruiterService {

public String registerRecruiter(Recruiter recruiter);
	
	public Recruiter checkRecruiterlogin(String email,String pwd);
	
	
}
