<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">  
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio4-Charla</title>
</head>
<body>
    <?php
        if(empty($_POST['usuario']) && !isset($_GET['usuario'])){
            header("Location: login.php");
            exit();
        }
        $usuario = "";
        if(isset($_GET['usuario']))
            $usuario = $_GET['usuario'];
        if(isset($_POST['usuario']))
            $usuario = $_POST['usuario'];
        function aniadirComentario(){
            global $usuario;
            $f = fopen("historial.txt", "a");
            $linea = "\n".trim($usuario).";".trim($_POST['mensaje']);
            fwrite($f, $linea);
            fclose($f);
        } 
        if(isset($_POST['botEnviar']) && !empty($_POST['mensaje']))
            aniadirComentario();
    ?>
    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
        <iframe src="contenido_charla.php"></iframe><br>
        <input type="hidden" name="usuario" value="<?php echo $usuario; ?>"/>
        <p>Usuario: <b><?php echo $usuario;?></b></p>
        Mensaje: <input type="txt" name="mensaje"/>
        <input type="submit" name="botEnviar" value="ENVIAR"/>
    </form>
</body>
</html>