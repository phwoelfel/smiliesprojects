<!--
	Copyright 2008 Philip W&ouml;lfel <philip.woelfel@frig.at>
-->

<?php
	if(isset($_POST['name']) && isset($_POST['text'])){
		$name=$_POST['name'];
		$text=$_POST['text'];
		mysql_query("INSERT INTO shoutbox (Name, Nachricht) VALUES ('" .$name ."', '" .$text ."')");
	}
	


	
	echo '<form action="" method="post">
		<table border="0">
		<tr>
				<td colspan="7"><input type="text" id="name" name="name" value="Name" onfocus="if(this.value==\'Name\'){this.value=\'\'}" /></td>
		</tr>
		<tr>
				<td colspan="7"><input type="text" name="text" id="text" value="Text" onfocus="if(this.value==\'Text\'){this.value=\'\'}" /></td>
		</tr>
		<tr>
				<td colspan="7" align="center"><input type="submit" value=":: senden ::" /></td>
		</tr>
		<tr>
			<td align="center"><a href="javascript:addImage(\':D\')"><img src="images/smileys/biggrin.gif" alt="biggrin" border="0" /></a></td>
			<td align="center"><a href="javascript:addImage(\':P\')"><img src="images/smileys/tongue.gif" alt="tongue" border="0" /></a></td>
			<td align="center"><a href="javascript:addImage(\':)\')"><img src="images/smileys/smile.gif" alt="smile" border="0" /></a></td>
			<td align="center"><a href="javascript:addImage(\':(\')"><img src="images/smileys/frown.gif" alt="frown" border="0" /></a></td>
			<td align="center"><a href="javascript:addImage(\';(\')"><img src="images/smileys/cry.gif" alt="cry" border="0" /></a></td>
			<td align="center"><a href="javascript:addImage(\';|\')"><img src="images/smileys/mad.gif" alt="mad" border="0" /></a></td>
			<td align="center"><a href="javascript:addImage(\';D\')"><img src="images/smileys/diablotin.gif" alt="biggrin" border="0" /></a></td>
		</tr>
		</table></form>';
		$replace = array(":D", ":P", ":)", ":(", ";(", ";|", ";D");
		$repl = array("biggrin.gif", "tongue.gif", "smile.gif", "frown.gif", "cry.gif", "mad.gif", "diablotin.gif");
		$temp="";
		//SELECT s.Nachricht as Nachricht, l.Name as Name, s.personID as personID FROM shoutbox s,login_daten l WHERE s.personID=l.personID ORDER BY created DESC
		$daten = mysql_query("SELECT Nachricht, Name, id FROM shoutbox ORDER BY created DESC");
		
		while($row = mysql_fetch_array($daten)){
				$temp = $row['Nachricht'];
				for ($i = 0; $i < sizeof($replace); $i++){
					$temp = str_replace($replace[$i], '<img src="images/smileys/' .$repl[$i] .'" alt="' .$repl[$i] .'" />', $temp);
				}				
				echo '<b>' .$row['Name'] .':</b> ' . $temp .'<br />-----------------------<br />';
		}
		
		?>