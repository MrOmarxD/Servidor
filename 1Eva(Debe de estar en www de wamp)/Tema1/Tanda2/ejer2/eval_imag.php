<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio2</title>
</head>
<body>
    <?php if(isset($_POST['cantImg'])){ ?>
    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
        <div style='border: 1px solid black; width: 300px; margin: 0 auto;'>
            <table>
                <?php rellenarTabla(); ?>
            </table>
            <input type="submit" value="ENVIAR VALORACIONES" name="botVal"/>
        </div>
    </form>
    <?php
        }
        function rutas_imag(){
            define("DIRIMG", "images");
            $arrext = array("jpg","png","tiff");
            $arr = [];
            if(is_dir(DIRIMG)){
                $gestor = opendir(DIRIMG);
                while(($archivo = readdir($gestor)) !== false){
                    if($archivo != "." && $archivo != ".."){
                        if (in_array(substr($archivo, stripos($archivo, ".")+1), $arrext))
                            $arr[] = DIRIMG."/".$archivo;
                    } 
                }
                closedir($gestor);
            }
            return $arr;
        }
        function rellenarTabla(){
            $rutasImg = rutas_imag();
            $claves = array_rand($rutasImg, $_POST['cantImg']);
            $arr_check = [];
            for($i = 0; $i< count($claves); $i++){
                $indice = $claves[$i];
                echo "<tr>";
                echo "<td><img src=$rutasImg[$indice] alt='Imagen'/></td>";
                echo "<td><input type='checkbox' name='megusta[]' value=$rutasImg[$indice]/>Me gusta</td>";
                echo "</tr>";
            }
        }
        function ficherotexto(){
            $f = fopen("../megustas.txt", "a");
            $linea = "\n".gethostbyname(gethostname()).":\t";
                foreach($_POST['megusta'] as $checked){
                    $linea .= $checked."\t";
                }
            fwrite($f, $linea);
            fclose($f);
        }
        if (isset($_POST['botVal'])){
            if(isset($_POST['megusta'])){
                echo "Gracis por tu envio<br>";
                ficherotexto();
            }    
            else
                echo "Sentimos que no le haya gustado ninguna<br>";
            echo ('<a href="selec_cantidad.php">Volver a la pag principal</a>');
        }
    ?>
</body>
</html>