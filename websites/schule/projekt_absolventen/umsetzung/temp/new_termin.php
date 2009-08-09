<!--
	Copyright 2008 Philip W&ouml;lfel <philip.woelfel@frig.at>
-->
<?php
$monate[1]='J&auml;nner';
$monate[2]='Februar';
$monate[3]='M&auml;rz';
$monate[4]='April';
$monate[5]='Mai';
$monate[6]='Juni';
$monate[7]='Juli';
$monate[8]='August';
$monate[9]='September';
$monate[10]='Oktober';
$monate[11]='November';
$monate[12]='Dezember';
$jahr = date("Y");
//$selbst = "?site=" .$_GET['site'];
global $selbst;
echo '<form method="post" action="' .$selbst .'">
Jahr: <select name="jahr">
		<option value="' .$jahr .'">' .$jahr .'</option>
		<option value="' .($jahr+1) .'">' .($jahr+1) .'</option>
		<option value="' .($jahr+2) .'">' .($jahr+2) .'</option>
		<option value="' .($jahr+3) .'">' .($jahr+3) .'</option>
	</select><br />
Monat: <select name="monat">
';
for($i=1;$i<=12;$i++){
	echo '<option value="' .$i .'">' .$monate[$i] .'</option>
	';
}
echo '</select>
			<br />
			Tag: <select name="tag">
			';
for($j=1;$j<=31;$j++){
	echo '<option value="' .$j .'">' .$j .'</option>
	';
}
echo '</select>
<br />
Termin: <input type="text" name="termin" />
<br />
<input type="submit" value="Termin speichern" />
</form>';

if(isset($_POST['monat']) && isset($_POST['tag']) && isset($_POST['termin']) && isset($_POST['jahr'])){
	$monat = $_POST['monat'];
	$tag = $_POST['tag'];
	$termin = $_POST['termin'];
	$jahr = $_POST['jahr'];
	mysql_query("insert into termine (`datum`,`kurztext`,`langtext`) values ('" .$jahr ."-" .$monat ."-" .$tag ."', '" .$termin ."', 'lang')");
}
?>