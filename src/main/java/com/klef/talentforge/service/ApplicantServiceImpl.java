package com.klef.talentforge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.talentforge.model.Applicant;
import com.klef.talentforge.model.ApplicantImage;
import com.klef.talentforge.model.Job;
import com.klef.talentforge.repository.ApplicantRepository;
import com.klef.talentforge.repository.Uploadapplicantprofileimage;


@Service
public class ApplicantServiceImpl implements ApplicantService {

	
	@Autowired
	private ApplicantRepository applicantRepository;
	
	
	
	@Autowired
	private Uploadapplicantprofileimage uploadapplicantprofileimage;
	
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
	
	
	

}
