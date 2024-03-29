
<?php
    /**
     * Pagina de verificacion del mail que se carga en el div de contenido main
     */
    //Cagamos la estructura de la pagina de cabecera
    require("cabecera.php");

    include_once("libreria_subastas.php");
    if(isset($_GET['email']) && isset($_GET['cadena_verif'])){
        $mail = $_GET['email'];
        $cadena_verif = $_GET['cadena_verif'];
        if(comprobarMail($mail, $cadena_verif)){
            $id = getIdUsuarioEmail($mail);
            darUsuarioAlta($id);
            echo "<p class='verificacion'>Se ha verificado tu cuenta. Puedes entrar pinchando
                <a href='index.php?ira=login'>aquí</a>
            </p>";
        }
        else {
            echo "No se puede vefificar dicha cuenta";
        }
    }

?>
