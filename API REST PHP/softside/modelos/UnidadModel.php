<?php
	//Para cargar todos lo archivos dentro de librerias/*
	spl_autoload_register(function($nombreClase){
		require_once '../controladores/'.$nombreClase.'.php';
	});

 	require_once '../controladores/UnidadController.php';

 	# Recibimos los datos leídos de php://input
	$datosRecibidos = file_get_contents("php://input");
	# No los hemos decodificado, así que lo hacemos de una vez:
	$getDatos = json_decode($datosRecibidos);

	$metodo = $getDatos->metodo;

 	$unidad = new UnidadController();

 	// Obtener codigo
	if($metodo == 1){
 		$json_cod = $unidad->getUnidadCod();
 		echo json_encode($json_cod);
 	}

 	// Registrar unidad
 	if ($metodo == 2) {
 		$codigo = $getDatos->codigo;
 		$descripcion = $getDatos->descripcion;

 		$datos=[$codigo, $descripcion];

 		$json_registrar = $unidad->agregarUnidad($datos);
 		echo json_encode($json_registrar);
 	}

 	// Modificar unidad
 	if ($metodo == 3) {
 		$codigo = $getDatos->codigo;
 		$descripcion = $getDatos->descripcion;

 		$datos=[$codigo, $descripcion];

 		$json_modificar = $usuario->modificarUnidad($datos);
 		echo json_encode($json_modificar);
 	}

 	// Listar unidades
 	if ($metodo == 4) {
 		$json_listar = $unidad->listarUnidades();
 		echo json_encode($json_listar);
 	}
/*
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
*/
 ?>
