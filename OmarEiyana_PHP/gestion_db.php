<?php 

// Función que realizara las querys y las devolvera
function realizarQuery($conn, $sql) {
    $resultado = $conn->query($sql);
    if($conn->errno) die($conn->error);
    return $resultado;
}
?>