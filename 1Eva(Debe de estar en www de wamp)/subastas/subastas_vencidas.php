<h1>Ultimas subastas vencidas</h1>
<table>
    <tr>
        <th>FINALIZÓ EL </th>
        <th>CATEGORIA</th>
        <th>IMAGEN</th>
        <th>ÍTEM</th>
        <th>GANADOR</th>
    </tr>
    <?php
        $subastasVencidas = obtenerTodasSubastasVencidas($conn);
        // Escribir la tabla
        while($subasta = $subastasVencidas -> fetch_assoc()) {
            $idItem = $subasta['idItem'];
            $fechafin = $subasta['FECHAFIN'];
            $categoria = $subasta['CATEGORIA'];
            $imgSrc = $subasta['IMAGEN'];
            $descripcion = $subasta['DESCRIPCION'];
            $fondoGris = "";
            $infoExtra = "";
            if(isset($_GET['idItem'])){
                if($_GET['idItem']==$idItem){
                    $fondoGris = "style: background = gray;";
                    $infoExtra = obtenerIformacionAdicionalSubasta($conn, $idItem);
                }
            }
            echo "<tr ".$fondoGris.">";
                echo "<td>".$fechafin."</td>";
                echo "<td>".$categoria."</td>";
                echo "<td><img src='".CARPETA_IMAGENES."/".$imgSrc."'></td>";
                echo "<td>".$descripcion."</td>";
                if($subasta['USERNAME']!= null)
                    echo "<td><a href='?idItem=".$idItem."&pagina=subastas_vencidas'>".$subasta['USERNAME']."</a>".$infoExtra."</td>";
                else
                    echo "<td>Sin Pujas</td>";
            echo "</tr>";
        }
    ?>
</table>