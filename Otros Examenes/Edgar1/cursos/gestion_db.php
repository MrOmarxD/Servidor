<?php 

// Función que realizara las querys y las devolvera
function realizarQuery($conn, $sql) {
    $resultado = $conn->query($sql);
    if($conn->errno) die($conn->error);
    return $resultado;
}

// Función para obtener todas las categorias de los temas
function obtenerCategoriasTemas($conn) {
    $result = realizarQuery($conn, "SELECT DISTINCT(CATEGORIA) FROM TEMAS");
    $categorias = [];
    while($fila = $result -> fetch_assoc()) {
        // Se añade cada categoria al Array de categorias
        array_push($categorias, $fila['CATEGORIA']);
    }
    return $categorias;
}

// Función que se encarga de cargar la tabla temas dependiendo de la categoria seleccionada
function cargarTablaTemas($conn, $categoria) {
    // Consultar los datos a escribir en la tabla
    if ($categoria != 'todas') {
        $sql = "SELECT TEMA, COUNT(ID_CURSO) 'CANT', CURSOS.ID_TEMA 'ID'
                FROM CURSOS, TEMAS 
                WHERE CATEGORIA = '$categoria' 
                AND CURSOS.ID_TEMA = TEMAS.ID_TEMA
                GROUP BY TEMA";
    } else {
        $sql = "SELECT TEMA, COUNT(ID_CURSO) 'CANT', CURSOS.ID_TEMA 'ID'
                FROM CURSOS, TEMAS 
                WHERE CURSOS.ID_TEMA = TEMAS.ID_TEMA
                GROUP BY TEMA";
    }
    $result = realizarQuery($conn, $sql);
    // Escribir la tabla
    while($fila = $result -> fetch_assoc()) {
        $id = $fila['ID'];
        $cant = $fila['CANT'];
        $tema = $fila['TEMA'];
        echo "<tr>";
        echo "<td><input type='checkbox' name='temaSeleccionado[]' value='$id'></td>";
        echo "<td>$tema</td>";
        echo "<td>$cant Cursos</td>";
        echo "</tr>";
    }
}

// Función para obtener la cantidad de cursos que tiene un tema
function obtenerCantCursosPorTema($conn, $idTema) {
    $sql = "SELECT COUNT(ID_CURSO) 'CANT'
                FROM CURSOS
                WHERE ID_TEMA = $idTema";
    return realizarQuery($conn, $sql);
}

// En el examen no funcionaba saltaba un error, no sabiamos como arreglarlo
function cambiarPrecios($conn, $operacion) {
    $temas = $_POST['temaSeleccionado'];
    $porcentaje = $_POST['porcentajeCambio'];
    $cantTemasCambiados = 0;
    foreach ($temas as $idTema) { 
        $sql = "UPDATE BDCURSOS.CURSOS 
        SET CURSOS.PRECIO=(CURSOS.PRECIO *(1$operacion($porcentaje/100))) 
        WHERE CURSOS.ID_CURSO IN (
            SELECT ID_CURSO 
            FROM (
                SELECT ID_CURSO 
                FROM CURSOS, TEMAS 
                WHERE CURSOS.ID_TEMA=TEMAS.ID_TEMA
                AND "."TEMAS.ID_TEMA='".$idTema."') AS CURSOS)";
        realizarQuery($conn, $sql);
        $cantTemasCambiados += obtenerCantCursosPorTema($conn, $idTema);
    }
    return ($cantTemasCambiados);
}

/* Anulaciones */

