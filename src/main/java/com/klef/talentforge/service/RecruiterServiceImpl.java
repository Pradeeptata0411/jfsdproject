package com.klef.talentforge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.talentforge.model.Recruiter;
import com.klef.talentforge.repository.RecruiterRepository;


@Service
public class RecruiterServiceImpl implements RecruiterService {

	
	@Autowired
	private RecruiterRepository recruiterRepository;
	
	
	
	@Override
	public String registerRecruiter(Recruiter recruiter) {
		recruiterRepository.save(recruiter);
		return "Recruiter Registered Sucessfully";
	}

	@Override
	public Recruiter checkRecruiterlogin(String email, String pwd) {
		return recruiterRepository.checkRecruiterlogin(email, pwd);
	}

}
