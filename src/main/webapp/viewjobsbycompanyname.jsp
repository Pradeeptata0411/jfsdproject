<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>TalentForge</title>


  <style>
  #searchInput {
  max-width:160px; /* Set the width of the input */
  padding: 10px; /* Add padding for better spacing */
  border: 1px solid #ccc; /* Add a border for a defined boundary */
  border-radius: 5px; /* Add rounded corners */
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1); /* Add a subtle box shadow for depth */
  font-size: 26px; /* Set the font size */
  transition: all 0.3s ease; /* Add a smooth transition effect */
}

#searchInput::placeholder {
  color: #999;
  transition: all 0.3s ease;
}

#searchInput.typing::placeholder,
#searchInput:focus::placeholder {
  opacity: 0;
  transform: translateX(-100%);
}
  #toggle-btn {
  display: none; /* Initially hide the toggle button */
}
  
  .my-form {
    background: #f4f4f4;
    padding: 20px;
    border-radius: 8px;
}

.my-form .form-group {
    margin-bottom: 15px;
}
.my-form select option {
    padding: 15px;
    font-size: 15px;
}
.my-form select {
 font-size: 15px;
    width: 100%; /* Set the width of the dropdown to 100% */
}
.my-form label {
    margin-bottom: 15px;
    font-weight: bold;
    display: inline-block; /* Display label and input on the same line */
    
}

.my-form input,
.my-form select {
    width: 41%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    margin-left:10px;
}

.my-form button {
    background-color: #778A35;
    color: #fff;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.my-form button:hover {
    background-color: green;
}
 

   .card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  max-width: 300px;
  margin: auto;
  text-align: center;
  font-family: arial;
}


.my-form1 {
    background: #f4f4f4;
    padding: 20px;
    border-radius: 8px;
}

.my-form1 .form-group {
    margin-bottom: 15px;
}
.my-form1 select option {
    padding: 15px;
    font-size: 15px;
}
.my-form1 select {
 font-size: 15px;
    width: 100%; /* Set the width of the dropdown to 100% */
}
.my-form1 label {
    margin-bottom: 15px;
    font-weight: bold;
    display: inline-block; /* Display label and input on the same line */
    
}

.my-form1 input,
.my-form1 select {
    width: 18%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    margin-left:10px;
}

.my-form1 button {
    background-color: #778A35;
    color: #fff;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.my-form1 button:hover {
    background-color: green;
}
  

.title {
  color: grey;
  font-size: 18px;
}
    body {
      margin: 0;
      padding: 0;
      font-family: Arial, sans-serif;
      background-color: white;
      display: flex;
      height: 100vh;
    }

    #container {
      display: flex;
      width: 100%;
    }

    #left-half {
      flex: 0 0 80%;
      overflow-y: auto;
      padding: 20px;
      box-sizing: border-box;
      display: flex;
      flex-direction: column;
      gap: 20px;
      max-height: 100vh; /* Set a max height to enable scrolling */
    }

    #search-bar {
      position: fixed;
      top: 0;
      left: 0;
      width: 99%;
      background-color: white;
      z-index: 1000;
      display: flex;
      align-items: center;
      padding: 10px;
      border-bottom: 1px solid #ccc;
    }

    #search-bar input {
      flex: 1;
      padding: 8px;
      font-size: 16px;
      width:80px;
    }

    #search-bar button {
      padding: 8px 16px;
      background-color: #4caf50;
      color: #fff;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
    .btn{
      padding: 4px 6px;
      background-color: #778A35;
      color: #fff;
      border: none;
      border-radius: 100px;
      cursor: pointer;
    }

    .job-card {
      background-color: #fff;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      padding: 8px;
      display: flex;
      flex-direction: column;
      gap: 2px;	
    }

    .job-card img {
      max-width: 200px;
      max-height: 100px;
      border-radius: 0%;
    }



     #right-half {
      flex: 0 0 10%;
      background-color: #F9F9FB;
      color: blue;
      display: flex;
      flex-direction: column;
      align-items: center;
      padding: 15px;
      box-sizing: border-box;
        width: 100%;
    }

    nav {
      display: flex;
      flex-direction: column;
      gap: 20px;
      margin-top: auto; /* Push the nav items to the bottom */
    }

    nav a {
      color: black;
      text-decoration: none;
      font-weight: bold;
    }
  
  
     .apply-btn {
  display: inline-block;
  background-color: #778A35;
  color: #fff;
  padding: 8px 12px;
  border: none;
  border-radius: 4px;
    text-align: center; /* Center the text */
  
  text-decoration: none; /* Remove underline */
  cursor: pointer;
}

.apply-btn:hover {
  background-color: #097969; /* Change color on hover if desired */
}
.upload-btn {
  display: inline-block;
  background-color: #28A745; /* Adjust the color as needed */
  color: #fff;
  padding: 8px 12px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}


   #profileimage {
        width: 100%;
        height: 200px;
        border-radius: 50%; /* This sets the border-radius to 50% to make it a circle. You can adjust this value as needed. */
    }
    @media screen and (max-width: 768px) {
/* Style for the search input */
#searchInput {
  max-width:160px; /* Set the width of the input */
  padding: 10px; /* Add padding for better spacing */
  border: 1px solid #ccc; /* Add a border for a defined boundary */
  border-radius: 5px; /* Add rounded corners */
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1); /* Add a subtle box shadow for depth */
  font-size: 26px; /* Set the font size */
  transition: all 0.3s ease; /* Add a smooth transition effect */
}

