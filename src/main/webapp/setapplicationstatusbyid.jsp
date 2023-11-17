<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Form Title</title>
</head>
<body>
 <h5 align="center" style="color:red">${message}</h5>
    <form action="your_server_endpoint" method="post">
        <!-- Application Status Combo Box -->
        <label for="applicationStatus">Application Status:</label>
        <select name="applicationStatus" id="applicationStatus" required>
            <option value="">Select Option</option>
            <option value="option1">1</option>
            <option value="option2">2</option>
            <option value="option3">1</option>
            <option value="option4">2</option>
        </select>
        <br>

        <!-- Comment Textarea -->
        <label for="comment">Comment:</label>
        <textarea name="comment" id="comment" rows="4" cols="50" required></textarea>
        <br>

        <!-- Submit Button -->
        <input type="submit" value="Submit">
    </form>
</body>
</html>
