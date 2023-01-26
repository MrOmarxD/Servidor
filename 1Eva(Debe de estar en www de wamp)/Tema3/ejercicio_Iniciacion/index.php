<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Index</title>
</head>
<body>
    <?php
        include_once("config.php");
        include_once("gestionBD.php");

        //Crear conexion BD
        $con = mysqli_connect(DB_HOST, DB_USER, DB_PASS);
        mysqli_select_db($con, DB_DATABASE);
    ?>
    <form method="post" action="pedir.php">


        <input type="submit" name="botLogin" value="Login">
    </form>
</body>
</html>