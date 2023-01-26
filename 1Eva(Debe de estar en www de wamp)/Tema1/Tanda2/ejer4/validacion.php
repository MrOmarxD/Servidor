<?php
    define("FICHUSU", "usuarios.txt");
    function usuariosContrasenias(){
        $handle = fopen(FICHUSU, "r");
        $arrUsuarios = [];
        while (!feof($handle)) {
            $linea = fgets($handle);
            $partes = explode(";", $linea);
            $arrUsuarios[$partes[0]] = $partes[1];
        }
        fclose($handle);
        return $arrUsuarios;
    }
    function validarUsu(){
        $arrUsuContra = usuariosContrasenias();
        if (array_key_exists($_POST['cajaUsu'], $arrUsuContra)) {
            if($_POST['cajaContra']==trim($arrUsuContra[$_POST['cajaUsu']]))
                header("Location: charla.php?usuario=".$_POST['cajaUsu']);
            else
                header("Location: login.php?usuario=".$_POST['cajaUsu']);
        }
       else
            header("Location: alta.php?usuario=".$_POST['cajaUsu']);
    }
    validarUsu();
?>