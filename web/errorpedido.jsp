<%-- 
    Document   : errorpedido
    Created on : 31-ene-2021, 12:22:56
    Author     : Alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<script>	
function DestinoA(){
     
		document.myForm.action='menuusuario.jsp';
		document.myForm.submit();
	 	
}
</script>
<head> <title> BRAIN - SHELTER </title> 
    <link rel="stylesheet" type="text/css" href="css\csserrorstock.css?1.0" media="all">
<style>
body {
  background-image: url('imagenes/fondo15.png');
  background-size: 1680px;
}
</style>
</head>


<form name="myForm" method="get">


<font color="#000000 "  face="Cambria">	


<h1 align="center">ERROR EN INSERCIÓN DE DATOS</h1>

<h2 align="center"> EL PRODUCTO YA EXISTE <br><br>

<img src="imagenes/error.png" align="center" height=200 width=300><br><br>

<input type="button" value="VOLVER AL MENÚ" onclick="DestinoA()"><br><br>

</form>
</body>
</html>

