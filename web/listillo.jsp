<%@page session="true"%>
<%@page isErrorPage="true"%>


<html>
<script>	
function DestinoA(){
     
		document.myForm.action='index.jsp';
		document.myForm.submit();
	 	
}
</script>

<head> 
    <title> BRAIN - SHELTER </title>
    <link rel="stylesheet" type="text/css" href="css/csslistillo.css?1.0" media="all">
<style>
body {
  background-image: url('imagenes/fondo15.png');
  background-size: 1680px;
}
</style>
</head>

<body>

<form name="myForm" action="menuemp.jsp" method="get">


<font color="#F80D0D "  face="Cambria">	

<h1 align="center">ERROR EN INSERCIÓN DE DATOS</h1>

<h1 align="center"> ACCESO DENEGADO </h1>

<img src="imagenes/error.jpg" align="center" height=200 width=300></img>
<input type="button" value="SALIR" onclick="DestinoA()"></input>
<h2> LISTILLO TE HAS COLADO </h2>


</form>
</body>
</html>

