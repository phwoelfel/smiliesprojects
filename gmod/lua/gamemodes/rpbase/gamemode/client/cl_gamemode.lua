------------------------------------
-- by SMILIE[AUT] 
------------------------------------

function GM:AddNotify(msg, typ, dur)
	LocalPlayer():SendMsg(msg);
end

function GM:AddDeathNotice(attacker, attackerteam, weapon, victim, victimteam)
	RP:dbgPrint("attacker: " ..tostring(attacker));
	RP:dbgPrint("attackerteam: " ..tostring(attackerteam));
	RP:dbgPrint("weapon: " ..tostring(weapon));
	RP:dbgPrint("victim: " ..tostring(victim));
	RP:dbgPrint("victimteam: " ..tostring(victimteam));
	if(weapon == "worldspawn")then
		chat.AddText(RP.colors.blue1,  
		victim, 
		RP.colors.white, 
		" hit the ground too hard!");
	elseif(weapon == "suicide")then
		chat.AddText(RP.colors.blue1,  
		victim, 
		RP.colors.white, 
		" was fed up with his life!");
	elseif(weapon == "prop_physics" && attackerteam == -1)then
		chat.AddText(RP.colors.blue1,  
		victim, 
		RP.colors.white, 
		": Watch your stuff. It may stab you if you turn around.");
	else
		chat.AddText(RP.colors.blue1,  
		victim, 
		RP.colors.white, 
		" was killed by ", 
		RP.colors.red1, 
		attacker, 
		RP.colors.white, 
		" using ", 
		RP.colors.red1, 
		weapon, 
		RP.colors.white, 
		"!");
	end
end

