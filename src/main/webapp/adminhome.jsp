<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>TalentForge ${uname} Home</title>
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css'><link rel="stylesheet" href="/css/style.css">
<head>
  <!-- Existing head content -->

  <script>
    document.addEventListener('copy', function (e) {
      e.preventDefault();
    });

    document.addEventListener('cut', function (e) {
      e.preventDefault();
    });

    document.addEventListener('paste', function (e) {
      e.preventDefault();
    });
  </script>
  


<style>
   body {
            display: flex;
            justify-content: space-between;
            height: 120vh;
            margin: 0;
        }
       
       
       
        .card {
            width: 200px;
            height: 150px;
            background-color: lightblue;
            border-radius: 10px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            position: relative;
            cursor: pointer;
            margin: 35px; /* Add some margin between cards */
        }

        .count {
            font-size: 60px;
            font-weight: bold;
        }
       

        /* Hover effect */
        .card:hover {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transform: scale(1.05);
            transition: box-shadow 0.3s ease-in-out, transform 0.3s ease-in-out;
            background-color: lightpink;
            
        }
</style>
</head>
<body>
<nav>
  <div class="wrapper">
    <div class="logo"><p style="color: white; font-size: 24px;"> <a href="adminhome"> 𝑻𝒂𝒍𝒆𝒏𝒕𝑭𝒐𝒓𝒈𝒆</a> <img src="/images/search.png" width="30px" height="30" /></p>
</div>
    <input type="radio" name="slider" id="menu-btn">
    <input type="radio" name="slider" id="close-btn">
    <ul class="nav-links">
      <label for="close-btn" class="btn close-btn"><i class="fas fa-times"></i></label>
      <li><a href="adminhome">Home</a></li>
      <li><a href="viewallapplicants">View All Applicants</a></li>
            <li><a href="viewallrecruiters">View All Companies</a></li>
      
      <li>
        <a href="" class="desktop-item">Actions</a>
        <input type="checkbox" id="showDrop">
        <label for="showDrop" class="mobile-item">Dropdown Menu</label>
        <ul class="drop-menu">
          <li><a href="adminviewalljobs">VIEW ALL JOBS</a></li>
          <li><a href="">JOB APPLICATIONS</a></li>
        
        </ul>
      </li>
      
      <li><a href="admin">Logout</a></li>
    </ul>
    <label for="menu-btn" class="btn menu-btn"><i class="fas fa-bars"></i></label>
  </div>
</nav>

 <div class="column">
        <div class="card" id="registerCard1">
            <span class="count" id="applicantCount">0</span>
         <span class="label" id="applicantCount">Total Applicants</span>
        </div>
        <div class="card" id="registerCard1">
           <span class="count" id="recruiterCount">0</span>
         <span class="label" id="recruiterCount">Total Applicants</span>
        </div>
        <!-- Add more cards as needed -->
    </div>
    <div class="body-text">
  <div class="title"><h3>Welcome</h3><h1 id="username"></h1></div>
  <div class="sub-title"> </div>
</div>

    <div class="column">
        <div class="card" id="registerCard2">
           <span class="count" id="jobsCount">0</span>
        <span class="label">Total Jobs Posted</span>
        </div>
        <!-- Add more cards as needed -->
 <div class="card" id="registerCard2">
           <span class="count" id="jobApplicationsCount">0</span>
        <span class="label">Total Jobs Applied</span>
        </div>
    </div>
<!-- partial -->
   <script>
    const uname = "${uname}"; // Replace with the actual username
    const usernameElement = document.getElementById('username');

    // Function to display each letter with a delay
    function typeText(text, index) {
      if (index < text.length) {
        usernameElement.innerHTML += text.charAt(index);
        setTimeout(() => typeText(text, index + 1), 100);
      }
    }

    // Call the function with the username
    typeText(uname, 0);
  </script>
  <script>
      // Set the target counts
      const targetApplicantCount = ${applicantCount};
      const targetRecruiterCount = ${recruiterCount};
      
      
  
      // Function to update counts
      function updateCounts() {
          const applicantCountElement = document.getElementById('applicantCount');
          const recruiterCountElement = document.getElementById('recruiterCount');
  
          // Get the current counts
          let currentApplicantCount = parseInt(applicantCountElement.textContent);
          let currentRecruiterCount = parseInt(recruiterCountElement.textContent);
  
          // Increment counts until they reach the target
          if (currentApplicantCount < targetApplicantCount) {
              currentApplicantCount++;
              applicantCountElement.textContent = currentApplicantCount;
          }
  
          if (currentRecruiterCount < targetRecruiterCount) {
              currentRecruiterCount++;
              recruiterCountElement.textContent = currentRecruiterCount;
          }
  
          // Repeat the update every second (1000 milliseconds)
          if (currentApplicantCount < targetApplicantCount || currentRecruiterCount < targetRecruiterCount) {
              setTimeout(updateCounts, 120);
          }
      }
  
      // Start updating counts
      updateCounts();
   </script>
<script>
    // Set the target counts for jobs
    const targetJobsCount = ${jobsCount};
    const targetJobApplicationsCount = ${jobApplicationsCount};

    // Function to update job counts
    function updateJobCounts() {
        const jobsCountElement = document.getElementById('jobsCount');
        const jobApplicationsCountElement = document.getElementById('jobApplicationsCount');

        // Get the current job counts
        let currentJobsCount = parseInt(jobsCountElement.textContent);
        let currentJobApplicationsCount = parseInt(jobApplicationsCountElement.textContent);

        // Increment counts until they reach the target
        if (currentJobsCount < targetJobsCount) {
            currentJobsCount++;
            jobsCountElement.textContent = currentJobsCount;
        }

        if (currentJobApplicationsCount < targetJobApplicationsCount) {
            currentJobApplicationsCount++;
            jobApplicationsCountElement.textContent = currentJobApplicationsCount;
        }

        // Repeat the update every second (1000 milliseconds)
        if (currentJobsCount < targetJobsCount || currentJobApplicationsCount < targetJobApplicationsCount) {
            setTimeout(updateJobCounts, 120);
        }
    }

    // Start updating job counts
    updateJobCounts();
</script>
</body>
</html>
    