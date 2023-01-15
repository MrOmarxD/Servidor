<%@page import="java.util.ArrayList"%>

    <h2>TU CARRO</h2>
    <ul>
        <%
            ArrayList<String> productos=(ArrayList<String>)session.getAttribute("productos");
            ArrayList<Integer> compras=(ArrayList<Integer>)session.getAttribute("compras");
            int cont=0;
            for (Object producto : productos) {
                if(compras.get(cont)>0){
                    %>
                    <li><b><%=producto%></b>: <%=compras.get(cont)%></li>
                    <%
                }
                cont++;
            }
        %>
    </ul>