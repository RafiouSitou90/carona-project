<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Confirmation Email | Carona App GabRaf</title>
</head>
<body>
<h1>Hi ${fullName}! Please confirm your email!</h1>

<div>
    <h3>Identifiers</h3>
    <p>Username : <strong>${username}</strong></p>
    <p>Email : <strong>${email}</strong></p>
</div>

<br>

<p>
    Please confirm your email address by clicking the following link: <br><br>
    <a href="${url}" target="_blank">Confirm my Email</a>.
    This link will expire at <strong>${expiration}</strong> (12 hours).
</p>

<p>
    Cheers!
</p>
</body>
</html>
