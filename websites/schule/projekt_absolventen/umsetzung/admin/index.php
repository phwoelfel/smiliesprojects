<?php
ob_start();
?>

<!--
 * Copyright 2008 Philip Woelfel <philip@woelfel.at>
-->

<?php
	require("admin_funcs.php");
	require("config.php");
	global $config;
	global $selbst;
	$con = mysql_connect($config['host'], $config['user'], $config['psw']);
	mysql_select_db($config['db'], $con);
	//mysql_query("use " .$config['db']);
	$head = "";
	$body = "";
	$login = false;
	$uname = "";
	$upsw = "";
	$actions = "";
	$selbst = "?";
	
	$admin = null;
	
	if(isset($_POST['name']) && isset($_POST['psw'])){
		$login = true;
		$uname = $_POST['name'];
		$upsw = $_POST['psw'];
	}
	session_start();
	if(isset($_SESSION['userid'])){
		$cadmins = mysql_query("select id, name, passwort, rechte from users where rechte >= 100 and id = '" .$_SESSION['userid'] ."' and unlocked = 1");
		if(mysql_num_rows($cadmins)>0){
			$login = true;
		}
	}
	
	if($login){
		$admin = new AdminFunctions($uname, $upsw);		
	}
	
	?>

	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Admin Control Panel - <?php  echo $config['sitename'] ?></title>
	<link rel="stylesheet" type="text/css" href="../css/admin_style.css" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="control-cache" content="no-cache" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

</head>
<body>
<?php
	if($admin != null){
		if($admin->testUser()){
			$actions = $admin->getActions();
			//$body .= "Logged In";
			
			echo '<div id="nav">';
			foreach($actions as $action => $actionname){
				echo '<a href="?action=' .$action .'">' .$actionname .'</a><br />'."\n";
			}
			echo '</div>'."\n\n";
			
			echo '<div id="content">'."\n\n";
			if(isset($_GET['action'])){
				$admin->action($_GET['action']);
			}
			
			//$admin->db_insert("nav", array("titel", "site", "nav", "pos", "klasse", "recht"), array("Test", "news", "2", "3", "navi", "0"));
			//$admin->db_update("nav", array("titel", "site", "nav", "pos", "klasse", "recht"), array("Test upd", "termine", "1", "3", "navi_sub", "0"), 17);
			//$admin->db_delete("nav", 17);
			echo "\n\n".'</div>';
		}
		else{
			echo 'Fehler beim einloggen! (falsches Passwort oder Username)<br /> <a href="index.php">Hier</a> klicken um erneut zu versuchen!';
		}
	}
	else{
			echo '<h3>Admin Login</h3>
				<form action="?" method="post">
				Name: <input name="name" type="text" /><br />
				Passwort: <input name="psw" type="password" /><br />
				<input value="OK!" type="submit" />
				</form>';
		}
?>
</body>
</html>
			
<?php 
	mysql_close($con);
	echo ob_get_clean();
?>