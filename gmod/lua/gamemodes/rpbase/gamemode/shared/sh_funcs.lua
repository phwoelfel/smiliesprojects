------------------------------------
-- by SMILIE[AUT] 
------------------------------------

function RP:AddEnt(classname, prize, jobids, nicename, showmodel)
	local id = #RP.entities+1;
	
	RP.entities[id] = {};
	RP.entities[id].class = classname;
	RP.entities[id].prize = prize;
	RP.entities[id].jobids = jobids;
	RP.entities[id].name = nicename;
	RP.entities[id].showmodel = showmodel;
	
end

function RP:AddWeapon(classname, prize, jobids, nicename, showmodel, ammo)
	local id = #RP.weapons+1;
	
	RP.weapons[id] = {};
	RP.weapons[id].class = classname;
	RP.weapons[id].prize = prize;
	RP.weapons[id].jobids = jobids;
	RP.weapons[id].name = nicename;
	RP.weapons[id].showmodel = showmodel;
	RP.weapons[id].ammo = ammo;
end

function RP:AddAmmo(ammotype, prize, jobids, nicename, showmodel, amount)
	local id = #RP.ammo+1;
	
	RP.ammo[id] = {};
	RP.ammo[id].type = ammotype;
	RP.ammo[id].prize = prize;
	RP.ammo[id].jobids = jobids;
	RP.ammo[id].name = nicename;
	RP.ammo[id].showmodel = showmodel;
	RP.ammo[id].amount = amount;
end



function RP:AddJob(name, color, models, salary, weps, ammo, maxamount, needsvote, jobneeded)
	local id = #RP.jobs+1;
	
	RP.jobs[id] = {};
	RP.jobs[id].id = id;
	RP.jobs[id].name = name;	
	RP.jobs[id].color = color;
	RP.jobs[id].models = models;
	RP.jobs[id].salary = salary;
	RP.jobs[id].weps = weps;
	RP.jobs[id].ammo = ammo;
	RP.jobs[id].vote = needsvote;
	RP.jobs[id].jobneeded = jobneeded;
	
	local maxcvarn = "rp_max" ..string.lower(string.Replace(name, " ", "_")) .."s";
	if(SERVER)then
		if(!ConVarExists(maxcvarn))then CreateConVar(maxcvarn, maxamount, { FCVAR_ARCHIVE }); end
	end
	
	RP:dbgPrint("id: " ..id);
	RP:dbgPrintTable(RP.jobs[id]);
	
	team.SetUp(id, name, color);
end

function RP:GetAmmoByJob(jobid)
	local ret = {};
	for k,v in pairs(RP.ammo) do
		if(table.HasValue(v.jobids, jobid) || #v.jobids == 0)then
			table.insert(ret, v);
		end
	end
	
	return ret;
end

function RP:GetWeaponsByJob(jobid)
	local ret = {};
	for k,v in pairs(RP.weapons) do
		if(table.HasValue(v.jobids, jobid) || #v.jobids == 0)then
			table.insert(ret, v);
		end
	end
	
	return ret;
end

function RP:GetEntitiesByJob(jobid)
	local ret = {};
	for k,v in pairs(RP.entities) do
		if(table.HasValue(v.jobids, jobid) || #v.jobids == 0)then
			table.insert(ret, v);
		end
	end
	
	return ret;
end
