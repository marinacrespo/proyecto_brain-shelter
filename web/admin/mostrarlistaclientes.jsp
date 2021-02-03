
<%@page import="java.util.Iterator"%>
<%@page import="Clases.Clientes"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


 <!DOCTYPE html>
<html>
<script>	

function DestinoA(){
     
		document.myForm.action='menuadmin.jsp';
		document.myForm.submit();
}

</script>
<head><title>BRAIN - SHELTER</title>
    <link rel="stylesheet" type="text/css" href="../css/cssmostrarclientes.css?1.0" media="all">
    
<style>
body {
  background-image: url('../imagenes/fondo15admin.png');
  background-size: 1680px;
}
</style>
</head>

<%
    HttpSession sesion = request.getSession();
    String nombreusuario = (String) sesion.getAttribute ("login");
%>

<body>
<font color="#000000"  face="Cambria">

<h2>HOLA <%=nombreusuario%>, BOSS </h2>

<h1>LISTADO DE CLIENTES</h1>

<form name="myForm" method="get">


<table>
<TR> 
    <th>Código Usuario</th>
    <th>Login</th>
    <th>Password</th>
    <th>Código Cliente</th>
    <th>Nombre</th>
    <th>Apellidos</th>
    <th>Teléfono</th>
    <th>Email</th>
    <th>Dirección</th>
</TR>
<%
ArrayList listaclientes = (ArrayList) request.getSession().getAttribute("listaCLI");
 
Iterator it = listaclientes.iterator();
        
while(it.hasNext()){
Clientes cli_tmp = (Clientes) it.next();
%> 
<tr>
    <td><%=cli_tmp.getCoduser()%></td>
    <td><%=cli_tmp.getLogin()%></td>
    <td><%=cli_tmp.getPassword()%></td>
    <td><%=cli_tmp.getCodcliente()%></td>
    <td><%=cli_tmp.getNombre()%></td>
    <td><%=cli_tmp.getApellidos()%></td>
    <td><%=cli_tmp.getTelefono()%></td>
    <td><%=cli_tmp.getEmail()%></td>
    <td><%=cli_tmp.getDireccion()%></td>
<%}
request.getSession().removeAttribute("listaCLI");
%>
</table>



</form>

<input type="button" value="VOLVER AL MENÚ" onclick="DestinoA()">
<a download href="http://localhost:8080/excel/listadoclientes.xlsx" >LINK - DESCARGAR EXCEL</a>


</body>
</html>
