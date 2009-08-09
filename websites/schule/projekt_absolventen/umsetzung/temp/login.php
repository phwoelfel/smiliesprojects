<!--
	Copyright 2008 Philip W&ouml;lfel <philip.woelfel@frig.at>
--><?php
	//$selbst = "?site=" .$_GET['site'];
	global $selbst;
	if(isset($_POST['name']) && isset($_POST['passwd'])){
		$users = mysql_query("select id, name, passwort from users where name = '" .$_POST['name'] ."' and unlocked = 1");
		if(mysql_num_rows($users)>0){
			$row = mysql_fetch_array($users);
			if($row['passwort'] == hash($config['hash_algo'], $_POST['passwd'])){
				$_SESSION['userid'] = $row['id'];
				$_SESSION['userpsw'] = $row['passwort'];
				$_SESSION['username'] = $row['name'];
			
				echo '<a href="index.php">Hier</a> gelangen sie zur Startseite.';
				//http_redirect("index.php");
				header("Location: index.php");
				
			}
			else{
				echo 'Sie haben ein falsches Passwort eingegeben.<br /><a href="index.php' .$selbst .'">Hier</a> k&ouml;nnen sie erneut versuchen sich einzuloggen.';
			}
		}
		else{
			echo 'Der Benutzername existiert nicht oder ihr Account wurde noch nicht freigeschalten!<br /><a href="index.php' .$selbst .'">Hier</a> k&ouml;nnen sie erneut versuchen sich einzuloggen';
		}
	}
	else{
		echo 'Hier k&ouml;nnen sie sich einloggen:
					<form method="post" action="">
						Username: <input type="text" name="name" /><br />
						Passwort: <input type="password" name="passwd" /><br />
						<input type="submit" value="Login" />
					</form>';
	}
?>
