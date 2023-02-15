<?php 
    // Llamada al header
    require 'header.php';
?>
    <h2>Lista de autobuses</h2>
    <?php 
        $lstBuses = obtenerBuses($conn);
        foreach ($lstBuses as $bus) { 
            $id = $bus['id'];
            $img = CARPETA_IMAGENES."/".$bus['imagen'];
            $marca = ucfirst(substr($bus['imagen'], 0, strpos($bus['imagen'], " ")));
           echo "<table>";
                echo "<tr>";
                    echo "<th>Imagen</th>";
                    echo "<th>Matricula</th>";
                    echo "<th>Marca</th>";
                    echo "<th>Rutas</th>";
                echo "</tr>";
                echo "<tr>";
                    echo "<td><img src='".$img."'></td>";
                    echo "<td>".$id."</td>";
                    echo "<td>".$marca."</td>";
                    echo "<td>";
                        $lstRutasBus = obtenerRutasBus($conn, $id);
                        echo "<h2>".count($lstRutasBus)." RUTAS</h2>";
                        echo "<table>";
                        foreach ($lstRutasBus as $ruta) {
                            $origen = $ruta['ciudadOrigen'];
                            $destino = $ruta['ciudadDestino'];
                            $fechaSalida = date("d/m/Y", strtotime($ruta['horaSalida']));
                            $idRuta = $ruta['id'];
                            echo "<tr>";
                                echo "<td>".$origen."\t".$destino."\tDía ".$fechaSalida."\t<a href='reservar.php?idRuta=".$idRuta."'>RESERVAR</a></td>";
                            echo "</tr>";
                        }
                        echo "</table>";
                    echo "</td>";
                echo "</tr>";
           echo "</table>";
        }
        // Llamada al footer
        require 'footer.php';
    ?>