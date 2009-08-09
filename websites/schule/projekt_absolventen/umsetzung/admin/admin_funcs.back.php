<!--
 * Copyright 2008 Philip Wölfel <philip.woelfel@frig.at>
-->
<?php
class AdminFunctions{
	protected $con=null;
	private $replace = null;
	private $config;
	private $rights;
	protected $cur_func;
	protected $error;
	protected $user;
	
	function __construct($name, $psw){
		global $config;
		
		$this->replace[0][0]="ö";
		$this->replace[0][1]="&ouml;";
		$this->replace[1][0]="ä";
		$this->replace[1][1]="&auml;";
		$this->replace[2][0]="ü";
		$this->replace[2][1]="&uuml;";
		$this->replace[3][0]="Ö";
		$this->replace[3][1]="&Ouml;";
		$this->replace[4][0]="Ä";
		$this->replace[4][1]="&Auml;";
		$this->replace[5][0]="Ü";
		$this->replace[5][1]="&Uuml;";
		$this->replace[6][0]="ß";
		$this->replace[6][1]="&szlig;";
		
		$this->rights[0]['name'] = "keine";
		$this->rights[0]['recht'] = 0;
		$this->rights[1]['name'] = "Benutzer";
		$this->rights[1]['recht'] = 50;
		$this->rights[2]['name'] = "Admin";
		$this->rights[2]['recht'] = 100;
		$this->rights[3]['name'] = "Sys Admin";
		$this->rights[3]['recht'] = 150;
		$this->rights[4]['name'] = "nicht eingeloggt";
		$this->rights[4]['recht'] = -1;
		
		$allowed = false;
		//echo "name: $name <br />psw: $psw";
		if($this->testUser()){
			return true;
		}
		else{
			$admins = mysql_query("select id, name, passwort, rechte from users where rechte >= 100 and unlocked = 1 and name = '" .$name ."'");
			while($admin = mysql_fetch_array($admins)){
				//echo "<pre>" .print_r($admin,true) ."</pre>";
				if(hash($config['hash_algo'], $psw)==$admin['passwort']){
					$allowed = true;
					@session_start();
					$_SESSION['userid'] = $admin['id'];
					$_SESSION['username'] = $admin['name'];
					$_SESSION['userpsw'] = $admin['passwort'];
					$this->user['id'] = $admin['id'];
					$this->user['name'] = $admin['name'];
					$this->user['psw'] = $admin['passwort'];
					$this->user['rechte'] = $admin['rechte'];
					break;
				}
				else{
					$allowed = false;
					break;
				}
			}
			if($allowed){
				return true;
				echo "Logged in";
			}
			else{
				return false;
				echo "Invalid User";
			}
		}
	}
	
