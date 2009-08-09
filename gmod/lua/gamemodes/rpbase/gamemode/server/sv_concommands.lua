------------------------------------
-- by SMILIE[AUT] 
------------------------------------

function ccRegisterUser(ply, cmd, args)
	if(!args) then return end
	local sqlusrdata = sql.Query("select * from " ..GetConVar("rp_sqltable"):GetString() .." where uid = " ..ply:UniqueID());
	if(sqlusrdata)then
		ply:ChatPrint("You have already created a useraccount!");
	else
		sql.Query("insert into " ..GetConVar("rp_sqltable"):GetString() .."(uid, rpname, money) values(" ..ply:UniqueID() ..", " ..SQLStr(args[1]) ..", " ..GetConVar("rp_startmoney"):GetInt() ..")")
		ply:ChatPrint("Your useraccount has been created!");
		ply:SetUp();
	end
end
concommand.Add("rp_register", ccRegisterUser);

function ccShowUsersFromDB(ply, cmd, args)
	if(!ply:IsSuperAdmin())then return end
	local sqlusers = sql.Query("select * from " ..GetConVar("rp_sqltable"):GetString());
	if(sqlusers)then
		PrintTable(sqlusers);
	end

end
concommand.Add("rp_showusers", ccShowUsersFromDB);


function ccSaveDataToDB(ply, cmd, args)	
	sql.Query("update " ..GetConVar("rp_sqltable"):GetString() .." set money = " ..ply:GetNWInt("rp_money"));
	if(args && args[1]=="1")then
		ply:ChatPrint("Your data has been saved!");
	end
end
concommand.Add("rp_save", ccSaveDataToDB);

function ccChangeJob(ply, cmd, args)	
	if(!ply || !args)then return end
	local jobname = args[1];
	print("job: "..jobname);
	local jobinfo = RP:getJobByName(jobname);
	if(jobinfo)then
		local maxpls = GetConVar("rp_max" ..string.lower(jobinfo.name)):GetInt();
		local curpls = team.NumPlayers(jobinfo.id);
		if(curpls>=maxpls)then
			ply:ChatPrint("There are already enough players in this job!");
		else
			ply:SetTeam(jobinfo.id);
			ply:ReadData();
			ply:Reequip();
			local modrand = math.random(#RP.jobs[ply:Team()].models);
			ply:SetModel(RP.jobs[ply:Team()].models[modrand]);
		end
	else
		ply:ChatPrint("This job doesn't exist!");
		return false;
	end
	
end
concommand.Add("rp_job", ccChangeJob);


function ccCreateDoor(ply, cmd, args)
	if(!ply:IsSuperAdmin())then return end
	local tr = ply:GetEyeTrace();
	PrintTable(tr);
	local pos = tr.HitPos;
	print(pos);
	pos.z = pos.z+60;
	print(pos);
	local dor = ents.Create("prop_door_rotating");
	dor:SetModel("models/props_c17/door01_left.mdl");
	dor:SetPos(pos);
	dor:Spawn();
end
concommand.Add("rp_createdoor", ccCreateDoor);

function ccChangePayTime(ply, cmd, args)	
	if(!ply:IsSuperAdmin())then return end
	if(args && args[1]!="")then
		local newtime = tonumber(args[1]);
		for _,pl in pairs(player.GetAll()) do
			timer.Adjust("rpsalary_" ..ply:UniqueID(), newtime*60, 0, function() ply:PaySalary() end);
		end
	end
end
concommand.Add("rp_paytime", ccChangePayTime);