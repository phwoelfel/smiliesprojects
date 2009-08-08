------------------------------------
-- by SMILIE[AUT] 
-- 08.08.2009
------------------------------------

DeriveGamemode("sandbox")

AddCSLuaFile("shared.lua");
AddCSLuaFile("cl_init.lua");
local clAddDlFiles = file.FindInLua("../" ..GM.Folder .."/gamemode/client/*.lua");
for _,f in pairs(clAddDlFiles) do
	AddCSLuaFile(GM.Folder .."/client/"..f);
end
local shAddDlFiles = file.FindInLua("../" ..GM.Folder .."/gamemode/shared/*.lua");
for _,f in pairs(shAddDlFiles) do
	AddCSLuaFile("/shared/"..f);
end
include("shared.lua");

local svIncFiles = file.FindInLua("../" ..GM.Folder .."/gamemode/server/*.lua");
for _,f in pairs(svIncFiles) do
	print("[RP Base][Server] Included "..f);
	include("server/"..f);
end

function GM:PlayerSpawn(ply)
	local teamid = ply:Team();
	
	if(ply:IsAdmin() || ply:IsSuperAdmin())then
		ply:Give("weapon_physgun");
		ply:Give("gmod_tool");
	end
	ply:Give("weapon_physcannon");
	ply:Give("gmod_camera");
	
	for _,wp in pairs(self.jobs[teamid].weps) do
		ply:Give(wp);
	end
	
	for _,ammo in pairs(self.jobs[teamid].ammo) do
		ply:GiveAmmo(ammo[2], ammo[1]);
	end
	
	
	local modrand = math.random(#self.jobs[teamid].models);
	ply:SetModel(self.jobs[teamid].models[modrand]);
	
	
end

function GM:PlayerInitialSpawn(ply)
	ply:SetTeam(1);
end