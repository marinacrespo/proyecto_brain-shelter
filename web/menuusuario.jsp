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
     
		document.myForm.action='ListarStock';
		document.myForm.submit();
}		
function DestinoB(){
     
		document.myForm.action='buscararticulo.jsp';
		document.myForm.submit();
}		
function DestinoC(){
     
		document.myForm.action='sSeleccionar';
		document.myForm.submit();
}
		
function DestinoD(){
     
		document.myForm.action='index.jsp';
		document.myForm.submit();
	 	
}

</script>
<head> <title>BRAIN - SHELTER</title>
    <link rel="stylesheet" type="text/css" href="css\cssusuario.css?1.0" media="all">
    
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

<h2 align="left">¡HOLA <%=nombreusuario%>! </h2>

<h1 align="center"> <br>MENÚ USUARIO </h1>

<center>
<form name="myForm" method="get">

        
	<input type="button" id="1" value="VER STOCK" onclick="DestinoA()">

	<input type="button" id="2" value="BUSCAR ARTÍCULO" onclick="DestinoB()">
	
	<input type="button" id="3" value="COMPRAR" onclick="DestinoC()">

	<input type="button" class="exit" value="CERRAR SESIÓN" onclick="DestinoD()">

</form>
</center>
</body>
</html>
