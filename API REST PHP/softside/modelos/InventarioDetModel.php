<?php
	//Para cargar todos lo archivos dentro de librerias/*
	spl_autoload_register(function($nombreClase){
		require_once '../controladores/'.$nombreClase.'.php';
	});

 	require_once '../controladores/InventarioDetController.php';

 	# Recibimos los datos leídos de php://input
	$datosRecibidos = file_get_contents("php://input");
	# No los hemos decodificado, así que lo hacemos de una vez:
	$getDatos = json_decode($datosRecibidos);

	$metodo = $getDatos->metodo;

 	$inv_det = new InventarioDetController();

 	// Obtener codigo
	if($metodo == 1){
 		$json_cod = $inv_det->getInvDetalleCod();
 		echo json_encode($json_cod);
 	}

 	// Registrar inventario detalle
 	if ($metodo == 2) {
 		$det_codigo = $getDatos->det_codigo;
 		$cab_codigo = $getDatos->cab_codigo;
 		$det_movimiento = $getDatos->det_movimiento;
 		$det_cantidad = $getDatos->det_cantidad;
 		$det_precio_unit = $getDatos->det_precio_unit;
 		$det_precio_total = $getDatos->det_precio_total;
 		$det_fecha = $getDatos->det_fecha;
 		$det_saldo_cant = $getDatos->det_saldo_cant;
 		$det_obs = $getDatos->det_obs;
 		$cab_val_unit = $getDatos->cab_val_unit;
 		$cab_val_total = $getDatos->cab_val_total;

 		$datos=[$det_codigo, $cab_codigo, $det_movimiento, $det_cantidad, $det_precio_unit, $det_precio_total, $det_fecha, $det_saldo_cant, $det_obs, $cab_val_unit, $cab_val_total];

 		$json_registrar = $inv_det->agregarInvDetalle($datos);
 		echo json_encode($json_registrar);
 	}
/*
 	// Modificar unidad
 	if ($metodo == 3) {
 		$codigo = $getDatos->codigo;
 		$descripcion = $getDatos->descripcion;

 		$datos=[$codigo, $descripcion];

 		$json_modificar = $unidad->modificarUnidad($datos);
 		echo json_encode($json_modificar);
 	}
*/
 	// Listar inventario detalle por codigo de cabecera
 	if ($metodo == 4) {
 		$codigo = $getDatos->codigo;
 		$json_list = $inv_det->getInvDetByInvCabCod($codigo);
 		echo json_encode($json_list);
 	}

 	// Listar inventario detalle activos
 	if ($metodo == 5) {
 		$codigo = $getDatos->codigo;
 		$json_listar_act = $inv_det->getInvDetActivosByInvCabCod($codigo);
 		echo json_encode($json_listar_act);
 	}

/*
 	// Eliminar unidad
 	if ($metodo == 6) {
 		$codigo = $getDatos->codigo;
 		$json_eliminar = $unidad->eliminarUnidad($codigo);
 		echo json_encode($json_eliminar);
 	}

 	// Buscar unidad
 	if ($metodo == 7) {
 		$dato = $getDatos->dato;
 		$filtro = $getDatos->filtro;
 		$json_buscar = $unidad->buscarUnidad($dato, $filtro);
 		echo json_encode($json_buscar);
 	}
*/

 ?>