<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>TalentForge | Admin Home</title>
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css'><link rel="stylesheet" href="/css/adminstyle.css">
    <link rel="shortcut icon" type="image/png" href="/images/apple-touch-icon.png"/>

</head>
<style>
   .background-container {
            background-image: url('/images/recruiter.png');
             background-size: cover;
		    background-position: center;
		    background-repeat: no-repeat;
		    height: 100vh;
		    display: flex;
		    justify-content: center;
            width: 100%;
            
          
        }
   body {
            display: flex;
            justify-content: space-between;
            height: 120vh;
            margin: 0;
        }
       

        .column {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

        .card1 {
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
            font-size: 2em;
            font-weight: bold;
        }

        .label {
            margin-top: 10px;
        }

        /* Hover effect */
        .card:hover {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transform: scale(1.05);
            transition: box-shadow 0.3s ease-in-out, transform 0.3s ease-in-out;
            background-color: lightpink;
            
        }


</style>
<body>
<nav>
  <div class="wrapper">
  <p > <a href="adminhome"> <img src="/images/adminnavlogo.jpg" width="200px" height="93" /></a></p>

   
    <ul class="nav-links">
      <label for="close-btn" class="btn close-btn"><i class="fas fa-times"></i></label>
      <li><a href="adminhome"><i class="fa fa-home">  HOME</i></a></li> 
      <li><a href="viewallapplicants"><i class="fa fa-eye" > ALL APPLICANTS</a></i></li>
      <li><a href="viewallrecruiters"><i class="fa fa-eye" > ALL COMPANIES</a></i></li>
      <li><a href="adminviewalljobs"><i class="fa fa-list-alt""> ALL JOBS</i></a></li>
       <li><a href="viewqueries"><i class="fa fa-question-circle"></i>&nbsp;QUERIES</a></li>   
      <li><a href="admin"><i class="fas fa-sign-in-alt" > &nbsp;LOGOUT</i></a></li>
    </ul>
    <label for="menu-btn" class="btn menu-btn"><i class="fas fa-bars"></i></label>
  </div>
</nav>

</div>
  <div class="background-container">
 
 
 <div class="column">
        <div class="card1" id="registerCard1">
            <span class="count">${applicantCount}</span>
            <span class="label">Total Applicants</span>
        </div>
        <div class="card1" id="registerCard1">
            <span class="count">${recruiterCount}</span>
            <span class="label">Total Companies</span>
        </div>
        <!-- Add more cards as needed -->
    </div>
    <br><br><br><br>
    
  
      <div class="column">
        <div class="card1" id="registerCard2">
            <span class="count">${jobsCount}</span>
            <span class="label">Total Jobs Posted</span>
        </div>
        <!-- Add more cards as needed -->
         <div class="card1" id="registerCard2">
            <span class="count">${jobApplicationsCount}</span>
            <span class="label">Total Jobs Applied</span>
        </div>
    </div></div>
</body>
</html>
    