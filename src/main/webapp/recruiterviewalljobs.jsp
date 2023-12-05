<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>TalentForge Recruiter Home</title>
  <link rel="shortcut icon" type="image/png" href="/images/apple-touch-icon.png"/>
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css'><link rel="stylesheet" href="/css/style.css">
	<style>
	
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
	background-color: skyblue;
	padding: 5px 16px;
	font-size: 12px;
	cursor: pointer;
}
/* Style for the delete button */
.delete-button {
    display: inline-block;
    padding: 8px 16px;
    background-color: #e74c3c; /* Use your preferred color */
    color: #fff;
    text-decoration: none;
    border-radius: 4px;
    transition: background-color 0.3s ease;
}

/* Hover effect */
.delete-button:hover {
    background-color: #c0392b; /* Darker shade for hover effect */
}
/* Style for the confirmation modal */
.modal {
    display: none;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
}

.modal-content {
    position: absolute;
    top: 50%;
    left: 50%;
    font-size:20px;
    transform: translate(-50%, -50%);
    background-color: #fff;
    padding: 100px;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}

/* Add more styling based on your design preferences */

/* Style for the buttons with spacing between them */
.modal-content button {
    text-align:center;
    margin-right: 20px;
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
       <li><a href="recruiterviewjobs">view jobs</a></li>
      <li><a href="viewalljobapplications">Job Applications</a></li>
      
      <li><a href="#">logout</a></li>
    </ul>
    <label for="menu-btn" class="btn menu-btn"><i class="fas fa-bars"></i></label>
  </div>
</nav>
<br>
<br>
<br>
<br>
<br>
<table id="employee">
			<tr bgcolor="black" style="color: black">
				<th>Job ID</th>
				<th>Job Title</th>
				<th>Company</th>
				<th>Description</th>
				<th>Salary</th>
				
				<th>Action</th>
				
				
			</tr>
			<c:forEach items="${jobsbyname}" var="job">
				<tr>
					<td><c:out value="${job.id}" /></td>
					<td><c:out value="${job.jobtitle}" /></td>
				    <td><img src='displaycompanyimage?id=${job.id}' alt="Company Logo" height="45px" width="80px"></td>
					
					<td><c:out value="${job.description}" />
					</td>
					<td><c:out value="${job.salary}" /></td>
					<!-- Add this modal at the end of your HTML body -->
					<div id="confirmationModal" class="modal">
					    <div class="modal-content">
					        <p>Are you sure you want to delete this job?</p>
					        <button id="confirmDelete">Delete</button>
					        <button id="cancelDelete">Cancel</button>
					    </div>
					</div>
					
					<!-- Your table cell with the delete link -->
					<!-- Your table cell with the delete link -->
						<td>
						    <a href="#" class="delete-button" onclick="showConfirmation(${job.id}); return false;">Delete</a>
						</td>


				</tr>
			</c:forEach>
		</table>
<!-- partial -->
</body>
<script>

function showConfirmation(jobId) {
    var modal = document.getElementById('confirmationModal');
    var confirmBtn = document.getElementById('confirmDelete');
    var cancelBtn = document.getElementById('cancelDelete');

    // Event listener for the confirm button
    confirmBtn.onclick = function () {
        window.location.href = 'deletejob?id=' + jobId;
    };

    // Event listener for the cancel button
    cancelBtn.onclick = function () {
        modal.style.display = 'none';
    };

    modal.style.display = 'block';

    // Event listener to close the modal if clicked outside of the modal content
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = 'none';
        }
    };
}


</script>
</html>
    