<!--
	Copyright 2008 Philip W&ouml;lfel <philip.woelfel@frig.at>
-->
<?php	
	$content ='<h2>Termine</h2>';
	
	$termine = mysql_query("select id, datum, kurztext from termine where datum > CURDATE() order by datum asc") or die(mysql_error());
	if(mysql_num_rows($termine)==0){
		$content .= "keine Termine vorhanden";
	}
	else{
		$content .=	'<ul type="square">';
		while($row = mysql_fetch_array($termine)){
			$content .= '<li>' .$row['datum'] .': ' .$row['kurztext'] .'</li>';
		}
		$content .= '</ul>';
	}
	echo $content;
?>

