<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Form Title</title>
    <style>
     .card {
      box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
      max-width: 400px;
      margin: auto;
      text-align: center;
      font-family: Arial, sans-serif;
      background-color: #fff;
      padding: 20px;
      border-radius: 8px;
    }

    h5 {
      color: red;
    }

    form {
      display: flex;
      flex-direction: column;
      align-items: center;
    }

    label {
      margin-top: 10px;
      color: #333; /* Dark color */
      font-size: 16px;
    }

    select,
    textarea {
      width: 100%;
      padding: 8px;
      margin: 6px 0;
      box-sizing: border-box;
      border: 1px solid #ccc;
      border-radius: 4px;
    }

    input[type="submit"] {
      background-color: #4caf50;
      color: #fff;
      padding: 10px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }

    input[type="submit"]:hover {
      background-color: #45a049;
    }
    </style>
</head>
<body>
 <h5 align="center" style="color:red">${message}</h5>
  
<div class="card">
   
    <form action="#" method="post">
      <!-- Application Status Combo Box -->
      <label for="applicationStatus">Application Status:</label>
      <select name="applicationStatus" id="applicationStatus" required>
        <option value="">Select Option</option>
        <option value="option1">1</option>
        <option value="option2">2</option>
        <option value="option3">1</option>
        <option value="option4">2</option>
      </select>

      <!-- Comment Textarea -->
      <label for="comment">Comment:</label>
      <textarea name="comment" id="comment" rows="4" cols="50" required></textarea>

      <!-- Submit Button -->
      <input type="submit" value="Submit">
    </form>
  </div>
  
  </body>
</html>
