<?php
function isPasswordSecure($userPassword) {
    $minLengthRequired = 8;
    if (strlen($userPassword) < $minLengthRequired) {
        return false;
    }
    return true;
}

function isCommonPassword($userPassword) {
    $commonPasswordsFile = '10-million-password-list-top-1000.txt';
    $commonPasswordsList = file($commonPasswordsFile, FILE_IGNORE_NEW_LINES);
    return in_array($userPassword, $commonPasswordsList);
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $enteredPassword = $_POST['password'];

    if (isPasswordSecure($enteredPassword) && !isCommonPassword($enteredPassword)) {
        echo <<<HTML
        <!DOCTYPE html>
        <html>
        <head>
            <title>Welcome Page</title>
        </head>
        <body>
            <h1>Welcome Page</h1>
            <p>Password you entered: $enteredPassword</p>
            <form action="index.php">
                <input type="submit" value="Logout">
            </form>
        </body>
        </html>
        HTML;
        exit;
    } else {
        echo "Blocked: Password does not meet security requirements.";
    }
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
    <h1>Home Page</h1>
    <form method="post">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>
