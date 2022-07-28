<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration Successful</title>
</head>
<body>
<div style="margin-top: 10px">Greetings, ${user.name}</div>
<div>Your Email is <b>${user.email}</b></div>
<div>Your Address is <b>${user.address}</b></div>
<div>Your Number is <b>${user.number}</b></div>
<div>Your Password is <b>${user.password}</b></div>
<div>Your status is <b>${user.verificationStatus?string("true", "false")}</b></div>
<img src=${user.image} alt="userImg"/>
<br/>
<div> Have a nice day..!</div>
</body>
</html>