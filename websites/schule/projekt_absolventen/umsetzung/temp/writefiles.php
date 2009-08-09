<?php
	$content_qry = mysql_query("select * from content");
	while($content_res = mysql_fetch_array($content_qry)){
		$titel = "temp/" .$content_res['titel'] .".php";
		$fh = fopen($titel, "w");
		$inh = $content_res['content'];	
		if(fwrite($fh, utf8_encode($inh))){
			echo "$titel wurde erstellt!<br />";
		}
		fclose($fh);
	}
?>