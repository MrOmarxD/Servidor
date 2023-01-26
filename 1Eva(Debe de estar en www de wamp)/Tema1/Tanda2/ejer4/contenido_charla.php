<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="refresh" content="2">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio4</title>
</head>
<body>
    <?php
        $handle = fopen("insultos.txt", "r");
        $textoARemplazar = [":)",":("];
        $textoARemplazar_asterisco = ["images/smile.jpg","images/angry.PNG"];
        while (!feof($handle)) {
            $linea = fgets($handle);
            $textoARemplazar[] = $linea;
            $asteriscos = "";
            for($i = 0; $i<strlen($linea); $i++){
                $asteriscos = $asteriscos."*";
            }
            $textoARemplazar_asterisco[] = $asteriscos;
        }
        fclose($handle);
        function historialChat(){
            $handle = fopen("historial.txt", "r");
            global $textoARemplazar;
            global $textoARemplazar_asterisco;
            while (!feof($handle)) {
                $linea = str_ireplace($textoARemplazar, $textoARemplazar_asterisco, fgets($handle));
                $partes = explode(";", $linea);
                if(count($partes)==2)
                    echo "<b>".$partes[0]."</b>: ".$partes[1]."<br>";
            }
            fclose($handle);
        }
        historialChat();
    ?>
    <script type="text/javascript">
        window.onload = function() {
            window.scrollTo(0, document.body.scrollHeight);
        }
    </script>
</body>
</html>