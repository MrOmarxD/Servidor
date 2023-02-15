<?php 
    // Llamada al header
    require 'header.php';
?>
    <h2>Reservas</h2>
    <?php
        if(!isset($_GET['idRuta']) && !isset($_SESSION['idRuta']))
            header('Location: index.php');
        if(isset($_GET['idRuta']))
            $_SESSION['idRuta'] = $_GET['idRuta'];
        $idRuta = $_SESSION['idRuta'];
        $lstAsientosSeleccionados = [];
        if(isset($_POST['reservarMarcado'])){
            $asientoSeleccionado = $_POST['asientos'];
            $lstAsientosSeleccionados[] = $asientoSeleccionado;
            if(isset($_SESSION['asientosSeleccionada'])){
                $partes = explode(";", $_SESSION['asientosSeleccionada']);
                foreach ($partes as $asiento) {
                    echo $asiento;
                    array_push($lstAsientosSeleccionados, $asiento);
                }
            }
            $str = "";
            foreach ($lstAsientosSeleccionados as $asiento) {
                $str= $str.";".$asiento;
            }
            $_SESSION['asientosSeleccionada'] = $str;
        }
        $ruta = obtenerRuta($conn, $idRuta);
        $origen = $ruta['ciudadOrigen'];
        $destino = $ruta['ciudadDestino'];
        $capacidad = obtenerCapacidad($conn, $ruta['bus']);
        $lstAsientosReservados = obtenerAsientosReservados($conn, $idRuta);
        echo "<form action='reservar.php' method='post'>";
            echo "<table>";
                echo "<tr>";
                    echo "<td id='lineaCabecera' colspan=4>Reservado ruta ".$origen." - ".$destino." (Capacidad ".$capacidad.")</td>";
                echo "</tr>";
                echo "<tr>";
                    for($i = 1; $i<=$capacidad; $i++){
                        if(in_array($i, $lstAsientosReservados))
                            echo "<td style='color: red'><input type='radio' name='asientos' value='".$i."' disabled/>Asiento ".$i."</td>";
                        else
                            echo "<td><input type='radio' name='asientos' value='Asiento ".$i."'/>Asiento ".$i."</td>";
                        if($i%4 == 0){
                            echo "</tr>";
                            echo "<tr>";
                        }
                    }
                echo "</tr>";
                echo "<tr>";
                    echo "<td><input type='submit' name='reservarMarcado' value='Reservar marcado'/></td>";
                    echo "<td><input type='submit' name='reservarAlAzar' value='Reservar al azar'/></td>";
                echo "</tr>";
            echo "</table>";
            if(count($lstAsientosSeleccionados)>0){
                echo "<p>".count($lstAsientosSeleccionados)." ASIENTOS SELECECIONADOS</p>";
                foreach ($lstAsientosSeleccionados as $asiento) {
                    echo "<input type='checkbox' name='borrarAsiento' value='".$asiento.">".$asiento;
                }
                echo "<input type='submit' name='borrarAsientos' value='BORRAR ASIENTOS'/>";
            }
        echo "</form>";
        // Llamada al footer
        require 'footer.php';
    ?>