<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio2-Pedido</title>
</head>
<body>
    <?php
        session_start();
        if(isset($_GET['invitado'])){
            $_SESSION['invitado'] = $_GET['invitado'];
        }
        if(isset($_GET['usuario'])){
            $_SESSION['usuario'] = $_GET['usuario'];
            $_SESSION['descuento'] = $_GET['descuento'];
            setcookie("usuario", $_GET['usuario'],0);//No se como hacer un tiempo "ifinito", cero hasta que cierre sesion
        }
        if(!isset($_SESSION['usuario']) && !isset($_SESSION['invitado'])){
            header("Location: entrada.php");
            exit();
        }
        function listaPedido(){
            if(isset($_POST['botElegir'])){
                if(isset($_POST['Primero']))
                    $_SESSION['arrOrden']['Primero'] = $_POST['Primero'];
                if(isset($_POST['Segundo']))
                     $_SESSION['arrOrden']['Segundo'] = $_POST['Segundo'];
                if(isset($_POST['Postre']))
                    $_SESSION['arrOrden']['Postre'] = $_POST['Postre'];
                if(isset($_POST['Bebida']))
                    $_SESSION['arrOrden']['Bebida'] = $_POST['Bebida'];
                echo "<p>SU ELECCIÓN:</p>";
                echo "<ul>";
                    foreach($_SESSION['arrOrden'] as $tipo => $valor){
                        if($tipo == "Primero" || $tipo == "Segundo")
                            $tipo = "Primer plato";
                        if($tipo == "Segundo")
                            $tipo = "Segundo plato";
                        echo "<li>".$tipo.": ".$valor."</li>";
                    }
                echo "</ul>";
            }
        }
    ?>
    <p><a href="pedidoplato.php?tipo=Primero">PRIMER PLATO</a></p>
    <p><a href="pedidoplato.php?tipo=Segundo">SEGUNDO PLATO</a></p>
    <p><a href="pedidoplato.php?tipo=Postre">POSTRE</a></p>
    <p><a href="pedidoplato.php?tipo=Bebida">BEBIDA</a></p>
    <form action="finpedido.php" method="post">
        <?php listaPedido(); ?>
        <input type="image" name="ImgHacerPedido" src="images/hacerPedido.PNG" value="submit"/>
    </form>
</body>
</html>