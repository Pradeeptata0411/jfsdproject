package com.klef.talentforge.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.klef.talentforge.model.Admin;
import com.klef.talentforge.model.Applicant;
import com.klef.talentforge.model.ApplicantImage;
import com.klef.talentforge.model.ContactForm;
import com.klef.talentforge.model.Job;
import com.klef.talentforge.model.JobApplications;
import com.klef.talentforge.model.Recruiter;
import com.klef.talentforge.model.ViewApplicationStatus;
import com.klef.talentforge.service.AdminService;
import com.klef.talentforge.service.ApplicantService;
import com.klef.talentforge.service.ContactUsEmailManager;
import com.klef.talentforge.service.EmailManager;
import com.klef.talentforge.service.RecruiterService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ClientController 
{
	@Autowired
	private ApplicantService applicantService;
	
	
	@Autowired
	private RecruiterService recruiterService;
	
	
	@Autowired
	private AdminService adminService;
	

	@Autowired
	private EmailManager emailManager;
	
	
	@Autowired
	private ContactUsEmailManager contactusform;
	
	
	
	
	
	@GetMapping("register")
	public ModelAndView employeeregister() {
		ModelAndView mv=new ModelAndView("ApplicantRegister");
		return mv;
	}

	
	@GetMapping("contactus")
	public ModelAndView contactus() {
		ModelAndView mv=new ModelAndView("contactus");
		return mv;
	}
	
	
	
	
	 @GetMapping("/")
	    public ModelAndView homepage() {
	      ModelAndView mv=new ModelAndView("homepage");
	      return mv;
	    }
	 
	 @GetMapping("ApplicantLogin")
	    public ModelAndView applicantlogin() {
	      ModelAndView mv=new ModelAndView("ApplicantLogin");
	      return mv;
	    }
	 
	
	@PostMapping("registration")
	public ModelAndView addapplicant(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String msg = null;
		try {
			String fname = request.getParameter("firstname");
			String lname = request.getParameter("lastname");
			String email = request.getParameter("email");
			String gender = request.getParameter("gender");
			String pwd = request.getParameter("password");
			String contact = request.getParameter("contactnumber");
			String address = request.getParameter("address");
			
			
			LocalDate currentDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String currentDateStr = currentDate.format(formatter);
			
			LocalTime currentTime = LocalTime.now();
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			String currentTimeStr = currentTime.format(timeFormatter);
    
			
			Applicant applicant = new Applicant();
		    applicant.setFirstname(fname);
		    applicant.setLastname(lname);
		    applicant.setEmail(email);
		    applicant.setContactno(contact);
		    applicant.setPassword(pwd);
		    applicant.setGender(gender);
		    applicant.setAddress(address);
		    applicant.setLoginstatus(true);
		    
		    msg = applicantService.register(applicant);
		    
		    
			 String fileName = "invite.html"; 
	            String filePath = request.getServletContext().getRealPath("/" + fileName);
	            
		    String fromEmail = "jfsdsdpams@gmail.com"; // Set your email
            String toEmail = email; // Use the user's email from the booking
            String subject = "Talentforge  Registration Confirmation mail";
            String text = "Hello " +fname+" "+lname +"\n"+" Your Registration into talentforge  has been Sucessfull "+"\n"+" Through this email: "+email;
            String text2="Date :-" +currentDateStr +"\n"+"On this Time :-"+currentTimeStr;
            // Inject JavaMailSender
            String htmlContent = new String(Files.readAllBytes(Paths.get(filePath)));
            htmlContent = htmlContent.replace("[name]", fname);
            htmlContent = htmlContent.replace("[text]", text);
            htmlContent=htmlContent.replace("[text2]", text2);
            
            htmlContent=htmlContent.replace("[password]", pwd);
            
        
            emailManager.sendEmail(fromEmail, toEmail, subject, text,htmlContent);
            mv.setViewName("applicantsucessfullloginpageafterregistration");
			mv.addObject("message", msg);
			
		}
		catch (Exception e) {
			mv.setViewName("ApplicantRegister");
			msg = "Registration Failed & Provide Valid Details..!!";
			mv.addObject("message", msg);
		}
		return mv;
	}
	

	@PostMapping("checkapplicantlogin")
	public ModelAndView ckeckapplogin(HttpServletRequest request) {
		String uname = request.getParameter("email");
		String pwd = request.getParameter("password1");
		HttpSession session = request.getSession();
		Applicant c = applicantService.checkApplicantlogin(uname, pwd);
		  JobApplications applications = new JobApplications();

			ModelAndView mv =new ModelAndView();
			 List<Job> jobslist1 = recruiterService.ViewAllJobs();
	          Set<String> companyNamesSet = new HashSet<>();

	          		         // Extract company names from each Job object and add to the Set
	          		         for (Job job : jobslist1) {
	          		             companyNamesSet.add(job.getCompanyname());
	          		         }

	          
	          mv.addObject("reclist", companyNamesSet);
		  
		if(c!=null ) {
		
			session.setAttribute("cid",c.getId()); 
			session.setAttribute("fname", c.getFirstname());
			session.setAttribute("lname",c.getLastname());
			session.setAttribute("email",c.getEmail());
			session.setAttribute("address",c.getAddress());
			session.setAttribute("contact",c.getContactno());
			
			   List<Job> jobslist = recruiterService.ViewAllJobs();
			 
			   //ApplicantImage image = applicantService.ViewimageByID(sid);
		          mv.addObject("jobslist", jobslist);
		          //mv.addObject("image", image);
			mv.setViewName("index");
		}else  {
			mv.setViewName("ApplicantLogin");
			mv.addObject("message", "Invalid Login..!");
					}
		return mv;
	}
	
	
	 @GetMapping("applicanthome")
     public ModelAndView indexpage(HttpServletRequest request) {
       ModelAndView mv=new ModelAndView("index");
      
       
       HttpSession session = request.getSession();
       int sid = (int) session.getAttribute("cid"); 
	    String fname = (String) session.getAttribute("fname");
	    String lname = (String) session.getAttribute("lname");
	    String email1 = (String) session.getAttribute("email");
	    String address1=(String) session.getAttribute("address");
	    String contact1=(String) session.getAttribute("contact");
	    mv.addObject("cid", sid);
	    mv.addObject("fname", fname);
	    mv.addObject("lname", lname);
	    mv.addObject("address", address1);
	    mv.addObject("contact", contact1);
	    Applicant aa= applicantService.viewapplicantbyid(sid);
	    mv.addObject("aa", aa);
        List<Job> jobslist = recruiterService.ViewAllJobs();
        ApplicantImage image = applicantService.ViewimageByID(sid);
          mv.addObject("jobslist", jobslist);
          List<Job> jobslist1 = recruiterService.ViewAllJobs();
          Set<String> companyNamesSet = new HashSet<>();

          		         // Extract company names from each Job object and add to the Set
          		         for (Job job : jobslist1) {
          		             companyNamesSet.add(job.getCompanyname());
          		         }

          
          mv.addObject("reclist", companyNamesSet);
          mv.addObject("image", image);
       return mv;
     }

	
	
	
	//recruiter
	
	@GetMapping("recruiterhome")
	  public ModelAndView recruiterhome() {
	    ModelAndView mv=new ModelAndView("recruiterhome");
	    return mv;
	  }
	 @GetMapping("companylogin")
		public ModelAndView companylogin() 
		{
			long applicantCount=adminService.applicantCount();
			long recruiterCount=adminService.recruiterCount();
			long jobsCount=adminService.JobsCount();
			long jobApplicationsCount=adminService.JobApplicationsCount();
			ModelAndView mv=new ModelAndView("companylogin");

			mv.addObject("applicantCount", applicantCount);
			mv.addObject("recruiterCount",recruiterCount );
			mv.addObject("jobsCount",jobsCount );
			mv.addObject("jobApplicationsCount",jobApplicationsCount );
			return mv;
		}
	  @GetMapping("companyregistration")
	  public ModelAndView companyregistration() {
	    ModelAndView mv=new ModelAndView("companyregistration");
	    return mv;
	  }
	  
	  
	  
	  
	     @PostMapping("recruiterRegistration")
		public ModelAndView recruiterRegistration(HttpServletRequest request) {
			ModelAndView mv = new ModelAndView();
			
			
			long applicantCount=adminService.applicantCount();
			long recruiterCount=adminService.recruiterCount();
			long jobsCount=adminService.JobsCount();
			long jobApplicationsCount=adminService.JobApplicationsCount();
			
			mv.addObject("applicantCount", applicantCount);
			mv.addObject("recruiterCount",recruiterCount );
			mv.addObject("jobsCount",jobsCount );
			mv.addObject("jobApplicationsCount",jobApplicationsCount );
			
			String msg = null;
			try {
					String companyname = request.getParameter("companyname");
				String email = request.getParameter("email");
				String pwd = request.getParameter("password");
				String contact = request.getParameter("contactnumber");
				String address = request.getParameter("address");
				
				
				LocalDate currentDate = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String currentDateStr = currentDate.format(formatter);
				
				LocalTime currentTime = LocalTime.now();
				DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
				String currentTimeStr = currentTime.format(timeFormatter);
	    
				
				Recruiter recruiter = new Recruiter();
				recruiter.setEmail(email);
				recruiter.setCompanyname(companyname);
				recruiter.setPassword(pwd);
				recruiter.setContactno(contact);
				recruiter.setAddress(address);
				recruiter.setLoginstatus(false);
				
			    msg = recruiterService.registerRecruiter(recruiter);
			    
			    
				 String fileName = "invite.html"; 
		            String filePath = request.getServletContext().getRealPath("/" + fileName);
		            
			    String fromEmail = "jfsdsdpams@gmail.com"; // Set your email
	            String toEmail = email; // Use the user's email from the booking
	            String subject = "Talentforge  Registration Confirmation";
	            String text = "Hello " +companyname +"\n"+" Your Registration into talentforge  has been Sucessfull "+"\n"+" Through this email "+email;
	            String text2="Date :-" +currentDateStr +"\n"+"On this Time :-"+currentTimeStr;
	            // Inject JavaMailSender
	            String htmlContent = new String(Files.readAllBytes(Paths.get(filePath)));
	            htmlContent = htmlContent.replace("[name]", companyname);
	            htmlContent = htmlContent.replace("[text]", text);
	            htmlContent=htmlContent.replace("[text2]", text2);
	            
	            htmlContent=htmlContent.replace("[password]", pwd);
	            
	        
	            emailManager.sendEmail(fromEmail, toEmail, subject, text,htmlContent);
	            mv.setViewName("companysucessfullloginpageafterregistration");
				mv.addObject("message", msg);
				
			}
			catch (Exception e) {
				mv.setViewName("companyregistration");
				msg = "Registration Failed & Provide Valid Details..!!";
				mv.addObject("message", msg);
			}
			return mv;
		}
	  
	  
	     @PostMapping("checkrecruiterlogin")
	 	public ModelAndView checkrecruiterlogin(HttpServletRequest request) {
	 		String uname = request.getParameter("email");
	 		String pwd = request.getParameter("password1");
	 		HttpSession session = request.getSession();
	 		ModelAndView mv =new ModelAndView();
	 		long applicantCount=adminService.applicantCount();
			long recruiterCount=adminService.recruiterCount();
			long jobsCount=adminService.JobsCount();
			long jobApplicationsCount=adminService.JobApplicationsCount();
			
			
			mv.addObject("applicantCount", applicantCount);
			mv.addObject("recruiterCount",recruiterCount );
			mv.addObject("jobsCount",jobsCount );
			mv.addObject("jobApplicationsCount",jobApplicationsCount );
	 		
	 		Recruiter rec = recruiterService.checkRecruiterlogin(uname, pwd);
	 	

	 		if(rec!=null ) {
	 		
	 			session.setAttribute("rid",rec.getId()); 
	 			session.setAttribute("remail",rec.getEmail());
	 			session.setAttribute("rcompanynmae",rec.getCompanyname());
	 			mv.setViewName("recruiterhome");
	 		}else  {
	 			mv.setViewName("companylogin");
	 			mv.addObject("message", "Invalid Login..!");
	 					}
	 		return mv;
	 	}
	     
	     
	     @GetMapping("recruiterviewjobs")
	     public ModelAndView recruiterviewjobs(HttpServletRequest request) {
	       ModelAndView mv=new ModelAndView("recruiterviewalljobs");
	       HttpSession session = request.getSession();
	       int sid = (int) session.getAttribute("rid"); 
		    String sname = (String) session.getAttribute("rcompanynmae");
	       List<Job> jobsbyname = recruiterService.viewjobsbycompanyname(sname);
	       mv.addObject("jobsbyname", jobsbyname);
	      
	       return mv;
	     }
	     
	     
	     
	     

	     
	 	
	  
	  //admincodes
	     
	     @GetMapping("admin")
	     public ModelAndView adminlogin() {
	       ModelAndView mv=new ModelAndView("adminlogin");
	       return mv;
	     }

	     @PostMapping("checkadminlogin")
	     public ModelAndView checkadminlogin(HttpServletRequest request) {
	       String username=request.getParameter("email");
	       String password=request.getParameter("password1");
	       ModelAndView mv=new ModelAndView();
	       long applicantCount=adminService.applicantCount();
			long recruiterCount=adminService.recruiterCount();
			long jobsCount=adminService.JobsCount();
			long jobApplicationsCount=adminService.JobApplicationsCount();
				  mv.addObject("applicantCount", applicantCount);
			mv.addObject("recruiterCount",recruiterCount );
			mv.addObject("jobsCount",jobsCount );
			mv.addObject("jobApplicationsCount",jobApplicationsCount );
	       HttpSession session=request.getSession();
	       Admin adm=adminService.checkadminlogin(username, password);
	       if(adm!=null) {
	          
	         session.setAttribute("uname", adm.getUsername());
	         mv.setViewName("adminhome");
	         
	       }
	         else  {
	       mv.setViewName("adminlogin");
	       mv.addObject("message", "Invalid Login..!");
	           }
	     return mv;
	       
	     }

	     
	     
	     
	     @GetMapping("viewallrecruiters")
	     public ModelAndView viewallrecruiters() {
	       ModelAndView mv=new ModelAndView("adminviewallrecruiter");
	       List<Recruiter> recruiters = adminService.ViewAllRecruiters();
	       mv.addObject("recruiters", recruiters);
	       return mv;
	     }
	     
	     
	   
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     @PostMapping("uploadapplicantprofileimage")
	        public ModelAndView uploadapplicantprofileimage(HttpServletRequest request,@RequestParam("ApplicantImage") MultipartFile file)throws IOException, SerialException, SQLException
	        {
	          ModelAndView mv=new ModelAndView();
	          
	          List<Job> jobslist1 = recruiterService.ViewAllJobs();
	          Set<String> companyNamesSet = new HashSet<>();

	          		         // Extract company names from each Job object and add to the Set
	          		         for (Job job : jobslist1) {
	          		             companyNamesSet.add(job.getCompanyname());
	          		         }

	          
	          mv.addObject("reclist", companyNamesSet);
	          
	          byte[] bytes = file.getBytes();
	      Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
	      HttpSession session =request.getSession();
	      int id=(int)session.getAttribute("cid");
	      ApplicantImage image = new ApplicantImage();
	      image.setId(id);
	      image.setImage(blob);
	      
	      String msg=applicantService.uploadapplicantprofileimage(image);
	      List<Job> jobslist = recruiterService.ViewAllJobs();
	      mv.addObject("jobslist", jobslist);
	      mv.setViewName("index");
	      mv.addObject("msg", msg);
	      return mv;
	          
	        }

	     
	     
	     @GetMapping("displayApplicantimage")
	     public ResponseEntity<byte[]> displayApplicantimage(@RequestParam("id") int id) throws IOException, SQLException
	        {
	          ApplicantImage mem =  applicantService.ViewimageByID(id);
	          byte [] imageBytes = null;
	          imageBytes = mem.getImage().getBytes(1,(int) mem.getImage().length());

	          return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	        }
	     
	     
	     
//	     @PostMapping("addjob")
//	        public ModelAndView addajob(HttpServletRequest request,@RequestParam("companyimage") MultipartFile file)throws IOException, SerialException, SQLException
//	        {
//	          ModelAndView mv=new ModelAndView();
//	          String title=request.getParameter("jobtitle");
//	          String location=request.getParameter("location");
//	          String skills=request.getParameter("skills");
//	          String description=request.getParameter("description");
//	          String salary=request.getParameter("salary");
//	          String companyname = request.getParameter("companyname");
//	          
//	          byte[] bytes = file.getBytes();
//	      Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
//	      Job j=recruiterService.viewJobByTitleAndDescription(title, description);
//	      if(j==null) {
//	          
//	      Job job=new Job();
//	      job.setJobtitle(title);
//	      job.setLocation(location);
//	      job.setSkills(skills);
//	      job.setSalary(salary);
//	      job.setDescription(description);
//	      job.setImage(blob);
//	      job.setCompanyname(companyname);
//	      
//	      String msg=recruiterService.addjob(job);
//	      mv.setViewName("addjob");
//	      mv.addObject("msg", msg);
//	      }
//	      else {
//	        mv.addObject("msg", "Failed to Add.This is Already Existing Job");
//	      }
//	      return mv;
//	          
//	        }
  
	     
	     
	     @GetMapping("postajob")
         public ModelAndView postajob() {
         ModelAndView mv=new ModelAndView("addjob");
         return mv;
       }

	     @GetMapping("displaycompanyimage")
	     public ResponseEntity<byte[]> displayprofileimage(@RequestParam("id") int id) throws IOException, SQLException
	        {
	          Job mem =  recruiterService.ViewJobByID(id);
	          byte [] imageBytes = null;
	          imageBytes = mem.getImage().getBytes(1,(int) mem.getImage().length());

	          return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	        }

	     
	    
	     
	     
	     @GetMapping("adminviewjobsbyid")
	     public ModelAndView adminviewalljobs(@RequestParam("id") int id) {
	       ModelAndView mv=new ModelAndView("adminviewjobbyid");
	       
	       Job j = adminService.adminviewjobbyid(id);
	          mv.addObject("j", j);
	       return mv;
	     }
	     
	     
	     
	     
	     
	     
	     @GetMapping("adminhome")
		  public ModelAndView adminhome() {
			  long applicantCount=adminService.applicantCount();
				long recruiterCount=adminService.recruiterCount();
				long jobsCount=adminService.JobsCount();
				long jobApplicationsCount=adminService.JobApplicationsCount();
				
			  ModelAndView mv=new ModelAndView("adminhome");
			  mv.addObject("applicantCount", applicantCount);
				mv.addObject("recruiterCount",recruiterCount );
				mv.addObject("jobsCount",jobsCount );
				mv.addObject("jobApplicationsCount",jobApplicationsCount );
			  return mv;
		  }
	     
	     
	     @GetMapping("deletejob")
	     public String deletejob(@RequestParam("id") int id) {
		       ModelAndView mv=new ModelAndView();
		       mv.setViewName("adminviewalljobs");
		      String msg = recruiterService.deletejob(id);
		      mv.addObject("msg", msg);
		       return "redirect:/recruiterviewjobs";
		     }

	     @GetMapping("withdrawApplication")
	     public String withdrawApplication(@RequestParam("id") int id , @RequestParam("applicationid") int applicantid) {
		       ModelAndView mv=new ModelAndView();
		       mv.setViewName("myjobApplications");
		      String msg = applicantService.withdrawapplication(id, applicantid);
		      mv.addObject("msg", msg);
		       return "redirect:/myjobApplications";
		     }
	        
	     @PostMapping("/apply")
	       public ModelAndView applyjob(@RequestParam("jobtitle") String jobtitle,@RequestParam("fname") String firstname,@RequestParam("lname") String lastname, @RequestParam("email") String email, @RequestParam("dateofbirth") String dateofbirth
	         
	         ,@RequestParam("experience") String experience,@RequestParam("contactnumber") String contactno,@RequestParam("companyname") String companyname,@RequestParam("resume") MultipartFile file,HttpServletRequest request) 
	       {
	         ModelAndView mv=new ModelAndView();
	         
	        
	         HttpSession session=request.getSession();
	         int id=(int) session.getAttribute("cid");
	        
	         JobApplications applications =applicantService.checkJobApplication(email, jobtitle, companyname);
	         if(applications==null)
	         {
	         
	            String response=applicantService.applyJob(id,jobtitle, firstname, lastname, email, dateofbirth, experience, contactno, companyname, file,true);
	         mv.addObject("msg", response);
	         
	         mv.setViewName("applicationsuccessfulpage");
	         
	         return mv;
	       }
	         else {
	           mv.setViewName("applicationsuccessfulpage");
	           mv.addObject("msg", "This Job is Already Applied !!");
	         }
	         return mv;
	       }

	     
	     
	       @GetMapping("applyjob")
	       public ModelAndView applyjob(HttpServletRequest request,@RequestParam("id") int  id) {
	         ModelAndView mv=new ModelAndView("applyjob");
	         HttpSession session = request.getSession();
	             int sid = (int) session.getAttribute("cid"); 
	             Applicant app=applicantService.getApplicantById(sid);
	             Job job =recruiterService.ViewJobByID(id);
	             
	             
	             JobApplications applications =applicantService.checkJobApplication(app.getEmail(), job.getJobtitle(), job.getCompanyname());
	             if(applications==null) 
	           {
	             Job j=recruiterService.ViewJobByID(id);
	             
	             mv.addObject("applicant", app);
	             mv.addObject("job", j);
	             
	         return mv;
	             }
	             else {
	               mv.setViewName("applicationsuccessfulpage");
	               mv.addObject("msg", "This Job is Already Applied !!!!");
	               
	             }
	         
	             return mv;
	       }
	       
	       
	       @GetMapping("applicationsuccessfulpage")
	       public ModelAndView applicationsuccessfulpage()
	       {
	         ModelAndView mv=new ModelAndView("applicationsuccessfulpage");
	         return mv;
	       }

	       
	       @GetMapping("myjobApplications")
	       public ModelAndView viewMyJobApplications(HttpServletRequest request) {
	         ModelAndView mv=new ModelAndView();
	         HttpSession session=request.getSession();
	         int id=(int) session.getAttribute("cid");
	         List<JobApplications> jobslist=applicantService.ViewMyJobApplications(id);
	         
	         mv.setViewName("myjobApplications");
	         mv.addObject("jobslist", jobslist);
	         return mv;
	       }
	       
	       
//	       

	       //downloading from here
 //downloading from here
	       @GetMapping("/download/{id}/{jobtitle}")
	       public ResponseEntity<byte[]> downloadBook(@PathVariable("id") int fileid,@PathVariable("jobtitle") String jobtitle) {
	           JobApplications job = recruiterService.ViewJobApplicationByID(fileid,jobtitle);

	           if (job != null) {
	               byte[] response = job.getBfileContent();
	               return ResponseEntity.ok()
	                       .contentType(MediaType.parseMediaType("application/pdf"))
	                       .header(
	                               HttpHeaders.CONTENT_DISPOSITION,
	                               "attachment; filename=\"" + fileid +".pdf"+ "\""
	                       )
	                       .body(response);
	           } else {
	               return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	           }
	       }

	       @GetMapping("viewalljobapplications")
	       public ModelAndView viewalljobapplications(HttpServletRequest request) {
	         ModelAndView mv=new ModelAndView("viewallapplications");
	         HttpSession session=request.getSession();
	         String companyname=(String)session.getAttribute("rcompanynmae");
	         List<JobApplications> jobslist=recruiterService.viewalljobapplicationsByCompany(companyname);
	         mv.addObject("jobslist", jobslist);
	         return mv;
	       }

	       
	       
	       
	       
	       @GetMapping("recruitersetstatus")
	       public ModelAndView recruitersetstatus(HttpServletRequest request, @RequestParam("id") int eid, @RequestParam("jobtitle") String jobtitle) {
	           ModelAndView mv = new ModelAndView("recruitersetstatusbyid");
	           HttpSession session = request.getSession();
	           JobApplications job = recruiterService.ViewJobApplicationByID(eid, jobtitle);
	           mv.addObject("jobslist", job);
	           return mv;
	       }

	       
	       
	       @PostMapping("setapplicationstatus")
	       public ModelAndView setapplicationstatus(HttpServletRequest request) {
	           ModelAndView mv = new ModelAndView();
	           String msg = null;
	           HttpSession session = request.getSession();
	          

	           try {
	               // Get 'id' parameter from the request
	               String idString = request.getParameter("id");

	               // Check if idString is not null and not empty before parsing
	               if (idString != null && !idString.isEmpty()) {
	                   int id = Integer.parseInt(idString);

	                   // Get other parameters from the request
	                   String companyname = request.getParameter("applicationStatus");
	                   String email = request.getParameter("comment");
	                   String tittle = request.getParameter("tittle");

	                   // Create a ViewApplicationStatus object
	                   ViewApplicationStatus viewApplicationStatus = new ViewApplicationStatus();
	                   viewApplicationStatus.setApplicationstatus(companyname);
	                   viewApplicationStatus.setComment(email);
	                   viewApplicationStatus.setId(id);
	                   viewApplicationStatus.setApplicationstatustittle(tittle);
	                   // Call the service method to set the status
	                   msg = recruiterService.setstatusofapplicant(viewApplicationStatus);

	                   // Set the view and message for success
	                   String companynameee=(String)session.getAttribute("rcompanynmae");
	      	         List<JobApplications> jobslist=recruiterService.viewalljobapplicationsByCompany(companynameee);
	      	         mv.addObject("jobslist", jobslist);
	                   mv.setViewName("viewallapplications");
	                   mv.addObject("message", msg);
	               } else {
	                   // Handle the case where 'id' is null or empty
	                   mv.setViewName("setapplicationstatusbyid");
	                   msg = "Invalid 'id' parameter.";
	                   mv.addObject("message", msg);
	               }
	           } catch (Exception e) {
	               // Handle any other exceptions
	               mv.setViewName("setapplicationstatusbyid");
	               msg = e.getMessage();
	               mv.addObject("message", msg);
	           }

	           return mv;
	       }
	       
	       
	       
//	       @GetMapping("viewmystatus")
//	       public ModelAndView viewmystatus( @RequestParam("id") int eid, @RequestParam(name = "jobtitle", required = false) String company) {
//	           ModelAndView mv = new ModelAndView("applicantviewmystatusbyid");     
//	           ViewApplicationStatus status = applicantService.byid(eid, company);
//	           mv.addObject("jobslist", status);
//	           return mv;
//	       }

	       
	       
	       
	       
	       
	       
	       
	       
//	       @PostMapping("addjob")
//	       public ModelAndView addajob(HttpServletRequest request, @RequestParam("companyimage") MultipartFile file)
//	               throws IOException, SerialException, SQLException {
//	           ModelAndView mv = new ModelAndView();
//	           String title = request.getParameter("jobtitle");
//	           String location = request.getParameter("location");
//	           String skills = request.getParameter("skills");
//	           String description = request.getParameter("description");
//	           String salary = request.getParameter("salary");
//	           String companyname = request.getParameter("companyname");
//
//	           byte[] bytes = file.getBytes();
//	           Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
//
//	           Job j = recruiterService.viewJobByTitleAndDescription(title, description);
//	           if (j == null) {
//	               Job job = new Job();
//	               job.setJobtitle(title);
//	               job.setLocation(location);
//	               job.setSkills(skills);
//	               job.setSalary(salary);
//	               job.setDescription(description);
//	               job.setImage(blob);
//	               job.setCompanyname(companyname);
//
//	               String msg = recruiterService.addjob(job);
//	               mv.setViewName("addjob");
//	               mv.addObject("msg", msg);
//
//	               Applicant a = new Applicant();
//	               
//	               // Fetch emails from the applicant_table
//	               List<String> applicantEmails = recruiterService.getAllApplicantEmails(a);
//
//	               // Send notification to each applicant
//	               for (String email : applicantEmails) {
//	                   sendJobNotificationEmail(email, title, companyname);
//	               }
//	           } else {
//	               mv.addObject("msg", "Failed to Add. This is Already Existing Job");
//	           }
//	           return mv;
//	       }

//	       // New method to send job notification email
//	       private void sendJobNotificationEmail(String toEmail, String jobTitle, String companyName) {
//	           try {
//	               String fromEmail = "your-email@example.com"; // Set your email
//	               String subject = "Job Notification: " + jobTitle + " at " + companyName;
//	               String text = "Hello,\n\nA new job has been added:\nTitle: " + jobTitle + "\nCompany: " + companyName;
//
//	               // You can use your email manager or any other method to send the email
//	               emailManager.sendEmail(fromEmail, toEmail, subject, text, text);
//	           } catch (Exception e) {
//	               // Handle exceptions, e.g., log or print an error message
//	               e.printStackTrace();
//	           }
//	       }
//	       private void sendJobNotificationEmail(String toEmail, String jobTitle, String companyName,HttpServletRequest request) {
//	           try {
//
//			String fileName = "job_notification.html"; 
//		            String filePath = request.getServletContext().getRealPath("/" + fileName);
//	                String fromEmail = "jfsdsdpams@gmail.com"; // Set your email
//
//	               String subject = "Job Notification: " + jobTitle + " at " + companyName;
//	               String text = "Hello,\n\nA new job has been added:\nTitle: " + jobTitle + "\nCompany: " + companyName;
//	               String htmlContent = new String(Files.readAllBytes(Paths.get(filePath)));
//	               // You can use your email manager or any other method to send the email
//	               emailManager.sendEmail(fromEmail, toEmail, subject, text, htmlContent);
//	           } catch (Exception e) {
//	               // Handle exceptions, e.g., log or print an error message
//	               e.printStackTrace();
//	           }
//	       }
	       
	     

	    	@PostMapping("addjob")
	    	public ModelAndView addajob(HttpServletRequest request, @RequestParam("companyimage") MultipartFile file)
	    	        throws IOException, SerialException, SQLException {
	    	    ModelAndView mv = new ModelAndView();
	    	    String title = request.getParameter("jobtitle");
	    	    String location = request.getParameter("location");
	    	    String skills = request.getParameter("skills");
	    	    String description = request.getParameter("description");
	    	    String salaryParameter = request.getParameter("salary");
	    	    int salary = Integer.parseInt(salaryParameter);
	    	    String companyname = request.getParameter("companyname");

	    	    String dateposted=request.getParameter("dateposted");
	    	    LocalDate parsedDate = LocalDate.parse(dateposted, DateTimeFormatter.ISO_DATE);
	            String formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	            
	            
	    	    
	    	    byte[] bytes = file.getBytes();
	    	    Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

	    	    Job j = recruiterService.viewJobByTitleAndDescription(title, description);
	    	    if (j == null) {
	    	        Job job = new Job();
	    	        job.setJobtitle(title);
	    	        job.setLocation(location);
	    	        job.setSkills(skills);
	    	        job.setSalary(salary);
	    	        job.setDescription(description);
	    	        job.setImage(blob);
	    	        job.setCompanyname(companyname);
	    	        job.setPosteddate(formattedDate);

	    	        String msg = recruiterService.addjob(job);
	    	        mv.setViewName("addjob");
	    	        mv.addObject("msg", msg);

	    	        Applicant a = new Applicant();

	    	        // Fetch emails from the applicant_table
	    	        List<String> applicantEmails = recruiterService.getAllApplicantEmails(a);

	    	        // Send notification to each applicant
	    	        for (String email : applicantEmails) {
	    	            sendJobNotificationEmail(email, title, companyname, request);
	    	        }
	    	    } else {
	    	        mv.addObject("msg", "Failed to Add. This is Already Existing Job");
	    	    }
	    	    return mv;
	    	}

	    	  private void sendJobNotificationEmail(String toEmail, String jobTitle, String companyName, HttpServletRequest request) {
		    	    try {
		    	        String fileName = "job_notification.html";
		    	        String filePath = request.getServletContext().getRealPath("/" + fileName); // Adjust the path as needed
		    	        String fromEmail = "your-email@example.com"; // Set your email

		    	        String subject = "Job Notification: " + jobTitle + " at " + companyName;
		    	        String text = "Hello,\n\nA new job has been added:\nTitle: " + jobTitle + "\nCompany: " + companyName;

//		    	        // Read email content from template file
//		    	        String htmlContent = new String(Files.readAllBytes(Paths.get(filePath)));
//
//		    	        // You can use your email manager or any other method to send the email
//		    	        emailManager.sendEmail(fromEmail, toEmail, subject, text, htmlContent);
		    	        String htmlContent = new String(Files.readAllBytes(Paths.get(filePath)))
		    	                .replace("[name]", toEmail)
		    	                .replace("[jobTitle]", jobTitle)
		    	                .replace("[companyName]", companyName)
		    	                .replace("[text2]", "Unlock your potential and seize the opportunity. Remember, great things never came from comfort zones. Apply now and embark on a journey to redefine your career success!");
		    	                

		    	        // Send the email
		    	        emailManager.sendEmail(fromEmail, toEmail, subject, text, htmlContent);
		    	    } catch (Exception e) {
		    	        // Handle exceptions, e.g., log or print an error message
		    	        e.printStackTrace();
		    	    }
		    	}
	       
	    	@GetMapping("setstatusacceptanceordeclined")
	        public ModelAndView setstatusacceptanceordeclinedseller(@RequestParam("id") int rid , @RequestParam("status") boolean rstatus ){
	    		
	            int n = adminService.recruiteracceptance(rid,rstatus);
	            ModelAndView mv = new ModelAndView();
	            mv.setViewName("adminviewallrecruiter");
	            List<Recruiter> recruiters = adminService.ViewAllRecruiters();
	 	       mv.addObject("recruiters", recruiters);
	            if(n>0)
	            {
	              mv.addObject("message", "Status Updated Successfully");
	            }
	            else
	            {
	              mv.addObject("message", "Failed to Update Status");
	            }
	            return mv;
	        }
	       
	    	
	    	
	    	
	    	
	    	
	    	
	    	@GetMapping("adminsearching")
	        public ModelAndView adminsearching(@RequestParam("companyname") String companyname , @RequestParam("salary")int salary ){
	    		
	            List<Job> searchedjobs=adminService.adminsearchbasedonsalary(companyname, salary);
	            ModelAndView mv = new ModelAndView();
	            mv.setViewName("adminsearchedjobsare");
	 	       mv.addObject("searchedjobs", searchedjobs);
	 	      List<Job> jobslist = adminService.ViewAllJobs();
	 	      Set<String> companyNamesSet = new HashSet<>();

		         // Extract company names from each Job object and add to the Set
		         for (Job job : jobslist) {
		             companyNamesSet.add(job.getCompanyname());
		         }

		         // Add the Set of company names to the ModelAndView
		         mv.addObject("companyNamesSet", companyNamesSet);
	 	     
		         mv.addObject("jobslist", jobslist);
	         
	            return mv;
	        }
	    	
	    	
	    	
	    	@GetMapping("setstatusacceptanceordeclinedforapplicant")
	        public ModelAndView setstatusacceptanceordeclinedforapplicant(@RequestParam("id") int rid , @RequestParam("status") boolean rstatus ){
	    		
	            int n = adminService.applicantacceptance(rid, rstatus);
	            ModelAndView mv = new ModelAndView();
	            mv.setViewName("adminviewallapplicants");
	            List<Applicant> applicants = adminService.ViewAllApplicant();
	 	       mv.addObject("applicants", applicants);
	            if(n>0)
	            {
	              mv.addObject("message", "Status Updated Successfully");
	            }
	            else
	            {
	              mv.addObject("message", "Failed to Update Status");
	            }
	            return mv;
	        }

	    	
	    	  
		     @GetMapping("viewallapplicants")
		     public ModelAndView viewallapplicants() {
		       ModelAndView mv=new ModelAndView("adminviewallapplicants");
		       List<Applicant> applicants = adminService.ViewAllApplicant();
		       mv.addObject("applicants", applicants);
		       return mv;
		     }
	    	
		  // Controller
		     @GetMapping("adminviewalljobs")
		     public ModelAndView viewalljobs() {
		         ModelAndView mv = new ModelAndView("adminviewalljobs");
		         List<Job> jobslist = adminService.ViewAllJobs();
		         
		         
		         
//		         List<Recruiter> companynames = adminService.getcompanynames();
//		         mv.addObject("companynames", companynames);
		         
		         Set<String> companyNamesSet = new HashSet<>();

		         // Extract company names from each Job object and add to the Set
		         for (Job job : jobslist) {
		             companyNamesSet.add(job.getCompanyname());
		         }

		         // Add the Set of company names to the ModelAndView
		         mv.addObject("companyNamesSet", companyNamesSet);
		         
		         
		          mv.addObject("jobslist", jobslist);
		         return mv;
		     }


		     // List<String> uniquecompanynames=adminService.findAllDistinctCompanyNames();
		     // mv.addObject("uniquecompanynames", uniquecompanynames);

		     
		     
		     @GetMapping("updateapplicationstatus") 
		     public ModelAndView updatestatusofApplication(@RequestParam("id") int id,@RequestParam("jobtitle") String jobtitle)
		     {
		       ModelAndView mv=new ModelAndView("recruitersetstatusbyid");
		       mv.addObject("id", id);
		       mv.addObject("jobtitle", jobtitle);
		       return mv;
		     }
		     
		     
//		     @PostMapping("addapplicationstatus")
//		     public ModelAndView addApplicationStatus(HttpServletRequest request)
//		     {
//		       ModelAndView mv=new ModelAndView("viewallapplications");
//		       
//		       HttpSession session=request.getSession();
//		         String companyname=(String)session.getAttribute("rcompanynmae");
//		         List<JobApplications> jobslist=recruiterService.viewalljobapplicationsByCompany(companyname);
//		         mv.addObject("jobslist", jobslist);
//		       
//		       int id=Integer.parseInt(request.getParameter("id"));
//		       String jobtitle=request.getParameter("jobtitle");
//		       String status=request.getParameter("applicationStatus");
//		       String comment=request.getParameter("comment");
//		       
//		       ViewApplicationStatus stat=new ViewApplicationStatus();
//		       stat.setId(id);
//		       stat.setApplicationstatus(status);
//		       stat.setApplicationstatustittle(jobtitle);
//		       stat.setComment(comment);
//		       
//		       String msg=recruiterService.addApplicationStatus(stat);
//		       mv.addObject("message", msg);
//		       
//		       return mv;  
//		     } 
//		     

		     
		     
		     @PostMapping("addapplicationstatus")
		     public ModelAndView addApplicationStatus(HttpServletRequest request) {
		         ModelAndView mv = new ModelAndView("viewallapplications");

		         HttpSession session = request.getSession();
		         String companyname = (String) session.getAttribute("rcompanynmae");
		         List<JobApplications> jobslist = recruiterService.viewalljobapplicationsByCompany(companyname);
		         mv.addObject("jobslist", jobslist);

		         int id = Integer.parseInt(request.getParameter("id"));
		         String jobtitle = request.getParameter("jobtitle");
		         String status = request.getParameter("applicationStatus");
		         String comment = request.getParameter("comment");

		         // Check if the record already exists
		         ViewApplicationStatus existingStatus = recruiterService.checkingduplicatestautsalreadythere(id, jobtitle, status);

		         if (existingStatus != null) {
		             // Record already exists, show a message indicating it's already updated
		             mv.addObject("message", "Application status already updated for this ID, Job Title, and Status");
		         } else {
		             // Record does not exist, proceed with insertion
		             ViewApplicationStatus stat = new ViewApplicationStatus();
		             stat.setId(id);
		             stat.setApplicationstatus(status);
		             stat.setApplicationstatustittle(jobtitle);
		             stat.setComment(comment);

		             String msg = recruiterService.addApplicationStatus(stat);
		             mv.addObject("message", msg);
		         }

		         return mv;
		     }

		     
		     @GetMapping("getApplicationStatus")
		        public ModelAndView getApplicationsStatus(@RequestParam("id") int id,
		            @RequestParam("jobtitle") String jobtitle,@RequestParam("jobid") int jobid) 
		        {
		          ModelAndView mv=new ModelAndView("myApplicationstatus");
		          System.err.println(id+" "+jobtitle);
		          
		          List<ViewApplicationStatus> statuslist=applicantService.viewmyjobapplicationStatus(id,jobtitle);
		          System.out.println(statuslist.size());
		          mv.addObject("statuslist", statuslist);
		          mv.addObject("jobtitle", jobtitle);  
		          mv.addObject("jobid", jobid);  
		          return mv;
		          
		        }
		     
		     @GetMapping("recruitergetApplicationStatus")
		        public ModelAndView recruitergetApplicationStatus(@RequestParam("id") int id,
		            @RequestParam("jobtitle") String jobtitle,@RequestParam("jobid") int jobid) 
		        {
		          ModelAndView mv=new ModelAndView("recruiterviewapplicatstatus");
		          System.err.println(id+" "+jobtitle);
		          
		          List<ViewApplicationStatus> statuslist=applicantService.viewmyjobapplicationStatus(id,jobtitle);
		          System.out.println(statuslist.size());
		          mv.addObject("statuslist", statuslist);
		          ViewApplicationStatus[] statusArray = statuslist.toArray(new ViewApplicationStatus[0]);
		          System.err.println(statusArray.length);
		          mv.addObject("statusArray", statusArray);
		          
		          mv.addObject("jobtitle", jobtitle);  
		          mv.addObject("jobid", jobid);  
		          return mv;
		          
		        }
		     
		     @GetMapping("updateprofileApplicant")
		      public ModelAndView applicantupdateprofile(HttpServletRequest request) {
		        ModelAndView mv=new ModelAndView("applicantupdateprofile");
		        HttpSession session=request.getSession();
		        int id=(int)session.getAttribute("cid");
		        Applicant app=applicantService.getApplicantById(id);
		        mv.addObject("app", app);
		        return mv;
		        
		        
		      }
		      
		      
		      @PostMapping("update")
		      public String updateprofile(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		        ModelAndView mv=new ModelAndView("applicantupdateprofile");
		        HttpSession session =request.getSession();
		        int id=(int)session.getAttribute("cid");
		        String email=request.getParameter("email");
		        String fname=request.getParameter("firstname");
		        String lname=request.getParameter("lastname");
		        String contact=request.getParameter("contactnumber");
		        String address=request.getParameter("address");
		        String password=request.getParameter("password");
		        Applicant app=new Applicant();
		        app.setAddress(address);
		        app.setContactno(contact);
		          app.setEmail(email);
		          app.setFirstname(fname);
		          app.setId(id);
		          app.setPassword(password);
		          app.setLastname(lname);
		          String msg=applicantService.ApplicantupdateProfile(app);
		          mv.addObject("message", msg);
		          redirectAttributes.addFlashAttribute("message", msg);

		        return "redirect:/ApplicantLogin";
		            
		      }
		     
		        
		      @GetMapping("searchbycompany")
		      public ModelAndView viewallcompanies(@RequestParam("companyname") String companyname) {
		          ModelAndView mv = new ModelAndView("viewjobsbycompanyname");

		          List<Job> jobslist1 = adminService.ViewAllJobs();
		          Set<String> companyNamesSet = new HashSet<>();

		          // Extract company names from each Job object and add to the Set
		          for (Job job : jobslist1) {
		              companyNamesSet.add(job.getCompanyname());
		          }

		          mv.addObject("reclist", companyNamesSet);

		          if ("All".equals(companyname)) {
		              List<Job> jobslist = recruiterService.ViewAllJobs();
		              mv.addObject("jobslist", jobslist);
		          } else {
		              List<Job> jobslist = applicantService.viewJobsByCompanyName(companyname);
		              mv.addObject("jobslist", jobslist);
		          }

		          return mv;
		      }

		      @GetMapping("searchbydate")
		      public ModelAndView viewallcompaniesbydate(@RequestParam("fromdate") String fromdate) 
		      {
		        ModelAndView mv=new ModelAndView("viewalljobsbydate");
		        List<Recruiter> companylist=applicantService.viewallCompanies();
		            mv.addObject("reclist", companylist);
		           System.err.println(fromdate);
		           LocalDate parsedDate = LocalDate.parse(fromdate, DateTimeFormatter.ISO_DATE);
		          String formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		          List<Job> jobslist = applicantService.viewAllJobsByDate(formattedDate);
		        mv.addObject("jobslist", jobslist);
		        System.err.println(jobslist);
		        return mv;
		        
		      }

		      @GetMapping("searchbycompanynameberforelogin")
		      public ModelAndView searchbycompanynameberforelogin(@RequestParam("companyname") String companyname) {
		          ModelAndView mv = new ModelAndView("viewjobsinhomebycompanyname");

		          List<Job> jobslist1 = adminService.ViewAllJobs();
		          Set<String> companyNamesSet = new HashSet<>();

		          // Extract company names from each Job object and add to the Set
		          for (Job job : jobslist1) {
		              companyNamesSet.add(job.getCompanyname());
		          }

		          mv.addObject("reclist", companyNamesSet);

		          if ("All".equals(companyname)) {
		              List<Job> jobslist = recruiterService.ViewAllJobs();
		              mv.addObject("jobslist", jobslist);
		          } else {
		              List<Job> jobslist = applicantService.viewJobsByCompanyName(companyname);
		              mv.addObject("jobslist", jobslist);
		          }

		          return mv;
		      }
		      @GetMapping("viewalljobs")
		      public ModelAndView homejobs() {
		        ModelAndView mv=new ModelAndView("viewjobsinhome");
		        List<Job> jobslist = recruiterService.ViewAllJobs();
		        mv.addObject("jobslist", jobslist);
		           
		        List<Job> jobslist1 = adminService.ViewAllJobs();
		          Set<String> companyNamesSet = new HashSet<>();

		          // Extract company names from each Job object and add to the Set
		          for (Job job : jobslist1) {
		              companyNamesSet.add(job.getCompanyname());
		          }

		          mv.addObject("reclist", companyNamesSet);
		        
		        
		        return mv;
		      }
		      
		      
		      
		      
		      
		      
			     @PostMapping("contactusform")
			     public ModelAndView contactus(HttpServletRequest request)
			     {
			       ModelAndView mv=new ModelAndView("contactus");
			       
			       HttpSession session=request.getSession();
			       
			       String name=request.getParameter("name");
			       String email=request.getParameter("email");
			       String msg=request.getParameter("message");
			       String subject=request.getParameter("subject");
			       
			       ContactForm contactus=new ContactForm();
			       
			       contactus.setEmail(email);
			       contactus.setName(name);
			       contactus.setMessage(msg);
			       contactus.setSubject(subject);
			       
			       String msgg=applicantService.contactusform(contactus);
			       
			       
			       contactusform.sendEmailToManager(email, name, subject, msg);
			       return mv;  
			     } 	      
		      
}
