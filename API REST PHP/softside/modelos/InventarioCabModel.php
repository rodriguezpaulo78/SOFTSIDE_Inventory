<?php
	//Para cargar todos lo archivos dentro de librerias/*
	spl_autoload_register(function($nombreClase){
		require_once '../controladores/'.$nombreClase.'.php';
	});

 	require_once '../controladores/InventarioCabController.php';

 	# Recibimos los datos leídos de php://input
	$datosRecibidos = file_get_contents("php://input");
	# No los hemos decodificado, así que lo hacemos de una vez:
	$getDatos = json_decode($datosRecibidos);

	$metodo = $getDatos->metodo;

 	$inv_cab = new InventarioCabController();

 	// Registrar inventario cabecera
 	if ($metodo == 1) {
 		$productoCod = $getDatos->productoCod;
 		$almacen = $getDatos->almacen;

 		$datos=[$productoCod, $almacen];

 		$json_registrar = $inv_cab->agregarInvCab($datos);
 		echo json_encode($json_registrar);
 	}

 	// Listar inventarios cabeceras
 	if ($metodo == 2) {
 		$json_listar = $inv_cab->listarInvCabs();
 		echo json_encode($json_listar);
 	}

 	// Eliminar inventario cabecera
 	if ($metodo == 3) {
 		$codigo = $getDatos->codigo;
 		$json_eliminar = $inv_cab->eliminarInvCab($codigo);
 		echo json_encode($json_eliminar);
 	}

 	// Obtener existencias
 	if ($metodo == 4) {
 		$productoCod = $getDatos->productoCod;
 		$json_existencias = $inv_cab->getExistenciasProductos($productoCod);
 		echo json_encode($json_existencias);
 	}

 	// Obtener existencia total
 	if ($metodo == 5) {
 		$productoCod = $getDatos->productoCod;
 		$json_existencia_total = $inv_cab->getExistenciaTotal($productoCod);
 		echo json_encode($json_existencia_total);
 	}

 ?>