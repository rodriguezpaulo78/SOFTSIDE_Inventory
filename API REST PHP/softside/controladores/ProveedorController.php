<?php

include_once '../conexion/db-connect.php';

class ProveedorController{
	private $db;
	private $db_table = "proveedor";
	public function __construct(){
		$this->db = new DbConnect();

	}
	
	public function getUserCod(){

		$query = "SELECT LPAD((SELECT COUNT(*) + 1 FROM ".$this->db_table."), 5, '0') AS nextCod";
		$result = mysqli_query($this->db->getDb(),$query);
		$cod = "00000";

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

	public function agregarProveedor($datos){	
		$json = array();

		$query = "INSERT INTO ".$this->db_table."(prov_id, prov_raz_soc, prov_nombre_rep, prov_ruc, prov_rubro, prov_telefono) VALUES ('$datos[0]', '$datos[1]', '$datos[2]', '$datos[3]', '$datos[4]', '$datos[5]')";

		$inserted = mysqli_query($this->db->getDb(), $query);

		if($inserted == 1){
			$json['message'] = "SUCCESS";
		}else{
			$json['message'] = "FAILED";
		}
		mysqli_close($this->db->getDb());
			
		
		return $json;
	}

	public function modificarProveedor($datos){	
		$json = array();

		$pass = password_hash($datos[7], PASSWORD_BCRYPT);

		$query = "UPDATE ".$this->db_table." SET prov_raz_soc='$datos[1]', prov_nombre_rep='$datos[2]', prov_ruc='$datos[3]', prov_rubro='$datos[4]', prov_telefono='$datos[5]' WHERE prov_id='$datos[0]'";

		$updated = mysqli_query($this->db->getDb(), $query);

		if($updated == 1){
			$json['message'] = "SUCCESS";
		}else{
			$json['message'] = "FAILED";
		}
		mysqli_close($this->db->getDb());
		
		return $json;
	}

	public function listarProveedores(){
		$query = "SELECT prov_id, prov_raz_soc, prov_nombre_rep, prov_ruc, prov_rubro, prov_telefono, prov_est_reg FROM ".$this->db_table;
		$result = mysqli_query($this->db->getDb(),$query);

		if(mysqli_num_rows($result) > 0){
			$json = array();
			$i = 0;
 			while($row = mysqli_fetch_assoc($result)){
				$json[$i]=$row;
				$i++;
			 }
			
 			mysqli_close($this->db->getDb());
			return $json;
 		}else{
 			mysqli_close($this->db->getDb());
 			$json_message = array();
 			$json_message['message'] = "EMPTY";
			return $json_message;
		}
	}

	public function getProveedorByCod($codigo){
		$query = "SELECT prov_id, prov_raz_soc, prov_nombre_rep, prov_ruc, prov_rubro, prov_telefono, user_est_reg FROM ".$this->db_table." WHERE prov_id='".$codigo."'";
		$result = mysqli_query($this->db->getDb(),$query);

		if(mysqli_num_rows($result) > 0){
			$json = array();
			$i = 0;
 			while($row = mysqli_fetch_assoc($result)){
				$json[$i]=$row;
				$i++;
			 }
			
 			mysqli_close($this->db->getDb());
			return $json;
 		}else{
 			mysqli_close($this->db->getDb());
 			$json_message = array();
 			$json_message['message'] = "NOT EXISTS";
			return $json_message;
		}
	}

	public function eliminarProveedor($codigo){	
		$json = array();

		$query = "UPDATE ".$this->db_table." SET prov_est_reg='I' WHERE prov_id='".$codigo."'";

		$deleted = mysqli_query($this->db->getDb(), $query);

		if($deleted == 1){
			$json['message'] = "SUCCESS";
		}else{
			$json['message'] = "FAILED";
		}
		mysqli_close($this->db->getDb());
		
		return $json;
	}

	public function buscarProveedor($dato, $filtro){
		$query = "";

		if ($filtro == "Razon Social")
			$query = "SELECT prov_id FROM ".$this->db_table." WHERE prov_raz_soc='".$dato."'";
		elseif ($filtro == "Representante")
			$query = "SELECT prov_id FROM ".$this->db_table." WHERE prov_nombre_rep='".$dato."'";
		elseif ($filtro == "Rubro")
			$query = "SELECT prov_id FROM ".$this->db_table." WHERE prov_rubro='".$dato."'";

		$result = mysqli_query($this->db->getDb(),$query);

		if(mysqli_num_rows($result) > 0){
			$json = array();
			$i = 0;
 			while($row = mysqli_fetch_assoc($result)){
				$json[$i]=$row;
				$i++;
			 }
			
 			mysqli_close($this->db->getDb());
			return $json;
 		}else{
 			mysqli_close($this->db->getDb());
 			$json_message = array();
 			$json_message['message'] = "NOT EXISTS";
			return $json_message;
		}
	}
}
?>