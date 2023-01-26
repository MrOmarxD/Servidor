<html>    
<head>        
	<meta charset="UTF-8">
    <link rel="stylesheet" href="<?= $hojaestilos ?>" type="text/css"/>       
	<title>Biblioteca </title>    
</head>
<body>
    <div id="header">
        <h1><?= TITULO; ?></h1>
    </div>
    <div id="menu">
        <a href="<?= site_url() ?>">Home</a>
        <a href="<?= site_url() ?>/Cprestamo/calendario">Calendario</a>
        <a href="<?= site_url() ?>/Cprestamo/dibujarSelectLibrosPrestados">Parte 2</a>
    </div>
    <div id="container">
        <div id="bar">
            <ul>
                <?php
                    foreach ($arraygeneros as $genero){
                        echo "<li><a href='".site_url()."/Cprestamo/tablalibroautor/".$genero['genero']."'>".$genero['genero']."</a></li>";
                    }
                ?>
            </ul>
        </div>
        <div id="main">
