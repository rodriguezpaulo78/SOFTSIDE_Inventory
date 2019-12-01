<?php

include_once '../conexion/db-connect.php';

class InventarioDetController{
	private $db;
	private $db_table = "inventario_detalle";
	public function __construct(){
		$this->db = new DbConnect();

	}
	
	public function getInvDetalleCod(){

		$query = "SELECT LPAD((SELECT COUNT(*) + 1 FROM ".$this->db_table."), 6, '0') AS nextCod";
		$result = mysqli_query($this->db->getDb(),$query);
		$cod = "000000";

		if(mysqli_num_rows($result) > 0){
 			while($row = mysqli_fetch_assoc($result)){
				$cod = $row["nextCod"];
			}
 			mysqli_close($this->db->getDb());
 		}

		$json = array();
		$json['codigo'] = $cod;

		return $json;
	}

	public function agregarInvDetalle($datos){	
		$json = array();

		$query = "INSERT INTO ".$this->db_table."(inv_det_id, inventario_cab_id, inv_det_movimiento, inv_det_cantidad, inv_det_precio_unit, inv_det_precio_total, inv_det_fec, inv_det_saldo_cant, inv_det_obs) VALUES ('$datos[0]', '$datos[1]', '$datos[2]', '$datos[3]', '$datos[4]', '$datos[5]', '$datos[6]', '$datos[7]', '$datos[8]')";

		$inserted = mysqli_query($this->db->getDb(), $query);

		$query2 = "UPDATE inventario_cabecera SET inv_cab_cant='$datos[7]', inv_cab_val_unit='$datos[9]', inv_cab_val_total='$datos[10]' WHERE inv_cab_id='$datos[1]'";

		$updated = mysqli_query($this->db->getDb(), $query2);

		if($inserted == 1 AND $updated == 1){
			$json['message'] = "SUCCESS";
		}else{
			$json['message'] = "FAILED";
		}
		mysqli_close($this->db->getDb());
			
		
		return $json;
	}
/*
	public function modificarUnidad($datos){	
		$json = array();

		$query = "UPDATE ".$this->db_table." SET uni_descripcion='$datos[1]' WHERE uni_id='$datos[0]'";

		$updated = mysqli_query($this->db->getDb(), $query);

		if($updated == 1){
			$json['message'] = "SUCCESS";
		}else{
			$json['message'] = "FAILED";
		}
		mysqli_close($this->db->getDb());
		
		return $json;
	}

	public function listarUnidades(){
		$query = "SELECT uni_id, uni_descripcion, uni_est_reg FROM ".$this->db_table;
		$result = mysqli_query($this->db->getDb(),$query);

		$json = array();
		if(mysqli_num_rows($result) > 0){
			$i = 0;
 			while($row = mysqli_fetch_assoc($result)){
				$json[$i]=$row;
				$i++;
			 }
 		}

 		mysqli_close($this->db->getDb());
		return $json;
	}
*/
	public function getInvDetByInvCabCod($codigo){
		// inv_det_id, inventario_cab_id, inv_det_movimiento, inv_det_cantidad, inv_det_precio_unit,
        // inv_det_precio_total, inv_det_fec, inv_det_saldo_cant, inv_det_obs, inv_det_est_reg
		$query = "SELECT inv_det_id, inventario_cab_id, inv_det_movimiento, inv_det_cantidad, inv_det_precio_unit, inv_det_precio_total, inv_det_fec, inv_det_saldo_cant, inv_det_obs, inv_det_est_reg FROM ".$this->db_table." WHERE inventario_cab_id='".$codigo."'";
		$result = mysqli_query($this->db->getDb(),$query);

		$json = array();
		if(mysqli_num_rows($result) > 0){
			$i = 0;
 			while($row = mysqli_fetch_assoc($result)){
				$json[$i]=$row;
				$i++;
			 }
 		}

 		mysqli_close($this->db->getDb());
		return $json;
	}

	public function getInvDetActivosByInvCabCod($codigo){
		$query = "SELECT inv_det_id, inventario_cab_id, inv_det_movimiento, inv_det_cantidad, inv_det_precio_unit, inv_det_precio_total, inv_det_fec, inv_det_saldo_cant, inv_det_obs, inv_det_est_reg FROM ".$this->db_table." WHERE inv_det_est_reg='A' AND inventario_cab_id='".$codigo."'";
		$result = mysqli_query($this->db->getDb(),$query);

		$json = array();
		if(mysqli_num_rows($result) > 0){
			$i = 0;
 			while($row = mysqli_fetch_assoc($result)){
				$json[$i]=$row;
				$i++;
			 }
 		}

 		mysqli_close($this->db->getDb());
		return $json;
	}

/*
	public function eliminarUnidad($codigo){	
		$json = array();

		$query = "UPDATE ".$this->db_table." SET uni_est_reg='I' WHERE uni_id='".$codigo."'";

		$deleted = mysqli_query($this->db->getDb(), $query);

		if($deleted == 1){
			$json['message'] = "SUCCESS";
		}else{
			$json['message'] = "FAILED";
		}
		mysqli_close($this->db->getDb());
		
		return $json;
	}

	public function buscarUnidad($dato, $filtro){
		$query = "SELECT uni_id FROM ".$this->db_table." WHERE uni_descripcion='".$dato."'";

		$result = mysqli_query($this->db->getDb(),$query);

		$json = array();
		if(mysqli_num_rows($result) > 0){
			$i = 0;
 			while($row = mysqli_fetch_assoc($result)){
				$json[$i]=$row;
				$i++;
			 }
 		}

 		mysqli_close($this->db->getDb());
		return $json;
	}

	*/
}
?>