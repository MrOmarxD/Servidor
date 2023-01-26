<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio 4-Login</title>
</head>
<body>
    <?php
        $usuario = "";
        if(isset($_GET["usuario"])){
            $usuario = $_GET["usuario"];
            echo "CONTRASEÑA ERRÓNEA PARA <b>".$usuario."</b><br>";
            echo "Inténtalo de nuevo";
        }    
    ?>
    <table>
        <form action="validacion.php" method="post">
            <tr>
                <td>Nombre de usuario:</td>
                <td><input type="text" name="cajaUsu" value="<?php echo $usuario; ?>"/></td>
                <td rowspan="2"><input type="submit" name="botEntrar" value="ENTRAR"/></td>
            </tr>
            <tr>
            <td>Contraseña:</td>
            <td><input type="password" name="cajaContra"/></td>
            </tr>
        </form>
    </table>
</body>
</html>