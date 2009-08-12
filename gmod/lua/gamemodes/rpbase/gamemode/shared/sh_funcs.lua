------------------------------------
-- by SMILIE[AUT] 
------------------------------------

function RP:AddEnt(classname, prize, jobid, nicename, showmodel)
	local id = #RP.entities+1;
	
	RP.entities[id] = {};
	RP.entities[id].class = classname;
	RP.entities[id].prize = prize;
	RP.entities[id].jobid = jobid;
	RP.entities[id].name = nicename;
	RP.entities[id].showmodel = showmodel;
end

function RP:AddJob(name, color, models, salary, weps, ammo, maxamount, needsvote)
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
	
	local maxcvarn = "rp_max" ..string.lower(name) .."s";
	if(SERVER)then
		if(!ConVarExists(maxcvarn))then CreateConVar(maxcvarn, maxamount, { FCVAR_ARCHIVE }); end
	end
	
	RP:dbgPrint("id: " ..id);
	RP:dbgPrintTable(RP.jobs[id]);
	
	team.SetUp(id, name, color);
end

