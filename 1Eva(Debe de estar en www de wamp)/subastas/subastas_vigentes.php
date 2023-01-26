<h1>Ultimas subastas vencidas</h1>
<table>
    <tr>
        <th>FECHA SUBASTA</th>
        <th>ÍTEM</th>
        <th>PRECIO PARTIDA</th>
        <th>ULTIMA PUJA</th>
        <th>MODIFICAR PRECIO PARTIDA</th>
    </tr>
    <?php
        $subastasVigentes = obtenerTodasSubastasVigentes($conn);
        // Escribir la tabla
        while($subasta = $subastasVigentes -> fetch_assoc()) {
            $idItem = $subasta['idItem'];
            $fechafin = $subasta['FECHAFIN'];
            $preciopartida = $subasta['PRECIOPARTIDA'];
            $descripcion = $subasta['DESCRIPCION'];
            $maxCantidadPuja = $subasta['MAX(CANTIDAD)'];
            if(isset($_POST['idItem'])){
                if($_POST['idItem']==$idItem){
                    if($_POST['cantModificar']<$maxCantidadPuja){
                        if(isset($_SESSION['catSeleccionada']))
                            $cantidadesModificadas = $_SESSION['catSeleccionada'];
                        $cantidadesModificadas.array_push($_POST['cantModificar']);
                    }
                }
            }
            echo "<tr>";
                echo "<td>".$fechafin."</td>";
                echo "<td>".$descripcion."</td>";
                echo "<td>".$preciopartida."</td>";
                if($subasta['MAX(CANTIDAD)']!= null)
                    echo "<td>".$maxCantidadPuja."</td>";
                else
                    echo "<td>Sin Pujas</td>";
                echo "<form action='#' method='post'>";
                    echo "<td><input type='text' name='cantModificar'><br><input type='submit' name='botModificarPrecio' value='modificar'></td>";
                    echo "<input type='hidden' name='pagina' value='subastas_vigentes'>";
                    echo "<input type='hidden' name='idItem' value='".$idItem."'>";
                echo "</form>";
            echo "</tr>";
        }
    ?>
</table>