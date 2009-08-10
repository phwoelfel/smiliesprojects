------------------------------------
-- by SMILIE[AUT] 
------------------------------------

function ccRegisterUser(ply, cmd, args)
	if(!args) then return end
	local sqlusrdata = sql.Query("select * from " ..GetConVar("rp_sqltable"):GetString() .." where uid = " ..ply:UniqueID());
	if(sqlusrdata)then
		ply:SendMsg("You have already created a useraccount!");
	else
		sql.Query("insert into " ..GetConVar("rp_sqltable"):GetString() .."(uid, rpname, money) values(" ..ply:UniqueID() ..", " ..SQLStr(args[1]) ..", " ..GetConVar("rp_startmoney"):GetInt() ..")")
		ply:SendMsg("Your useraccount has been created!");
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
		ply:SendMsg("Your data has been saved!");
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
			ply:SendMsg("There are already enough players in this job!");
		else
			ply:SetTeam(jobinfo.id);
			ply:ReadData();
			ply:Reequip();
			local modrand = math.random(#RP.jobs[ply:Team()].models);
			ply:SetModel(RP.jobs[ply:Team()].models[modrand]);
		end
	else
		ply:SendMsg("This job doesn't exist!");
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

function ccEntInfo(ply, cmd, args)
	if(!ply:IsSuperAdmin())then return end
	local tr = ply:GetEyeTrace();
	PrintTable(tr);
	local hp = tr.HitPos;
	local ep = tr.StartPos;
	local dist = ep:Distance(hp);
	print("distance: " ..dist);
	if(tr.Entity)then
		print("class: " ..tr.Entity:GetClass());
		print("model: " ..tr.Entity:GetModel());
		print("ownable: " ..tostring(tr.Entity:IsOwnable()));
		print("owner: " ..tr.Entity:GetNWString("rp_owner", ""));
	end
end
concommand.Add("rp_showentinfo", ccEntInfo);

function ccBuy(ply, cmd, args)
	local tr = ply:GetEyeTrace();
	if(tr.StartPos:Distance(tr.HitPos)<150)then
		if(tr.Entity && tr.Entity:IsOwnable())then
			local ent = tr.Entity;
			ent:Own(ply);
		else
			ply:SendMsg("Can't buy this!");
		end
	end
end
concommand.Add("rp_buy", ccBuy);

function ccSell(ply, cmd, args)
	local tr = ply:GetEyeTrace();
	if(tr.StartPos:Distance(tr.HitPos)<150)then
		if(tr.Entity && tr.Entity:IsOwnable())then
			local ent = tr.Entity;
			ent:UnOwn(ply);
		else
			ply:SendMsg("Can't buy this!");
		end
	end
end
concommand.Add("rp_sell", ccSell);

function ccLock(ply, cmd, args)
	local tr = ply:GetEyeTrace();
	if(tr.StartPos:Distance(tr.HitPos)<150)then
		if(tr.Entity && tr.Entity:IsOwnable())then
			local ent = tr.Entity;
			ent:RPLock(ply);
		else
			ply:SendMsg("Can't lock this!");
		end
	end
end
concommand.Add("rp_lock", ccLock);

function ccUnLock(ply, cmd, args)
	local tr = ply:GetEyeTrace();
	if(tr.StartPos:Distance(tr.HitPos)<150)then
		if(tr.Entity && tr.Entity:IsOwnable())then
			local ent = tr.Entity;
			ent:RPUnLock(ply);
		else
			ply:SendMsg("Can't unlock this!");
		end
	end
end
concommand.Add("rp_unlock", ccUnLock);

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