#searchInput::placeholder {
  color: #999;
  transition: all 0.3s ease;
}

#searchInput.typing::placeholder,
#searchInput:focus::placeholder {
  opacity: 0;
  transform: translateX(-100%);
}
.verification-container {
      display: flex;

      align-items: center;
    }

    .verified-tick {
      width: 40px;
      height: 40px;
      background-color: #2ecc71; /* Green color */
      border-radius: 50%;
      display: flex;
      align-items: center;
      justify-content: center;
      margin-right: 10px;
    }

    .tick-icon {
 
      width: 25px;
      height: 25px;
      fill: #fff; /* White color */
    }

    .verification-text {
      font-size: 18px;
      color: #333; /* Dark color */
      line-height: 40px; /* Adjust line-height to match the height of the circle */
    }
  .card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  max-width: 300px;
  margin: auto;
  text-align: center;
  font-family: arial;
}

.title {
  color: grey;
  font-size: 18px;
}
    body {
      margin: 0;
      padding: 0;
      font-family: Arial, sans-serif;
      background-color: #f2f2f2;
      display: flex;
      height: 100vh;
    }

    #container {
      display: flex;
      width: 100%;
    }

    #left-half {
      flex: 0 0 100%;
      overflow-y: auto;
      padding: 20px;
      box-sizing: border-box;
      display: flex;
      flex-direction: column;
      gap: 20px;
      max-height: 100vh; /* Set a max height to enable scrolling */
    }

    #search-bar {
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      background-color: white;
      z-index: 1000;
      display: flex;
      align-items: center;
      padding: 10px;
      border-bottom: 1px solid #ccc;
    }

    #search-bar input {
      flex: 1;
      padding: 8px;
      font-size: 16px;
      width:80px;
    }

    #search-bar button {
      padding: 8px 20px;
       top: -1px;
      background-color: #4caf50;
      color: #fff;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }

    .job-card {
      background-color: #fff;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      padding: 8px;
      display: flex;
      flex-direction: column;
      gap: 2px;	
    }

    .job-card img {
      max-width: 200px;
      max-height: 100px;
      border-radius: 0%;
    }



 #right-half {
    position: fixed;
    top: 0;
    right: -100%;
    height: 100%;
    width: 100%;
    background-color: #F9F9FB;
    z-index: 1;
    overflow-x: hidden;
    transition: right 0.3s ease;
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  #right-half.show {
    right: 0;
  }

  #toggle-btn {
    display: block;
    position: fixed;
   margin-top: 32px;
    right: 10px;
    cursor: pointer;
  }
  
  

    nav {
      display: flex;
      flex-direction: column;
      gap: 20px;
      margin-top: auto; /* Push the nav items to the bottom */
    }

    nav a {
      color: black;
      text-decoration: none;
      font-weight: bold;
    }
  
   #profileimage {
        width: 100%;
        height: 200px;
        border-radius: 50%; /* This sets the border-radius to 50% to make it a circle. You can adjust this value as needed. */
    }
    
     .apply-btn {
  display: inline-block;
  background-color: darkblue;
  color: #fff;
  padding: 8px 12px;
  border: none;
  border-radius: 4px;
    text-align: center; /* Center the text */
  
  text-decoration: none; /* Remove underline */
  cursor: pointer;
}

.apply-btn:hover {
  background-color: navy; /* Change color on hover if desired */
}
	
}

  </style>
