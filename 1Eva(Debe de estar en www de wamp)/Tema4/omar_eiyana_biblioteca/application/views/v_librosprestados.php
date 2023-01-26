<table>
    <tr>
        <form action="<?php echo site_url()."/Cprestamo/dibujarPrestamosLibro" ?>" method="post">
            <td><select name="librosPrestados" id="librosPrestados">
            <?php
                if(isset($idlibro)){
                    foreach ($arrayLibrosPrestados as $libro){
                        if($idlibro == $libro['idlibro'])
                            echo "<option value='".$libro['idlibro']."' selected>".$libro['titulo']."</option>";
                        else
                            echo "<option value='".$libro['idlibro']."'>".$libro['titulo']."</option>";
                    }  
                }
                else{
                    foreach ($arrayLibrosPrestados as $libro){
                        echo "<option value='".$libro['idlibro']."'>".$libro['titulo']."</option>";
                    }
                } 
            ?>
            </select></td>
            <td><input type='submit' value='VER PRESTAMOS'></td>
        </form>
    </tr>
</table>