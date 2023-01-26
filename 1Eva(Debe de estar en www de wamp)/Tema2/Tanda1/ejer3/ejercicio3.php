<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercico3</title>
</head>
<body>
    <?php
        session_start();
        $productosPrecio = ["Prod1"=>10, "Prod2"=>20, "Prod3"=>10, "Prod4"=>30];
        $visualizar = "block";
        if(isset($_POST['botVaciar'])){
            unset($_SESSION['productosUnidades']);
        }
        if(isset($_POST['botFormalizar'])){
            global $visualizar;
            $visualizar = "none";
            echo "<ul>";
                $total = 0;
                foreach($_SESSION['productosUnidades'] as $nombre => $cantidad){
                    $precio = $_SESSION['productosUnidades'][$nombre] * $productosPrecio[$nombre];
                    echo "<li>".$nombre.": ".$cantidad." a ".$productosPrecio[$nombre]."€ = ".$precio."</li>";
                    $total = $total + $precio;
                }
            echo "</ul>";
            echo "Total: ".$total."€<br>";
            echo "<a href='ejercicio3.php'>Hacer una lista nueva</a>";
            unset($_SESSION['productosUnidades']);
        }
        if(!isset($_SESSION['productosUnidades'])){
            $_SESSION['productosUnidades']["Prod1"] = 0;
            $_SESSION['productosUnidades']["Prod2"] = 0;
            $_SESSION['productosUnidades']["Prod3"] = 0;
            $_SESSION['productosUnidades']["Prod4"] = 0;
        }
        if(isset($_POST['botAniadir'])){
            if(isset($_POST['seleccionados'])){
                foreach($_POST['seleccionados'] as $nombre => $valor){
                    if($_POST['cantidad'.$valor]>0){
                        $_SESSION['productosUnidades'][$valor]= $_SESSION['productosUnidades'][$valor]+$_POST['cantidad'.$valor];
                    }
                }
            }
        }
        function crearLista(){
            global $productosPrecio;
            foreach($productosPrecio as $nombre => $valor){
                echo "<tr>";
                    echo "<td><input type='checkbox' value='".$nombre."' name='seleccionados[]'>".$nombre."</td>";
                    echo "<td>".$valor."€</td>";
                    echo "<td><select name='cantidad".$nombre."'>";
                        bucleSelect();
                    echo "</select></td>";
                    echo "<td>".$_SESSION['productosUnidades'][$nombre]." uds pedidas</td>";
                echo "</tr>";
            }
        }
        function bucleSelect(){
            for($i = 0; $i<=10; $i++){
                echo "<option value='".$i."'>".$i."</option>";
            }
        }
    ?>
    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
        <table style="display: <?php echo $visualizar ?>; ">
            <tr>
                <th>Producto</th>
                <th>Precio</th>
                <th>ELIJA CANTIDAD</th>
                <th>PEDIDO ACTUAL</th>
            </tr>
            <?php crearLista(); ?>
            <tr>  
                <td><input type="submit" name="botAniadir" value="AÑADIR UNIDADES"/></td>
                <td><input type="submit" name="botFormalizar" value="FORMALIZAR COMPRA"/></td>
                <td><input type="submit" name="botVaciar" value="VACIAR CARRO"/></td>
            </tr>
        </table>
    </form>
</body>
</html>