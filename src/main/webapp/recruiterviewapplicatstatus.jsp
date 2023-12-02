<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>TalentForge Recruiter Home</title>
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css'><link rel="stylesheet" href="/css/style.css">
<style>
.job-card {
      position:relative;
      background-color: #fff;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      padding: 8px;
      display: flex;
      flex-direction: column;
      gap: 2px;	
      width: 1000px;
    }

    .job-card img {
      max-width: 200px;
      max-height: 100px;
      border-radius: 0%;
    }

    .workflow {
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 20px;
    }

    .stage {
      position: relative;
      width: 100px;
      text-align: center;
      margin-bottom: 20px; /* Added margin for spacing between stages */
    }

    .tick, .line {
      position: absolute;
      top: 180%;
      transform: translateY(-50%);
    }

    .tick {
      left: 0;
      width: 30px;
      height: 30px;
      border-radius: 50%;
      border: 2px solid #fff;
      color: #fff;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    .tick.failed {
      background-color: red; /* Color for failed status */
    }

    .line {
      left: 30px;
      height: 2px;
      width: 350px;
      background-color: #ccc;
    }

    @media (max-width: 768px) {
      .workflow {
        flex-direction: column;
        align-items: flex-start;
      }
    }
    .container {
    max-width: 800px;
    margin: 20px auto;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.status-list {
    list-style: none;
    padding: 0;
}

.status-list li {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px solid #ddd;
    padding: 10px 0;
}

.applicant-name {
    flex: 1;
}

.status-icon {
    width: 20px;
    height: 20px;
    background-size: cover;
}

.approved {
    background-image: url('path-to/approved-icon.png'); /* Replace with the actual path to your approved icon */
}

.rejected {
    background-image: url('path-to/rejected-icon.png'); /* Replace with the actual path to your rejected icon */
}
</style>
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


<!-- partial -->
<br>
<br>
<br>
<br>
<br>
<br>
 <div class="job-card">
  <img src='displaycompanyimage?id=${jobid}' alt="Company Logo" height="45px" width="80px">
  <h2 style="color: darkblue"><i>Your Job Application Status for Role *${jobtitle} :</i> </h2>
  <c:forEach items="${statuslist}" var="status">
    ✅ <c:out value="${status.applicationstatus}"></c:out><br>
    🔻<br>
  </c:forEach>
  &nbsp;&nbsp;⋮<br>
  🏆
</div>
<br>
<br>
<br>

<div class="container">
        <h2>Application Status for Job: ${jobtitle}</h2>
        <ul class="status-list">
            <!-- Loop through statusArray and populate the list -->
            <c:forEach var="status" items="${statuslist}">
                <li>
                    <span class="applicant-name">${status.applicationstatus}</span>
                    <span class="status-icon ${status.applicationstatus == 'Approved' ? 'approved' : 'rejected'}"></span>
                </li>
            </c:forEach>
        </ul>
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
    