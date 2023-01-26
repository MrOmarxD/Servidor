<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        fieldset{border: 1px solid blue; padDing:5px; width:400px}
    </style>
    <title>Ejercicio1</title>
</head>
<body>
    <?php
        session_start();
        $arrNombres = [];
        if(isset($_POST['botAniadir'])){
            if(!empty($_POST['nombre'])){
                if(validarNombre($_POST['nombre'])){
                    if(isset($_SESSION['nombre']))
                        $_SESSION['nombre'] = $_SESSION['nombre'].";".$_POST['nombre'];
                    else
                        $_SESSION['nombre'] = $_POST['nombre'];
                    $arrNombres = explode(";", $_SESSION['nombre']);
                }
                else
                    echo "<font color=\"red\">No has escrito el nombre únicamente con letras y espacios.</font>";
            }
        }
        if(isset($_POST['botBorrar'])){
            unset($_SESSION['nombre']);
        }
        if(isset($_GET['cerrarSesion']))
            session_destroy();
        function validarNombre($cadena){
            for($i = 0; $i<=strlen($cadena)-1; $i++){
                if(!(($cadena[$i]>='a' && $cadena[$i]<='z') || ($cadena[$i]>='A' && $cadena[$i]<='Z') || $cadena[$i]==' '))
                    return false;
            }
            return true;
        }
    ?>
    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
        <fieldset>
            <legend>FORMULARIO</legend>
            <label for="nombre">Escribe algún nombre:</label>
            <input type="text" name="nombre"/><br>
            <input type="submit" name="botAniadir" value="AÑADIR"/></td>
            <input type="submit" name="botBorrar" value="BORRAR"/></td> 
        </fieldset>
    </form>
    <?php
        if(count($arrNombres)!=0){
            echo "<ul>";
            for($i = 0; $i<count($arrNombres); $i++){
                echo "<li>".$arrNombres[$i]."</li>";
            }
            echo "</ul>";
        }
        else
            echo "<p>Todavía no se han introducido nombres</p>"
    ?>
    <p><a href="?cerrarSesion='true'">cerrar sesión (se perderán los datos almacenados)</a></p>
</body>
</html>