<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <form name="formulario" action="compra.jsp" method="POST">
                <tr>
                    <th>PRODUCTO</th>
                    <th>PEDIR</th>
                </tr>
                <% 
                    ArrayList<String> productos=(ArrayList<String>)session.getAttribute("productos");
                    ArrayList<Integer> compras;
                    boolean alguno=false;
                    if(session.getAttribute("compras")==null){
                        compras = new ArrayList();
                        int index=0;
                        for (Object producto : productos) {
                            compras.add(index, 0);
                            index++;
                        }
                        
                    } else {
                        compras=(ArrayList<Integer>)session.getAttribute("compras");
                    }
                    int cont=0;
                    for (Object producto : productos) {
                %>
                    <tr>
                        <td><%=producto%></td>
                        <td><input type="submit" name="<%=cont%>" value="Adquirir unidad"></td>
                        <%
                            if(request.getParameter(cont+"")!=null){
                                if(compras.get(cont)!=null){
                                    compras.set(cont, compras.get(cont)+1);
                                } else {
                                    compras.set(cont, 1);
                                }
                            }
                            session.setAttribute("compras", compras);
                            if(compras.get(cont)>0){
                                alguno=true;
                        %>
                        <td><%=compras.get(cont)%> unidades</td>
                        <%
                            }
                            cont++;
                        }
                        %>
                </tr>
            </form>
        </table>
                <%
                    if(alguno){
                %>
                	<jsp:include page="muestracarro.jsp"/>
                <%}%>
    </body>
</html>