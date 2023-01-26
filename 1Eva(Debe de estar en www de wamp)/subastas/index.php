<?php
     /**
     * Pagina pagina index principal
     */
    require("cabecera.php");

    if(isset($_GET['pagina'])){
        // Para visualizar subastas vencidas
        if($_GET['pagina']=="subastas_vencidas")
            require("subastas_vencidas.php");
        
        // Para visualizar subastas vigentes
        if($_GET['pagina']=="subastas_vigentes" || $_POST['pagina']=="subastas_vigentes")
            require("subastas_vigentes.php");
    }
    require("footer.php"); 
?>