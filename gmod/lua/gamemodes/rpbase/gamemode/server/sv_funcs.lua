------------------------------------
-- by SMILIE[AUT] 
------------------------------------

/*
	parameters: 
		name: name of the job
		
	returns:
		if job exists a table with the jobdinfos else false
*/

function RP:getJobByName(name)
	for _,job in pairs(RP.jobs)do
		if(job.name == name)then
			return job;
		end
	end
	return false;
end

function RP:getPlayer(name)
	for _,ply in pairs(player.GetAll())do
		if(string.find(string.lower(ply:GetName()), string.lower(name)) || string.find(ply:SteamID(), name))then
			return ply;
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

function RP:plyChangeJob(ply, jobid)
	ply:SetTeam(jobid);
	ply:ReadData();
	ply:Reequip();
	local modrand = math.random(#RP.jobs[ply:Team()].models);
	ply:SetModel(RP.jobs[ply:Team()].models[modrand]);
end

function RP:finishVote()
	local data = RP.Jobvoting_data;
	if(data.yesvotes > data.novotes)then
		RP:plyChangeJob(data.ply, data.jobid)
		data.ply:SendMsg("You changed your job to " ..data.jobname);
	else
		data.ply:SendMsg("Your vote didn't success!", true);
	end
	RP.Jobvoting = false;
	table.Empty(RP.Jobvoting_data);
end


