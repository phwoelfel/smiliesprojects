<?php
	
	require("includes/rssfeed.class.php");
	require("admin/config.php");
	global $config;
	$con = mysql_connect($config['host'], $config['user'], $config['psw']);
	mysql_select_db($config['db'], $con);
	
	$myfeed = new RSSFeed();
	$myfeed->SetChannel('feed.php',
		$config['sitename'] .' - News',
		'RSS News Feed von ' .$config['sitename'],
		'de-at',
		'Copyright by Philip Woelfel philip[at]woelfel.at',
		'Philip Woelfel',
		$config['sitename']);
	$myfeed->SetImage('images/logo.png');
	
	$query = "select * from news";
	$news = mysql_query($query);
	
	while($row = mysql_fetch_array($news)){
		$myfeed->SetItem('index.php?site=news&id=' .$row['id'],
			$row['titel'],
			str_replace("<br />", "\n", $row['text']),
			date("r", strtotime($row['datum'])));
	}
	$fdf = fopen("feed.xml", "w");
	fwrite($fdf, $myfeed->output());
	fclose($fdf);
	echo $myfeed->output();
?>