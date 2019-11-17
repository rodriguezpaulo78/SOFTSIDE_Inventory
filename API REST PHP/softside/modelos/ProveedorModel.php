<?php
	//Para cargar todos lo archivos dentro de librerias/*
	spl_autoload_register(function($nombreClase){
		require_once '../controladores/'.$nombreClase.'.php';
	});

 	require_once '../controladores/ProveedorController.php';

 	# Recibimos los datos leídos de php://input
	$datosRecibidos = file_get_contents("php://input");
	# No los hemos decodificado, así que lo hacemos de una vez:
	$getDatos = json_decode($datosRecibidos);

	$metodo = $getDatos->metodo;

 	$proveedor = new ProveedorController();

 	// Obtener codigo
	if($metodo == 1){
 		$json_cod = $proveedor->getProveedorCod();
 		echo json_encode($json_cod);
 	}

 	// Registrar proveedor
 	if ($metodo == 2) {
 		$codigo = $getDatos->codigo;
 		$raz_soc = $getDatos->raz_soc;
 		$nombre_rep = $getDatos->nombre_rep;
 		$ruc = $getDatos->ruc;
 		$rubro = $getDatos->rubro;
 		$telefono = $getDatos->telefono;

 		$datos=[$codigo, $raz_soc, $nombre_rep, $ruc, $rubro, $telefono];

 		$json_registrar = $proveedor->agregarProveedor($datos);
 		echo json_encode($json_registrar);
 	}

 	// Modificar proveedor
 	if ($metodo == 3) {
 		$codigo = $getDatos->codigo;
 		$raz_soc = $getDatos->raz_soc;
 		$nombre_rep = $getDatos->nombre_rep;
 		$ruc = $getDatos->ruc;
 		$rubro = $getDatos->rubro;
 		$telefono = $getDatos->telefono;

 		$datos=[$codigo, $raz_soc, $nombre_rep, $ruc, $rubro, $telefono];

 		$json_modificar = $proveedor->modificarProveedor($datos);
 		echo json_encode($json_modificar);
 	}

 	// Listar usuarios
 	if ($metodo == 4) {
 		$json_listar = $proveedor->listarProveedores();
 		echo json_encode($json_listar);
 	}

 	// Listar proveedor por codigo
 	if ($metodo == 5) {
 		$codigo = $getDatos->codigo;
 		$json_list = $proveedor->getProveedorByCod($codigo);
 		echo json_encode($json_list);
 	}

 	// Eliminar proveedor
 	if ($metodo == 6) {
 		$codigo = $getDatos->codigo;
 		$json_eliminar = $proveedor->eliminarProveedor($codigo);
 		echo json_encode($json_eliminar);
 	}

 	// Buscar proveedor
 	if ($metodo == 7) {
 		$dato = $getDatos->dato;
 		$filtro = $getDatos->filtro;
 		$json_buscar = $proveedor->buscarProveedor($dato, $filtro);
 		echo json_encode($json_buscar);
 	}

 ?>
