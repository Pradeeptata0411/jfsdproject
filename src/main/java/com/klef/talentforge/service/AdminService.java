package com.klef.talentforge.service;

import java.util.List;

import com.klef.talentforge.model.Admin;
import com.klef.talentforge.model.Job;
import com.klef.talentforge.model.Recruiter;

public interface AdminService {

	public Admin checkadminlogin(String username, String password);
	
	
	public List<Recruiter> ViewAllRecruiters();
	
	
	public int recruiteracceptance(int cid, boolean acceptance);
	
    public long applicantCount();
	
	public long recruiterCount();
	
	public long JobsCount();
	
	public long JobApplicationsCount();
	
	
}
