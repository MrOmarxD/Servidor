<?php 
    // Llamada al header
    require 'header.php';
    if (isset($_POST['categoria'])) {
        $_SESSION['catSeleccionada'] = $_POST['categoria'];
    }
    // Borrar de sesión la categoria seleccionada si se van a subir o bajar precios
    if ((isset($_POST['subirPrecio']) || isset($_POST['bajarPrecio'])) && isset($_POST['temaSeleccionado'])) {
        unset($_SESSION['catSeleccionada']);
    }
?>
    <h2>Elija la categoria de cursos</h2>
    <form action="#" method="post">
    <?php 
        $categorias = obtenerCategoriasTemas($conn); 
        foreach ($categorias as $cat) {
            // darle el check a la categoria previamente seleccionada
            if (isset($_SESSION['catSeleccionada']) && $cat === $_SESSION['catSeleccionada'])
                echo "<input type='radio' name='categoria' id='$cat' value='$cat' checked>";
            else
                echo "<input type='radio' name='categoria' id='$cat' value='$cat'>";
            echo "<label for='categoria'>$cat</label>";
            echo "<br>";
        }
        // darle el check a la categoria previamente seleccionada si es 'todas'
        if (isset($_SESSION['catSeleccionada']) && $_SESSION['catSeleccionada'] === 'todas')
            echo "<input type='radio' name='categoria' id='todas' value='todas' checked>";
        else
            echo "<input type='radio' name='categoria' id='todas' value='todas'>";
        echo "<label for='todas'>TODAS LAS CATEGORIAS</label>";
    ?>
        <br>
        <input type="submit" value="Ver cursos" name='verCursos'>
    </form>
    <?php 
    // Si se pulsa el boton ver cursos y hay una categoria seleccionada
    // o se pulsa el boton subir/bajar precio pero no hay ningún tema seleccionado
        if(isset($_POST['verCursos']) && isset($_POST['categoria']) || ((isset($_POST['subirPrecio']) || isset($_POST['bajarPrecio'])) && !isset($_POST['temaSeleccionado']))) {
            echo "<hr>";
            echo "<h2>Imparticiones de cursos de categoria ".$_SESSION['catSeleccionada']."</h2>";
            echo "<form action='#' method='post'>";
            echo "<table>";
                echo "<tr>";
                    echo "<th>SELECCIONAR</th>";
                    echo "<th>TEMA</th>";
                    echo "<th>CANTIDAD DE CURSOS</th>";
                echo "</tr>";
                // Cargar filas de la tabla
                cargarTablaTemas($conn ,$_SESSION['catSeleccionada']);
            echo "</table>";
            echo "<input type='submit' value='Subir Precio' name='subirPrecio'>";
            echo "<select name='porcentajeCambio'>";
                // Cargar porcentajes en el select
                for ($i=5; $i <= 50; $i+=5)  
                    echo "<option value='$i'>$i%</option>";
            echo "</select>";
            echo "<input type='submit' value='Bajar Precio' name='bajarPrecio'>";
            echo "</form>";
        }
        // Si se ha pulsado el boton subir/bajar precio y un tema
        if ((isset($_POST['subirPrecio']) || isset($_POST['bajarPrecio'])) && isset($_POST['temaSeleccionado'])) {
            echo "<hr>";
            $signo = '-';
            if (isset($_POST['subirPrecio'])) 
                $signo = '+';
            cambiarPrecios($conn, $signo);
            echo "<p>Se han cambiado el precio de <b>".count($_POST['temaSeleccionado'])."</b> cursos</p>";
        }
        // Llamada al footer
        require 'footer.php';
    ?>