<?php
	//Para cargar todos lo archivos dentro de librerias/*
	spl_autoload_register(function($nombreClase){
		require_once '../controladores/'.$nombreClase.'.php';
	});

 	require_once '../controladores/SesionController.php';

 	# Recibimos los datos leídos de php://input
	$datosRecibidos = file_get_contents("php://input");
	# No los hemos decodificado, así que lo hacemos de una vez:
	$getDatos = json_decode($datosRecibidos);

 	$sesion = new SesionController();

 	$user= $getDatos->usuario;
 	$password = $getDatos->password;

 	if(!empty($user) && !empty($password)){
 		$json_array = $sesion->loginUser($user, $password);
 		echo json_encode($json_array);
 	}
 ?>
