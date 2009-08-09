<?php
	include("config.php");
	if(isset($_POST['phpcode'])){
		$phpcode = "<?php ".$_POST['phpcode']." ?>";
		echo runphp($phpcode, "../cache/tmp.inc.php");
	}
?>
<br />
<br />
<form method="post" action="?">
	<textarea name="phpcode" rows="20" cols="40"><?php if(isset($_POST['phpcode'])){ echo $_POST['phpcode']; } ?></textarea>
	<input type="submit" value="run" />
</form>