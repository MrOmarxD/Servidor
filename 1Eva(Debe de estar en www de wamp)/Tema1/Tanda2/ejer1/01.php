<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio1</title>
</head>
<body>
    <?php
        $texto = "";
        if(!empty($_POST["txt"]))
            $texto = strtoupper($_POST["txt"]);
        function cesar($str, $cant){
            for($j = 0; $j<$cant; $j++){
                $str = substr($str, -1).substr($str, 0, strlen($str)-1);
            }
            return $str;
        }
        function sustitucion($str, $fichero){
            $f = fopen("ficheros/".$fichero, "r");
            $arrCaracteres = [];
            $j = 65;
            while (!feof($f)) {
                $arrCaracteres [chr($j) ]= fgetc($f);
                $j++;
            }
            fclose($f);
            for($i = 0; $i<strlen($str); $i++){
                $str[$i]=$arrCaracteres[$str[$i]];
            }
            return $str;
        }
    ?>
    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
        Texto a filtrar <input type="text" name="txt" value="<?php echo $texto;?>" />
        <?php
            if((isset($_POST['botCifCesar']) || isset($_POST['botCifSustitucion'])) && empty($_POST['txt']))
                echo "<span style='color:red'>Debes de introducir un texto para cifrar</span>";
        ?>
        <br/>
        <table>
            <tr>
                <td>Desplazamiento</td>
                <td>
                    <?php
                        $valoresCesar = [3,5,10];
                        foreach($valoresCesar as $valor){
                            echo "<input type='radio' name='desplazamiento' id=$valor value=$valor>";
                            echo "<label for=$valor >$valor</label><br>";
                        }
                    ?>
                <td>
                    <input type="submit" name="botCifCesar" value="CIFRADO CESAR"/>
                    <?php
                        if(isset($_POST['botCifCesar']) && empty($_POST['desplazamiento']))
                            echo "<span style='color:red'>Debes elegir una opcion de cifrado cesar</span>";
                    ?>
                </td>
            </tr>
        </table> 
        <label for="sustitucion">Cifrado por sustitucion</label>
        <select name="sustitucion" id="sustitucion">
            <?php
                function listadoFicheros($ruta){
                    if(is_dir($ruta)){
                        $gestor = opendir($ruta);

                        //Recoger todos los elementos del directorio
                        while(($archivo = readdir($gestor)) !== false){
                            if($archivo != "." && $archivo != "..")
                               echo "<option value='$archivo'>$archivo</option>";
                        }
                        $gestor = closedir($ruta);
                    }
                }
                listadoFicheros("ficheros");
            ?>
        </select>
        <input type="submit" name="botCifSustitucion" value="CIFRADO POR SUSTITUCION"/>
        <?php
            if(isset($_POST['botCifSustitucion']) && empty($_POST['sustitucion']))
                echo "<span style='color:red'>Debes elegir una opcion de cifrado por sustitucion</span>";
            if(!empty($_POST["txt"])){
                if (isset($_POST["botCifCesar"])){
                    if (isset($_POST["desplazamiento"]))
                        echo "<br>Texto cifrado: ".cesar(strtoupper($_POST["txt"]),intval($_POST["desplazamiento"]));
                }
                else{
                    if(isset($_POST["botCifSustitucion"])){
                        if (isset($_POST["sustitucion"]))
                            echo "<br>Texto cifrado: ".sustitucion(strtoupper($_POST["txt"]),$_POST["sustitucion"]);
                    }
                }
            }
         ?>
    </form>
</body>
</html>