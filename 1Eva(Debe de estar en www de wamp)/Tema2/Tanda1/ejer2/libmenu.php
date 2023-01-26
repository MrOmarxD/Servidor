<?php
    function leerFichero($f){
        $handle = fopen($f, "r");
        $arrLineasFichero = [];
        while (!feof($handle)) {
            $linea = fgets($handle);
            $partes = explode(" ", $linea);
            $arrLineasFichero[$partes[0]] = $partes[1]." ".$partes[2];
        }
        fclose($handle);
        return $arrLineasFichero;
    }
    function autentifica($nombre, $contrasenia){
        $arrSocios = leerFichero("ficheros/socios.txt");
            if(array_key_exists($nombre, $arrSocios)){
                $partes = explode(" ", $arrSocios[$nombre]);
                if($partes[0]==$contrasenia)
                    return 1;
        }
        return 0;
    }
    function dameDcto($nombre){
        $arrSocios = leerFichero("ficheros/socios.txt");
        $partes = explode(" ", $arrSocios[$nombre]);
        return $partes[1];
    }
    function damePlatos($tipo){
        $arrPlatosPrecio = [];
        $arrPlatos = leerFichero("ficheros/platos.txt");
        foreach($arrPlatos as $nombre => $info){
            $partes = explode(" ", $info);
            if($partes[0]==$tipo)
                $arrPlatosPrecio[$nombre] = $partes[1];
        }
        return $arrPlatosPrecio;
    }
    function damePrecio($nombre){
        $arrPlatos = leerFichero("ficheros/platos.txt");
        $partes = explode(" ", $arrPlatos[$nombre]);
        return $partes[1];
    }
?>