</head>
  <div id="container">
    <div id="left-half">
      <div id="search-bar">
       <p style="color: darkblue; font-size: 20px;"> <a href="applicanthome"> 𝑻𝒂𝒍𝒆𝒏𝒕𝑭𝒐𝒓𝒈𝒆</a> <img src="/images/search.png" width="30px" height="30" /></p>
        <input type="search" id="searchInput" class="form-control" placeholder="Type Here to search for your required jobs">
        <button id="toggle-btn">&#9776;</button>
      </div>

      <!-- Sample job cards -->
     
     <br><br><br><br>
     
  <div class="wrapper">
        <form class="my-form" action="searchbycompany" >
          
               
          
            <div class="form-group">
                <label for="branch">Search Jobs By Company :</label>
                <select id="companyname" name="companyname" required>
                    <option value="">---Select Company---</option>
                    <c:forEach var="companyName" items="${reclist}">
			        <option value="${companyName}">${companyName}</option>
			    </c:forEach>
                </select>
           
            <button type="submit">Search</button>
             </div>
        </form>
    </div>
     <div class="wrapper">
        <form class="my-form1">
          
               
          
            <div class="form-group">
                <label for="branch">Search By Date Posted :&nbsp;&nbsp;&nbsp;&nbsp;</label>
                <input type="date" name="fromdate" id="dateposted">
                <input type="date" name="todate" id="dateposted">
            <button type="submit">Search</button>
             </div>
        </form>
    </div>
   
     
     
     
     
     
     
     
     
     
     
     
     
    <c:forEach items="${jobslist}" var="job">
    
    <div class="job-card">
      <img src='displaycompanyimage?id=${job.id}' alt="Company Logo" height="45px" width="80px">
      <h3><c:out value="${job.jobtitle}"></c:out></h3>
      
         <c:if test="getApplicationStatus?jobtitle=${job.jobtitle}&companyname=${job.companyname}==true">
   			 <div class="verification-container">
        		<div class="verified-tick">
            		<svg class="tick-icon" viewBox="2 2 12 19">
               		 <path d="M2 12l4 4 8-8" stroke="#fff" stroke-width="2" fill="none" stroke-linecap="round" stroke-linejoin="round"/>
            		</svg>
        		</div>
       		 <div class="verification-text">Verified</div>
    		</div>
        </c:if>


      <p>Location: <c:out value="${job.location}"></c:out></p>
      <p>Skills: <c:out value="${job.skills}"></c:out></p>
      <p>Description: <c:out value="${job.description}"></c:out></p>
      <p>Salary: <c:out value="${job.salary}"></c:out></p>
        <a href="applyjob?id=${job.id}" class="apply-btn">Apply</a>
    </div>

   </c:forEach>
 </div>
   <br>
     <div id="right-half" class="hidden">
    <br><br><br><br>
      <div class="card">
       <form action="uploadapplicantprofileimage" method="post" enctype="multipart/form-data">
  <img src='displayApplicantimage?id=${cid}' alt="Upload Your image here"  style="width:100%"   height="200px" id="profileimage">
   
                </form>
  <h1 style="color: black;">${fname} ${lname}  </h1>
  <pstyle="color: black;" class="title"><h4>${email}</h4></p>
 
  
</div>
      <nav>
          <a href="applicanthome">Home</a>
        <a href="myjobApplications">My Applications</a>
          <a href="updateprofileApplicant">Update Profile</a>
            <a href="/">Logout</a>
        <!-- Add more navigation items here -->
      </nav>
    </div>
     </div>

  <script>
    // JavaScript for handling scrolling by revealing additional job cards
    const loadMoreBtn = document.getElementById('load-more-btn');

    loadMoreBtn.addEventListener('click', () => {
      const hiddenCards = document.querySelectorAll('.job-card.hidden');

      hiddenCards.forEach(card => {
        card.classList.remove('hidden');
      });
    });
  </script>
  
  <script>
        // Previous JavaScript code for adding to the cart

        const searchInput = document.getElementById('searchInput');
        const products = document.querySelectorAll('.job-card');

        searchInput.addEventListener('input', function () {
            const searchText = searchInput.value.toLowerCase();

            products.forEach(product => {
                const productName = product.querySelector('h3').textContent.toLowerCase();
                if (productName.includes(searchText)) {
                    product.style.display = 'block';
                } else {
                    product.style.display = 'none';
                }
            });
        });
    </script>
    
    <script>
    function filterFunction() {
        var input, dropdown, items, i;
        input = document.getElementById("searchInput");
        dropdown = document.getElementById("dropdown");
        items = Array.from(dropdown.getElementsByTagName("a"));

        // Get the search input value
        var filter = input.value.toUpperCase();

        // Sort the items based on their text content and the search input
        items.sort(function(a, b) {
            var textA = a.textContent || a.innerText;
            var textB = b.textContent || b.innerText;
            return textA.toUpperCase().indexOf(filter) - textB.toUpperCase().indexOf(filter);
        });

        // Remove existing items from the dropdown
        while (dropdown.firstChild) {
            dropdown.removeChild(dropdown.firstChild);
        }

        // Add the sorted items back to the dropdown
        items.forEach(function(item) {
            dropdown.appendChild(item);
        });

        // Show or hide the dropdown based on the search input
        dropdown.classList.toggle("show", filter.length > 0);
    }

    // Close the dropdown if the user clicks outside of it
    window.onclick = function(event) {
        if (!event.target.matches('#searchInput')) {
            var dropdowns = document.getElementsByClassName("dropdown-content");
            for (var i = 0; i < dropdowns.length; i++) {
                var openDropdown = dropdowns[i];
                if (openDropdown.classList.contains('show')) {
                    openDropdown.classList.remove('show');
                }
            }
        }
    }
</script>
 <script>
    // JavaScript for handling scrolling by revealing additional job cards
    const loadMoreBtn = document.getElementById('load-more-btn');

    loadMoreBtn.addEventListener('click', () => {
      const hiddenCards = document.querySelectorAll('.job-card.hidden');

      hiddenCards.forEach(card => {
        card.classList.remove('hidden');
      });
    });
  </script>
  <script>
//JavaScript for handling toggle of right-half on button click
  document.getElementById('toggle-btn').addEventListener('click', function () {
    document.getElementById('right-half').classList.toggle('show');
  });
</script>

</body>
</html>
