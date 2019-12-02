<?php

include_once '../conexion/db-connect.php';

class InventarioCabController{
	private $db;
	private $db_table = "inventario_cabecera";
	public function __construct(){
		$this->db = new DbConnect();

	}

	public function agregarInvCab($datos){	
		$json = array();

		$query = "INSERT INTO ".$this->db_table."(producto_id, inv_cab_almacen) VALUES ('$datos[0]', '$datos[1]')";

		$inserted = mysqli_query($this->db->getDb(), $query);

		if($inserted == 1){
			$json['message'] = "SUCCESS";
		}else{
			$json['message'] = "FAILED";
		}
		mysqli_close($this->db->getDb());
		
		return $json;
	}

	public function listarInvCabs(){
		$query = "SELECT inv_cab_id, producto_id, inv_cab_almacen, inv_cab_cant, inv_cab_val_unit, inv_cab_val_total, inv_cab_est_reg FROM ".$this->db_table;
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

	public function eliminarInvCab($codigo){	
		$json = array();

		$query = "UPDATE ".$this->db_table." SET inv_cab_est_reg='I' WHERE inv_cab_id='".$codigo."'";

		$deleted = mysqli_query($this->db->getDb(), $query);

		if($deleted == 1){
			$json['message'] = "SUCCESS";
		}else{
			$json['message'] = "FAILED";
		}
		mysqli_close($this->db->getDb());
		
		return $json;
	}

}
?>