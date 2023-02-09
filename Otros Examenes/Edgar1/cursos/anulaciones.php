<?php 
    // Llamada al header
    require 'header.php';
    $fechaHoy = date('Y-m-d');
    // Guardar los datos de los cursos en sesion
    if (!isset($_SESSION['datosCursos'])) {
        $_SESSION['datosCursos'][0] = $fechaHoy;
        $_SESSION['datosCursos'][1] = obtenerDatosParaTablaAnulaciones($conn);
    } else {
        if ($_SESSION['datosCursos'][0] != $fechaHoy) {
            $_SESSION['datosCursos'][0] = $fechaHoy;
            $_SESSION['datosCursos'][1] = obtenerDatosParaTablaAnulaciones($conn);  
        }
    }
    // Llamada a la funcion para borrar una asistencia en sesion
    if (isset($_GET['borrarAsistencia'])) {
        borrarUnaAsistenciaEnSesion($_GET['borrarAsistencia']);
    }
?>
    <h2>CURSOS DE HOY (<?= $fechaHoy ?>)</h2>
    <?php 
        // Llamada a guardar Anulaciones en la BBDD
        if (isset($_GET['guardarAnulaciones'])) {
            guardarAnulacionesEnBBDD($conn);
            echo "<p>Las anulaciones han sido guardadas correctamentes</p>";
        }
    ?>
    <table>
        <?php cargarTablaAnulaciones($conn); ?>
    </table>
    <a href='anulaciones.php?guardarAnulaciones'>Guardar anulaciones</a>
<?php 
    // Llamada al footer
    require 'footer.php';
?>