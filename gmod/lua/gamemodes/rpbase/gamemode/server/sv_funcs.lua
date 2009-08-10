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