<?php 

// Función que realizara las querys y las devolvera
function realizarQuery($conn, $sql) {
    $resultado = $conn->query($sql);
    if($conn->errno) die($conn->error);
    return $resultado;
}

// Función para obtener todos los buses
function obtenerBuses($conn) {
    $result = realizarQuery($conn, "SELECT * FROM bus");
    $lstBuses = [];
    while($bus = $result -> fetch_assoc()) {
        // Se añade cada bus al Array de buses
        array_push($lstBuses, $bus);
    }
    return $lstBuses;
}

// Función para obtener todos las rutas de un bus
function obtenerRutasBus($conn, $id) {
    $result = realizarQuery($conn, "SELECT * FROM ruta WHERE bus ='".$id."' ORDER BY horaSalida");
    $lstRutasBus = [];
    while($ruta = $result -> fetch_assoc()) {
        // Se añade cada ruta al Array de RutasBus
        array_push($lstRutasBus, $ruta);
    }
    return $lstRutasBus;
}

// Función para obtener una rutas pasando el id
function obtenerRuta($conn, $id) {
    $result = realizarQuery($conn, "SELECT * FROM ruta WHERE id ='".$id."'");
    $ruta = $result -> fetch_assoc();
    return $ruta;
}

// Función para obtener capacidad de un bus
function obtenerCapacidad($conn, $id) {
    $result = realizarQuery($conn, "SELECT capacidad FROM bus WHERE id ='".$id."'");
    $bus = $result -> fetch_assoc();
    return $bus['capacidad'];
}

// Función para obtener una lista de asientos reservados de un bus en una ruta
function obtenerAsientosReservados($conn, $id_ruta) {
    $result = realizarQuery($conn, "SELECT * FROM reserva WHERE ruta ='".$id_ruta."'");
    $lstAsientosReservados = [];
    while($reserva = $result -> fetch_assoc()) {
        // Se añade cada reserva al Array de Asientos Reservados
        array_push($lstAsientosReservados, $reserva['numAsiento']);
    }
    return $lstAsientosReservados;
}
?>