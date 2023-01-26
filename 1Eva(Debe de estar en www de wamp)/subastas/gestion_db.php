<?php 

// Función que realizara las querys y las devolvera
function realizarQuery($conn, $sql) {
    $resultado = $conn->query($sql);
    if($conn->errno) die($conn->error);
    return $resultado;
}

//Función para obtener todas las subastas vencidas
function obtenerTodasSubastasVencidas($conn){
    $hoy = date("Y-m-d H:i:s");
    $sql = "SELECT ITEMS.ID 'idItem', FECHAFIN, CATEGORIA, IMAGEN, DESCRIPCION, USERNAME
    FROM ITEMS, USUARIOS, CATEGORIAS, IMAGENES
    WHERE ID_CAT=CATEGORIAS.ID 
        AND ID_USER=USUARIOS.ID 
        AND ID_ITEM=ITEMS.ID
        AND FECHAFIN<'".$hoy."'";
    $result = realizarQuery($conn, $sql);
   return $result;
}

//Función para obtener informacion adicional de una subasta
function obtenerIformacionAdicionalSubasta($conn, $idItem){
    $sql = "SELECT MAX(CANTIDAD), PRECIOPARTIDA
    FROM PUJAS, ITEMS
    WHERE ID_ITEM=ITEMS.ID
        AND ITEMS.ID='".$idItem."'";
    $result = realizarQuery($conn, $sql);
    // Escribir la tabla
    while($fila = $result -> fetch_assoc()) {
        $cant = $fila['MAX(CANTIDAD)'];
        $preciopartida = $fila['PRECIOPARTIDA'];
        $porcentajeIncremento = $cant*100/$preciopartida;
        echo "Ganado por ".$cant."€<br>".$porcentajeIncremento."% superior al precio de partida (".$preciopartida."€)</p>";
    }
}

//Función para obtener todas las subastas vigentes
function obtenerTodasSubastasVigentes($conn){
    $hoy = date('Y-m-d');
    $sql = "SELECT ITEMS.ID 'idItem', FECHAFIN, PRECIOPARTIDA, DESCRIPCION, MAX(CANTIDAD)
    FROM ITEMS, PUJAS
    WHERE ID_ITEM = ITEMS.ID
        AND FECHAFIN>'".$hoy."'";
    $result = realizarQuery($conn, $sql);
   return $result;
}

function cambiarPrecioPartida($conn, $cantidad, $idItem){
    $sql = "UPDATE ITEMS SET PRECIOPARTIDA='$cantidad' WHERE ID='".$idItem."'";
    realizarQuery($conn, $sql);
}
?>