<%-- 
    Document   : errorarticulo
    Created on : 14-ene-2021, 13:01:15
    Author     : Alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<script>	
function DestinoA(){
     
		document.myForm.action='altaarticulos.jsp';
		document.myForm.submit();
	 	
}
</script>
<head> 
    <title> BRAIN - SHELTER </title> 

</head>
<body background="imagenes/fondo15admin.png">

<form name="myForm" action="menuemp.jsp" method="get">

<br><br><br>
<font color="#F80D0D "  face="Cambria">	

<font color="#F80D0D "  face="Cambria">	

<h1 align="center">ERROR EN INSERCIÓN DE DATOS</h1>

<h2 align="center"> EL PRODUCTO YA EXISTE <br><br>

<img src="imagenes/error.png" align="center" height=200 width=300><br><br>

<input type="button" value="VOLVER" onclick="DestinoA()"><br><br>

</form>
</body>
</html>
