<?php
class Mprestamo extends CI_Model
{
  function __construct(){
    parent::__construct();
  }   
 
  // Devulve un array con todos los generos
  function todosgeneros(){        
    $rs=$this->db->query("select distinct genero from libros");
    return $rs->result_array();
  }

  // Devuelve un array con el nombre del autor, el libro y id de los libros de un genero
  function librosautorgenero($genero){
    $rs=$this->db->query("select idlibro, titulo, nombre from libros, autores where genero='".$genero."' and libros.idautor=autores.idautor");
    return $rs->result_array();
  }

  // Devulve el numero de veces que se a prestado un libro
  function numeroVecesPrestado($idlibro){
    $rs=$this->db->query("select * from prestamos where idlibro='".$idlibro."'");
    return $rs->num_rows();
  }

  // Devulve el titulo de un libro pasando una id
  function obtenertitulolibro($idlibro){
    $rs=$this->db->query("select titulo from libros where idlibro='".$idlibro."'");
    return $rs->result_array();
  }

  // Insertar prestamo nuevo
  function insertarprestamo($idlibro){
    //$this->db->query("insert into prestamos (fecha, idlibro) values ('".date('Y-m-d')."' , '".$idlibro."')");
    $data = array("fecha"=>date('Y-m-d'), "idlibro"=>$idlibro);
    $this->db->insert('prestamos', $data);
  }

  // Devulve el titulo de un libro pasando una id
  function prestamosrealizadosundia($dia){
    $fecha = date('Y-m-'.$dia);
    $rs=$this->db->query("select distinct titulo from prestamos, libros where fecha='".$fecha."' and prestamos.idlibro=libros.idlibro");
    return $rs->result_array();
  }

  // Devuelve todos los libros que se estan en prestamo
  function obtenerLibrosPrestados(){
    $rs=$this->db->query("select distinct titulo, libros.idlibro from prestamos, libros where prestamos.idlibro=libros.idlibro");
    return $rs->result_array();
  }

  // Eliminar libros de la tabla prestamos
  function eliminarLibroTablaPrestamo($idlibro){
    $this->db->where('idlibro', $idlibro);
    $this->db->delete('prestamos');
  }

  // Devuelve todos los prestamos de un libro
  function obtenerPrestamosLibro($idlibro){
    $rs=$this->db->query("select * from prestamos where prestamos.idlibro='".$idlibro."'");
    return $rs->result_array();
  }
}
?>