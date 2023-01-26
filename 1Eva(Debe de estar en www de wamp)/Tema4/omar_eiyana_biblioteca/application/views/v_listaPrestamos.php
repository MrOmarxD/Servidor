<?php 
    if(count($librosprestados)!=0){
        echo "<h2>LIBROS PRESTADOS</h2>";
        echo "<ul>";
        foreach ($librosprestados as $libro){
            echo "<li>".$libro[0]['titulo']."</li>";
        }
        echo "</ul>";
    }
    if(count($librosnoprestados)!=0){
        echo "<h2>LIBROS NO PRESTADOS</h2>";
        echo "<ul>";
        foreach ($librosnoprestados as $libro){
            echo "<li>".$libro[0]['titulo']."</li>";
        }
        echo "</ul>";
    }
?>