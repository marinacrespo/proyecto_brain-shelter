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
     
		document.myForm.action='BuscarArticulos';
		document.myForm.submit();
}
		
function DestinoB(){
     
		document.myForm.action='menuusuario.jsp';
		document.myForm.submit();
	 	
}

</script>
<head><title>BRAIN - SHELTER</title>
    <link rel="stylesheet" type="text/css" href="css\cssbuscarart.css?1.0" media="all">
    
<style>
body {
  background-image: url('imagenes/fondo15.png');
  background-size: 1680px;
}
</style> 
</head>

<%
    HttpSession sesion = request.getSession();
    String nombreusuario = (String) sesion.getAttribute ("login");
%>

<body>

<h2 align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DIME <%=nombreusuario%>, ¿QUÉ NECESITAS? </h2>


<center>
<form name="myForm" method="get">

 
    <label for="tituloART">Nombre de artículo:</label>
    <input type="text" name="tituloART" placeholder="Artículo">

    <input type="button" value="BUSCAR" onclick="DestinoA()">
    
    <input type="button" value="VOLVER AL MENÚ" onclick="DestinoB()">
	
</form>
</center>
</body>
</html>