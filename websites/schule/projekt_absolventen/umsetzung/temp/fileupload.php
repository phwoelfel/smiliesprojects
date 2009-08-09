<?php
echo '<form method="post" enctype="multipart/form-data" action="">
		<input type="file" name="datei" />
		<input type="submit" value="upload" />
	  </form>';
$types = array("text/xml", //xml
				"text/plain", //txt
				"text/rtf", //rtf
				"text/html", //html
				"image/jpeg", //jpeg
				"image/png", //png
				"image/gif", //gif
				"image/bmp", //bmp
				"application/x-photoshop", //psd
				"application/vnd.oasis.opendocument.spreadsheet", //ods
				"application/vnd.oasis.opendocument.text", //odt
				"application/vnd.oasis.opendocument.presentation", //odp
				"application/vnd.openxmlformats-officedocument.wordprocessingml.document", //docx
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", //xlsx
				"application/vnd.openxmlformats-officedocument.presentationml.presentation", //pptx
				"application/vnd.ms-excel", //xls
				"application/msword", //doc
				"application/vnd.ms-powerpoint" //ppt
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
		if(!move_uploaded_file($_FILES['datei']['tmp_name'], "uploads/" .$_FILES['datei']['name'])){
			echo "Fehler beim uploaden!";
		}
		else{
			echo "Datei upgeloadet!";
		}
	
	}
	else{
		echo "Dateityp ist nicht erlaubt oder datei ist zu gro&szlig;!";
	}
}
?>