<?php

include_once '../conexion/db-connect.php';

class UsuarioController{
	private $db;
	private $db_table = "usuario";
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

	public function agregarUsuario($datos){	
		$json = array();

		$pass = password_hash($datos[7], PASSWORD_BCRYPT);

		$query = "INSERT INTO ".$this->db_table."(user_id, user_nombres, user_apellidos, user_dni, user_fec_nac, user_cargo, user_username, user_password, user_tipo_user) VALUES ('$datos[0]', '$datos[1]', '$datos[2]', '$datos[3]', '$datos[4]', '$datos[5]', '$datos[6]', '$pass', '$datos[8]')";

		$inserted = mysqli_query($this->db->getDb(), $query);

		if($inserted == 1){
			$json['message'] = "SUCCESS";
		}else{
			$json['message'] = "FAILED";
		}
		mysqli_close($this->db->getDb());
			
		
		return $json;
	}

	public function modificarUsuario($datos){	
		$json = array();

		$pass = password_hash($datos[7], PASSWORD_BCRYPT);

		$query = "UPDATE ".$this->db_table." SET user_nombres='$datos[1]', user_apellidos='$datos[2]', user_dni='$datos[3]', user_fec_nac='$datos[4]', user_cargo='$datos[5]', user_username='$datos[6]', user_password='$pass', user_tipo_user='$datos[8]' WHERE user_id='$datos[0]'";

		$updated = mysqli_query($this->db->getDb(), $query);

		if($updated == 1){
			$json['message'] = "SUCCESS";
		}else{
			$json['message'] = "FAILED";
		}
		mysqli_close($this->db->getDb());
		
		return $json;
	}

	public function listarUsuarios(){
		$query = "SELECT user_id, user_nombres, user_apellidos, user_dni, user_fec_nac, user_cargo, user_username, user_tipo_user, user_est_reg FROM ".$this->db_table;
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

	public function getUserByCod($codigo){
		$query = "SELECT user_id, user_nombres, user_apellidos, user_dni, user_fec_nac, user_cargo, user_username, user_tipo_user, user_est_reg FROM ".$this->db_table." WHERE user_id='".$codigo."'";
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
}
?>