<?php

include_once '../conexion/db-connect.php';

class SesionController{
	private $db;
	private $db_table = "usuario";
	public function __construct(){
		$this->db = new DbConnect();

	}
	
	public function loginUser($username, $password){
		$query = "SELECT user_id, user_nombres, user_apellidos, user_dni, user_cargo, user_tipo_user from ".$this->db_table." where user_username='".$username."' and user_password='".$password."'";
		$result = mysqli_query($this->db->getDb(),$query);

		if(mysqli_num_rows($result) > 0){
			$json = array();
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
			return $json;
 		}else{
 			$jsonMessage = array();
 			$jsonMessage['message'] = "FAILED";
			mysqli_close($this->db->getDb());
			return $jsonMessage;
		}
	}

}
?>
