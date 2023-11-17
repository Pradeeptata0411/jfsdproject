<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
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
            background-color: #4CAF50;
            border: 2px solid #fff;
            color: #fff;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .line {
            left: 30px;
            height: 2px;
            width: 350px;
            background-color: #ccc;
        }
    </style>
</head>
<body>

<div class="workflow">
    <div class="stage">
        <div class="tick">✔️</div>
        <div class="line"></div>
        Profile Screening
    </div>
    <div class="stage">
        <div class="tick">✔️</div>
        <div class="line"></div>
        Online Assessment
    </div>
    <div class="stage">
        <div class="tick">✔️</div>
        <div class="line"></div>
        Technical and HR Interview
    </div>
    <div class="stage">
        <div class="tick">✔️</div>
        Final Offer Confirmation
    </div>
</div>

</body>
</html>