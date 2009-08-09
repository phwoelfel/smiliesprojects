------------------------------------
-- by SMILIE[AUT] 
-- 08.08.2009
------------------------------------



CivModels = {
	"models/player/Group01/Female_01.mdl",
	"models/player/Group01/Female_02.mdl",
	"models/player/Group01/Female_03.mdl",
	"models/player/Group01/Female_04.mdl",
	"models/player/Group01/Female_05.mdl",
	"models/player/Group01/Female_06.mdl",
	"models/player/Group01/Female_07.mdl",
	"models/player/Group01/Male_01.mdl",
	"models/player/Group01/male_02.mdl",
	"models/player/Group01/male_03.mdl",
	"models/player/Group01/Male_04.mdl",
	"models/player/Group01/Male_05.mdl",
	"models/player/Group01/male_06.mdl",
	"models/player/Group01/male_07.mdl",
	"models/player/Group01/male_08.mdl",
	"models/player/Group01/male_09.mdl",
	"models/player/Group03/Female_01.mdl",
	"models/player/Group03/Female_02.mdl",
	"models/player/Group03/Female_03.mdl",
	"models/player/Group03/Female_04.mdl",
	"models/player/Group03/Female_05.mdl",
	"models/player/Group03/Female_06.mdl",
	"models/player/Group03/Female_07.mdl",
	"models/player/Group03/Male_01.mdl",
	"models/player/Group03/male_02.mdl",
	"models/player/Group03/male_03.mdl",
	"models/player/Group03/Male_04.mdl",
	"models/player/Group03/Male_05.mdl",
	"models/player/Group03/male_06.mdl",
	"models/player/Group03/male_07.mdl",
	"models/player/Group03/male_08.mdl",
	"models/player/Group03/male_09.mdl",
}

GM.jobs = {};
GM.jobs[1] = {};
GM.jobs[1].name = "Citizen";
GM.jobs[1].color = Color(86, 131, 255, 255);
GM.jobs[1].models = CivModels;
GM.jobs[1].salary = 0;
GM.jobs[1].weps = {};
GM.jobs[1].ammo = {};
team.SetUp(1, GM.jobs[1].name, GM.jobs[1].color);

function GM:AddJob(name, color, models, salary, weps, ammo)
	local id = #self.jobs+1;
	
	self.jobs[id] = {};
	self.jobs[id].name = name;
	self.jobs[id].color = color;
	self.jobs[id].models = models;
	self.jobs[id].salary = salary;
	self.jobs[id].weps = weps;
	self.jobs[id].ammo = ammo;
	
	//print("id: " ..id);
	//PrintTable(self.jobs[id]);
	
	team.SetUp(id, name, color);
end

local shIncFiles = file.FindInLua("../" ..GM.Folder .."/gamemode/shared/*.lua");
for _,f in pairs(shIncFiles) do
	print("[" ..GM.RP_NAME .."][Shared] Included "..f);
	include("/shared/"..f);
end