	function testUser(){
		//echo session_id() ." " .session_name();
		if(isset($_SESSION['userid'])){
			$cadmins = mysql_query("select id, name, passwort, rechte from users where rechte >= 100 and id = '" .$_SESSION['userid'] ."' and unlocked = 1");
			if(mysql_num_rows($cadmins)>0){
				$row = mysql_fetch_array($cadmins);
				if($row['passwort'] == $_SESSION['userpsw']){
					$this->user['id'] = $row['id'];
					$this->user['name'] = $row['name'];
					$this->user['psw'] = $row['passwort'];
					$this->user['rechte'] = $row['rechte'];
					return true;
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
		}
		return false;
	}
	
	function action($what){
		if($this->testUser()){
			$dat = date("l d.m.Y H:i:s T");
			$fh = fopen("log.txt", "a");
			fwrite($fh, $dat ." -- " .$_SESSION['username'] ."(" .$_SESSION['userid'] .", " .$_SERVER['REMOTE_ADDR'] .") -- " .$what ."\n\r");
			fclose($fh);
			if($what!=""){
				if($what=='logout'){
					session_destroy();
					//$out[1] = '<meta http-equiv="refresh" content="0; url=index.php">';
					echo 'Sie wurden erfolgreich ausgeloggt! <a href="index.php">Hier</a> klicken um sich wieder anzumelden.';
					return true;
				}
				else{
					return $this->$what();
				}
			}
		}
	}
	
	function getActions(){
		$actions = array("newContent" => "neuer Inhalt",
						"editContent" => "Inhalt bearbeiten",
						"delContent" => "Inhalt l&ouml;schen",
						"newNav" => "neuer Men&uuml;eintrag",
						"editNav" => "Men&uuml;eintrag bearbeiten",
						"delNav" => "Men&uuml;eintrag l&ouml;schen",
						"newNavGroup" => "neue Men&uuml;kat.",
						"editNavGroup" => "Men&uuml;kat. bearbeiten",
						"delNavGroup" => "Men&uuml;kat. l&ouml;schen",
						"newNews" => "neue News",
						"editNews" => "News bearbeiten",
						"delNews" => "News l&ouml;schen",
						"delNewsComment" => "News Comment l&ouml;schen",
						"newTermin" => "neuer Termin",
						"delTermin" => "Termin l&ouml;schen",
						"delShoutbox" => "Shoutboxeintr. l&ouml;schen",
						"newUser" => "neuer User",
						"editUser" => "User bearbeiten",
						"delUser" => "User l&ouml;schen",
						"unlockUser" => "User freischalten",
						"testInsert" => "Insert testen",
						"testUpdate" => "Update testen",
						"testDelete" => "Delete testen",
						"logout" => "ausloggen");
		return $actions;
	}
	
	private function escape($str){
		for($i=0;$i<sizeof($this->replace);$i++){
			$str = str_replace($this->replace[$i][0], $this->replace[$i][1], $str);
		}
		//$str = str_replace("\r\n", "\n", $str);
		//$str = mysql_real_escape_string($str);
		return $str;
	}
	private function unescape($str){
		for($i=0;$i<sizeof($this->replace);$i++){
			$str = str_replace($this->replace[$i][1], $this->replace[$i][0], $str);
		}
		return $str;
	}
	
	
	private function query($sql){
		//$sql = mysql_real_escape_string($sql);
		$resul = mysql_query($sql);
		/*
		if(!preg_match("/^select/i", $sql)){//selects werden nicht in das query log geschrieben
			$dat = date("l d.m.Y H:i:s T");
			$fh = fopen("log_querys.txt", "a");
			fwrite($fh, $dat ." -- " .$_SESSION['username'] ."(" .$_SESSION['userid'] .", " .$_SERVER['REMOTE_ADDR'] .") -- " .$sql ."\n\r");
			fclose($fh);
		}
		*/
		if(!$resul){//Fehler beim Query
			$err = "Error: " .mysql_error() ."<br />Query: " .$sql;
			echo $err;
			$this->error = $err;
			return false;
		}
		else{
			if(is_resource($resul)){//select oder aehnliches
				return $resul;
			}
			else{//insert oder aehnliches
				return true;
			}
		}
	}
	
	private function db_insert($table, $fields, $values){
		if(!is_array($fields) || !is_array($values) || sizeof($fields) != sizeof($values) || sizeof($fields)<1 || sizeof($values)<1 || $table == ""){ // ungueltige Parameter
			echo "Fehler bei den Parametern!<br />\n";
			$this->error = "Fehler bei den Parametern!<br />\n";
			return false;
		}
		else{ // gueltige Parameter
			$ins_qry = "insert into $table (";
			$ins_qry .= implode(", ", $fields);
			$ins_qry .= ")";
			$ins_qry .= " values (";
			//$ins_qry .= implode(", ", $values);
			for($i=0;$i<sizeof($values);$i++){
				$ins_qry .= "'" .mysql_real_escape_string($values[$i]) ."'";
				if($i!=sizeof($values)-1){ // wenn nicht das letzte Element is kommt ein ", " dran
					$ins_qry .= ", ";
				}
			}
			$ins_qry .= ");";
			//echo $ins_qry;
			
			$ins_res = $this->query($ins_qry);
			if(!$ins_res){
				$err = "Fehler beim einf&uuml;gen der Daten!<br />"
						."Query: $del_qry<br />"
						."Error: " .mysql_error();
				echo $err;
				$this->error = $err;
				return false;
			}
			else{
				echo "Daten eingef&uuml;gt!<br />\n";
			}
		}
	}
	
	private function db_update($table, $fields, $values, $id){
		if(!is_array($fields) || !is_array($values) || sizeof($fields) != sizeof($values) || sizeof($fields)<1 || sizeof($values)<1 || $table == "" || !is_int($id)){ // ungueltige Parameter
			echo "Fehler bei den Parametern!<br />\n";
			$this->error = "Fehler bei den Parametern!<br />\n";
			return false;
		}
		else{ // gueltige Parameter
			$upd_qry = "update $table set ";
			
			for($i=0;$i<sizeof($values);$i++){
				$upd_qry .= "$fields[$i] = '" .mysql_real_escape_string($values[$i]) ."'";
				if($i!=sizeof($values)-1){ // wenn nicht das letzte Element is kommt ein ", " dran
					$upd_qry .= ", ";
				}
			}
			$upd_qry .= " where id = $id;";
			//echo $upd_qry;
			
			$upd_res = $this->query($upd_qry);
			if(!$upd_res){
				$err = "Fehler beim updaten der Daten!<br />"
						."Query: $del_qry<br />"
						."Error: " .mysql_error();
				echo $err;
				$this->error = $err;
				return false;
			}
			else{
				echo "Daten upgedatet!<br />\n";
			}
		}
	}
	
	private function db_delete($table, $id){
		if($table == "" || !is_int($id)){
			echo "Fehler bei den Parametern!<br />\n";
			$this->error = "Fehler bei den Parametern!<br />\n";
			return false;
		}
		else{
			$del_qry = "delete from $table where id = $id;";
			$del_res = $this->query($del_qry);
			if(!$del_res){
				$err = "Fehler beim l&ouml;schen der Daten!<br />"
						."Query: $del_qry<br />"
						."Error: " .mysql_error();
				echo $err;
				$this->error = $err;
				return false;
			}
			else{
				echo "Daten gel&ouml;scht!<br />\n";
				return true;
			}
		}
	}
	
	private function insert($types, $names, $fields, $table){
		if(!is_array($types)
			|| !is_array($names)
			|| !is_array($fields)
			|| sizeof($types) != sizeof($names)
			|| sizeof($names) != sizeof($fields)
			|| sizeof($types)<1
			|| sizeof($names)<1
			|| sizeof($fields)<1
			|| $table == ""
			){ // ungueltige Parameter
			echo "Fehler bei den Parametern!<br />\n";
			$this->error = "Fehler bei den Parametern!<br />\n";
			return false;
		}
		else{ // gueltige Parameter
			
			//eingabeform:
			echo '<form method="post" action="?action=' .$this->cur_func .'" name="insert_form_' .$table .'">';
			echo '<table>';
			foreach($types as $info => $type){
				$name = each($names);
				$field = each($fields);
				echo '<tr>';
				switch($type){
					case 'textfield':
						echo '<td>' .$name['value'] .'</td><td><input type="text" name="' .$field['value'] .'" /></td>';
						break;
					
					case 'password':
						echo '<td>' .$name['value'] .'</td><td><input type="password" name="' .$field['value'] .'" /></td>';
						break;
						
					case 'textarea':
						echo '<td>' .$name['value'] .'</td><td><textarea cols="45" rows="15" name="' .$field['value'] .'"></textarea></td>';
						break;
					
					case 'checkbox':
						echo '<td>' .$name['value'] .'</td><td><input type="checkbox" name="' .$field['value'] .'" value="1" /></td>';
						break;
						
					case 'select':
						
						break;
				}
				echo '</tr>';
			}
			echo '<td colspan="2"><input type="submit" name="insert_form_' .$table .'_submit" value="speichern" /></td>';
			echo '</table>';
			echo '</form>';
			
			//in die DB schreiben:
			foreach($fields as $field){
				if( !isset($_POST[$field]) ){
					return;
				}
			}
			
			$values = array();
			foreach($fields as $field){
				$values[] = $_POST[$field];
			}
			$this->db_insert("termine", $fields, $values);
		}
	}
	
	private function testInsert(){
		global $config;
		$this->cur_func = "testInsert";
		if($this->testUser()){
			$types = array("textfield", "textfield", "textarea");
			$names = array("Datum", "Kurzbeschreibung", "Inhalt");
			$fields = array("datum", "kurztext", "langtext");
			
			$this->insert($types, $names, $fields, "termine");
		}
	}
	
	private function newContent(){
		global $config;
		$this->cur_func = "newContent";
		if($this->testUser()){
			echo '
				<form method="post" action="?action=' .$this->cur_func .'">
				<table border="0" align="center">
				<tr>
					<td align="center" colspan="2">Titel:<input type="text" name="title" /></td>
				</tr>
				<tr>
					<td align="center">Head:</td>
					<td align="center"><textarea name="head" cols="80" rows="20"></textarea></td>
				</tr>
				<tr>
					<td align="center">Content:</td>
					<td align="center"><textarea name="content" cols="80" rows="20"></textarea></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><input type="submit" value="speichern" /></td>
				</tr>
				</table>
				</form>';
			
			if(isset($_POST['title']) && isset($_POST['content']) && isset($_POST['head'])){
				$title = $_POST['title'];
				$content = $_POST['content'];
				$head_cont = $_POST['head'];
				
				//$content = htmlspecialchars_decode($content);
				$content = $this->escape($content);
				$head_cont = $this->escape($head_cont);

				$this->db_insert("content",
						array("titel", "head", "content"),
						array($title, $head_cont, $content));
			}
		}
		//echo $out[0];
		$this->cur_func = "";
		return;
	}
	
	private function editContent(){
		global $config;
		$this->cur_func = "editContent";
		if($this->testUser()){	
			if(isset($_POST['title']) && isset($_POST['content']) && isset($_POST['head']) && isset($_POST['id'])){
					$title = $_POST['title'];
					$content = $_POST['content'];
					$id = intval($_POST['id']);
					$head_cont = $_POST['head'];
					
					$content = $this->escape($content);
					$head_cont = $this->escape($head_cont);
					
					$this->db_update("content",
						array("titel", "head", "content"),
						array($title, $head_cont, $content), $id);
					
			}
				
			if(isset($_POST['id'])){
				$id = $_POST['id'];
				$daten = $this->query("select * from content where id='" .$id ."'");
				if($daten){
					$row = mysql_fetch_array($daten);
	
					$cont = $row['content'];
					$cont = $this->unescape($cont);
					$cont = htmlspecialchars($cont);
					$cont = $this->escape($cont);
					$head = $row['head'];
					echo '
						<form method="post" action="?action=' .$this->cur_func .'">
						<table border="0" align="center">
						<tr>
							<td align="center" colspan="2">Titel: <input type="text" name="title" value="' .$row['titel'] .'" /></td>
						</tr>
						<tr>
							<td align="center">Head:</td>
							<td align="center"><textarea name="head" cols="80" rows="20">' .$head .'</textarea></td>
						</tr>
						<tr>
							<td align="center">Content:</td>
						<td align="center"><textarea name="content" cols="80" rows="20">' .$cont .'</textarea></td>
						</tr>
						<tr>
							<td align="center" colspan="3"><input type="submit" value="speichern" /><input type="hidden" value="' .$id .'" name="id" /></td>
						</tr>
						</table>
						</form>';
				}
				
			}
			else{
				$daten=$this->query("select * from content order by titel asc");
				if($daten){
					echo '<form method="post" action="?action=' .$this->cur_func .'">
							<select size="1" name="id">';
					
					while($row = mysql_fetch_array($daten)){
						echo '<option value="' .$row['id'] .'">' .$row['titel'] .'</option>';
					}
					
					echo 	'</select>
							<input type="submit" value="ausw&auml;hlen" />
						</form>';
				}
				
			}
		}
		$this->cur_func = "";
		return;
	}
	
	private function delContent(){
		global $config;
		$this->cur_func = "delContent";
		if($this->testUser()){
			if(isset($_POST['del'])){
				$this->db_delete("content", intval($_POST['del']));
			}
			
			$cont = $this->query("select * from content order by titel asc");
			if($cont){
				echo '<form method="post" action="?action=' .$this->cur_func .'">
					<select size="1" name="del">
					';
				
				while($row=mysql_fetch_array($cont)){
					echo '<option value="' .$row['id'] .'">' .$row['titel'] .'</option>
					';
				}
				echo '</select>
				<input type="submit" value="l&ouml;schen" />
				</form>';
			}
		}
		$this->cur_func = "";
		return;
	}
	
	private function newNav(){
		global $config;
		$this->cur_func = "newNav";
		if($this->testUser()){
			if(isset($_POST['title']) && isset($_POST['pos']) && isset($_POST['nav']) && (isset($_POST['site']) || isset($_POST['sitelist'])) && isset($_POST['recht'])){
				$title = $_POST['title'];
				$pos = $_POST['pos'];
				$nav = $_POST['nav'];
				
				$recht = $_POST['recht'];
				
				if(isset($_POST['sub'])){
					$class = 'navi_sub';
				}
				else{
					$class = 'navi';
				}
				if(isset($_POST['issite'])){
					$extern = 0;
					$site = $_POST['sitelist'];
				}
				else{
					$extern = 1;
					$site = $_POST['site'];
				}
				$title = $this->escape($title);
				
				$this->db_insert("nav",
					array("titel", "site", "nav", "pos", "klasse", "recht", "extern"),
					array($title, $site, $nav, $pos, $class, $recht, $extern));
			}

			echo "<script type=\"text/javascript\">
							function showsites(){
								if(document.getElementById('issite').checked){
									document.getElementById('site').style.display = 'none';
									document.getElementById('sitelist').style.display = 'block';
								}
								else{
									document.getElementById('site').style.display = 'block';
									document.getElementById('sitelist').style.display = 'none';
								}
							}
						</script>".
				'<form method="post" action="?action=' .$this->cur_func .'">
				<table border="0" align="center">
				<tr>
					<td align="center" colspan="2">Titel:</td>
					<td align="center">
						<input type="text" name="title" /></td>
				</tr>
				<tr>
					<td align="center">Link:</td>
					<td align="center">
						<input type="checkbox" name="issite" value="true" onclick="showsites()" id="issite" />interne Seite<br />
					</td>
					<td align="center">
						<input type="text" name="site" id="site" />
						<select size="1" name="sitelist" id="sitelist" style="display: none;">';
						
				$sites = $this->query("select titel from content order by titel asc");
				
				if($sites){
					while($si = mysql_fetch_array($sites)){
						echo '<option value="' .$si['titel'] .'">' .$si['titel'] .'</option>';
					}
				}
				
					echo '</select>
					</td>
				</tr>
				<tr>
					<td colspan="2">Position:</td>
					<td>
						<input type="text" name="pos" />
					</td>
				</tr>
				<tr>
					<td colspan="2">Nav:</td>
					<td>
						<select size="1" name="nav">';
						$navgroups = $this->query("select * from navgroups order by pos asc");
						if($navgroups){
							while($navitem = mysql_fetch_array($navgroups)){
								echo  '<option value="' .$navitem['id'] .'">' .$navitem['titel'] .'</option>';
							}
						}
					echo  '</select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						Berechtigung:
					</td>
					<td>
						<select size="1" name="recht">';
						for($i=0;$i<sizeof($this->rights);$i++){
							echo '<option value="' .$this->rights[$i]['recht'] .'">' .$this->rights[$i]['name'] .'</option>';
						}
					echo '</select>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="checkbox" name="sub" value="true" />sub<br /></td>
					<td><input type="submit" value="speichern" /><input type="hidden" value="' .$id .'" name="id" /></td>
				</tr>
				</table>
				</form>';
		}
		$this->cur_func = "";
		return;
	}
	
	private function editNav(){
		global $config;
		$this->cur_func = "editNav";
		if($this->testUser()){
			if(isset($_POST['title']) && isset($_POST['pos']) && isset($_POST['nav']) && isset($_POST['site']) && isset($_POST['id']) && isset($_POST['recht'])){
				$title = $_POST['title'];
				$pos = $_POST['pos'];
				$nav = $_POST['nav'];
				$id = intval($_POST['id']);
				$recht = $_POST['recht'];
				
				if(isset($_POST['sub'])){
					$class = 'navi_sub';
				}
				else{
					$class = 'navi';
				}
				
				if(isset($_POST['issite'])){
					$extern = 0;
					$site = $_POST['sitelist'];
				}
				else{
					$extern = 1;
					$site = $_POST['site'];
				}
				
				$title = $this->escape($title);
				
				$this->db_update("nav",
								array("titel", "site", "nav", "pos", "klasse", "recht", "extern"),
								array($title, $site, $nav, $pos, $class, $recht, $extern),
								$id);
			}
				
			if(isset($_POST['id'])){
				$id = $_POST['id'];
				$daten = $this->query("select * from nav where id='" .$id ."'");
				if($daten){
					$row = mysql_fetch_array($daten);
					echo "<script type=\"text/javascript\">
							function showsites(){
								if(document.getElementById('issite').checked){
									document.getElementById('site').style.display = 'none';
									document.getElementById('sitelist').style.display = 'block';
								}
								else{
									document.getElementById('site').style.display = 'block';
									document.getElementById('sitelist').style.display = 'none';
								}
							}
						</script>".'
					<form method="post" action="?action=' .$this->cur_func .'">
					<table border="0">
					<tr>
						<td colspan="2">Titel:</td>
						<td>
							<input type="text" name="title" value="' .$row['titel'] .'" /></td>
					</tr>
					<tr>
						<td>Link:</td>
						<td><input type="checkbox" name="issite" value="true" onclick="showsites()" id="issite"' .($row['extern']==0?' checked="checked"':'') .' />interne Seite</td>
						<td><input type="text" name="site" id="site" value="' .$row['site'] .'" style="' .($row['extern']==0?'display: none;':'') .'" />
							<select size="1" name="sitelist" id="sitelist" style="' .($row['extern']==1?'display: none;':'') .'">';
							
							$sites = $this->query("select titel from content order by titel asc");
							
							if($sites){
								while($si = mysql_fetch_array($sites)){
									echo '<option value="' .$si['titel'] .'"' .($si['titel']==$row['site']?' selected="selected"':'') .'>' .$si['titel'] .'</option>';
								}
							}
							
						echo '</select>
							
						</td>
					</tr>
					<tr>
						<td colspan="2">Position:</td>
						<td>
							<input type="text" name="pos" value="' .$row['pos'] .'" />
						</td>
					</tr>
					<tr>
						<td colspan="2">Nav:</td>
						<td>
							<select size="1" name="nav">';
							
							$navgroups = $this->query("select * from navgroups order by pos asc");
							while($navitem = mysql_fetch_array($navgroups)){
								echo  '<option value="' .$navitem['id'] .'" '.($navitem['id'] == $row['nav']?'selected="selected"':'').'>' .$navitem['titel'] .'</option>';
							}
							
							
						echo  '</select>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							Berechtigung:
						</td>
						<td>
							<select size="1" name="recht">';
							for($i=0;$i<sizeof($this->rights);$i++){
								echo '<option value="' .$this->rights[$i]['recht'] .'"' .($row['recht']==$this->rights[$i]['recht']?' selected="selected"':'') .'>' .$this->rights[$i]['name'] .'</option>';
							}
						echo '</select>
						</td>
					</tr>
					<tr>
						<td colspan="2"><input type="checkbox" name="sub" value="true"' .($row['klasse'] == 'sub'?'  checked="checked"':'') .' />sub<br /></td>
						<td><input type="submit" value="speichern" /><input type="hidden" value="' .$id .'" name="id" /></td>
					</tr>
					</table>
					<input type="hidden" name="id" value="' .$id .'" />
					</form>';
				}
				
				
			}
			else{
				echo '<form method="post" action="?action=' .$this->cur_func .'">
				<select size="1" name="id">
					';
				
				$daten=$this->query("select * from nav order by nav, pos asc");
				if($daten){
					while($row = mysql_fetch_array($daten)){
						echo  '<option value="' .$row['id'] .'">' .$row['titel'] .'</option>
						';
					}
				}
				
				echo  '</select>
				<input type="submit" value="ausw&auml;hlen" />
				</form>';
			}
		}
		$this->cur_func = "";
		return;
	}
	
	private function delNav(){
		global $config;
		$this->cur_func = "delNav";
		if($this->testUser()){
			if(isset($_POST['del'])){
				$this->db_delete("nav", intval($_POST['del']));
			}
			
			$cont = $this->query("select * from nav order by nav, pos asc");
			echo  '<form method="post" action="?action=' .$this->cur_func .'">
				<select size="1" name="del">
				';
			if($cont){
				while($row=mysql_fetch_array($cont)){
					echo '<option value="' .$row['id'] .'">' .$row['titel'] .'</option>
					';
				}
			}
			
			echo  '</select>
			<input type="submit" value="l&ouml;schen" />
			</form>';
		}
		$this->cur_func = "";
		return;
	}
	
	private function delTermin(){
		global $config;
		$this->cur_func = "delTermin";
		if($this->testUser()){
			if(isset($_POST['id'])){
				$id = intval($_POST['id']);
				$this->db_delete("termine", $id);
			}
			
			$daten = $this->query("select id, datum, kurztext from termine order by datum asc");
			
			
			echo  '<form method="post" action="?action=' .$this->cur_func .'">
			Datum: <select name="id">
			';
			
			if($daten){
				while($termin = mysql_fetch_array($daten)){
					echo  '<option value="' .$termin['id'] .'">' .$termin['datum'] .' - ' .$termin['kurztext'] .'</option>';
				}
			}
			
			echo  '</select>
			<input type="submit" value="Termin l&ouml;schen" />
			</form>';

		}
		$this->cur_func = "";
		return;
	}
	
	private function delShoutbox(){
		global $config;
		$this->cur_func = "delShoutbox";
		if($this->testUser()){
			if(isset($_POST['id'])){
				$id = intval($_POST['id']);
				$this->db_delete("shoutbox", $id);
			}
			
			echo "<form action=\"?action=delShoutbox\" method=\"post\">";
			$replacea = array(":D", ":P", ":)", ":(", ";(", ";|", ";D");
			$repl = array("biggrin.gif", "tongue.gif", "smile.gif", "frown.gif", "cry.gif", "mad.gif", "diablotin.gif");
			
			$daten = $this->query("SELECT Name, Nachricht, id FROM shoutbox ORDER BY id DESC");
			if($daten){
				while($row = mysql_fetch_array($daten)){
						$temp = $row['Nachricht'];
						for ($i = 0; $i < sizeof($replacea); $i++){
							$temp = str_replace($replacea[$i], '<img src="../images/smileys/' .$repl[$i] .'" alt="' .$repl[$i] .'" />', $temp);
						}	
					echo "<input type=\"radio\" class=\"radio\" value=\"" .$row['id'] ."\" name=\"id\" />" .$row['Name'].": " .$temp ."<br />";
				}
			}
			
			echo "<input value=\"l&ouml;schen\" type=\"submit\" /></form>";
		}
		$this->cur_func = "";
		return;
	}
	
	private function newNews(){
		global $config;
		global $selbst;
		$this->cur_func = "newNews";
		if($this->testUser()){
			if(isset($_POST['text'])&& isset($_POST['titel'])){
				$text=$_POST['text'];
				$titel=$_POST['titel'];
				//$text = str_replace("\n", "<br />", $text);
				$text = nl2br($text);
				$text = $this->escape($text);
				
				$this->db_insert("news",
						array("titel", "text"),
						array($titel, $text));
			}
			
			echo  '<form action="' .$selbst .'&action=' .$this->cur_func .'" method="post">
						<table border="0">
						<tr>
								<td><input type="text" id="titel" name="titel" onfocus="if(this.value==\'Titel\'){this.value=\'\'}" value="Titel" /></td>
						</tr>
						<tr>
								<td><textarea cols="50" rows="10" name="text" id="text"></textarea></td>
						</tr>
						<tr>
								<td><input type="submit" value="speichern" /></td>
						</tr>
						</table>
						</form>';
		}
		$this->cur_func = "";
		return;
	}
	
	private function editNews(){
		global $config;
		global $selbst;
		$this->cur_func = "editNews";
		if($this->testUser()){
			if(isset($_POST['text'])&& isset($_POST['titel']) && isset($_POST['id'])){
				$text=$_POST['text'];
				$titel=$_POST['titel'];
				//$text = str_replace("\n", "<br />", $text);
				$text = nl2br($text);
				$text = $this->escape($text);
				
				$this->db_update("news",
						 array("titel", "text"),
						 array($titel, $text),
						 intval($_POST['id']));
			}
			if(isset($_POST['id'])){
				$id = $_POST['id'];
				
				$daten = $this->query("select * from news where id='" .$id ."'");
				if($daten){
					$row = mysql_fetch_array($daten);
					$text = str_replace("<", "&lt;", $row['text']);
					$text = str_replace("&lt;br />", "", $text);
					echo  '<form action="' .$selbst .'&action=' .$this->cur_func .'" method="post">
								<table border="0">
								<tr>
										<td><input type="text" id="titel" name="titel" onclick="if(this.value==\'Titel\'){this.value=\'\'}" value="' .$row['titel'] .'" /></td>
								</tr>
								<tr>
										<td><textarea cols="50" rows="10" name="text" id="text">' .$text .'</textarea></td>
								</tr>
								<tr>
										<td><input type="submit" value="speichern" /></td>
								</tr>
								</table>
								<input type="hidden" name="id" value="' .$id .'" />
								</form>';
				}
				
			}
			else{
				echo '<form method="post" action="' .$selbst .'&action=' .$this->cur_func .'">
				<select size="1" name="id">
					';
				
				$daten=$this->query("select * from news order by id desc");
				
				if($daten){
					while($row = mysql_fetch_array($daten)){
						echo  '<option value="' .$row['id'] .'">' .$row['titel'] .'</option>
						';
					}
				}
				
				echo  '</select>
				<input type="submit" value="ausw&auml;hlen" />
				</form>';
			}
		}
		$this->cur_func = "";
		return;
	}
	
	private function delNews(){
		global $config;
		global $selbst;
		$this->cur_func = "delNews";
		if($this->testUser()){
			if(isset($_POST['id'])){
				$id = intval($_POST['id']);
				$this->db_delete("news", $id);
			}
			
			echo "<form action=\"$selbst&action=$this->cur_func\" method=\"post\"><select size=\"1\" name=\"id\">";
			
			$daten = $this->query("select titel, datum, text, id from news order by id desc");
			if($daten){
				while($row = mysql_fetch_array($daten)){
					echo "<option value=\"" .$row['id'] ."\">" .$row['titel'] ."</option>";
				}
			}
			echo "</select><input value=\"OK!\" type=\"submit\" /></form>";
		}
		$this->cur_func = "";
		return;
	}
	
	private function newTermin(){
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
		
		global $config;
		$this->cur_func = "newTermin";
		if($this->testUser()){
			$jahr = date("Y");
			echo  '<form method="post" action="?action=' .$this->cur_func .'">
			Jahr: <select name="jahr">
					<option value="' .$jahr .'">' .$jahr .'</option>
					<option value="' .($jahr+1) .'">' .($jahr+1) .'</option>
					<option value="' .($jahr+2) .'">' .($jahr+2) .'</option>
					<option value="' .($jahr+3) .'">' .($jahr+3) .'</option>
				</select><br />
			Monat: <select name="monat">
			';
			for($i=1;$i<=12;$i++){
				echo  '<option value="' .$i .'">' .$monate[$i] .'</option>
				';
			}
			echo  	'</select>
						<br />
						Tag: <select name="tag">
						';
			for($j=1;$j<=31;$j++){
				echo  '<option value="' .$j .'">' .$j .'</option>
				';
			}
			echo  '</select>
			<br />
			Termin: <input type="text" name="termin" />
			<br />
			<input type="submit" value="Termin speichern" />
			</form>
			<br />';
			
			if(isset($_POST['monat']) && isset($_POST['tag']) && isset($_POST['termin']) && isset($_POST['jahr'])){
				$monat = $_POST['monat'];
				$tag = $_POST['tag'];
				$termin = $_POST['termin'];
				$jahr = $_POST['jahr'];
				$termin = $this->escape($termin);
				$this->db_insert("termine",
						array("datum", "kurztext"),
						array($jahr ."-" .$monat ."-" .$tag, $termin));
			}
		}
		$this->cur_func = "";
		return;
	}
	private function newUser(){
		global $config;
		$this->cur_func = "newUser";
		if($this->testUser()){
			if(isset($_POST['name'])&& isset($_POST['passwort'])&& isset($_POST['recht'])){
				$name=$_POST['name'];
				$pw=hash($config['hash_algo'], $_POST['passwort']);
				$rechte = $_POST['recht'];
				
				$this->db_insert("users",
							array("name", "passwort", "rechte", "unlocked"),
							array($name, $pw, $rechte , 1));
				
			}
			
			echo  '<form action="?action=' .$this->cur_func .'" method="post">
						<table border="0">
						<tr>
								<td>Name:</td><td><input type="text" id="name" name="name" />
						</tr>
						<tr>
								<td>Passwort:</td><td><input type="password" id="passwort" name="passwort" /></td>
						</tr>
						<tr>
								<td>Berechtigung:</td>
								<td>
									<select size="1" name="recht">';
								for($i=0;$i<sizeof($this->rights);$i++){
									echo '<option value="' .$this->rights[$i]['recht'] .'">' .$this->rights[$i]['name'] .'</option>';
								}
								echo '</select>
								</td>
						</tr>
						<tr>
								<td colspan="2"><input type="submit" value="speichern" /></td>
						</tr>
						</table>
						</form>
						';
		}
		$this->cur_func = "";
		return;
	}
	
	private function editUser(){
		global $config;
		$this->cur_func = "editUser";
		if($this->testUser()){
			if(isset($_POST['name'])&& isset($_POST['passwort'])&& isset($_POST['recht']) && isset($_POST['id'])){
				$name=$_POST['name'];
				$pw=hash($config['hash_algo'], $_POST['passwort']);
				$rechte = $_POST['recht'];
				$fields = array("name", "rechte");
				$values = array($name, $rechte);
				
				//$this->query("update users set name='" .$name ."', rechte='" .$rechte ."' where id='" .$_POST['id'] ."'");
				$query = "update users set name='" .$name ."', rechte='" .$rechte ."'";
				if(isset($_POST['newpw'])){
					array_push($fields, "passwort");
					array_push($values, $pw);
				}
				array_push($fields, "unlocked");
				if(isset($_POST['unlock'])){
					array_push($values, 1);
				}
				else{
					array_push($values, 0);
				}
				
				$this->db_update("users",
							$fields,
							$values,
							intval($_POST['id']));
			}
			if(isset($_POST['id'])){
				$id = $_POST['id'];
				
				$daten = $this->query("select * from users where id='" .$id ."'");
				if($daten){
					$row = mysql_fetch_array($daten);
					
					echo  '<form action="?action=' .$this->cur_func .'" method="post">
					<table border="0">
					<tr>
							<td>Name:</td><td><input type="text" id="name" name="name" value="' .$row['name'] .'" /></td><td></td>
					</tr>
					<tr>
							<td>Passwort:</td><td><input type="password" id="passwort" name="passwort" /></td><td><input type="checkbox" name="newpw" value="1" />neues PW?</td>
					</tr>
					<tr>
							<td>Berechtigung:</td>
							<td>
								<select size="1" name="recht">';
								for($i=0;$i<sizeof($this->rights);$i++){
									if($this->rights[$i]['recht']<$this->user['rechte']){
										echo '<option value="' .$this->rights[$i]['recht'] .'"' .($row['rechte']==$this->rights[$i]['recht']?' selected="selected"':'') .'>' .$this->rights[$i]['name'] .'</option>';
									}
								}
								echo '</select>
							</td>
							<td>	
								<input type="checkbox" name="unlock" value="1"' .($row['unlocked']==1?' checked="checked"':'') .' />freischalten
							</td>
					</tr>
					<tr>
							<td colspan="3"><input type="hidden" value="' .$id .'" name="id" /><input type="submit" value="speichern" /></td>
					</tr>
					</table>
					</form>';
				}
				
			}
			else{
				echo '<form method="post" action="?action=' .$this->cur_func .'">
				<select size="1" name="id">
					';
				if($this->user['rechte']!=150){
					$daten=$this->query("select * from users where rechte < {$this->user['rechte']} or id = {$this->user['id']} order by name asc");
				}
				else{
					$daten=$this->query("select * from users order by name asc");
				}
				if($daten){
					while($row = mysql_fetch_array($daten)){
						echo  '<option value="' .$row['id'] .'">' .$row['name'] .'</option>
						';
					}
				}
				
				echo  '</select>
				<input type="submit" value="ausw&auml;hlen" />
				</form>';
			}	
		}
		$this->cur_func = "";
		return;
	}
	
	private function delUser(){
		global $config;
		$this->cur_func = "delUser";
		if($this->testUser()){
			if(isset($_POST['id'])){
				$id = intval($_POST['id']);
				$this->db_delete("users", $id);
			}
			echo '<form method="post" action="?action=' .$this->cur_func .'">
				<select size="1" name="id">
					';
				
				$daten=$this->query("select * from users where rechte < {$this->user['rechte']} or id = {$this->user['id']} order by name asc");
				
				if($daten){
					while($row = mysql_fetch_array($daten)){
						echo  '<option value="' .$row['id'] .'">' .$row['name'] .'</option>
						';
					}
				}
				
				echo  '</select>
				<input type="submit" value="l&ouml;schen" />
				</form>';
		}
		$this->cur_func = "";
		return;
	}
	private function unlockUser(){
		global $config;
		$this->cur_func = "unlockUser";
		if($this->testUser()){
			if(isset($_POST['unl_id'])){
				$id = intval($_POST['unl_id']);
				$this->db_update("users",
							array("unlocked"),
							array(1),
							$id);
				echo  'Person freigeschaltet!';
			}
			if(isset($_POST['l_id'])){
				$id = intval($_POST['l_id']);
				$this->db_update("users",
							array("unlocked"),
							array(0),
							$id);
				echo  'Person gesperrt!';
			}
			echo '<form method="post" action="?action=' .$this->cur_func .'">
				<select size="1" name="unl_id">
					';
				
				$daten=$this->query("select * from users where unlocked = 0 order by id desc");
				
				if($daten){
					while($row = mysql_fetch_array($daten)){
						echo  '<option value="' .$row['id'] .'">' .$row['name'] .'</option>
						';
					}
				}
				
				echo  '</select>
				<input type="submit" value="freischalten" />
				</form>';
			echo '<form method="post" action="?action=' .$this->cur_func .'">
				<select size="1" name="l_id">
					';
				
				$daten=$this->query("select * from users where unlocked = 1 order by id desc");
				
				if($daten){
					while($row = mysql_fetch_array($daten)){
						echo  '<option value="' .$row['id'] .'">' .$row['name'] .'</option>
						';
					}
				}
				
				echo  '</select>
				<input type="submit" value="sperren" />
				</form>';
		}
		$this->cur_func = "";
		return;
	}
	
	private function newNavGroup(){
		global $config;
		$this->cur_func = "newNavGroup";
		if($this->testUser()){
			//print_r($_POST);
			if(isset($_POST['titel']) && isset($_POST['pos']) && isset($_POST['recht'])){
				$titel = $_POST['titel'];
				$pos = $_POST['pos'];
				$titel= $this->escape($titel);
				$recht = $_POST['recht'];
				
				$this->db_insert("navgroups",
							array("titel", "pos", "recht"),
							array($titel, $pos, $recht));
				
			}

			echo  '
				<form method="post" action="?action=' .$this->cur_func .'">
				<table border="0">
				<tr>
					<td>Titel:</td>
					<td>
						<input type="text" name="titel" /></td>
				</tr>
				<tr>
					<td colspan="2">
						Berechtigung:
						<select size="1" name="recht">';
						for($i=0;$i<sizeof($this->rights);$i++){
							echo '<option value="' .$this->rights[$i]['recht'] .'">' .$this->rights[$i]['name'] .'</option>';
						}
						echo '</select>
					</td>
				</tr>
				<tr>
					<td>Position:</td>
					<td>
						<input type="text" name="pos" />
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="speichern" /></td>
				</tr>
				</table>
				</form>';
		}
		$this->cur_func = "";
		return;
	}
	
	private function editNavGroup(){
		global $config;
		$this->cur_func = "editNavGroup";
		if($this->testUser()){
			//print_r($_POST);
			if(isset($_POST['titel']) && isset($_POST['pos']) && isset($_POST['id'], $_POST['recht'])){
				$titel = $this->escape($_POST['titel']);
				$pos = $_POST['pos'];
				$id = intval($_POST['id']);
				$recht = $_POST['recht'];
				
				$this->db_update("navgroups",
							array("titel", "pos", "recht"),
							array($titel, $pos, $recht),
							$id);
			}
				
			if(isset($_POST['id'])){
				$id = $_POST['id'];
				$daten = $this->query("select * from navgroups where id='" .$id ."'");
				if($daten){
					$row = mysql_fetch_array($daten);
					echo  '
					<form method="post" action="?action=' .$this->cur_func .'">
					<table border="0">
					<tr>
						<td>Titel:</td>
						<td>
							<input type="text" name="titel" value="' .$row['titel'] .'" /></td>
					</tr>
					<tr>
						<td>
							Berechtigung:
						</td>
						<td>
							<select size="1" name="recht">';
							for($i=0;$i<sizeof($this->rights);$i++){
								echo '<option value="' .$this->rights[$i]['recht'] .'"' .($row['recht']==$this->rights[$i]['recht']?' selected="selected"':'') .'>' .$this->rights[$i]['name'] .'</option>';
							}
							echo '</select>
						</td>
					</tr>
					<tr>
						<td>Position:</td>
						<td>
							<input type="text" name="pos" value="' .$row['pos'] .'" />
						</td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="speichern" /><input type="hidden" value="' .$id .'" name="id" /></td>
					</tr>
					</table>
					</form>';
				}
				
				
			}
			else{
				echo '<form method="post" action="?action=' .$this->cur_func .'">
				<select size="1" name="id">
					';
				
				$daten=$this->query("select * from navgroups order by pos asc");
				if($daten){
					while($row = mysql_fetch_array($daten)){
						echo  '<option value="' .$row['id'] .'">' .$row['titel'] .'</option>
						';
					}
				}
				
				echo  '</select>
				<input type="submit" value="ausw&auml;hlen" />
				</form>';
			}
		}
		$this->cur_func = "";
		return;
	}
	
	private function delNavGroup(){
		global $config;
		$this->cur_func = "delNavGroup";
		if($this->testUser()){
			if(isset($_POST['del'])){
				$this->db_delete("navgroups", intval($_POST['del']));
			}
			
			$daten = $this->query("select * from navgroups order by pos asc");

			echo  '<form method="post" action="?action=' .$this->cur_func .'">
				<select size="1" name="del">
				';
			
			if($daten){
				while($row=mysql_fetch_array($daten)){
					echo '<option value="' .$row['id'] .'">' .$row['titel'] .'</option>
					';
				}
			}
			
			echo  '</select>
			<input type="submit" value="l&ouml;schen" />
			</form>';
		}
		return;
	}
	
	private function delNewsComment(){
		global $config;
		$this->cur_func = "delNewsComment";
		if($this->testUser()){
			if(isset($_POST['del'])){
				$this->db_delete("news_comments", intval($_POST['del']));
			}
			
			if(isset($_POST['id'])){
				$daten = $this->query("select nc.id, nc.datum, nc.comment, u.name, n.titel from news_comments nc, users u, news n where nc.userid=u.id and nc.newsid=n.id and n.id='" .$_POST['id'] ."' order by nc.id desc");
	
				echo  '<form method="post" action="?action=' .$this->cur_func .'"><input type="hidden" value="' .$_POST['id'] .'" name="id" /> ';
					
				
				if($daten){
					while($row=mysql_fetch_array($daten)){
						echo "<input type=\"radio\" class=\"radio\" value=\"" .$row['id'] ."\" name=\"del\" />" .date("l d.m.Y H:i:s T", strtotime($row['datum'])).", " .$row['name'] .": " .$row['comment'] ."<br />";
					}
				}
				
				echo  '
				<input type="submit" value="l&ouml;schen" />
				</form>';
			}
			else{
				$daten = $this->query("select * from news order by id desc");

				echo  '<form method="post" action="?action=' .$this->cur_func .'">
					<select size="1" name="id">
					';
				
				if($daten){
					while($row=mysql_fetch_array($daten)){
						echo '<option value="' .$row['id'] .'">' .$row['titel'] .'</option>
						';
					}
				}
				
				echo  '</select>
				<input type="submit" value="w&auml;hlen" />
				</form>';

			}
		}
		return;
	}
	
	
}
/*
	
	private function name(){
		global $config;
		
		if($this->testUser()){
			
		}
		return;
	}
*/
?>
