<?php if(count($arrayPrestamosDia)!=0) { ?>
    <table>
        <tr>
            <th>Prestamos <?= $fecha ?></th>
        </tr>
        <?php
            foreach($arrayPrestamosDia as $prestamo){
                echo "<tr>";
                    echo "<td>".$prestamo['titulo']."</td>";
                echo "</tr>";
            }
        ?>
    </table>
<?php 
    }
    else
        echo "El día ".$fecha." no se han realizado prestamos.";
    echo "<br><a href='".site_url()."/Cprestamo/calendario'><input type=button value='Volver atras'></a>";
?>
