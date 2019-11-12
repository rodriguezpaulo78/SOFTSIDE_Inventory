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


}
?>