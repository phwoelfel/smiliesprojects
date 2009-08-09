<!--
	Copyright 2008 Philip W&ouml;lfel <philip.woelfel@frig.at>
-->
<?php
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
	$jahr = date("Y");
	//$selbst = "?site=" .$_GET['site'];
	global $selbst;
	if(isset($_GET['month'])){
		$month = $_GET['month'];
		
		if($month==1 || $month==3 || $month==5 || $month==7 || $month==8 || $month==10 || $month==12){
			$days = 31; 
		}
		else{
			if($month==4 || $month==6 || $month==9 || $month==11){
				$days = 30;
			}
			else{
				$days = 28;
			}
		}
		$body =	'<table border="1" align="center">
				<tr>
				<td colspan="2" align="center" class="noborder"><a href="' .$selbst .'&amp;month=' .($month-1) .'">' .$monate[$month-1] .'</a></td>
				<td colspan="3" align="center" class="noborder">' .$monate[$month] .'</td>
				<td colspan="2" align="center" class="noborder"><a href="' .$selbst .'&amp;month=' .($month+1) .'">' .$monate[$month+1] .'</a></td>
				</tr>
				<tr>
				<td align="center" class="noborder">Montag</td>
				<td align="center" class="noborder">Dienstag</td>
				<td align="center" class="noborder">Mittwoch</td>
				<td align="center" class="noborder">Donnerstag</td>
				<td align="center" class="noborder">Freitag</td>
				<td align="center" class="noborder">Samstag</td>
				<td align="center" class="noborder">Sonntag</td>
				</tr>
				<tr>';
				
				//verschiebung f&uuml;r wochentage
				/*
				$weekday = jddayofweek ( cal_to_jd(CAL_GREGORIAN, $month, 1, $jahr) , 0 );
				switch ($weekday){
					case 1: $vers=0; break;//Montag
					case 2: $vers=1; break;//Dienstag
					case 3: $vers=2; break;//Mittwoch
					case 4: $vers=3; break;//Donnerstag
					case 5: $vers=4; break;//Freitag
					case 6: $vers=5; break;//Samstag
					case 0: $vers=6; break;//Sonntag
				}
				*/
				$vers = date("N")-1;
				$tds=0;
				
				for($k=0;$k<$vers;$k++){
					$body .= '<td class="noborder"> </td>';
					$tds++;
				}
				
		$dates = mysql_query("select id, datum, kurztext, langtext, DATE_FORMAT(datum,'%e') as tag,DATE_FORMAT(datum,'%c') as monat, DATE_FORMAT(datum,'%Y') as jahr from termine where DATE_FORMAT(datum,'%Y')=" .$jahr ." and  DATE_FORMAT(datum,'%c')=" .$month ." order by datum asc");
		$j=0;
		$termin = mysql_fetch_array($dates);
		for($i=1;$i<=$days;$i++){
			$body .= '<td width="100" height="100" align="left" valign="top">' .$i .'<br />';
			$tds++;
			if($termin['tag']==$i){
				$body .= '<a href="' .$selbst .'&amp;tid=' .$termin['id'] .'">' .$termin['kurztext'] .'</a>';
				$termin = mysql_fetch_array($dates);
			}
			$body .= '</td>';
			if($tds%7==0 && $tds/7<5){
				$body .= '</tr>
				<tr>
				';
			}
		}
		$body .= '</tr>
		<tr>
		<td colspan="7" align="center" class="noborder"><a href="' .$selbst .'">zur&uuml;ck</a></td>
		</tr>
		</table>';
	}
	elseif(isset($_GET['new'])){
		$body .= '<form method="post" action="' .$selbst .'&amp;new=true">
Jahr: <select name="jahr">
				<option value="' .$jahr .'">' .$jahr .'</option>
				<option value="' .($jahr+1) .'">' .($jahr+1) .'</option>
				<option value="' .($jahr+2) .'">' .($jahr+2) .'</option>
				<option value="' .($jahr+3) .'">' .($jahr+3) .'</option>
			</select><br />

		Monat: <select name="monat">
		';
		for($i=1;$i<=12;$i++){
			$body .= '<option value="' .$i .'">' .$monate[$i] .'</option>
			';
		}
		$body .= 	'</select>
					<br />
					Tag: <select name="tag">
					';
		for($j=1;$j<=31;$j++){
			$body .= '<option value="' .$j .'">' .$j .'</option>
			';
		}
		$body .= '</select>
		<br />
		Termin: <input type="text" name="termin" />
		<br />
		<input type="submit" value="Termin speichern" />
		</form>
		<br />
		<a href="' .$selbst .'">zur&uuml;ck</a>';
		
		if(isset($_POST['monat']) && isset($_POST['tag']) && isset($_POST['termin']) && isset($_POST['jahr'])){
			$monat = $_POST['monat'];
			$tag = $_POST['tag'];
			$termin = $_POST['termin'];
			$jahr = $_POST['jahr'];
			mysql_query("insert into termine (`datum`,`kurztext`,`langtext`) values ('" .$jahr ."-" .$monat ."-" .$tag ."', '" .$termin ."', 'lang')");
		}
	}
	elseif(isset($_GET['tid'])){
		$termin_qry = mysql_query("select * from termine where id=" .$_GET['tid']);
		$termin = mysql_fetch_array($termin_qry);
		
		echo '<h2>' .$termin['kurztext'] .'</h2>';
		echo 'am ' .date("d.m.Y", strtotime($termin['datum'])) .'<br /><br />';
		echo $termin['langtext'];
	}
	else{
		$body=	'Bitte Monat ausw&auml;hlen!<br />
				<a href="' .$selbst .'&amp;month=1">J&auml;nner</a> <a href="' .$selbst .'&amp;month=2">Februar</a> <a href="' .$selbst .'&amp;month=3">M&auml;rz</a> <a href="' .$selbst .'&amp;month=4">April</a> <a href="' .$selbst .'&amp;month=5">Mai</a> <a href="' .$selbst .'&amp;month=6">Juni</a> <a href="' .$selbst .'&amp;month=7">Juli</a> <a href="' .$selbst .'&amp;month=8">August</a> <a href="' .$selbst .'&amp;month=9">September</a> <a href="' .$selbst .'&amp;month=10">Oktober</a> <a href="' .$selbst .'&amp;month=11">November</a> <a href="' .$selbst .'&amp;month=12">Dezember</a><br /><a href="' .$selbst .'&amp;new=true">neuer Eintrag</a>';
	}
	
	
	
	echo $body;
		
?>