// Consulta para obtener curso, edificio, aula y asistentes
function obtenerDatosParaTablaAnulaciones($conn) {
    $sql = "SELECT CURSOS.ID_CURSO AS CURSO ,EDIFICIOS.NOMBRE AS EDIFICIO,AULAS.ID_AULA AS AULA,TEMAS.TEMA AS TEMA , CURSOS.ASISTENTES AS ASISTENTES 
	FROM CURSOS, TEMAS, AULAS, EDIFICIOS WHERE CURSOS.ID_TEMA=TEMAS.ID_TEMA 
	AND CURSOS.ID_AULA=AULAS.ID_AULA AND AULAS.ID_EDIFICIO=EDIFICIOS.ID_EDIFICIO
	AND DATE_FORMAT(DIA, '%Y-%M-%D') = DATE_FORMAT(SYSDATE(), '%Y-%M-%D')
	ORDER BY TEMA DESC";
    $result = realizarQuery($conn, $sql);
    $infoCursos = [];
    while($fila = $result -> fetch_assoc()) {
        
        $curso = [
            "idCurso" => $fila['CURSO'],
            "edificio" => $fila['EDIFICIO'],
            "aula" => $fila['AULA'],
            "tema" => $fila['TEMA'],
            "asistentes" => $fila['ASISTENTES']
        ];
        array_push($infoCursos, $curso);
    }
    return $infoCursos;
}
// Functión que carga la tabla de anulaciones 
function cargarTablaAnulaciones($conn) {
    // Obtener los datos de la sesion
    // $_SESSION[0] contiene la fecha en la que se creo la sesion
    // $_SESSION[1] contiene los datos
    $cursos = $_SESSION['datosCursos'][1];
    foreach ($cursos as $curso) {
        // Guardar los datos en variables para un mejor manejo en los strings
        $idCurso = $curso['idCurso'];
        $edificio = $curso['edificio'];
        $aula = $curso['aula'];
        $tema = $curso['tema'];
        $asistentes = $curso['asistentes'];
        // Escribir cada fila de la tabla
        echo "<tr>";
        echo "<td>Curso $idCurso ($edificio $aula, $tema)</td>";
        echo "<td>$asistentes asistentes</td>";
        if ($asistentes > 0)
            echo "<td><a href='anulaciones.php?borrarAsistencia=$idCurso'>Cancelas 1 asistencia</a></td>";
        echo "</tr>";
    }
}

// Función que borra una asistencia en sesion pasandole el id del curso
function borrarUnaAsistenciaEnSesion($idCurso) {
    $cursos = $_SESSION['datosCursos'][1];
    for ($i=0; $i < count($cursos); $i++) { 
        if ($idCurso === $cursos[$i]['idCurso']) {
            $cursos[$i]['asistentes']--; 
        }
    }
    $_SESSION['datosCursos'][1] = $cursos;
}

// Función que guarda las anulaciones en la BBDD
function guardarAnulacionesEnBBDD($conn) {
    // Obtener los datos de la sesion
    $cursos = $_SESSION['datosCursos'][1];
    // Variable para contar la cantidad de anulaciones para poder guardarlas en la cookie
    $cantAnulaciones = 0;
    foreach ($cursos as $curso) {
        $idCurso = $curso['idCurso'];
        $asistencias = $curso['asistentes'];
        $asistenciasEnBBDD = obtenerAsistenciasActuales($conn, $idCurso);
        // Comprobar si han cambiado las asistencias
        if($asistencias != $asistenciasEnBBDD) {
            $sql = "UPDATE CURSOS SET ASISTENTES = $asistencias WHERE ID_CURSO = $idCurso";
            realizarQuery($conn, $sql);
            $cantAnulaciones += ($asistenciasEnBBDD - $asistencias);
        }
        
    }
    // Guardar en la cookie
    $contenidoCookie = json_decode($_COOKIE['anulaciones'], true);
    $contenidoCookie[0] = date('Y-m-d');
    $contenidoCookie[1] += $cantAnulaciones;
    setcookie("anulaciones",json_encode($contenidoCookie),time() + (86400 * 30));
    // Recargar la web para mostrar los cambios en la cookie
    header("Location: anulaciones.php");
}

// Función que devuelve las asistencias actuales de la bbdd de un curso pasado como parametro
function obtenerAsistenciasActuales($conn, $idCurso) {
    $sql = "SELECT ASISTENTES
            FROM CURSOS
            WHERE ID_CURSO = $idCurso";
    $res = realizarQuery($conn, $sql);
    $res = $res->fetch_assoc();
    return $res['ASISTENTES'];
}




