package com.klef.talentforge.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.klef.talentforge.model.Applicant;
import com.klef.talentforge.model.ApplicantImage;
import com.klef.talentforge.model.JobApplications;

public interface ApplicantService {

	public String register(Applicant applicant);
	
	public Applicant checkApplicantlogin(String uname,String pwd);
	
	public Applicant viewapplicantbyid(int aid);
	
	public String uploadapplicantprofileimage(ApplicantImage image);
	
	public Applicant getApplicantById(int id);
	
	public ApplicantImage ViewimageByID(int id);
	
	public String applyJob(int id,String jobtitle,String firstname,String lastname,String email,String dateofbirth
		      
		      ,String experience,String contactno,String companyname,MultipartFile request,boolean status);
		  
		   public JobApplications checkJobApplication(String email,String jobtitle,String companyname);
		   
		   
		   
			  public List<JobApplications> ViewMyJobApplications(int id);
			  
			  
			
	
}
