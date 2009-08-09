<!--
 * Copyright 2008 Philip Wölfel <philip.woelfel@frig.at>
-->
<?php
class AdminFunctions{
	protected $con=null;
	private $replace = null;
	private $config;
	private $rights;
	protected $minrights;
	protected $cur_func;
	protected $error;
	protected $user;
	
	function __construct($name, $psw){
		global $config;
		
		$this->minrights = 100; //mindestens Benoetigtes Level zum einloggen
		
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
			$admins = mysql_query("select id, name, passwort, rechte from users where rechte >= " .$this->minrights ." and unlocked = 1 and name = '" .$name ."'");
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
						"newNavIntern" => "neuer interner Men&uuml;eintrag",
						"newNavExtern" => "neuer externer Men&uuml;eintrag",
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
						"newRights" => "neues Recht",
						"editRights" => "Rechte bearbeiten",
						"delRights" => "Rechte l&ouml;schen",
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
						."Query: $ins_qry<br />"
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
	
	/*
	 * Parameter:
	 * 	$table:
	 * 		String mit Namen der Tabelle
	 * 	
	 * 	$types:
	 * 		Array mit Art des Eingabefelds
	 * 		moegliche Arten:
	 * 			textfeld
	 * 			password
	 * 				key:
	 * 				hash_algo;nummer
	 * 				hash_algo ist der Hash Algorythmus mit dem das Passwort verschluesselt wird und nummer is einfach eine einzigartige Nummer pro Form ( damit die keys nicht überschrieben werden wenn zweimal der gleiche hash Algorythmus verwendet wird)
	 * 			textarea
	 * 			checkbox:
	 * 				key:
	 * 				true_value;false_value;nummer
	 * 				true_value wird gespeichert wenn die Checkbox ausgewaehlt wurde, false_value wenn die Checkbox nicht ausgewaehlt wurde, nummer ist einfach eine einzigarte Nummer pro Form ( um gleiche Keys zu vermeiden )
	 * 			select:
	 * 				key:
	 * 				tabelle;value_spalte;inhalt_spalte
	 * 				tabelle ist die Tabelle aus der die Daten gelesen werden, value_spalte is der Wert der gespeichert wird und inhalt_spalte ist der Wert der angezeigt wird
	 * 			hidden:
	 * 				key enthaelt den Wert der das Feld haben soll mit ";" am Ende ( damit wenn numerisch nicht ein vorhandener Key ueberschrieben wird )
	 * 	
	 * 	$names:
	 * 		Array mit den Bezeichnungen die neben den Eingabefeldern stehen sollen
	 * 
	 *  $fields:
	 *  	Felder auf die in der DB zugegriffen wird
	 */
	
	private function insert($table, $types, $names, $fields){
		if(!is_array($types)){
			echo "\$types ist kein Array!<br />\n";
			$this->error = "\$types ist kein Array!<br />\n";
			return false;
		}
		else if(!is_array($names)){
			echo "\$names ist kein Array!<br />\n";
			$this->error = "\$names ist kein Array!<br />\n";
			return false;
		}
		else if(!is_array($fields)){
			echo "\$fields ist kein Array!<br />\n";
			$this->error = "\$fields ist kein Array!<br />\n";
			return false;
		}
		else if(sizeof($types) != sizeof($names) || sizeof($names) != sizeof($fields)){
			echo "Arrays sind nicht gleich lang!<br />\n";
			$this->error = "Arrays sind nicht gleich lang!<br />\n";
			return false;
		}
		else if(sizeof($types)<1){
			echo "\$types ist zu klein (leer?)!<br />\n";
			$this->error = "\$types ist zu klein (leer?)!<br />\n";
			return false;
		}
		else if(sizeof($names)<1){
			echo "\$names ist zu klein (leer?)!<br />\n";
			$this->error = "\$names ist zu klein (leer?)!<br />\n";
			return false;
		}
		else if(sizeof($fields)<1){
			echo "\$fields ist zu klein (leer?)!<br />\n";
			$this->error = "\$fields ist zu klein (leer?)!<br />\n";
			return false;
		}
		else if($table == ""){ // ungueltige Parameter
			echo "Tabelle ist nicht angegeben!<br />\n";
			$this->error = "Tabelle ist nicht angegeben!<br />\n";
			return false;
		}
		else{ // gueltige Parameter
			
			//eingabeform:
			echo '<script type="text/javascript" src="../jscripts/tiny_mce/tiny_mce.js"></script>
					<script type="text/javascript">
					tinyMCE.init({
						// General options
						mode : "specific_textareas",
						editor_selector : "mceAdvEditor",
						
						
						theme : "advanced",
						plugins : "safari,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template",
				
						// Theme options
						theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect",
						theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
						theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
						theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak",
						theme_advanced_toolbar_location : "top",
						theme_advanced_toolbar_align : "left",
						theme_advanced_statusbar_location : "bottom",
						theme_advanced_resizing : true,
					});
					</script>
					<script type="text/javascript">
					tinyMCE.init({
						// General options
						mode : "specific_textareas",
						editor_selector : "mceEditor",
						});
					</script>';
			echo '<form method="post" action="?action=' .$this->cur_func .'" name="insert_form_' .$table .'">'."\n";
			echo '<table>'."\n";
			foreach($types as $info => $type){
				$name = each($names);
				$field = each($fields);
				echo '<tr>'."\n";
				
				switch($type){
					case 'textfeld':
						echo '<td>' .$name['value'] .'</td><td><input type="text" name="' .$field['value'] .'" /></td>'."\n";
						break;
					
					case 'passwort':
						echo '<td>' .$name['value'] .'</td><td><input type="password" name="' .$field['value'] .'" /></td>'."\n";
						break;
					
					case 'hidden':
						list($info) = explode(";", $info);
						echo '<td colspan="2"><input type="hidden" name="' .$field['value'] .'" value="' .$info .'" /></td>'."\n";
						break;
						
					case 'textarea':
						echo '<td>' .$name['value'] .'</td><td><textarea cols="45" rows="15" name="' .$field['value'] .'"></textarea></td>'."\n";
						break;
					
					case 'checkbox':
						echo '<td>' .$name['value'] .'</td><td><input type="checkbox" name="' .$field['value'] .'" value="1" /></td>'."\n";
						break;
						
					case 'select':
						echo '<td>' .$name['value'] .'</td><td>';
						
						list($tbl, $col1, $col2) = explode(";", $info);
						//echo "tbl: $tbl<br />col: $col<br />info $info";
						
						$values = $this->query("select $col1, $col2 from $tbl order by id asc");
						
						
						if($values){
							echo '<select size="1" name="' .$field['value'] .'">'."\n";
							while($val = mysql_fetch_array($values)){
								echo '<option value="' .$val[$col1] .'">' .$val[$col2] .'</option>'."\n";
							}
							echo '</select>';
						}
						
						echo '</td>'."\n";
						
						break;
					
					case 'editor':
						echo '<td>' .$name['value'] .'</td>
							<td><textarea name="' .$field['value'] .'" class="mceEditor" cols="50" rows="15"></textarea>';
						break;
					
					case 'adveditor':
						echo '<td>' .$name['value'] .'</td>
							<td><textarea name="' .$field['value'] .'" class="mceAdvEditor" cols="50" rows="15"></textarea>';
						break;
				}
				echo '</tr>'."\n";
			}
			echo '<td colspan="2"><input type="submit" name="insert_form_' .$table .'_submit" value="speichern" /></td>'."\n";
			echo '</table>'."\n";
			echo '</form>'."\n";
			
			//in die DB schreiben:
			foreach($fields as $field){
				$type = each($types);
				if($type['value'] != "checkbox" && !isset($_POST[$field])){
					return;
				}
			}
			
			reset($types);
			reset($names);
			reset($fields);
			
			$values = array();
			foreach($fields as $field){
				$type = each($types);
				if($type['value']=="checkbox"){
					list($tval, $fval, $num) = explode(";", $type['key']);
					if(isset($_POST[$field])){
						$values[] = $tval;
					}
					else{
						$values[] = $fval;
					}
				}
				elseif($type['value']=="passwort"){
					list($hashalgo, $num) = explode(";", $type['key']);
					$values[] = hash($hashalgo, $_POST[$field]);
				}
				else{
					$values[] = $this->escape($_POST[$field]);
				}
			}
			$this->db_insert($table, $fields, $values);
		}
	}
	
	/*
	 * Erzeugt Eingabefelder usw. um Daten in DB upzudaten
	 * @param table_info string <p>
	 * 		tabelle;spalte;sort_spalte
	 * 		tabelle: String mit Namen der Tabelle
	 * 		spalte: die Spalte die bei der Auswahlliste angezeigt werden soll
	 * 		sort_splate: spalte nach der sortiert wird
	 * 	</p>
	 * 
	 * @param types array <p>
	 * 		Array mit Art des Eingabefelds
	 * 		moegliche Arten:
	 * 			textfeld
	 * 			password
	 * 				key:
	 * 				hash_algo;nummer
	 * 				hash_algo ist der Hash Algorythmus mit dem das Passwort verschluesselt wird und nummer ist einfach eine einzigartige Nummer pro Form ( damit die keys nicht überschrieben werden wenn zweimal der gleiche hash Algorythmus verwendet wird)
	 * 			textarea
	 * 			checkbox: hier muss der Key wie folgt aufgebaut sein:
	 * 				true_value;false_value;nummer
	 * 				true_value wird gespeichert wenn die Checkbox ausgewaehlt wurde, false_value wenn die Checkbox nicht ausgewaehlt wurde, nummer ist einfach eine einzigarte Nummer pro Form ( um gleiche Keys zu vermeiden )
	 * 			select: hier muss der Key wie folgt aufgebaut sein:
	 * 				tabelle;value_spalte;inhalt_spalte
	 * 				tabelle ist die Tabelle aus der die Daten gelesen werden, value_spalte is der Wert der gespeichert wird und inhalt_spalte ist der Wert der angezeigt wird
	 *  		hidden:
	 * 				key enthaelt den Wert der das Feld haben soll und einen ;
	 * </p>
	 * 
	 * @param names array <p>
	 * 		Array mit den Bezeichnungen die neben den Eingabefeldern stehen sollen
	 * </p>
	 * 
	 * @param fields array <p>
	 *  	Felder auf die in der DB zugegriffen wird
	 * </p>
	 * 
	 * @return bool false wenn die Parameter ungueltig sind, sonst true
	 */
	
	private function update($table_info, $types, $names, $fields){
		if(!is_array($types)){
			echo "\$types ist kein Array!<br />\n";
			$this->error = "\$types ist kein Array!<br />\n";
			return false;
		}
		else if(!is_array($names)){
			echo "\$names ist kein Array!<br />\n";
			$this->error = "\$names ist kein Array!<br />\n";
			return false;
		}
		else if(!is_array($fields)){
			echo "\$fields ist kein Array!<br />\n";
			$this->error = "\$fields ist kein Array!<br />\n";
			return false;
		}
		else if(sizeof($types) != sizeof($names) || sizeof($names) != sizeof($fields)){
			echo "Arrays sind nicht gleich lang!<br />\n";
			$this->error = "Arrays sind nicht gleich lang!<br />\n";
			return false;
		}
		else if(sizeof($types)<1){
			echo "\$types ist zu klein (leer?)!<br />\n";
			$this->error = "\$types ist zu klein (leer?)!<br />\n";
			return false;
		}
		else if(sizeof($names)<1){
			echo "\$names ist zu klein (leer?)!<br />\n";
			$this->error = "\$names ist zu klein (leer?)!<br />\n";
			return false;
		}
		else if(sizeof($fields)<1){
			echo "\$fields ist zu klein (leer?)!<br />\n";
			$this->error = "\$fields ist zu klein (leer?)!<br />\n";
			return false;
		}
		else if($table_info == ""){ // ungueltige Parameter
			echo "Tabelle ist nicht angegeben!<br />\n";
			$this->error = "Tabelle ist nicht angegeben!<br />\n";
			return false;
		}
		else{
			// gueltige Parameter
			list($table, $spalte, $sort_spalte) = explode(";", $table_info);
			
			$fieldsset = true;
			//in die DB schreiben:
			foreach($fields as $field){
				$type = each($types);
				if($type['value'] != "checkbox" && !isset($_POST[$field])){
					$fieldsset = false;
				}
			}
			reset($types);
			
			if($fieldsset){
				$id = intval($_POST['upd_selid']);
				$values = array();
				$myfields = array();
				//echo "<pre>" .print_r($_POST,true) ."</pre>";
				
				foreach($fields as $field){
					$type = each($types);
					//echo "value: " .$type['value'];
					if($type['value']=="checkbox"){
						list($tval, $fval, $num) = explode(";", $type['key']);
						if(isset($_POST[$field])){
							$values[] = $tval;
						}
						else{
							$values[] = $fval;
						}
						$myfields[] = $field;
					}
					elseif($type['value']=="passwort" && isset($_POST[$field."_pwd_check"])){
							list($hashalgo, $num) = explode(";", $type['key']);
							$values[] = hash($hashalgo, $_POST[$field]);
							$myfields[] = $field;
					}
					elseif($type['value']=="passwort" && !isset($_POST[$field."_pwd_check"])){
							continue;
					}
					else{
						$values[] = $this->escape($_POST[$field]);
						$myfields[] = $field;
					}
					
				}
					
				//echo "Fields:<pre>" .print_r($myfields,true) ."</pre>";
				//echo "Values:<pre>" .print_r($values,true) ."</pre>";
				$this->db_update($table, $myfields, $values, $id);
			}
			reset($types);
			reset($names);
			reset($fields);
			
			if(isset($_POST['upd_selid'])){
				
				$id = $_POST['upd_selid'];
				$werte_qry = $this->query("select " .implode(", ", $fields) ." from $table where id = $id");
				$werte = mysql_fetch_array($werte_qry);
				//eingabeform:
				echo '<script type="text/javascript" src="../jscripts/tiny_mce/tiny_mce.js"></script>
						<script type="text/javascript">
						tinyMCE.init({
							// General options
							mode : "specific_textareas",
							editor_selector : "mceAdvEditor",
							
							
							theme : "advanced",
							plugins : "safari,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template",
					
							// Theme options
							theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect",
							theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
							theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
							theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak",
							theme_advanced_toolbar_location : "top",
							theme_advanced_toolbar_align : "left",
							theme_advanced_statusbar_location : "bottom",
							theme_advanced_resizing : true,
						});
						</script>
						<script type="text/javascript">
						tinyMCE.init({
							// General options
							mode : "specific_textareas",
							editor_selector : "mceEditor",
							});
						</script>';
				echo '<form method="post" action="?action=' .$this->cur_func .'" name="update_form_' .$table .'">'."\n";
				echo '<table>'."\n";
				foreach($types as $info => $type){
					$name = each($names);
					$field = each($fields);
					echo '<tr>'."\n";
					
					switch($type){
						case 'textfeld':
							echo '<td>' .$name['value'] .'</td><td><input type="text" name="' .$field['value'] .'" value="' .$werte[$field['value']] .'" /></td>'."\n";
							break;
						
						case 'passwort':
							echo '<td>' .$name['value'] .'</td><td><input type="password" name="' .$field['value'] .'" value="' .$werte[$field['value']] .'" /><input type="checkbox" name="' .$field['value'] .'_pwd_check" value="1" /></td>'."\n";
							break;
							
						case 'hidden':
							list($info) = explode(";", $info);
							echo '<td colspan="2"><input type="hidden" name="' .$field['value'] .'" value="' .$info .'" /></td>'."\n";
							break;
							
						case 'textarea':
							echo '<td>' .$name['value'] .'</td><td><textarea cols="45" rows="15" name="' .$field['value'] .'">' .$werte[$field['value']] .'</textarea></td>'."\n";
							break;
						
						case 'checkbox':
							list($tval, $fval, $num) = explode(";", $info);
							echo '<td>' .$name['value'] .'</td><td><input type="checkbox" name="' .$field['value'] .'" value="1"' .($werte[$field['value']]==$tval?' checked="checked"':'') .' /></td>'."\n";
							break;
							
						case 'select':
							echo '<td>' .$name['value'] .'</td><td>';
							
							list($tbl, $col1, $col2) = explode(";", $info);
							//echo "tbl: $tbl<br />col: $col<br />info $info";
							
							$values = $this->query("select $col1, $col2 from $tbl order by id asc");
							
							if($values){
								echo '<select size="1" name="' .$field['value'] .'">'."\n";
								while($val = mysql_fetch_array($values)){
									echo '<option value="' .$val[$col1] .'"' .($werte[$field['value']]==$val[$col1]?' selected="selected"':'') .'>' .$val[$col2] .'</option>'."\n";
								}
								echo '</select>';
							}
							
							echo '</td>'."\n";
							
							break;
						
						case 'editor':
							echo '<td>' .$name['value'] .'</td>
								<td><textarea name="' .$field['value'] .'" class="mceEditor" cols="50" rows="15">' .$werte[$field['value']] .'</textarea>';
							break;
						
						case 'adveditor':
							echo '<td>' .$name['value'] .'</td>
								<td><textarea name="' .$field['value'] .'" class="mceAdvEditor" cols="50" rows="15">' .$werte[$field['value']] .'</textarea>';
							break;
					}
					echo '</tr>'."\n";
				}
				echo '<td colspan="2"><input type="submit" name="insert_form_' .$table .'_submit" value="speichern" /></td>'."\n";
				echo '</table>'."\n";
				echo '<input type="hidden" value="' .$id .'" name="upd_selid" />';
				echo '</form>'."\n";
			}
			else{
				echo '<form method="post" action="?action=' .$this->cur_func .'" name="update_form_' .$table .'">'."\n";
				$list = $this->query("select id, $spalte from $table order by $sort_spalte asc");
				
				echo '<select size="1" name="upd_selid">'."\n";
				while($list_item = mysql_fetch_array($list)){
					echo '<option value="' .$list_item['id'] .'">' .$list_item[$spalte] .'</option>'."\n";
				}
				
				echo '</select>'."\n";
				echo '<input type="submit" value="ok" />'."\n";
				echo '</form>'."\n";
			}
			
			
		}
		return true;
	}
	
	/*
	 * loescht Daten aus der DB
	 * 	@param table string <p>
	 * 			die Tabelle aus der ein Wert geloescht werden soll
	 * 	</p>
	 * 
	 * @param show_col string <p>
	 * 			die Spalte die bei der Auswahl angezgezeigt werden soll
	 * </p>
	 * 
	 * @param order_col string <p>
	 * 			die Spalte nach der sortiert werden solle
	 * </p>
	 * 
	 * @param order_mode string <p>
	 * 			wie sortiert werden soll
	 * 			asc fuer aufsteigend, desc fuer absteigend
	 * </p>
	 */
	
	private function delete($table, $show_col, $order_col, $order_mode){
		if(isset($_POST['del'])){
			$this->db_delete($table, intval($_POST['del']));
		}
		
		$cont = $this->query("select id, $show_col from $table order by $order_col $order_mode");
		if($cont){
			echo '<form method="post" action="?action=' .$this->cur_func .'">
				<select size="1" name="del">
				';
			
			while($row=mysql_fetch_array($cont)){
				echo '<option value="' .$row['id'] .'">' .$row[$show_col] .'</option>
				';
			}
			echo '</select>
			<input type="submit" value="l&ouml;schen" />
			</form>';
		}
		return;
	}
	
	
	
	private function newContent(){
		global $config;
		$this->cur_func = "newContent";
		if($this->testUser()){
			$types = array("textfeld", "textarea", "textarea");
			$names = array("Titel", "Head", "Content");
			$fields = array("titel", "head", "content");
			
			$this->insert("content", $types, $names, $fields);
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
			$this->delete("content", "titel", "titel", "asc");
		}
		$this->cur_func = "";
		return;
	}
	
	private function newNavIntern(){
		global $config;
		$this->cur_func = "newNavIntern";
		if($this->testUser()){
			$types = array("textfeld", "content;titel;titel"=>"select", "navgroups;id;titel"=>"select", "textfeld", "navi_sub;navi"=>"checkbox", "rechte;recht;titel"=>"select", "0;"=>"hidden", "1;0"=>"checkbox");
			$names = array("Titel", "Seite", "Navigationsgruppe", "Position", "Unterpunkt", "Berechtigung", "", "f&uuml;r ausgellogte User");
			$fields = array("titel", "site", "nav", "pos", "klasse", "recht", "extern", "no_login");
			$this->insert("nav",
				$types,
				$names, 
				$fields);
		}
		$this->cur_func = "";
		return;
	}
	
	private function newNavExtern(){
		global $config;
		$this->cur_func = "newNavExtern";
		if($this->testUser()){
			$types = array("textfeld", "textfeld", "navgroups;id;titel"=>"select", "textfeld", "navi_sub;navi"=>"checkbox", "rechte;recht;titel"=>"select", "1;"=>"hidden", "1;0"=>"checkbox");
			$names = array("Titel", "Seite", "Navigationsgruppe", "Position", "Unterpunkt", "Berechtigung", "", "f&uuml;r ausgellogte User");
			$fields = array("titel", "site", "nav", "pos", "klasse", "recht", "extern", "no_login");
			$this->insert("nav",
				$types,
				$names, 
				$fields);
		}
		$this->cur_func = "";
		return;
	}
	
	private function editNav(){
		global $config;
		$this->cur_func = "editNav";
		if($this->testUser()){
			$types = array("textfeld", "textfeld", "navgroups;id;titel"=>"select", "textfeld", "navi_sub;navi;1"=>"checkbox", "rechte;recht;titel"=>"select", "1;0;2"=>"checkbox", "1;0;3"=>"checkbox");
			$fields = array("titel", "site", "nav", "pos", "klasse", "recht", "extern", "no_login");
			$names = array("Titel", "Link", "Kategorie", "Position", "Unterpunkt", "Berechtigungen", "Externer Link", "nur ausgellogt");
			
			$this->update("nav;titel;nav,pos", $types, $names, $fields);
		}
		$this->cur_func = "";
		return;
	}
	
	private function delNav(){
		global $config;
		$this->cur_func = "delNav";
		if($this->testUser()){
			$this->delete("nav", "titel", "nav, pos", "asc");
		}
		$this->cur_func = "";
		return;
	}
	
	private function delTermin(){
		global $config;
		$this->cur_func = "delTermin";
		if($this->testUser()){
			$this->delete("termine", "kurztext", "datum", "asc");

		}
		$this->cur_func = "";
		return;
	}
	
	private function delShoutbox(){
		global $config;
		$this->cur_func = "delShoutbox";
		if($this->testUser()){
			$this->delete("shoutbox", "Nachricht", "created", "desc");
		}
		$this->cur_func = "";
		return;
	}
	
	private function newNews(){
		global $selbst;
		$this->cur_func = "newNews";
		if($this->testUser()){
			$types = array("textfeld", "textfeld", "editor");
			$names = array("Datum", "Titel", "Text");
			$fields = array("datum", "titel", "text");
			
			$this->insert("news", $types, $names, $fields);
		}
		$this->cur_func = "";
		return;
	}
	
	private function editNews(){
		global $config;
		global $selbst;
		$this->cur_func = "editNews";
		if($this->testUser()){
			$types = array("textfeld", "textfeld", "editor");
			$names = array("Datum", "Titel", "Text");
			$fields = array("datum", "titel", "text");
			
			$this->update("news;titel;datum", $types, $names, $fields);
		}
		$this->cur_func = "";
		return;
	}
	
	private function delNews(){
		global $config;
		global $selbst;
		$this->cur_func = "delNews";
		if($this->testUser()){
			$this->delete("news", "titel", "datum", "desc");
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
			$fields = array("name", "passwort", "rechte", "unlocked");
			$types = array("textfeld", $config['hash_algo'].";1"=>"passwort", "rechte;recht;titel"=>"select", "1;0;1"=>"checkbox");
			$names = array("Name", "Passwort", "Berechtigungen", "Freigeschalten");
			$this->insert("users", $types, $names, $fields);
		}
		$this->cur_func = "";
		return;
	}
	
	private function editUser(){
		global $config;
		$this->cur_func = "editUser";
		if($this->testUser()){
			$fields = array("name", "passwort", "rechte", "unlocked");
			$types = array("textfeld", $config['hash_algo'].";1"=>"passwort", "rechte;recht;titel"=>"select", "1;0;1"=>"checkbox");
			$names = array("Name", "Passwort", "Berechtigungen", "Freigeschalten");
			$this->update("users;name;name", $types, $names, $fields);
		}
		$this->cur_func = "";
		return;
	}
	
	private function delUser(){
		global $config;
		$this->cur_func = "delUser";
		if($this->testUser()){
			$this->delete("users", "name", "datum", "desc");
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
			$fields = array("titel", "pos", "recht");
			$names = array("Titel", "Position", "Berechtigungen");
			$types = array("textfeld", "textfeld", "rechte;recht;titel"=>"select");
			$this->insert("navgroups", $types, $names, $fields);
		}
		$this->cur_func = "";
		return;
	}
	
	private function editNavGroup(){
		global $config;
		$this->cur_func = "editNavGroup";
		if($this->testUser()){
			$fields = array("titel", "pos", "recht");
			$names = array("Titel", "Position", "Berechtigungen");
			$types = array("textfeld", "textfeld", "rechte;recht;titel"=>"select");
			$this->update("navgroups;titel;pos", $types, $names, $fields);
		}
		$this->cur_func = "";
		return;
	}
	
	private function delNavGroup(){
		global $config;
		$this->cur_func = "delNavGroup";
		if($this->testUser()){
			$this->delete("navgroups", "titel", "pos", "asc");
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
	
	private function newRights(){
		global $config;
		$this->cur_func = "newRights";
		if($this->testUser()){
			$fields = array("titel", "recht");
			$names = array("Titel", "Berechtigungen");
			$types = array("textfeld",  "textfeld");
			$this->insert("rechte", $types, $names, $fields);
		}
		$this->cur_func = "";
		return;
	}
	
	private function editRights(){
		global $config;
		$this->cur_func = "editRights";
		if($this->testUser()){
			$fields = array("titel", "recht");
			$names = array("Titel", "Berechtigungen");
			$types = array("textfeld",  "textfeld");
			$this->update("rechte;titel;recht", $types, $names, $fields);
		}
		$this->cur_func = "";
		return;
	}
	
	private function delRights(){
		global $config;
		$this->cur_func = "delRights";
		if($this->testUser()){
			$this->delete("rechte", "titel", "recht", "asc");
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
