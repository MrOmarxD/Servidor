<?php
    require_once('libmenu.php');
    if(isset($_POST['botInvitado'])){
        header("Location: pedido.php?invitado=true");
    }
    if(isset($_POST['botSocio'])){
        if(isset($_POST['usuario']) && isset($_POST['password'])){
            if(autentifica($_POST['usuario'], $_POST['password'])==1){
                $descuneto = dameDcto($_POST['usuario']);
                header("Location: pedido.php?usuario=".$_POST['usuario']."&descuento=".$descuento);
            }
            else
                header("Location: entrada.php?incorrecto=true");
        }
        else
            header("Location: entrada.php?incorrecto=true");
    }
?>