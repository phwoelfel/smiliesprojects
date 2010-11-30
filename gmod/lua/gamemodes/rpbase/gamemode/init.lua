------------------------------------
-- by SMILIE[AUT] 
------------------------------------

DeriveGamemode("sandbox")

AddCSLuaFile("config.lua");
AddCSLuaFile("shared.lua");
AddCSLuaFile("cl_init.lua");

AddCSLuaFile("client/cl_gui.lua");
AddCSLuaFile("client/cl_hud.lua");
AddCSLuaFile("client/cl_menus.lua");
AddCSLuaFile("client/cl_concommands.lua");
AddCSLuaFile("client/cl_gamemode.lua");
AddCSLuaFile("client/cl_hudpickup.lua");
AddCSLuaFile("client/cl_scoreboard.lua");
AddCSLuaFile("client/cl_usermessages.lua");

AddCSLuaFile("shared/sh_teams.lua");
AddCSLuaFile("shared/sh_entity.lua");
AddCSLuaFile("shared/sh_player.lua");
AddCSLuaFile("shared/sh_funcs.lua");
AddCSLuaFile("shared/sh_weapon.lua");

AddCSLuaFile("shared/sh_convars.lua");


include("config.lua");
include("shared.lua");

RP.Jobvoting = false;
RP.Salarytime = 5;

include("server/sv_concommands.lua");
//include("server/sv_convars.lua");
include("server/sv_gamemode.lua");
include("server/sv_player.lua");
include("server/sv_funcs.lua");



if(!sql.TableExists(GetConVar("rp_sqltable"):GetString()))then
	sql.Query("create table " ..GetConVar("rp_sqltable"):GetString() .."(uid INTEGER PRIMARY KEY, rpname TEXT, money INTEGER, model TEXT)");
end

if(RP.DEBUG)then
	local sqlusers = sql.Query("select * from " ..GetConVar("rp_sqltable"):GetString());
	if(sqlusers)then
		//PrintTable(sqlusers);
	else
		//print(sqlusers);
	end
end

function GM:Initialize()
	self.BaseClass:Initialize()
end

