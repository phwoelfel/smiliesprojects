------------------------------------
-- by SMILIE[AUT] 
------------------------------------

GM.Name = "RP Base"
GM.Author = "SMILIE[AUT]"
GM.Email = "N/A"
GM.Website = "N/A"

RP.jobs = {};
/*
RP.jobs[1] = {};
RP.jobs[1].id = 1;
RP.jobs[1].name = "Citizen";
RP.jobs[1].color = Color(86, 131, 255, 255);
RP.jobs[1].models = RP.CivModels;
RP.jobs[1].salary = 0;
RP.jobs[1].weps = {};
RP.jobs[1].ammo = {};
team.SetUp(1, RP.jobs[1].name, RP.jobs[1].color);*/

function GM:AddJob(name, color, models, salary, weps, ammo, maxamount)
	local id = #RP.jobs+1;
	
	RP.jobs[id] = {};
	RP.jobs[id].id = id;
	RP.jobs[id].name = name;	
	RP.jobs[id].color = color;
	RP.jobs[id].models = models;
	RP.jobs[id].salary = salary;
	RP.jobs[id].weps = weps;
	RP.jobs[id].ammo = ammo;
	local maxcvarn = "rp_max" ..string.lower(name);
	if(SERVER)then
		if(!ConVarExists(maxcvarn))then CreateConVar(maxcvarn, maxamount, { FCVAR_ARCHIVE }); end
	end
	
	//print("id: " ..id);
	//PrintTable(RP.jobs[id]);
	
	team.SetUp(id, name, color);
end



include("shared/sh_teams.lua");
include("shared/sh_entity.lua");
include("shared/sh_player.lua");

