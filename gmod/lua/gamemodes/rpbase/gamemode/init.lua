------------------------------------
-- by SMILIE[AUT] 
------------------------------------

DeriveGamemode("sandbox")

AddCSLuaFile("config.lua");
AddCSLuaFile("shared.lua");
AddCSLuaFile("cl_init.lua");
AddCSLuaFile("client/cl_gui.lua");
AddCSLuaFile("client/cl_hud.lua");
AddCSLuaFile("client/cl_gamemode.lua");
AddCSLuaFile("shared/sh_teams.lua");


include("config.lua");
include("shared.lua");
include("server/sv_concommands.lua");
include("server/sv_convars.lua");
include("server/sv_gamemode.lua");
include("server/sv_player.lua");
include("server/sv_funcs.lua");
include("server/sv_entity.lua");

if(!sql.TableExists(GetConVar("rp_sqltable"):GetString()))then
	sql.Query("create table " ..GetConVar("rp_sqltable"):GetString() .."(uid INTEGER PRIMARY KEY, rpname TEXT, money INTEGER)");
end

local sqlusers = sql.Query("select * from " ..GetConVar("rp_sqltable"):GetString());
if(sqlusers)then
	PrintTable(sqlusers);
else
	print(sqlusers);
end

function GM:Initialize()
	self.BaseClass:Initialize()
end

GM.Name = "RP Base"
GM.Author = "SMILIE[AUT]"