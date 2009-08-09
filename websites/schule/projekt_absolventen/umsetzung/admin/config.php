<?php
	global $config;
	$config['host'] = "localhost"; //Datenbankhost
	$config['user'] = "3an_woelfel"; //Datenbankuser
	$config['psw'] = "Project_3an#"; //Datenbankpasswort
	$config['db'] =  "3an_woelfel"; //Datenbank
	$config['writecodetofile']=true; //ignorieren
	$config['debug']=false; //das auch
	$config['hash_algo'] = "sha1";
	
	
	$config['sitename'] = "HTL Rennweg Absolventen"; //der Name der Seite
	
function runphp($data, $filename) { 
	global $config;
	$return="";
	$reporting=error_reporting();
	if($config['debug']===false)
		error_reporting(0);
	if($config['writecodetofile']===true){
		@ob_start();
		//$filename = 'cache/tmp.inc.php';
		@unlink($filename);
		writetofile($filename,'w',$data);				
		include($filename);
		@unlink($filename);
		$return=@ob_get_clean();
	} else {
		$offset=0;
		$pos=stripos($data,"<?php");
		while ($pos>-1) {	
			$return.=substr($data,$offset,$pos-$offset);
			$offset=stripos($data,"?>",$pos)+2;
			$php=substr($data,$pos+2,3);
			$code=substr($data,$pos+5);
			if(!($pos>-1)) { break; }
			$code=substr($code,0,stripos($code,"?>"));
			@ob_start();
			if($config['debug'])
				eval($code);
			else
				@eval($code);
			$replace=@ob_get_clean();
			$return.= $replace;
			$pos=stripos($data,"<?php",$offset);
		}	
		$return.=substr($data,$offset);
	}
	error_reporting($reporting);
	return $return;
}

function writetofile($filename,$openmode,$content){
	if(file_exists($filename))
		if(!is_writeable($filename)) return $filename.' not writeable!';
	$handle = fopen($filename, $openmode);
	if(fwrite($handle, utf8_encode($content))===false) return $filename.' not writeable!';
	fclose($handle);
}
?>