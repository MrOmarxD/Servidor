<?php
    /**
     * Pagina encargada crear la estructura, mostrar la cabecera con el titulo, el mehnu, 
     * abrir la etiqueta del div main, para poder insertar contenido en el contenedor main 
     */
    //Iniciamos session en la aplicación, si existe la recupera
    session_start();
    
    //incluimos elementos de configuracion, gestion de BD y libreria de la aplicacion
    include_once("config.php");

    //Crear conexion BD
    $con = mysqli_connect(DB_HOST, DB_USER, DB_PASS);
    mysqli_select_db($con, DB_DATABASE);
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/styles.css">
    <title><?= TITULO ?></title>
</head>
<body>
    <header id="header">
        <h1><?= TITULO ?></h1>
    </header>
</body>
</html>