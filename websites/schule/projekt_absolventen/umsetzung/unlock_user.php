<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>User freischalten</title>
	
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	</head>
	<body>
<?php
	require("admin/config.php");
	global $config;
	$con = mysql_connect($config['host'], $config['user'], $config['psw']);
	mysql_select_db($config['db'], $con);
	
	if(isset($_GET['unlock'])){
		$unlock = $_GET['unlock'];
		$unlu_sql = "select id, name, vorname, nachname from users where reg_hash = '$unlock' and unlocked = 0";
		if($unlu_qry = mysql_query($unlu_sql)){
			if(mysql_num_rows($unlu_qry)==1){
				$unlu_res = mysql_fetch_array($unlu_qry);
				$unl_sql = "update users set unlocked=1 where id=" .$unlu_res['id'];
				if($unl_qry = mysql_query($unl_sql)){
					echo "Herzlich Willkommen {$unlu_res['vorname']} {$unlu_res['nachname']}, ihr Account wurde nun freigeschalten.".'<br /> <a href="index.php">Hier</a> kommen sie zur Startseite';
				}
			}
			else{
				echo 'Der Account wurde bereits freigeschalten oder es wurde ein ung&uuml;ltiger Regestrierungsschl&uuml;ssel angegeben.<br /> <a href="index.php">Hier</a> kommen sie zur Startseite';
			}
		}
	}
	else{
		echo 'Ups wer hat sich den hierhin verirrt?<br /> <a href="index.php">Hier</a> kommen sie zur Startseite ;-)';
		//echo "<pre>" .print_r($GLOBALS,true) ."</pre>";
	}
	
?>
	</body>
</html>