<!--
	Copyright 2008 Philip W&ouml;lfel <philip.woelfel@frig.at>
-->
<h1>News</h1>
<?php
	//$selbst = "?site=" .$_GET['site'];
	global $selbst;
	global $loggedin;
	
	if(isset($_GET['id'])){
		$daten = mysql_query("select titel, datum, text, id from news where id = " .$_GET['id'] ." order by id desc");
		
		if($eintr = mysql_fetch_array($daten)){
			echo '<h2 class="news">' .$eintr['titel'] .'</h2>
					<b>vom ' . date("d.m.Y \u\m H:i:s", strtotime($eintr['datum'])) .'</b><br /><br />'
					.$eintr['text']
					.'<br /><hr /><br />';
			
			if($loggedin){
				if(isset($_POST['comment'])){
					$sql = "insert into news_comments(newsid, userid, comment) values('" .$_GET['id'] ."', '" .$_SESSION['userid'] ."', '" .mysql_real_escape_string(htmlspecialchars($_POST['comment'])) ."')";
					if(!mysql_query($sql)){
						echo mysql_error();
					}
				}
				echo '<h2 class="news">Kommentare</h2>';
				$comments = mysql_query("select nc.datum, nc.comment, u.vorname, u.nachname from news_comments nc, users u where nc.newsid=" .$_GET['id'] ." and nc.userid = u.id order by datum asc");
				while($com=mysql_fetch_array($comments)){
					echo '<b>' .$com['vorname'] .' ' .$com['nachname'] .', ' .date("d.m.Y \u\m H:i:s", strtotime($com['datum'])) .'</b><br />' .$com['comment'] .'<br />--------------------------------------------<br />';
				}
				
				echo '<br /><br /><form method="post" action="' .$selbst .'&amp;id=' .$_GET['id'] .'">
					Dein Kommentar:<br />
					<textarea name="comment" rows="10" cols="50"></textarea><br />
					<input type="submit" value="absenden" />
					</form>';
			}
		}
		else{
			echo 'Dieser Newsartikel existiert nicht!';
		}
	}
	else{
		$daten = mysql_query("select titel, datum, text, id from news order by id desc");
		while($row = mysql_fetch_array($daten)){
			echo '<h2 class="news"><a href="' .$selbst .'&amp;id=' .$row['id'] .'">' .$row['titel'] .'</a></h2>
					<b>vom ' . date("d.m.Y \u\m H:i", strtotime($row['datum'])) .'</b><br /><br />'
					.$row['text']
					.'<br /><hr /><br />';
		}
	}
?>