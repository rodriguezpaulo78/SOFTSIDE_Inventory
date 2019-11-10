<?php

include_once '../conexion/db-connect.php';

class SesionController{
	private $db;
	private $db_table = "usuario";
	public function __construct(){
		$this->db = new DbConnect();

	}
	
	public function loginUser($username, $password){

    	$query1 = "SELECT user_password from ".$this->db_table." where user_username='".$username."'";
		$result1 = mysqli_query($this->db->getDb(),$query1);
		$pass = "";

		if(mysqli_num_rows($result1) > 0){
			while($row = mysqli_fetch_assoc($result1)){
				$pass = $row["user_password"];
			}
		}

		$json = array();

		if(password_verify($password, $pass)){
			$query = "SELECT user_id, user_nombres, user_apellidos, user_dni, user_cargo, user_tipo_user from ".$this->db_table." where user_username='".$username."'";
			$result = mysqli_query($this->db->getDb(),$query);

			if(mysqli_num_rows($result) > 0){
				$json['message'] = "SUCCESS";
 				while($row = mysqli_fetch_assoc($result)){
					$json['user_id']=$row["user_id"];
					$json['user_nombres']=$row["user_nombres"];
					$json['user_apellidos']=$row["user_apellidos"];
					$json['user_dni']=$row["user_dni"];
					$json['user_cargo']=$row["user_cargo"];
					$json['user_tipo_user']=$row["user_tipo_user"];
				}
			
 				mysqli_close($this->db->getDb());
 			}
		} else {
 			$json['message'] = "FAILED";
		}

		return $json;
	}

}
?>
