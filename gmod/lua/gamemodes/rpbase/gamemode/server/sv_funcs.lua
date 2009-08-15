------------------------------------
-- by SMILIE[AUT] 
------------------------------------



function RP:plyChangeJob(ply, jobid)
	ply:SetTeam(jobid);
	ply:ReadData();
	ply:Reequip();
	local modrand = math.random(#RP.jobs[ply:Team()].models);
	ply:SetModel(RP.jobs[ply:Team()].models[modrand]);
	ply:SetNWString("rp_model", RP.jobs[ply:Team()].models[modrand]);
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


