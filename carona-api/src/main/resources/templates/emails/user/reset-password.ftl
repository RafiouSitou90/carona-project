<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Reset Password Token</title>
</head>
<body>
<h1>Hello ${fullName},</h1>
<strong>There was a request to change your password!</strong>

<br>

<p>If you did not make this request then please ignore this email.</p>

<p>
    Otherwise, please click this link to change your password: <br><br>
    <a href="${url}" target="_blank">Reset your password</a>.
    This link will expire at <strong>${expiration}</strong> (12 hours).
</p>

<p>
    Cheers!
</p>
</body>
</html>