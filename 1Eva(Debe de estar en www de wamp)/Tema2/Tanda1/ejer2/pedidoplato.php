<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio2-PedidosPlato</title>
</head>
<body>
    <?php
        session_start();
        require_once('libmenu.php');
        if(!isset($_SESSION['usuario']) && !isset($_SESSION['invitado'])){
            header("Location: entrada.php");
            exit();
        }
        if(!empty($_SESSION['arrOrden'][$_GET['tipo']]))
            echo "Va a cambiar su eleccion ".$_SESSION['arrOrden'][$_GET['tipo']]." por:";
        function crearSelect($tipo){
            $platos = damePlatos($tipo);
            foreach($platos as $nombre => $precio){
                echo "<option value='".$nombre."'>".$nombre."-".$precio."€</option>";
            }
        }
    ?>
    <form action="pedido.php" method="post">
        <p>Elegir un <?php echo $_GET['tipo'] ?></p>
        <select name="<?php echo $_GET['tipo']; ?>">
            <?php crearSelect($_GET['tipo']); ?>
        </select>
        <input type="submit" name="botElegir" value="<?php echo "ELEGIR ".$_GET['tipo']; ?>"/> 
    </form>
</body>
</html>