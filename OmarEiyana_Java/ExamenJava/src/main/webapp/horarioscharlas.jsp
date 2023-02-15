<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
        <title>APLICACIÓN CHARLAS</title>
        <%!
             String fechaHoy()
             {
                 Date hoy=new Date();
                 SimpleDateFormat f=new SimpleDateFormat("dd/MM/yyyy");
                 return f.format(hoy);
                 //return hoy.getDate() +  "-" + (hoy.getMonth()+1) + "-" + (hoy.getYear()+1900);                    
             }
        %>
    </head>
    <body>
   		 <div id="header">
            <h1>APLICACIÓN CHARLAS</h1> <!-- Nombre de la página -->
        </div>
        <div id="menu">
        	<jsp:include page="menu.jsp"></jsp:include>
        </div>
        <div id="container">
	          <% if (request.getAttribute("error")!=null)
	           {
	                out.println("<h3>" +request.getAttribute("error") +"</h3>");
	            }
	            %>
	         <form method="post" action="ServletPrepararCharlas">            
	             <h2> Programando ponencias para HOY (<%= fechaHoy()  %>)</h2>
	             <table>
	                 <tr>
	                     <% 
	                     	//TODO - Recuperar los horarios de los parametros de inicio del Servlet
	                     	int hora= 0;
	                     	int min= 0;
	                     	if (request.getAttribute("hora")!=null){
	                     		hora = (int) session.getAttribute("hora");
		                     	min= (int) session.getAttribute("minutos");
	                     	}
	                     %>
	                    <td>
	                        Desde las <input type="text" name="horaini" value="<%= hora %>"  size="2" /> h
	                        <input type="text" name="minutosini" value="<%= min %>" size="2" /> m
	                    </td>
	               	</tr>
	               	<tr>
	                   	<td>
	                         cada <input type="text" name="intervalo" size="5" /> minutos
	                    </td>    
	              	 </tr>
	              	 <tr>                                                    
	                    <td>
	                        <input type="submit" value="Organizar ponencias" name="submit1" />
	                    </td>
	                </tr>
	       		</table>
			</form>
		</div>
    </body>
</html>
