------------------------------------
-- by SMILIE[AUT] 
-- 08.08.2009
------------------------------------

DeriveGamemode("sandbox")

AddCSLuaFile("shared.lua");
AddCSLuaFile("cl_init.lua");
local clAddDlFiles = file.FindInLua("../" ..GM.Folder .."/gamemode/client/*.lua");
for _,f in pairs(clAddDlFiles) do
	AddCSLuaFile("/client/"..f);
end
local shAddDlFiles = file.FindInLua("../" ..GM.Folder .."/gamemode/shared/*.lua");
for _,f in pairs(shAddDlFiles) do
	AddCSLuaFile("/shared/"..f);
end

include("config.lua");
include("shared.lua");
local svIncFiles = file.FindInLua("../" ..GM.Folder .."/gamemode/server/*.lua");
for _,f in pairs(svIncFiles) do
	print("[" ..GM.RP_NAME .."][Server] Included "..f);
	include("server/"..f);
end

if(!sql.TableExists(GM.RP_TABLENAME))then
	sql.Query("create table " ..GetConVar("rp_sqltable"):GetString() .."(uid INTEGER PRIMARY KEY, rpname TEXT, money INTEGER)");
end

local sqlusers = sql.Query("select * from " ..GetConVar("rp_sqltable"):GetString());
if(sqlusers)then
	PrintTable(sqlusers);
else
	print(sqlusers);
end

function GM:PlayerSpawn(ply)
	local teamid = ply:Team();
	
	if( GetConVar("rp_physgun"):GetInt()==1 || ply:IsAdmin() || ply:IsSuperAdmin() )then
		ply:Give("weapon_physgun");
	end
	if( GetConVar("rp_toolgun"):GetInt()==1 || ply:IsAdmin() || ply:IsSuperAdmin() )then
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
	
	local sqlusr = sql.Query("select * from " ..GetConVar("rp_sqltable"):GetString() .." where uid = " ..SQLStr(ply:UniqueID()));
	if(sqlusr)then //user already joined the server
		PrintTable(sqlusr);
		ply:SetNWString("rp_name", sqlusr[1].rpname);
		ply:SetNWInt("rp_money", sqlusr[1].money);
	else // create new user account in database
		//ply:ConCommand("rp_reggui");
	
		umsg.Start("rp_registeruser", ply);
		umsg.End();
	
	end
end


