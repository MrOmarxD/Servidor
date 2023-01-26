<?php
    foreach ($arrayPrestamosLibro as $prestamo){
        echo "<p>Prestamo Nº ".$prestamo['idprestamo'].":".$prestamo['fecha']." <a href=<?php echo site_url().'/Cprestamo/devolverLibro' ?>Devolver</a></p>";
    } 
?>