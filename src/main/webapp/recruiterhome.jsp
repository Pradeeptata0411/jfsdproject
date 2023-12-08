<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="jakarta.tags.core" prefix="c" %>	
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>TalentForge | Recruiter Home</title>
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css'><link rel="stylesheet" href="/css/style.css">
<link rel="shortcut icon" type="image/png" href="/images/apple-touch-icon.png"/>
<style>
  
   .navlogo{
       margin-top: 19px;
       }
</style>
</head>
<body>

<nav>
  <div class="wrapper">
   <p > <a href="recruiterhome"> <img src="/images/greennavlogo.png" class="navlogo" width="180px" height="80" /></a></p>
   
    <input type="radio" name="slider" id="menu-btn">
    <input type="radio" name="slider" id="close-btn">
    <ul class="nav-links">
      <label for="close-btn" class="btn close-btn"><i class="fas fa-times"></i></label>
     <li><a href="recruiterhome"><i class="fa fa-home">  HOME</i></a></li> 
      <li><a href="postajob"><i class="fa fa-suitcase">  POST A JOB</i></a></li>
     <li><a href="recruiterviewjobs"><i class="fa fa-eye" > VIEW ALL JOBS</i></a></li>
      <li><a href="viewalljobapplications"><i class="fa fa-tasks">&nbsp; JOB APPLICATIONS</i> </a></li>
      <li><a href="companylogin"><i class="fa fa-power-off"></i> LOGOUT</a></li>
      
     
    </ul>
    <label for="menu-btn" class="btn menu-btn"><i class="fas fa-bars"></i></label>
  </div>
</nav>

<br>
<br>
<br>
<br>
<!-- partial -->
  <div class="body-text">
  <div class="title"><h3>Welcome</h3><h1 id="username" style="font-style: oblique;"></h1></div>
   <div class="sub-title"> </div>
</div>

</body>
<script>
    const uname = "${rcompanynmae}😊😊"; // Replace with the actual username
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
</html>
    