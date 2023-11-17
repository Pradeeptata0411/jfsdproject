package com.klef.talentforge.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.klef.talentforge.model.Applicant;
import com.klef.talentforge.model.ApplicantImage;
import com.klef.talentforge.model.JobApplications;
import com.klef.talentforge.repository.ApplicantRepository;
import com.klef.talentforge.repository.JobApplicationsRepository;
import com.klef.talentforge.repository.Uploadapplicantprofileimage;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
@Service
public class ApplicantServiceImpl implements ApplicantService {

	
	@Autowired
	private ApplicantRepository applicantRepository;
	
	
	
	@Autowired
	private Uploadapplicantprofileimage uploadapplicantprofileimage;
	
	
	@Autowired
	  private JobApplicationsRepository jobapplicationsRepository;

	@Override
	public String register(Applicant applicant) {
		applicantRepository.save(applicant);
		return "Registerd Sucessfuly";
	}

	@Override
	public Applicant checkApplicantlogin(String uname, String pwd) {
		return applicantRepository.checkApplicantlogin(uname, pwd);
	}

	@Override
	public Applicant viewapplicantbyid(int aid) {
		Optional<Applicant> obj =  applicantRepository.findById(aid);	
		if(obj.isPresent()){
			Applicant c = obj.get();
			return c;
		}
		else{
	         return null;
	    }
	}

	@Override
	public String uploadapplicantprofileimage(ApplicantImage image) {
		
		uploadapplicantprofileimage.save(image);
		return "Uploaded Sucessfuly";
	}

	@Override
	public ApplicantImage ViewimageByID(int id) {
		 Optional<ApplicantImage> obj=uploadapplicantprofileimage.findById(id);
		    if(obj.isPresent()) {
		    	ApplicantImage image=obj.get();
		      return image;
		    }
		    else return null;

	}
	
	@Override
	  public String applyJob(String jobtitle, String firstname, String lastname, String email, String dateofbirth,
	      String experience, String contactno, String companyname, MultipartFile request,boolean status) 
	  {
	       String fileName = StringUtils.cleanPath(request.getOriginalFilename());
	         String msg=null;
	         
	          try {
	              JobApplications jobApplications = new JobApplications();
	              
	              jobApplications.setJobtitle(jobtitle);
	              jobApplications.setFirstname(firstname);
	              jobApplications.setLastname(lastname);
	              jobApplications.setEmail(email);
	              jobApplications.setDateofbirth(dateofbirth);
	              jobApplications.setExperience(experience);
	              jobApplications.setCompanyname(companyname);
	              jobApplications.setContactno(contactno);
	              jobApplications.setApplicationstatus(status);
	           
	              jobApplications.setBfileContent(request.getBytes());
	              jobapplicationsRepository.save(jobApplications);
	              msg= "Your Job Application has been Successfull !";
	          } catch (IOException ex)
	          {
	             msg="This Job is Already Applied !";
	          }
	          return msg;
	    
	  }


	  public JobApplications checkJobApplication(String email, String jobtitle, String companyname) {
	    JobApplications applications=jobapplicationsRepository.checkJobApplication(email, jobtitle, companyname);
	    return applications;
	  }
	

}
