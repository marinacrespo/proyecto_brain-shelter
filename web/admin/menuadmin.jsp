<%-- 
    Document   : menuadmin.jsp
    Created on : 14-ene-2021, 11:11:21
    Author     : Alumno
--%>
<%@page session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<script>	

function DestinoA(){
     
		document.myForm.action='altaarticulos.jsp';
		document.myForm.submit();
}		
function DestinoB(){
     
		document.myForm.action='/BRAIN_SHELTER/ListarVentas';
		document.myForm.submit();
}		
function DestinoC(){
     
		document.myForm.action='/BRAIN_SHELTER/ListarClientes';
		document.myForm.submit();
}
		
function DestinoD(){
     
		document.myForm.action='indexadmin.jsp';
		document.myForm.submit();
	 	
}

</script>
<head> 

    <title>BRAIN - SHELTER</title>
    
    <link rel="stylesheet" type="text/css" href="../css/cssmenuadmin.css?1.0" media="all">
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

<body background="@A6.jpg">
<font color="#000000"  face="Cambria">

<h2> HOLA <%=nombreusuario%>, BOSS </h2>

<h1 align="center">MENÚ ADMINISTRADOR</h1>

<center>
<form name="myForm" method="get">



	<input type="button" value="ALTA ARTÍCULOS" onclick="DestinoA()">
	
	<input type="button" value="LISTAR VENTAS" onclick="DestinoB()">
	
	<input type="button" value="LISTAR CLIENTES" onclick="DestinoC()">
	
	<input type="button" value="CERRAR SESIÓN" onclick="DestinoD()">


</form>
</center>
</body>
</html>
