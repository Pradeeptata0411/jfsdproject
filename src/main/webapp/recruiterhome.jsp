<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="jakarta.tags.core" prefix="c" %>	
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>TalentForge Recruiter Home</title>
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css'><link rel="stylesheet" href="/css/style.css">

</head>
<body>
<nav>
  <div class="wrapper">
    <div class="logo"><p style="color: white; font-size: 24px;"> <a href="recruiterhome"> 𝑻𝒂𝒍𝒆𝒏𝒕𝑭𝒐𝒓𝒈𝒆</a> <img src="/images/search.png" width="30px" height="30" /></p>
</div>
    <input type="radio" name="slider" id="menu-btn">
    <input type="radio" name="slider" id="close-btn">
    <ul class="nav-links">
      <label for="close-btn" class="btn close-btn"><i class="fas fa-times"></i></label>
      <li><a href="recruiterhome">Home</a></li>
      <li><a href="postajob">Post a Job</a></li>
      <li>
        <a href="#" class="desktop-item">Actions</a>
        <input type="checkbox" id="showDrop">
        <label for="showDrop" class="mobile-item">Dropdown Menu</label>
        <ul class="drop-menu">
          <li><a href="recruiterviewjobs">view jobs</a></li>
          <li><a href="viewalljobapplications">Job Applications</a></li>
        
        </ul>
      </li>
      <li><a href="#">logout</a></li>
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
    