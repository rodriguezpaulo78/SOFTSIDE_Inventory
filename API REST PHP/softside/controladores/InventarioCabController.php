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

	public function getUnidByCod($codigo){
		$query = "SELECT uni_id, uni_descripcion, uni_est_reg FROM ".$this->db_table." WHERE uni_id='".$codigo."'";
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

	public function listarUnidadesActivas(){
		$query = "SELECT uni_id, uni_descripcion FROM ".$this->db_table." WHERE uni_est_reg='A'";
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
	}*/
}
?>