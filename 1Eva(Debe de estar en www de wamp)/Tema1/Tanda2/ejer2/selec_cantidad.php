<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio2</title>
</head>
<body>
    <?php
        function cuantasImag ($ruta){
            $arrext = array("jpg","png","tiff");
            $cant = 0;
            if(is_dir($ruta)){
                $gestor = opendir($ruta);

                //Recoger todos los elementos del directorio
                while(($archivo = readdir($gestor)) !== false){
                    if($archivo != "." && $archivo != ".."){
                        if (in_array(substr($archivo, stripos($archivo, ".")+1), $arrext))
                            $cant++;
                    } 
                }
                $gestor = closedir($ruta);
            }
            return $cant;
        }
    ?>
    <form action="eval_imag.php" method="post">
    <label for="cantImg">¿Cuántas imágenes deseas ver?</label>
        <select name="cantImg" id="cantImg">
            <?php
                define("DIRIMG", "images");
                $cant = cuantasImag(DIRIMG);
                for($i = 2; $i <= $cant; $i++){
                    echo "<option value='$i'>$i</option>";
                }
            ?>
        </select>
        <br/>
        <input type="submit" value="VER IMÁGENES" name="botVer"/>
    </form>
</body>
</html>