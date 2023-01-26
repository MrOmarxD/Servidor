<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio 4-Alta</title>
</head>
<body>
    <?php
        $loSentimos = "";
        $regExito = false;
        if(isset($_GET['usuario']))
            $usuario = $_GET['usuario'];
        else
            $usuario = $_POST['cajaLogin'];
        $arrUsu = usuariosContrasenias();
        if(isset($_POST['botReg'])){
            if (array_key_exists($usuario, $arrUsu))
                $loSentimos = "Lo sentimos, ya existe un usuario ".$_POST['cajaLogin'];
            else{
                aniadirUsufich($_POST['cajaLogin'],$_POST['cajaPassw']);
                $regExito = true;
            }
        }
        function aniadirUsufich($usu, $contra){
            $f = fopen("usuarios.txt", "a");
            $linea = "\n".trim($usu).";".trim($contra);
            fwrite($f, $linea);
            fclose($f);
        }
        function usuariosContrasenias(){
            $handle = fopen("usuarios.txt", "r");
            $arrUsuarios = [];
            while (!feof($handle)) {
                $linea = fgets($handle);
                $partes = explode(";", $linea);
                $arrUsuarios[$partes[0]] = $partes[1];
            }
            fclose($handle);
            return $arrUsuarios;
        }
        if($regExito){
            echo "<b>".$usuario."</b>: Has sido dado de alta<br>";
            echo "<a href='charla.php?usuario=".$_POST['cajaUsu']."'>ENTARAR AL CHAT</a>";
        }
        else{
    ?>
        <h1>REGISTRARSE</h1>
        <?php echo "<p color='red'>".$loSentimos."</p>"; ?>
        <table>
            <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
            <tr>
                <td>Login:</td>
                <td><input type="text" name="cajaLogin" value="<?php echo $usuario; ?>"/></td>
                <td rowspan="3"><img src="images/imag.PNG" alt="Imagen"></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="cajaPassw"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" name="botReg" value="REGISTAR"/></td>
            </tr>
        </table>
    <?php } ?>
</body>
</html>