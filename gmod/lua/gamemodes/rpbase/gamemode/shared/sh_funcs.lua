------------------------------------
-- by SMILIE[AUT] 
------------------------------------

function RP:AddEnt(id, classname, prize, jobids, nicename, showmodel)
	//local id = #RP.entities+1;
	
	RP.entities[id] = {};
	RP.entities[id].class = classname;
	RP.entities[id].prize = prize;
	RP.entities[id].jobids = jobids;
	RP.entities[id].name = nicename;
	RP.entities[id].showmodel = showmodel;
	
end

function RP:AddWeapon(id, classname, prize, jobids, nicename, showmodel, ammo)
	//local id = #RP.weapons+1;
	
	RP.weapons[id] = {};
	RP.weapons[id].class = classname;
	RP.weapons[id].prize = prize;
	RP.weapons[id].jobids = jobids;
	RP.weapons[id].name = nicename;
	RP.weapons[id].showmodel = showmodel;
	RP.weapons[id].ammo = ammo;
end

function RP:AddAmmo(id, ammotype, prize, jobids, nicename, showmodel, amount)
	//local id = #RP.ammo+1;
	
	RP.ammo[id] = {};
	RP.ammo[id].type = ammotype;
	RP.ammo[id].prize = prize;
	RP.ammo[id].jobids = jobids;
	RP.ammo[id].name = nicename;
	RP.ammo[id].showmodel = showmodel;
	RP.ammo[id].amount = amount;
end



function RP:AddJob(id, name, color, models, salary, weps, ammo, maxamount, needsvote, jobneeded)
	//local id = #RP.jobs+1;
	
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
	
	//RP:dbgPrint("id: " ..id);
	//RP:dbgPrintTable(RP.jobs[id]);
	
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

function RP:getPlayer(name)
	for _,ply in pairs(player.GetAll())do
		print(string.lower(ply:GetName()));
		print(string.lower(name));
		if(string.lower(ply:GetName())==string.lower(name))then
			return ply;
		end
	end
	return false;
end

function RP:getJobByName(name)
	for _,job in pairs(RP.jobs)do
		if(job.name == name)then
			return job;
		end
	end
	return false;
end

function RP:getWepByName(name)
	for _,wep in pairs(RP.weapons)do
		if(wep.class == name)then
			return wep;
		end
	end
	return false;
end

function RP:getEntByName(name)
	for _,ent in pairs(RP.entities)do
		if(ent.class == name)then
			return ent;
		end
	end
	return false;
end

function RP:getAmmoByName(name)
	for _,ammo in pairs(RP.ammo)do
		if(ammo.type == name)then
			return ammo;
		end
	end
	return false;
end

function RP:getEntPrize(entname)
	for k, enttbl in pairs(RP.entities) do
		if(enttbl.class == entname)then
			return enttbl.prize;
		end
	end
	return GetConVar("rp_defsentcost"):GetInt();
end

function RP:getWepPrize(wepname)
	for k, weptbl in pairs(RP.weapons) do
		if(weptbl.class == wepname)then
			return weptbl.prize;
		end
	end
	return GetConVar("rp_defswepcost"):GetInt();
end

function RP:getAmmoPrize(ammoname)
	for k, ammotbl in pairs(RP.ammo) do
		if(ammotbl.class == ammoname)then
			return ammotbl.prize;
		end
	end
	return GetConVar("rp_defammocost"):GetInt();
end
