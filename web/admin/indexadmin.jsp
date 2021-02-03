
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <script>
        
        function DestinoA(){
     
		document.myForm.action='/BRAIN_SHELTER/ValidaAdminTest';
		document.myForm.submit();
}
    </script>
<head> 

    <title>BRAIN - SHELTER</title>
    
    <link rel="stylesheet" type="text/css" href="../css/cssindexadmin.css?1.0" media="all">
<style>
body {
  background-image: url('../imagenes/fondo15admin.png');
  background-size: 1680px;
}
</style>
</head>
<body>


<center>
    <h1 > ADMIN VIEW </h1></center>

<center>
<form name="myForm" method="GET">


            <label for="login">Login:</label> 
            <input type="text" id="login" name="login"  placeholder="Escribe tu login" />
            <label for="pwd">Password:</label> 
            <input type="password" id="pwd" name="pwd" placeholder="Escribe tu password" />
        	
        <input type="button" value="INICIAR SESIÓN" onclick="DestinoA()">
		
</form>
</center>
</body>
</html>
