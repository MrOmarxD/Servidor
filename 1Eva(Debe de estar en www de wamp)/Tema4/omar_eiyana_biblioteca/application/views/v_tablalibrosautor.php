<table>
    <tr>
        <th></th>
        <th>LIBRO</th>
        <th>AUTOR</th>
    </tr>
    <form action="<?php echo site_url()."/Cprestamo/validarFormulario" ?>" method="post">
        <?php 
            foreach($librosautor as $data){
                $check = "";
                if(isset($libroseleccionados)){
                    if(in_array($data['idlibro'], $libroseleccionados))
                        $check = "checked";
                }
                echo "<tr>";
                    echo "<td><input type='checkbox' name='libroseleccionados[]' value='".$data['idlibro']."' ".$check."></td>";
                    echo "<td>".$data['titulo']."</td>";
                    echo "<td>".$data['nombre']."</td>";
                echo "</tr>";
            }
        ?>
        <tr>
            <td colspan="3"><input type="submit" value="PRESTAR LIBROS" style='padding: 6px 12px 5px; font-size: 14px; background-color: #555555; font-weight: bold; color: #ffffff; border-radius: 30px; border: 1px solid #999;'></td>
        </tr>
    </form>
</table>