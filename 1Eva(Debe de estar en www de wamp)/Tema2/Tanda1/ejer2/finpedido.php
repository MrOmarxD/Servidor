<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio2_finpedido</title>
</head>
<body>
    <?php
        session_start();
        require_once('libmenu.php');
        if(count($_SESSION['arrOrden'])>0){
            $total = 0;
            foreach($_SESSION['arrOrden'] as $tipo => $plato){
                echo "<p>".$plato."a ".damePrecio($plato)."€</p>";
                $total = $total + floatval(damePrecio($plato));
            }
            echo "<p>Total: ".$total."€</p>";
            unset($_SESSION['arrOrden']);
        }
        else{
            echo "Debe de seleccionar al menos un producto<br>";
        }
    ?>
    <a href="pedido.php">Pulse aquí si desea realizar otro pedido.</a>
</body>
</html>