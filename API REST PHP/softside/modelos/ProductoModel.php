<?php
	//Para cargar todos lo archivos dentro de librerias/*
	spl_autoload_register(function($nombreClase){
		require_once '../controladores/'.$nombreClase.'.php';
	});

 	require_once '../controladores/ProductoController.php';

 	# Recibimos los datos leídos de php://input
	$datosRecibidos = file_get_contents("php://input");
	# No los hemos decodificado, así que lo hacemos de una vez:
	$getDatos = json_decode($datosRecibidos);

	$metodo = $getDatos->metodo;

 	$producto = new ProductoController();

 	// Obtener codigo
	if($metodo == 1){
 		$json_cod = $producto->getProductoCod();
 		echo json_encode($json_cod);
 	}

 	// Registrar producto
 	if ($metodo == 2) {
 		$codigo = $getDatos->codigo;
 		$nombre = $getDatos->nombre;
 		$descripcion = $getDatos->descripcion;
 		$codigo_uni = $getDatos->codigo_uni;
 		$fec_venc = $getDatos->fec_venc;
 		$codigo_prov = $getDatos->codigo_prov;

 		$datos=[$codigo, $nombre, $descripcion, $codigo_uni, $fec_venc, $codigo_prov];

 		$json_registrar = $producto->agregarProducto($datos);
 		echo json_encode($json_registrar);
 	}

 	// Modificar producto
 	if ($metodo == 3) {
 		$codigo = $getDatos->codigo;
 		$nombre = $getDatos->nombre;
 		$descripcion = $getDatos->descripcion;
 		$codigo_uni = $getDatos->codigo_uni;
 		$fec_venc = $getDatos->fec_venc;
 		$codigo_prov = $getDatos->codigo_prov;

 		$datos=[$codigo, $nombre, $descripcion, $codigo_uni, $fec_venc, $codigo_prov];

 		$json_modificar = $producto->modificarProducto($datos);
 		echo json_encode($json_modificar);
 	}

 	// Listar productos
 	if ($metodo == 4) {
 		$json_listar = $producto->listarProductos();
 		echo json_encode($json_listar);
 	}

 	// Listar producto por codigo
 	if ($metodo == 5) {
 		$codigo = $getDatos->codigo;
 		$json_list = $producto->getProductoByCod($codigo);
 		echo json_encode($json_list);
 	}

 	// Eliminar producto
 	if ($metodo == 6) {
 		$codigo = $getDatos->codigo;
 		$json_eliminar = $producto->eliminarProducto($codigo);
 		echo json_encode($json_eliminar);
 	}

 	// Buscar producto
 	if ($metodo == 7) {
 		$dato = $getDatos->dato;
 		$filtro = $getDatos->filtro;
 		$json_buscar = $producto->buscarProducto($dato, $filtro);
 		echo json_encode($json_buscar);
 	}

 	// Listar productos activos
 	if ($metodo == 8) {
 		$json_listar_act = $producto->listarProductosActivos();
 		echo json_encode($json_listar_act);
 	}

 ?>
