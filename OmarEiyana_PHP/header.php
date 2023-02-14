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
    <title>Omar Eiyana</title>
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
    <!-- Main -->
    <div id="main">
        
