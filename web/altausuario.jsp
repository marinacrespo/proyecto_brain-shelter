<html>
<script>	

function DestinoA(){
     
		document.InsertarUsuario.action='InsertarUsuario';
		document.InsertarUsuario.submit();
}
function DestinoB(){
     
		document.InsertarUsuario.action='index.jsp';
		document.InsertarUsuario.submit();
}		
</script>
<head><title>BRAIN - SHELTER</title>
    <link rel="stylesheet" type="text/css" href="css/cssaltausuario.css?1.0" media="all">
    
<style>
body {
  background-image: url('imagenes/fondo15.png');
  background-size: 1680px;
}
</style>   
</head>

<body>
<font color="black"   face="Cambria">
<h1 align="center"> ALTA USUARIO </h1>
<br><br>
 
<center>
<form name="InsertarUsuario" method="get" action="InsertarUsuario">

 
    <label for="nombre">Nombre</label>
    <input type="text" name="nombre" placeholder="Tu nombre">
 
    <label for="apellidos">Apellidos</label>
    <input type="text" name="apellidos" placeholder="Tus apellidos">
 
    <label for="telefono">Telefono</label>
    <input type="text" name="telefono" placeholder="Tu teléfono">
    
    <label for="email">Email</label>
    <input type="text" name="email" placeholder="Tu email">
 
    <label for="direccion">Dirección</label>
    <input type="text" name="direccion" placeholder="Tu dirección">
 
    <label for="login">Login</label>
    <input type="text" name="login" placeholder="Tu login">

    <label for="password">Password</label>
    <input type="password" name="password" placeholder="Tu contraseña">

<input type="button" value="INSERTAR DATOS" onclick="DestinoA()">


</form>
</center>


<input type="button" value="VOLVER AL MENÚ" onclick="DestinoB()">



</body>
</html>