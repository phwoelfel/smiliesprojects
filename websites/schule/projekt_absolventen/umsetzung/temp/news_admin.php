<?php
	global $selbst;
	require("admin/admin_funcs.php");
	
	echo '<h1>News Administration</h1>
			<a href="' .$selbst .'&action=newNews">neue News</a><br />
			<a href="' .$selbst .'&action=editNews">News bearbeiten</a><br />
			<a href="' .$selbst .'&action=delNews">News l&ouml;schen</a><br /><hr />';
	
	if(isset($_GET['action'])){
		$action = $_GET['action'];
		if(isset($_SESSION['userid'], $_SESSION['username'])){
			$admin = new AdminFunctions("", "");
			if($admin->testUser()){
				switch($action){
					case "newNews": $admin->action("newNews"); break;
					case "editNews": $admin->action("editNews"); break;
					case "delNews": $admin->action("delNews"); break;
					default: break;
				}
			}
		}
	}
	
	
?>