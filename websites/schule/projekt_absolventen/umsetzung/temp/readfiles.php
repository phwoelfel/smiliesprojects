<?php
	$ordner = "temp/";
	$dir_handle = opendir($ordner);
	$str = "";
	while ($filename = readdir($dir_handle)){
		$suffix = substr($filename, -3, 3);
		$fname = substr($filename, 0, strlen($filename)-4);
		if ($suffix == 'php'){
			echo "filename: $filename, suffix: $suffix, fname: $fname<br />";
			if($curfile = file_get_contents("temp/" .$filename, FILE_TEXT)){
				mysql_query("update content set content='" .mysql_real_escape_string($curfile) ."' where titel = '" .$fname ."'") or die(mysql_error());
			}
			else{
				echo "Fehler bei der Datei $filename!<br />";
			}
		}
	}
	closedir($dir_handle);
?>