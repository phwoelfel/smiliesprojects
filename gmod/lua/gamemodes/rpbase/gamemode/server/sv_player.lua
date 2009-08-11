------------------------------------
-- by SMILIE[AUT] 
------------------------------------


function GM:PlayerSpawn(ply)
	self.BaseClass:PlayerSpawn(ply)
	local teamid = ply:Team();
	/*
	if( GetConVar("rp_physgun"):GetInt()==1 || ply:IsAdmin() || ply:IsSuperAdmin() )then
		ply:Give("weapon_physgun");
	end
	if( GetConVar("rp_toolgun"):GetInt()==1 || ply:IsAdmin() || ply:IsSuperAdmin() )then
		ply:Give("gmod_tool");
	end
	ply:Give("weapon_physcannon");
	ply:Give("gmod_camera");
	
	for _,wp in pairs(RP.jobs[teamid].weps) do
		ply:Give(wp);
	end
	
	for _,ammo in pairs(RP.jobs[teamid].ammo) do
		ply:GiveAmmo(ammo[2], ammo[1]);
	end
	*/
	local modrand = math.random(#RP.jobs[teamid].models);
	print(ply:Name() ..": " ..RP.jobs[teamid].models[modrand]);
	ply:SetModel(RP.jobs[teamid].models[modrand]);
	
end

function GM:PlayerLoadout(ply)
	print("loadout");
	local teamid = ply:Team();
	
	if( GetConVar("rp_physgun"):GetInt()==1 || ply:IsAdmin() || ply:IsSuperAdmin() )then
		ply:Give("weapon_physgun");
	end
	if( GetConVar("rp_toolgun"):GetInt()==1 || ply:IsAdmin() || ply:IsSuperAdmin() )then
		ply:Give("gmod_tool");
	end
	ply:Give("weapon_physcannon");
	ply:Give("gmod_camera");
	
	for _,wp in pairs(RP.jobs[teamid].weps) do
		ply:Give(wp);
	end
	
	for _,ammo in pairs(RP.jobs[teamid].ammo) do
		ply:GiveAmmo(ammo[2], ammo[1]);
	end
end

function GM:PlayerInitialSpawn(ply)
	ply:SetTeam(1);
	
	if(ply:ReadData())then
		ply:ChatPrint("Welcome back " ..ply:GetNWString("rp_name") .."!");
		ply:SetUp();
	else // create new user account in database
		ply:ConCommand("rp_reggui");
	end
end

function GM:PlayerDisconnected(ply)
	timer.Destroy("rpsalary_"  ..ply:UniqueID());
end
