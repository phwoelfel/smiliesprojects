<?php
	global $selbst;
	if(isset($_GET['id'])){
		
		
	}
	else{
		echo "<table>";
		$users_qry = mysql_query("select id, name, vorname, nachname, email, ort, telnr, msn, icq, skype, pic from users order by name");
		while($user = mysql_fetch_array($users_qry)){
			if(file_exists('images/avatars/' .$user['id'] .$user['pic'])){
				$img = 'images/avatars/' .$user['id'] .$user['pic'];
			}
			else{
				$img = 'images/avatars/default.jpg';
			}
			echo "<tr>";
			echo '<td><img src="' .$img .'" alt="Avatar von ' .$user['name'] .'" /></td>';
			echo '<td>' .$user['vorname'] .' ' .$user['nachname'] .'</td>';
			echo '<td><a href="' .$selbst .'&amp;id=' .$user['id'] .'">mehr Infos...</a></td>';
			echo "</tr>";
		}
		echo "</table>";
	}
?>