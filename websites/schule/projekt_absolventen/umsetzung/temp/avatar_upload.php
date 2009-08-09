<?php
echo '<form method="post" enctype="multipart/form-data" action="">
		<input type="file" name="datei" />
		<input type="submit" value="upload" />
	  </form>';
$types = array(	"image/jpeg", //jpeg
				"image/png", //png
				"image/gif", //gif
				"image/bmp", //bmp
				);
if(isset($_FILES['datei'])){
	//echo $_FILES['datei']['type'];
	$allowedtype = false;
	for($i=0;$i<sizeof($types);$i++){
		if($types[$i]==$_FILES['datei']['type']){
			$allowedtype = true;
			break;
		}
	}
	//echo "<br />all: " .$allowedtype;
	if($allowedtype && isset($_FILES['datei']['tmp_name']) && $_FILES['datei']['size']<7000000){
		$filetmp = $_FILES['datei']['name'];
		$suffix = substr($filetmp, -(strlen($filetmp)-strrpos($filetmp, ".")), (strlen($filetmp)-strrpos($filetmp, ".")) );
		if(!move_uploaded_file($_FILES['datei']['tmp_name'], "images/avatars/" .$_SESSION['userid'] .$suffix)){
			echo "Fehler beim uploaden!";
		}
		else{
			mysql_query("update users set pic='$suffix' where id=" .$_SESSION['userid']) or die("Fehler beim speichern!");
			echo "Datei upgeloadet!";
		}
	
	}
	else{
		echo "Dateityp ist nicht erlaubt oder datei ist zu gro&szlig;!";
	}
}
?>