package com.klef.talentforge.service;

import com.klef.talentforge.model.Applicant;
import com.klef.talentforge.model.ApplicantImage;
import com.klef.talentforge.model.Job;

public interface ApplicantService {

	public String register(Applicant applicant);
	
	public Applicant checkApplicantlogin(String uname,String pwd);
	
	public Applicant viewapplicantbyid(int aid);
	
	public String uploadapplicantprofileimage(ApplicantImage image);
	
	
	public ApplicantImage ViewimageByID(int id);
	
	
	
}
