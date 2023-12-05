<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <link rel="shortcut icon" type="image/png" href="/images/apple-touch-icon.png"/>
  <title>Admin Viewall Job's</title>
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css'><link rel="stylesheet" href="/css/style.css">
<style>

 .button {
  display: inline-block;
  padding: 10px 20px;
  font-size: 16px;
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  border: none;
  border-radius: 5px;
  background-color: sky	blue;
}
	
#employee {
	font-family: Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#employee td, #employee th {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: center;
}

#employee tr:nth-child(even) {
	background-color: white;
}

#employee tr:hover {
	background-color: #ddd;
}

#employee th {
	padding-top: 12px;
	padding-bottom: 12px;
	background-color: skyblue;
	color: black;
}

.btn {
	 display: inline-block;
  padding: 10px 20px;
  font-size: 16px;
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  border: none;
  border-radius: 5px;
  background-color: lightgreen;
}
.btnr {
	 display: inline-block;
  padding: 10px 20px;
  font-size: 16px;
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  border: none;
  border-radius: 5px;
  background-color: #D96060;
}
.container {
            max-width: 800px;
            margin: 0 auto;
            margin-top: 30px;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }
        
       .profile-info {
    text-align: center;
    margin-bottom: 20px;

}


        .profile-pic {
            width: 150px;
            height: 70px;

            
        }

        .profile-name {
            font-size: 34px;
            margin-top: 10px;
        }

        .profile-location {
            color: grey;
            margin-top: 5px;
        }
        .navlogo{
       margin-top: 19px;
       }
</style>
</head>
<body>
<nav>
  <div class="wrapper">
     <p > <a href="adminhome"> <img src="/images/greennavlogo.png" class="navlogo" width="180px" height="80" /></a></p>
    <input type="radio" name="slider" id="menu-btn">
    <input type="radio" name="slider" id="close-btn">
    <ul class="nav-links">
      <label for="close-btn" class="btn close-btn"><i class="fas fa-times"></i></label>
       <li><a href="adminhome"><i class="fa fa-home">  HOME</i></a></li> 
      <li><a href="viewallapplicants"><i class="fa fa-eye" > ALL APPLICANTS</a></i></li>
      <li><a href="viewallrecruiters"><i class="fa fa-eye" > ALL COMPANIES</a></i></li>
      <li><a href="adminviewalljobs"><i class="fa fa-list-alt""> ALL JOBS</i></a></li>
      
       <li><a href="viewqueries">Queries</a></li>
      
         <li><a href="admin"><i class="fas fa-sign-in-alt" > &nbsp;LOGOUT</i></a></li>
    </ul>
    <label for="menu-btn" class="btn menu-btn"><i class="fas fa-bars"></i></label>
  </div>
</nav>

<br>
<br>
<br>
<br>
<br>
 <h5 align="center" style="color:red">${message}</h5>
 
 <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3"> <!-- Centered column -->
                <!-- Left Column for Profile Details -->
                <div class="profile-info">
                    <img  src="displaycompanyimage?id=${j.id}" alt="Profile Picture" width="20%" height="100%">
                    <div class="profile-name">Role :- ${j.jobtitle}</div>
                    <div class="profile-location">Location :-${j.location}</div>
                    <div class="profile-location">Skill :-${j.skills}</div>
                    <div class="profile-location">Description :-${j.description}</div>
                    <div class="profile-location">Salary :-${j.salary}</div>
                   <div class="boxed-container">
				  
				</div><br>
                </div>
            </div>
        </div>
    </div>
    <br>
    <br>
    
 
 
</body>
</html>
    