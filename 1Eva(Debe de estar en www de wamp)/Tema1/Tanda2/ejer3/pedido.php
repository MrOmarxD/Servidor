<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        th{background-color: #D3D3D3;}
        table{margin: 0 auto;}
    </style>
    <title>Ejercicio 3</title>
</head>
<body>
    <table>
        <tr>
            <th colspan="3">ELIGE TU PEDIDO</th>
        </tr>
        <?php
            actualizarLista();
            $total = 0;
            if(isset($_GET['nuevoTotal']))
                $total = $total + $_GET['nuevoTotal'];
        ?>
        <tr>
            <th colspan="3">TOTAL: <?php echo $total ?>€</th>
        </tr>
    </table>
    <table>
        <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
            <tr>
                <th colspan="3">Añade articulo</th>
            </tr>
            <tr>
                <td>Nombre:</td>
                <td>Precio(€):</td>
            </tr>
            <tr>
                <td><input type="text" name="cajaNombre"/></td>
                <td><input type="text" name="cajaPrecio"/></td>
                <td><input type="submit" name="botAnia" value="AÑADIR"/></td>
            </tr>
        </form>
    </table>
    <?php
        function leerFichero(){
            $handle = fopen("articulos.txt", "r");
            $arrProductos = [];
            while (!feof($handle)) {
                   $linea = fgets($handle);
                   $partes = explode(";", $linea);
                  $arrProductos[$partes[0]] = $partes[1];
            }
            fclose($handle);
            return $arrProductos;
        }
        function aniadirProducto($nombre, $precio){
            $f = fopen("articulos.txt", "a");
            $linea = "\n".trim($nombre).";".trim($precio);
            fwrite($f, $linea);
            fclose($f);
        }
        function actualizarLista(){
            $arrProd = leerFichero();
            foreach($arrProd as $nombre => $precio){
                echo "<tr>";
                    echo "<td>$nombre</td>";
                    echo "<td>$precio</td>";
                    echo "<td><a href='?nuevoTotal=".$precio."'>Añadir unidad</a></td>";
               echo "</tr>";
            }
        }
        if(isset($_POST['botAnia'])){
            if(isset($_POST['cajaNombre']) && isset($_POST['cajaPrecio'])){
                aniadirProducto($_POST['cajaNombre'],$_POST['cajaPrecio']);
                header("location: pedido.php");
            }     
        } 
    ?>
</body>
</html>