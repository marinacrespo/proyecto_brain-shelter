

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script>	
function DestinoA(){
     
		document.ValidarUser.action='altausuario.jsp';
		document.ValidarUser.submit();
}
</script>

<head> <title> BRAIN - SHELTER </title> 
    <link rel="stylesheet" type="text/css" href="css\css2.css?1.0" media="all">
<style>
body {
  background-image: url('imagenes/fondo15.png');
  background-size: 1680px;
}
</style>
</head>



<br>
<center>
    <h1 > BIENVENIDO A BRAIN-SHELTER </h1>
</center>


<center>
<br>
<form name="ValidarUser" action="ValidarUser" method="GET">

<label for="login">Login:</label>
<input type="text" id="login" name="login"  placeholder="Escribe tu login" />
<label for="pwd">Password:</label>
<input type="password" id="pwd" name="pwd" placeholder="Escribe tu password" />

	
<input type="submit" value="INICIAR SESIÓN" >
<br>
<input type="button" value="REGISTRARSE"  onclick="DestinoA()">
		
</form>
</center>
</body>
</html>