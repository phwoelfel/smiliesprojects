<?php
	ob_start();
	$navigation="";
	$content="";
	$head="";
	$curnav="";
	global $rechte;
	$rechte = 0;
	global $loggedin;
	$loggedin = false;
	$navid = 1;
	$site = "news";
	global $selbst;
	
	session_start();
	
	require("admin/config.php");
	global $config;
	$con = mysql_connect($config['host'], $config['user'], $config['psw']);
	mysql_select_db($config['db'], $con);
	
	
	if(isset($_GET['site']) && is_numeric($_GET['site'])){
		$navid = $_GET['site'];
	}
	
	
	
	if(isset($_GET['logout'])){
		session_destroy();
		$head .= '<meta http-equiv="Refresh" content="0;url=index.php" />';
	}
	
	if(isset($_SESSION['userid']) && isset($_SESSION['userpsw'])){
		$users = mysql_query("select id, name, passwort, rechte from users where id = '" .$_SESSION['userid'] ."' and unlocked = 1");
		$row = mysql_fetch_array($users);
		if($row['passwort'] == $_SESSION['userpsw']){
			$loggedin = true;
			$rechte = $row['rechte'];
		}
	}
	
	$navgroups = mysql_query("select * from navgroups where recht <= $rechte order by pos asc");
	while($navitem = mysql_fetch_array($navgroups)){
		$navigation .= '<div class="navbox"><div class="nav_top"></div>';
		$nav_inhalt = mysql_query("select * from nav where nav=" .$navitem['id'] ." and recht <= $rechte order by pos asc", $con);
		while($row = mysql_fetch_array($nav_inhalt)){
			//echo "<pre>" .print_r($row,true) ."</pre>";
			if($row['no_login'] == 1 && $rechte != 0){
			}
			else{
				if($row['extern']==1){
					$navigation .= '<div class="' .$row['klasse'] .'"><a href="' .$row['site'] .'">&raquo;' .$row['titel'] .'</a></div>' ."\n";
				}
				else{
					$navigation .= '<div class="' .$row['klasse'] .'"><a href="?site=' .$row['id'] .'">&raquo;' .$row['titel'] .'</a></div>' ."\n";
				}
			}
		}
		$navigation .= '<div class="nav_bottom"></div></div>' ."\n\n";
	}
	
	
	if(isset($_GET['login'])){
		$site = "login";
		$curnav = "Login";
	}
	else{
		$site_sql = "select site from nav where id=$navid and recht <= $rechte";
		$site_qry = mysql_query($site_sql);
		if(mysql_num_rows($site_qry)>0){
			$site_res = mysql_fetch_array($site_qry);
			$site = $site_res['site'];
		}
		else{
			$navid = 1;
		}
		$curnav_sql = "select titel from nav where id=$navid";
		$curnav_qry = mysql_query($curnav_sql);
		$curnav_res = mysql_fetch_array($curnav_qry);
		$curnav = $curnav_res['titel'];
	}
	$selbst = "?site=$navid";
	
	$inhalt = mysql_query("select * from content where titel='" .$site ."'");
	$cont = mysql_fetch_array($inhalt);
	
	$head .= runphp($cont['head'], 'cache/tmp.inc.php');	
	$content .= runphp($cont['content'], 'cache/tmp.inc.php');
		
	
	
	
	
	echo '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
    <html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>' .$config['sitename'] .' - ' .$curnav .'</title>
	<link rel="stylesheet" type="text/css" href="css/main.css" />
	<!--[if lt IE 7]>
		<link rel="stylesheet" type="text/css" href="css/ie.css" />
	<![endif]-->
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	' .$head .'
	</head>
	<body>
	<div id="box">
		<div id="header">
			<img src="images/logo.png" alt="' .$config['sitename'] .'" />
		</div>
		<div id="nav">
		' .$navigation .'
		<!--
			<p>
				<a href="http://validator.w3.org/check?uri=referer"><img style="border:0;" src="images/valid-xhtml11-blue.png" alt="Valid XHTML 1.0 Transitional" height="31" width="88" /></a>
			</p>
			<p>
				<a href="http://jigsaw.w3.org/css-validator/">
					<img style="border:0;width:88px;height:31px" src="images/vcss-blue.gif" alt="CSS ist valide!" />
				</a>
			</p>
		-->
		</div>
		<div id="content">
			<div class="content_top">
			</div>
			<div class="content_bg">
				' .$content .'
			</div>
			<div class="content_bottom">
			</div>
		</div>
		<div id="footer" style="text-align: right;">
		' .($loggedin?'<a href="?logout=true">Logout</a>&nbsp;&nbsp;':'<a href="?login=true">Login</a>&nbsp;&nbsp;') .'&copy; Philip W&ouml;lfel 2009
		</div>
	</div>
	</body>
	</html>';
	mysql_close($con);
	echo ob_get_clean();
?>

<!--
 * Copyright 2007 Philip Woelfel <philip.woelfel@frig.at>
-->
