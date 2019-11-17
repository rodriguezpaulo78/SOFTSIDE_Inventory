<?php

include_once '../conexion/db-connect.php';

class UnidadController{
	private $db;
	private $db_table = "unidad";
	public function __construct(){
		$this->db = new DbConnect();

	}
	
	public function getUnidadCod(){

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

	public function agregarUnidad($datos){	
		$json = array();

		$query = "INSERT INTO ".$this->db_table."(uni_id, uni_descripcion) VALUES ('$datos[0]', '$datos[1]')";

		$inserted = mysqli_query($this->db->getDb(), $query);

		if($inserted == 1){
			$json['message'] = "SUCCESS";
		}else{
			$json['message'] = "FAILED";
		}
		mysqli_close($this->db->getDb());
			
		
		return $json;
	}

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

	public function getUserByCod($codigo){
		$query = "SELECT user_id, user_nombres, user_apellidos, user_dni, user_fec_nac, user_cargo, user_username, user_tipo_user, user_est_reg FROM ".$this->db_table." WHERE user_id='".$codigo."'";
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

	public function eliminarUsuario($codigo){	
		$json = array();

		$query = "UPDATE ".$this->db_table." SET user_est_reg='I' WHERE user_id='".$codigo."'";

		$deleted = mysqli_query($this->db->getDb(), $query);

		if($deleted == 1){
			$json['message'] = "SUCCESS";
		}else{
			$json['message'] = "FAILED";
		}
		mysqli_close($this->db->getDb());
		
		return $json;
	}

	public function buscarUsuario($dato, $filtro){
		$query = "";

		if ($filtro == "Usuario(Ide)")
			$query = "SELECT user_id FROM ".$this->db_table." WHERE user_username='".$dato."'";
		elseif ($filtro == "DNI")
			$query = "SELECT user_id FROM ".$this->db_table." WHERE user_dni='".$dato."'";
		elseif ($filtro == "Nombres")
			$query = "SELECT user_id FROM ".$this->db_table." WHERE user_nombres='".$dato."'";
		elseif ($filtro == "Apellidos")
			$query = "SELECT user_id FROM ".$this->db_table." WHERE user_apellidos='".$dato."'";

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
}
?>