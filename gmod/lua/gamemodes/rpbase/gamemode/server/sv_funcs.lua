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
		data.ply:SendMsg("Your vote didn't succeed!", true);
	end
	RP.Jobvoting = false;
	table.Empty(RP.Jobvoting_data);
end


function RP:payWep(ply, swepname)
	local cost = RP:getWepPrize(swepname);
	if(ply:BuyAllowed(swepname))then
		if(ply:CanAfford(cost))then
			ply:AddMoney(-cost);
			local wepinfo = RP:getWepByName(swepname);
			ply:SendMsg("You paid $" ..cost .." for this " ..wepinfo.name ..".");
			return true;
		else
			ply:SendMsg("You can't afford this!", true);
			return false;
		end
	else
		ply:SendMsg("You are not allowed to buy this weapon!", true);
		return false;
	end
end