<%-- 
    Document   : altaarticulos
    Created on : 14-ene-2021, 12:02:39
    Author     : Alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@page session="true"%>

<%
// CODIGO DE PREGUNTAR SESION 
HttpSession sesion = request.getSession();
String nombreusuario = (String) sesion.getAttribute("login");


%>

<html>
<script>	

function DestinoA(){
     
		document.myForm.action='BRAIN_SHELTER/InsertarArticulos';
		document.myForm.submit();
}
function DestinoB(){
     
		document.myForm.action='menuadmin.jsp';
		document.myForm.submit();
}		
</script>
<head><title>BRAIN - SHELTER</title>
    <link rel="stylesheet" type="text/css" href="../css/cssaltaarticulos.css?1.0" media="all">
    
<style>
body {
  background-image: url('../imagenes/fondo15admin.png');
  background-size: 1680px;
}
</style>   
</head>


<font color="#000000"  face="Cambria">

<h2>HOLA <%=nombreusuario%>, BOSS </h2>

<font color="#000000"    face="Cambria">

<h1 align="center"> ALTA ARTICULO </h1>


<center>
<form name="myForm" method="get" action="InsertarArticulos">

<label for="nombre">Título:</label>
<input type="text" name="titulo">

<label for="nombre">Descripción:</label>
<input type="text" name="desc_art">

<label for="nombre">PVP: </label>
<input type="text" name="precio"> 
 
<label for="nombre">Unidades: </label>
<input type="text" name="unidades">

<label for="nombre">URL Foto:</label>
<input type="text" name="foto">


<input type="submit" value="INSERTAR DATOS" >

</form>
</center>

<input type="button" value="VOLVER AL MENÚ" onclick="DestinoB()">

</body>
</html>
