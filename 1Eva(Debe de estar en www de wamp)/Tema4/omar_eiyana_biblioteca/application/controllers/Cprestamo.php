<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Cprestamo extends CI_Controller {

	function __construct(){
            parent::__construct();            
            $this->load->model('mprestamo');
	}
    public function index(){
        $this->load->library('session');
        $this -> cabecera();
        $this -> pie();
    }
    function cabecera(){
        // Estilo css
        $data['hojaestilos'] = base_url()."estilos/stylesheet.css";
        // Consultar de gerneros
        $data['arraygeneros'] = $this->mprestamo->todosgeneros();
        $this->load->view('v_tituloprestamo',$data);
    }
    function pie(){
        // Pie
        $this->load->view('vpie');
    }

    // Crea la tabla con los libro y autores del genroo especificado
    public function tablalibroautor($genero){
        $this -> cabecera();
        $this->session->set_userdata('genero', $genero);
        $data['librosautor'] = $this->mprestamo->librosautorgenero($genero);
        $this->load->view('v_tablalibrosautor',$data);
        $this -> pie();
    }

    // Validar Formulario al intentar alquilar un libro
    public function validarFormulario(){
        $this -> cabecera();
        $genero = $this->session->userdata('genero');
        if(isset($_POST['libroseleccionados'])){
            $data['libroseleccionados'] = $_POST['libroseleccionados'];
        }
        $data['librosautor'] = $this->mprestamo->librosautorgenero($genero);
        $this->load->view('v_tablalibrosautor',$data);

        // Comprobamos si el libro lo ha prestado mas de 4 veces
        if(isset($_POST['libroseleccionados'])){
            $arraylibrosprestados = [];
            $arraylibrosnoprestados = [];
            $arrayLibrosSeleccionados = $_POST['libroseleccionados'];
            foreach($arrayLibrosSeleccionados as $idlibro){

                $cantidadVecesPrestado = $this->mprestamo->numeroVecesPrestado($idlibro);
                if($cantidadVecesPrestado>3)
                    $arraylibrosnoprestados[] = $this->mprestamo->obtenertitulolibro($idlibro);
                else{
                    $arraylibrosprestados[] = $this->mprestamo->obtenertitulolibro($idlibro);
                    $this->mprestamo->insertarprestamo($idlibro);
                }
            }
            $data['librosnoprestados'] = $arraylibrosnoprestados;
            $data['librosprestados'] = $arraylibrosprestados;
            $this->load->view('v_listaPrestamos',$data);
        }
        $this -> pie();
    }

    // Dibujar un calendario con links en los diferentes días 
    public function calendario(){
        $this->load->library('calendar');
        $arraylinkdias = [];
        // obtenemos el numero de dias que tiene este mes
        $d = cal_days_in_month(CAL_GREGORIAN,date("m"),date("Y"));
        for($i = 1; $i<=$d; $i++){
            $arraylinkdias[$i] = site_url()."/Cprestamo/tablaprestamosdia/".$i;
        }
        $data['arraylinkdias'] = $arraylinkdias;
        $this -> cabecera();
        $this->load->view('v_calendario', $data);
        $this -> pie();
    }

    // Mostrar una tabla con los prestamos que se hacen un día determinado
    public function tablaprestamosdia($dia){
        $this -> cabecera();
        $data['fecha'] = date('Y-m-'.$dia);
        $data['arrayPrestamosDia'] = $this->mprestamo->prestamosrealizadosundia($dia);
        $this->load->view('v_tablaPrestamoDia', $data);
        $this -> pie();
    }

    // Dibujar un select con los libros que tiene tuplas en prestados
    public function dibujarSelectLibrosPrestados(){
        $this -> cabecera();
        $data['arrayLibrosPrestados'] = $this->mprestamo->obtenerLibrosPrestados();
        $this->load->view('v_librosprestados', $data);
        $this -> pie();
    }

    // Escribimos una lista con los prestamos que hay de un libro en concreto
    public function dibujarPrestamosLibro(){
        $this -> cabecera();
        $idlibro = $this->input->post('librosPrestados');
        $this->session->set_userdata('idlibro', $idlibro);
        $data['idlibro'] = $idlibro;
        $data['arrayLibrosPrestados'] = $this->mprestamo->obtenerLibrosPrestados();
        $this->load->view('v_librosprestados', $data);
        $data['arrayPrestamosLibro'] = $this->mprestamo->obtenerPrestamosLibro($idlibro);
        $this->load->view('v_linksprestamos', $data);
        $this -> pie();
    }

    //Funcion que devuelve un libro prestado
    function devolverLibro(){
        // $this->mprestamo->eliminarLibroTablaPrestamo($idlibro);
        dibujarPrestamosLibro();
    }
}