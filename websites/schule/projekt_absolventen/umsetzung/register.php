<!--
	Copyright 2008 Philip Woelfel <philip@woelfel.at>
-->
<?php	
	function myescape($str){
		$replace[0][0]="ö";
		$replace[0][1]="&ouml;";
		$replace[1][0]="ä";
		$replace[1][1]="&auml;";
		$replace[2][0]="ü";
		$replace[2][1]="&uuml;";
		$replace[3][0]="Ö";
		$replace[3][1]="&Ouml;";
		$replace[4][0]="Ä";
		$replace[4][1]="&Auml;";
		$replace[5][0]="Ü";
		$replace[5][1]="&Uuml;";
		$replace[6][0]="ß";
		$replace[6][1]="&szlig;";
		//echo "funktion!";
		//echo "<pre>" .print_r($replace,true) ."</pre>";
		for($i=0;$i<sizeof($replace);$i++){
			$str = str_replace($replace[$i][0], $replace[$i][1], $str);
			//echo "0: {$replace[$i][0]} <br />1: {$replace[$i][1]}<br />";
		}
		return $str;
	}
	
	if(isset($_POST['username'], $_POST['passwort'], $_POST['vorname'], $_POST['nachname'], $_POST['email'])){
		
		$ins_qry = "INSERT INTO
						users (name, passwort, rechte, vorname, nachname, email, reg_hash";
		
		if(isset($_POST['ort']) && $_POST['ort']!=""){
			$ins_qry .= ", ort";
		}
		if(isset($_POST['telnr']) && $_POST['telnr']!=""){
			$ins_qry .= ", telnr";
		}
		if(isset($_POST['msn']) && $_POST['msn']!=""){
			$ins_qry .= ", msn";
		}
		if(isset($_POST['icq']) && $_POST['icq']!=""){
			$ins_qry .= ", icq";
		}
		if(isset($_POST['skype']) && $_POST['skype']!=""){
			$ins_qry .= ", skype";
		}
		
		$ins_qry .= ") VALUES ('" .$_POST['username'] ."', '" .hash($config['hash_algo'], $_POST['passwort']) ."', '50', '" . myescape($_POST['vorname']) ."', '" . myescape($_POST['nachname']) ."', '" . $_POST['email'] ."', '" .hash("whirlpool", microtime()) ."'";
		
		if(isset($_POST['ort']) && $_POST['ort']!=""){
			$ins_qry .= ", '".myescape($_POST['ort']) ."'";
		}
		if(isset($_POST['telnr']) && $_POST['telnr']!=""){
			$ins_qry .= ", '".myescape($_POST['telnr']) ."'";
		}
		if(isset($_POST['msn']) && $_POST['msn']!=""){
			$ins_qry .= ", '".myescape($_POST['msn']) ."'";
		}
		if(isset($_POST['icq']) && $_POST['icq']!=""){
			$ins_qry .= ", '".myescape($_POST['icq']) ."'";
		}
		if(isset($_POST['skype']) && $_POST['skype']!=""){
			$ins_qry .= ", '".myescape($_POST['skype']) ."'";
		}
		
		$ins_qry .= ")";
		
		if(mysql_query($ins_qry)){
			//echo $ins_qry."<br />";
			$hash_sql = "select reg_hash, vorname, nachname, name from users where id = " .mysql_insert_id();
			$hash_res = mysql_query($hash_sql);
			$hash = mysql_fetch_array($hash_res);
			
			$msg = "Herzlich willkommen {$hash['vorname']} {$hash['nachname']}!\n";
			$msg .= "Danke für ihre Anmeldung auf der Absolventenseite der HTL3R.\n\n";
			$msg .= "Ihr Benutzername lautet: {$hash['name']} \n\n";
			$msg .= "Klicken sie bitte auf den folgenden Link um ihren Benutzer freizuschalten: ";
			$msg .= dirname($_SERVER['HTTP_REFERER']) ."/unlock_user.php?unlock={$hash['reg_hash']}";
			//echo $msg;
			
			mail($_POST['email'], "Registrierung auf der HTL3Rennweg Absolventenseite", $msg);
			echo 'Danke f&uuml;r die Registrierung! <a href="index.php">Hier</a> kommen sie zur Hauptseite!';
		}
		else{
			echo mysql_error() ."<br />";// .$ins_qry ."<br />";
			echo "Fehler bei der Registrierung, bitte versuchen sie es erneut!";
		}
		
	}
	
	else{
		echo '<h1>Registrierung</h1>
		<form method="post" action="" name="useranmeldung">
			<table>
				<tr>
					<td>*Benutzername:</td>
					<td><input type="text" name="username" id="username" onblur="check(\'username\')"  maxlength="20" /></td>
					<td><div id="username_errmsg"></div></td>
				</tr>
				<tr>
					<td>*Passwort:</td>
					<td><input type="password" name="passwort" id="passwort" onblur="check(\'passwort\');checkDouble(\'passwort\', \'passwort_best\')" maxlength="20" /></td>
					<td><div id="passwort_errmsg"></div></td>
				</tr>
				<tr>
					<td>*Passwort wiederholen:</td>
					<td><input type="password" name="passwort_best" id="passwort_best" onblur="check(\'passwort_best\');checkDouble(\'passwort\', \'passwort_best\')"  maxlength="20" /></td>
					<td><div id="passwort_best_errmsg"></div></td>
				</tr>
				<tr>
					<td>*Vorname:</td>
					<td><input type="text" name="vorname" id="vorname" onblur="check(\'vorname\')" /></td>
					<td><div id="vorname_errmsg"></div></td>
				</tr>
				<tr>
					<td>*Nachname</td>
					<td><input type="text" name="nachname" id="nachname" onblur="check(\'nachname\')" /></td>
					<td><div id="nachname_errmsg"></div></td>
				</tr>
				<tr>
					<td>*E-Mail Adresse</td>
					<td><input type="text" name="email" id="email" onblur="check(\'email\')" /></td>
					<td><div id="email_errmsg"></div></td>
				</tr>
				<tr>
					<td>Wohnort</td>
					<td><input type="text" name="ort" id="ort" /></td>
					<td></td>
				</tr>
				<tr>
					<td>Telefonnummer</td>
					<td><input type="text" name="telnr" id="telnr" /></td>
					<td></td>
				</tr>
				<tr>
					<td>MSN</td>
					<td><input type="text" name="msn" id="msn" /></td>
					<td></td>
				</tr>
				<tr>
					<td>ICQ</td>
					<td><input type="text" name="icq" id="icq" /></td>
					<td></td>
				</tr>
				<tr>
					<td>Skype</td>
					<td><input type="text" name="skype" id="skype" /></td>
					<td></td>
				</tr>
				<tr>
					<td>Hobbys</td>
					<td>
						<select name="hobbys" id="hobbys" size="3" multiple="multiple">
							<option value="Feuerwehr">Feuerwehr</option>
							<option value="Schwimmen">Schwimmen</option>
							<option value="Schi fahren">Schi fahren</option>
							<option value="Computer spielen">Computer spielen</option>
							<option value="Rad fahren">Rad fahren</option>
						</select>
					</td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="button" name="absenden" id="absenden" value="Absenden" onclick="fullCheck()" /><input type="submit" value="absenden" /></td>
				</tr>
			</table>
		</form>
		Mit * markierte Felder m&uuml;ssen ausgef&uuml;llt werden!';
		
	}
?>