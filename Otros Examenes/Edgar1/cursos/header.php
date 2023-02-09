<?php 
    // Iniciar la session
    session_start();
    require 'config.php' 
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/styles.css">
    <title>Cursos | Edgar Martínez Palmero</title>
</head>
<body>
    <?php 
        // Conexión con la BBDD
        $conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_DATABASE);
        $conn->set_charset("utf8");
        if($conn->connect_errno > 0)
            die("Imposible conectarse con la base de datos [" . $conn->connect_error . "]"); 
            require 'gestion_db.php';
    ?>
    <!-- Header -->
    <header id="header">
        <h1>Cursos</h1>
    </header>
    <!-- Menu -->
    <nav id="menu">
        <a href="index.php">Home</a>
        <a href="anulaciones.php">Anulaciones</a>
        <?php 
            // Cookie
            // Si no existe la cookie se crea como vacia
            if (!isset($_COOKIE['anulaciones'])) {
                $contenidoCookie = [null, 0];
                setcookie("anulaciones",json_encode($contenidoCookie),time() + (86400 * 30));
            } else {
                // Si ya existe la cookie se extrae el array y se comprueba si son datos validos para escribir
                $contenidoCookie = json_decode($_COOKIE['anulaciones'], true);
                if ($contenidoCookie[0] != null) {
                    echo "<b>Fecha Ultima anulación: $contenidoCookie[0]</b>";
                    echo "<b>/Nº total de anulacioes: $contenidoCookie[1]</b>";
                }
            }
        ?>
    </nav>
    <!-- Main -->
    <div id="main">
        
