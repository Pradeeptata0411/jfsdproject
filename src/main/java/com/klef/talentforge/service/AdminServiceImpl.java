package com.klef.talentforge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.talentforge.model.Admin;
import com.klef.talentforge.model.Applicant;
import com.klef.talentforge.model.Job;
import com.klef.talentforge.model.Recruiter;
import com.klef.talentforge.repository.AdminRepository;
import com.klef.talentforge.repository.ApplicantRepository;
import com.klef.talentforge.repository.JobApplicationsRepository;
import com.klef.talentforge.repository.JobRepository;
import com.klef.talentforge.repository.RecruiterRepository;

@Service
public class AdminServiceImpl implements AdminService
{

	@Autowired
	private AdminRepository adminRepository;
	
	
	@Autowired
	private RecruiterRepository recruiterRepository;
	
	
	
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private JobApplicationsRepository jobapplicationsRepository;
	
	@Autowired
	private ApplicantRepository applicantRepository;
	
	
	
	
	public Admin checkadminlogin(String username, String password) {
		
		return adminRepository.checkAdminlogin(username, password);
	}


	@Override
	public List<Recruiter> ViewAllRecruiters() {
		// TODO Auto-generated method stub
		return recruiterRepository.findAll();
	}


	@Override
	public int recruiteracceptance(int cid, boolean acceptance) {
		return recruiterRepository.recruiteracceptance(cid, acceptance);
	}

	
	
	  @Override
		public long applicantCount() {
			
			return applicantRepository.count();
		}


		@Override
		public long recruiterCount() 
		{
			return recruiterRepository.count();
		}


		@Override
		public long JobsCount() {
			return jobRepository.count();
		}


		@Override
		public long JobApplicationsCount() {
			return jobapplicationsRepository.count();
		}


		@Override
		public int applicantacceptance(int cid, boolean acceptance) {
			return applicantRepository.applicantacceptance(cid, acceptance);
		}


		@Override
		public List<Applicant> ViewAllApplicant() {
			// TODO Auto-generated method stub
			return applicantRepository.findAll();
		}
	
	
		public List<Job> ViewAllJobs()
		{
			return  jobRepository.findAll();
		}


		@Override
		public Job adminviewjobbyid(int id) {
			
			Optional<Job> obj =  jobRepository.findById(id);	
			if(obj.isPresent()){
				Job c = obj.get();
				return c;
			}
			else{
		         return null;
		    }
		}
	
	
	
}
