<?php

include_once '../conexion/db-connect.php';

class ProductoController{
	private $db;
	private $db_table = "producto";
	public function __construct(){
		$this->db = new DbConnect();

	}
	
	public function getProductoCod(){

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

	public function agregarProducto($datos){	
		$json = array();

		$query = "INSERT INTO ".$this->db_table."(prod_id, prod_nombre, prod_descripcion, unidad_id, prod_fec_venc, proveedor_id) VALUES ('$datos[0]', '$datos[1]', '$datos[2]', '$datos[3]', '$datos[4]', '$datos[5]')";

		$inserted = mysqli_query($this->db->getDb(), $query);

		if($inserted == 1){
			$json['message'] = "SUCCESS";
		}else{
			$json['message'] = "FAILED";
		}
		mysqli_close($this->db->getDb());
			
		
		return $json;
	}

	public function modificarProducto($datos){	
		$json = array();

		$query = "UPDATE ".$this->db_table." SET prod_nombre='$datos[1]', prod_descripcion='$datos[2]', unidad_id='$datos[3]', prod_fec_venc='$datos[4]', proveedor_id='$datos[5]' WHERE prod_id='$datos[0]'";

		$updated = mysqli_query($this->db->getDb(), $query);

		if($updated == 1){
			$json['message'] = "SUCCESS";
		}else{
			$json['message'] = "FAILED";
		}
		mysqli_close($this->db->getDb());
		
		return $json;
	}

	public function listarProductos(){
		$query = "SELECT prod_id, prod_nombre, prod_descripcion, unidad_id, prod_fec_venc, proveedor_id, prod_est_reg FROM ".$this->db_table;
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

	public function getProductoByCod($codigo){
		$query = "SELECT prod_id, prod_nombre, prod_descripcion, unidad_id, prod_fec_venc, proveedor_id, prod_est_reg FROM ".$this->db_table." WHERE prod_id='".$codigo."'";
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

	public function eliminarProducto($codigo){	
		$json = array();

		$query = "UPDATE ".$this->db_table." SET prod_est_reg='I' WHERE prod_id='".$codigo."'";

		$deleted = mysqli_query($this->db->getDb(), $query);

		if($deleted == 1){
			$json['message'] = "SUCCESS";
		}else{
			$json['message'] = "FAILED";
		}
		mysqli_close($this->db->getDb());
		
		return $json;
	}

	public function buscarProducto($dato, $filtro){
		$query = "";

		if ($filtro == "Nombre")
			$query = "SELECT prod_id FROM ".$this->db_table." WHERE prod_nombre='".$dato."'";
		elseif ($filtro == "Unidad")
			$query = "SELECT prod_id FROM ".$this->db_table." WHERE unidad_id='".$dato."'";
		elseif ($filtro == "Proveedor")
			$query = "SELECT prod_id FROM ".$this->db_table." WHERE proveedor_id='".$dato."'";

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