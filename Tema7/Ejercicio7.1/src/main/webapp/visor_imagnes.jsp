<%@page import="java.util.ArrayList"%>
<%@page import="beans.Imagen"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    final String RUTA = "img";
    
    File carpeta = new File(this.getServletContext().getRealPath(RUTA));
    ArrayList<Imagen> lstImagenes = new ArrayList<Imagen>();
    
    File[] list = carpeta.listFiles();
    for(int i=0;i<list.length;i++){
       String ruta = this.getServletContext().getContextPath()+"/"+RUTA+"/"+list[i].getName();
       String nombre = list[i].getName().split("\\.")[0];
       long tamanio = list[i].length();
       Imagen imagen = new Imagen(ruta, nombre, tamanio);
       lstImagenes.add(imagen);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="./visor_imagenes.jsp" method="POST">
            <label for="imagenes">Imagen: </label>
            <select name="imagenes">
                <%
                    int num=0;
                    for (Imagen imagen : lstImagenes) {  
                %>
                        <option value="<%=num%>"><%=imagen.getNombre()%></option>
                <%  
                        num++;
                    }
                %>
            </select>
            Tamaño:
            <input type="radio" name="tamanyo" id="300px" value="300px" checked/>
            <label for="300px">300 px</label>
            <input type="radio" name="tamanyo" id="400px" value="400px" />
            <label for="400px">400 px</label>
            <input type="radio" name="tamanyo" id="500px" value="500px" />
            <label for="500px">500 px</label>
            <input type="submit" name="enviar" value="VER IMAGEN">
        </form>
        <%
            if(request.getParameter("enviar")!=null){
                int posicion = Integer.parseInt(request.getParameter("imagenes"));
        %>
        <p>Tamaño <%=lstImagenes.get(posicion).tamanioDesglosado()%></p>
        <img src="<%=lstImagenes.get(posicion).getRuta()%>" width="<%=request.getParameter("tamanio")%>"/>

        <%        
            }
        %>
    </body>
</html>