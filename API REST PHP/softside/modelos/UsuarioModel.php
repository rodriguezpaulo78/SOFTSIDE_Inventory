<?php
	//Para cargar todos lo archivos dentro de librerias/*
	spl_autoload_register(function($nombreClase){
		require_once '../controladores/'.$nombreClase.'.php';
	});

 	require_once '../controladores/UsuarioController.php';

 	# Recibimos los datos leídos de php://input
	$datosRecibidos = file_get_contents("php://input");
	# No los hemos decodificado, así que lo hacemos de una vez:
	$getDatos = json_decode($datosRecibidos);

	$metodo = $getDatos->metodo;

 	$usuario = new UsuarioController();

 	// Obtener codigo
	if($metodo == 1){
 		$json_cod = $usuario->getUserCod();
 		echo json_encode($json_cod);
 	}

 	// Registrar usuario
 	if ($metodo == 2) {
 		$codigo = $getDatos->codigo;
 		$username = $getDatos->username;
 		$password = $getDatos->password;
 		$dni = $getDatos->dni;
 		$nombres = $getDatos->nombres;
 		$apellidos = $getDatos->apellidos;
 		$fecha_nac = $getDatos->fecha_nac;
 		$cargo = $getDatos->cargo;
 		$tipo = $getDatos->tipo;

 		$datos=[$codigo, $nombres, $apellidos, $dni, $fecha_nac, $cargo, $username, $password, $tipo];

 		$json_registrar = $usuario->agregarUsuario($datos);
 		echo json_encode($json_registrar);
 	}

 	// Modificar usuario
 	if ($metodo == 3) {
 		$codigo = $getDatos->codigo;
 		$username = $getDatos->username;
 		$password = $getDatos->password;
 		$dni = $getDatos->dni;
 		$nombres = $getDatos->nombres;
 		$apellidos = $getDatos->apellidos;
 		$fecha_nac = $getDatos->fecha_nac;
 		$cargo = $getDatos->cargo;
 		$tipo = $getDatos->tipo;

 		$datos=[$codigo, $nombres, $apellidos, $dni, $fecha_nac, $cargo, $username, $password, $tipo];

 		$json_modificar = $usuario->modificarUsuario($datos);
 		echo json_encode($json_modificar);
 	}

 	// Listar usuarios
 	if ($metodo == 4) {
 		$json_listar = $usuario->listarUsuarios();
 		echo json_encode($json_listar);
 	}

 	// Listar usuario por codigo
 	if ($metodo == 5) {
 		$codigo = $getDatos->codigo;
 		$json_list = $usuario->getUserByCod($codigo);
 		echo json_encode($json_list);
 	}

 	// Eliminar usuario
 	if ($metodo == 6) {
 		$codigo = $getDatos->codigo;
 		$json_eliminar = $usuario->eliminarUsuario($codigo);
 		echo json_encode($json_eliminar);
 	}

 	// Buscar usuario
 	if ($metodo == 7) {
 		$dato = $getDatos->dato;
 		$filtro = $getDatos->filtro;
 		$json_buscar = $usuario->buscarUsuario($dato, $filtro);
 		echo json_encode($json_buscar);
 	}

 ?>
