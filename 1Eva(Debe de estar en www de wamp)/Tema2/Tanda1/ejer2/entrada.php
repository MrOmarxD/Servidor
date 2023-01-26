<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio2-login</title>
</head>
<body>
    <?php
        $usuario = "";
        if(isset($_COOKIE['usuario']))
            $usuario = $_COOKIE['usuario'];
        if(isset($_GET['incorrecto']))
            echo "<p style='color: red;'>Combínacion errónea de usuario-password</p>";
    ?>
    <p>Si eres SOCIO, introduce tu usuario y password</p>
    <form action="autentificacion.php" method="post">
        <label for="usuario">Usuario:</label>
        <input type="text" name="usuario" value="<?php echo $usuario; ?>"/><br>
        <label for="password">Password:</label>
        <input type="password" name="password"/><br>
        <input type="submit" name="botSocio" value="Acceso Socio"/>
        <hr>
        <p>Si no dispone de usuario, accede como invitado</p>
        <input type="submit" name="botInvitado" value="Acceso Invitado"/> 
    </form>
</body>
</html>