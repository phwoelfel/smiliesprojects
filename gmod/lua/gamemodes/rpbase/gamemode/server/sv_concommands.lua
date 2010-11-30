------------------------------------
-- by SMILIE[AUT] 
------------------------------------

------------------------------------------------------ Garry's functions ( with some modifications ) ------------------------------------------------------
/*---------------------------------------------------------
   Name: CanPlayerSpawnSENT
---------------------------------------------------------*/
local function CanPlayerSpawnSENT( player, EntityName )

	// Make sure this is a SWEP
	local sent = scripted_ents.GetStored( EntityName )
	if (sent == nil) then 
	
		// Is this in the SpawnableEntities list?
		local SpawnableEntities = list.Get( "SpawnableEntities" )
		if (!SpawnableEntities) then return false end
		local EntTable = SpawnableEntities[ EntityName ]
		if (!EntTable) then return false end
		if ( EntTable.AdminOnly && !player:IsAdmin() ) then return false end
		
		return true 
	
	end

	local sent = sent.t
	
	// We need a spawn function. The SENT can then spawn itself properly
	if (!sent.SpawnFunction) then return false end
	
	// You're not allowed to spawn this unless you're an admin!
	if ( !sent.Spawnable && !player:IsAdmin() ) then return false end 
	if ( sent.AdminOnly && !player:IsAdmin() ) then return false end
	
	return true
	
end

/*---------------------------------------------------------
   Name: CCSpawnSENT
   Desc: Console Command for a player to spawn different items
---------------------------------------------------------*/

function spawnSent( player, entname )

	local EntityName = entname
	if ( EntityName == nil ) then return end
	
	if ( !CanPlayerSpawnSENT( player, EntityName ) ) then return end
	
	// Ask the gamemode if it's ok to spawn this
	if ( !gamemode.Call( "PlayerSpawnSENT", player, EntityName ) ) then return end
	
	local vStart = player:GetShootPos()
	local vForward = player:GetAimVector()
	
	local trace = {}
	trace.start = vStart
	trace.endpos = vStart + (vForward * 2048)
	trace.filter = player
	
	local tr = util.TraceLine( trace )
	
	local entity = nil
	local PrintName = nil
	local sent = scripted_ents.GetStored( EntityName )
	if ( sent ) then
	
		local sent = sent.t
		entity = sent:SpawnFunction( player, tr )
		PrintName = sent.PrintName
	
	else
	
		// Spawn from list table
		local SpawnableEntities = list.Get( "SpawnableEntities" )
		if (!SpawnableEntities) then return end
		local EntTable = SpawnableEntities[ EntityName ]
		if (!EntTable) then return end
		
		PrintName = EntTable.PrintName
		
		local SpawnPos = tr.HitPos + tr.HitNormal * 16
		if ( EntTable.NormalOffset ) then SpawnPos = SpawnPos + tr.HitNormal * EntTable.NormalOffset end
	
		entity = ents.Create( EntTable.ClassName )
			entity:SetPos( SpawnPos )
		entity:Spawn()
		entity:Activate()
		
		if ( EntTable.DropToFloor ) then
			entity:DropToFloor()
		end
	
	end
	

	if ( ValidEntity( entity ) ) then
	
		gamemode.Call( "PlayerSpawnedSENT", player, entity )
		
		undo.Create("SENT")
			undo.SetPlayer(player)
			undo.AddEntity(entity)
			if ( PrintName ) then
				undo.SetCustomUndoText( "Undone "..PrintName )
			end
		undo.Finish( "Scripted Entity ("..tostring( EntityName )..")" )
		
		player:AddCleanup( "sents", entity )		
		entity:SetVar( "Player", player )
	
	end
	
	
end
/*---------------------------------------------------------
	// Give a swep.. duh.
---------------------------------------------------------*/
function spawnSWEP( player, entname )

	if ( entname == nil ) then return end
	if(
		entname =="weapon_physcannon" ||
		entname =="weapon_physgun" ||
		entname =="weapon_crowbar" ||
		entname =="weapon_stunstick" ||
		entname =="weapon_pistol" ||
		entname =="weapon_357" ||
		entname =="weapon_smg1" ||
		entname =="weapon_ar2" ||
		entname =="weapon_shotgun" ||
		entname =="weapon_crossbow" ||
		entname =="weapon_rpg" ||
		entname =="weapon_slam" ||
		entname =="weapon_frag" ||
		entname =="gmod_tool" ||
		entname =="gmod_camera"
	)then
		if ( !gamemode.Call( "PlayerSpawnSWEP", player, entname, swep ) ) then return end
		
		local tr = player:GetEyeTraceNoCursor()

		if ( !tr.Hit ) then return end
		
		local entity = ents.Create( entname )
		
		if ( ValidEntity( entity ) ) then
		
			entity:SetPos( tr.HitPos + tr.HitNormal * 32 )
			entity:Spawn()
		
		end
	else
		// Make sure this is a SWEP
		local swep = weapons.GetStored( entname )
		if (swep == nil) then return end
		
		// You're not allowed to spawn this!
		if ( !swep.Spawnable && !player:IsAdmin() ) then
			return
		end
		
		if ( !gamemode.Call( "PlayerSpawnSWEP", player, entname, swep ) ) then return end
		
		local tr = player:GetEyeTraceNoCursor()

		if ( !tr.Hit ) then return end
		
		local entity = ents.Create( swep.Classname )
		
		if ( ValidEntity( entity ) ) then
		
			entity:SetPos( tr.HitPos + tr.HitNormal * 32 )
			entity:Spawn()
		
		end
	end
end
------------------------------------------------------ end Garry's functions ( with some modifications ) ------------------------------------------------------


function ccRegisterUser(ply, cmd, args)
	if(!args) then return end
	local sqlusrdata = sql.Query("select * from " ..GetConVar("rp_sqltable"):GetString() .." where uid = " ..ply:UniqueID());
	if(sqlusrdata)then
		ply:SendMsg("You have already created a useraccount!", true);
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
		RP:dbgPrintTable(sqlusers);
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
	RP:dbgPrint("job: "..jobname);
	local jobinfo = RP:getJobByName(jobname);
	if(jobinfo)then
		if(ply:Team()==jobinfo.jobneeded || jobinfo.jobneeded == 0)then
			local maxpls = GetConVar("rp_max" ..string.lower(string.Replace(jobinfo.name, " ", "_")) .."s"):GetInt();
			local curpls = team.NumPlayers(jobinfo.id);
			if(curpls>=maxpls)then
				ply:SendMsg("There are already enough players in this job!", true);
			else
				if(jobinfo.vote)then
					if(RP.Jobvoting)then
						ply:SendMsg("There is a vote in progress, please try again later.", true);
					else
						RP.Jobvoting = true;
						RP.Jobvoting_data = {};
						RP.Jobvoting_data.ply = ply;
						RP.Jobvoting_data.jobid = jobinfo.id;
						RP.Jobvoting_data.jobname = jobinfo.name;
						RP.Jobvoting_data.yesvotes = 1;
						RP.Jobvoting_data.novotes = 0;
						RP.Jobvoting_data.users = {};
						
						local filter = RecipientFilter()
						filter:AddAllPlayers();
						filter:RemovePlayer(ply);
						
						umsg.Start("rp_jobvoting", filter);
							umsg.String(ply:UniqueID());
							umsg.String(ply:GetRPName());
							umsg.String(jobinfo.name);
						umsg.End();
						ply.weps = {};
						ply.ammo = {};
						for _,wp in pairs(ply:GetWeapons()) do
							if(!table.HasValue(RP.jobs[ply:Team()].weps, wp:GetClass()))then
								table.insert(ply.weps, wp);
								table.insert(ply.ammo, {wp:GetPrimaryAmmoClass(), ply:GetAmmoCount(wp:GetPrimaryAmmoType())});
								table.insert(ply.ammo, {wp:GetSecondaryAmmoClass(), ply:GetAmmoCount(wp:GetSecondaryAmmoType())});
							end
						end
						RP:dbgPrintTable(ply.ammo);
						if(#player.GetAll()==1)then
							RP:finishVote();
						else
							ply:SendMsg("You applied for changing you job to " ..jobinfo.name ..". Please wait for the vote to finish.");
						end
					end
				else
					ply.weps = {};
					ply.ammo = {};
					for _,wp in pairs(ply:GetWeapons()) do
						if(!table.HasValue(RP.jobs[ply:Team()].weps, wp:GetClass()))then
							table.insert(ply.weps, wp);
							table.insert(ply.ammo, {wp:GetPrimaryAmmoClass(), ply:GetAmmoCount(wp:GetPrimaryAmmoType())});
							table.insert(ply.ammo, {wp:GetSecondaryAmmoClass(), ply:GetAmmoCount(wp:GetSecondaryAmmoType())});
						end
					end
					RP:dbgPrintTable(ply.ammo);
					RP:plyChangeJob(ply, jobinfo.id);
					ply:SendMsg("You changed your job to " ..jobinfo.name);
				end
			end
		else
			ply:SendMsg("You need to be " ..RP.jobs[jobinfo.jobneeded].name .." first!", true);
		end
	else
		ply:SendMsg("This job doesn't exist!", true);
		return false;
	end
	
end
concommand.Add("rp_job", ccChangeJob);

function ccJobVote(ply, cmd, args)
	if(args && #args == 2)then
		if(RP.Jobvoting)then
			local userid = args[1];
			local decision = (tonumber(args[2])==1);
			RP:dbgPrint("decision: " ..tostring(decision));
			RP:dbgPrint("userid: " ..tostring(userid));
			if(RP.Jobvoting_data.ply:UniqueID() == userid)then
				if(table.HasValue(RP.Jobvoting_data.users, ply:UserID()))then
					ply:SendMsg("You have already voted!", true);
				else
					table.insert(RP.Jobvoting_data.users, ply:UserID());
					if(decision)then
						RP.Jobvoting_data.yesvotes = RP.Jobvoting_data.yesvotes + 1;
					else
						RP.Jobvoting_data.novotes = RP.Jobvoting_data.novotes + 1;
					end
					ply:SendMsg("Thanks for your vote.");
					local anzvotes = RP.Jobvoting_data.novotes + RP.Jobvoting_data.yesvotes;
					RP:dbgPrint("votes: " ..anzvotes);
					RP:dbgPrint("players: " ..#player.GetAll());
					if(anzvotes >= #player.GetAll())then
						RP:finishVote();
					end
				end
			end
		else
			ply:SendMsg("There is currently no vote!", true);
		end
	else
		ply:SendMsg("Invalid parameters!", true);
	end
end
concommand.Add("rp_vote", ccJobVote);


function ccCreateDoor(ply, cmd, args)
	if(!ply:IsSuperAdmin())then return end
	local tr = ply:GetEyeTrace();
	RP:dbgPrintTable(tr);
	local pos = tr.HitPos;
	RP:dbgPrint(pos);
	pos.z = pos.z+60;
	RP:dbgPrint(pos);
	local dor = ents.Create("prop_door_rotating");
	dor:SetModel("models/props_c17/door01_left.mdl");
	dor:SetPos(pos);
	dor:Spawn();
end
concommand.Add("rp_createdoor", ccCreateDoor);

function ccEntInfo(ply, cmd, args)
	if(!ply:IsSuperAdmin())then return end
	local tr = ply:GetEyeTrace();
	RP:dbgPrintTable(tr);
	local hp = tr.HitPos;
	local ep = tr.StartPos;
	local dist = ep:Distance(hp);
	RP:dbgPrint("distance: " ..dist);
	if(tr.Entity)then
		RP:dbgPrint("class: " ..tr.Entity:GetClass());
		RP:dbgPrint("model: " ..tr.Entity:GetModel());
		RP:dbgPrint("ownable: " ..tostring(tr.Entity:IsOwnable()));
		RP:dbgPrint("owner: " ..tr.Entity:GetNWString("rp_owner", ""));
		RP:dbgPrint("vehicle: " ..tostring(tr.Entity:IsVehicle()));
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
			ply:SendMsg("Can't buy this!", true);
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
			ply:SendMsg("Can't sell this!", true);
		end
	end
end
concommand.Add("rp_sell", ccSell);

function ccBuyEnt(ply, cmd, args)
	if(args && #args==1)then
		local entname = tostring(args[1]);
		if(entname && entname != "")then
			if(ply:BuyAllowed(entname))then
				umsg.Start("RP_spawnsent", ply)
					umsg.String(entname);
				umsg.End();
				//spawnSent(ply, entname);
				//rpSpawnSent(ply, entname);
				//ply:ConCommand("gm_spawnsent " ..entname);
			else
				ply:SendMsg("You are not allowed to buy this!", true);
			end
		else
			ply:SendMsg("Invalid entity name specified!", true);
		end
	else
		ply:SendMsg("No entity name specified!", true);
	end
end
concommand.Add("rp_buyent", ccBuyEnt);

function ccBuyWep(ply, cmd, args)
	if(args && #args==1)then
		local entname = tostring(args[1]);
		if(entname && entname != "")then
			if(ply:BuyAllowed(entname))then
				//spawnSWEP(ply, entname);
				/*
				umsg.Start("RP_giveswep", ply);
					umsg.String(entname);
				umsg.End();
				
				RP:dbgPrintTable(weapons.Get(entname));
				local ret = gamemode.Call("PlayerGiveSWEP", ply, entname, weapons.Get(entname));
				RP:print("ret: " ..tostring(ret));
				*/
				local ret = RP:payWep(ply, entname);
				if(ret)then
					ply:Give(entname);
				end
			else
				ply:SendMsg("You are not allowed to buy this!", true);
			end
		else
			ply:SendMsg("Invalid weapon name specified!", true);
		end
	else
		ply:SendMsg("No weapon name specified!", true);
	end
end
concommand.Add("rp_buyswep", ccBuyWep);

function ccBuyAmmo(ply, cmd, args)
	if(args && #args==1)then
		local ammoname = tostring(args[1]);
		if(ammoname && ammoname != "")then
			if(ply:BuyAllowed(ammoname))then
				local ammoinfo = RP:getAmmoByName(ammoname);
				if(ply:CanAfford(ammoinfo.prize))then
					ply:AddMoney(-ammoinfo.prize);
					ply:GiveAmmo(ammoinfo.amount, ammoinfo.type);
					ply:SendMsg("You bought " ..ammoinfo.amount .." rounds of " ..ammoinfo.name ..".");
				else
					ply:SendMsg("You can't afford this!", true);
				end
			else
				ply:SendMsg("You are not allowed to buy this!", true);
			end
		else
			ply:SendMsg("Invalid ammo name specified!", true);
		end
	else
		ply:SendMsg("No ammo name specified!", true);
	end
end
concommand.Add("rp_buyammo", ccBuyAmmo);

function ccLock(ply, cmd, args)
	local tr = ply:GetEyeTrace();
	if(tr.StartPos:Distance(tr.HitPos)<150)then
		if(tr.Entity && tr.Entity:IsOwnable())then
			local ent = tr.Entity;
			if(ent:IsOwner(ply))then
				ent:RPLock(ply);
			else
				ply:SendMsg("Can't lock this!", true);
			end
		else
			ply:SendMsg("Can't lock this!", true);
		end
	end
end
concommand.Add("rp_lock", ccLock);

function ccUnLock(ply, cmd, args)
	local tr = ply:GetEyeTrace();
	if(tr.StartPos:Distance(tr.HitPos)<150)then
		if(tr.Entity && tr.Entity:IsOwnable())then
			local ent = tr.Entity;
			if(ent:IsOwner(ply))then
				ent:RPUnLock(ply);
			else
				ply:SendMsg("Can't unlock this!", true);
			end
		else
			ply:SendMsg("Can't unlock this!", true);
		end
	end
end
concommand.Add("rp_unlock", ccUnLock);

function ccGive(ply, cmd, args)
	if(args && args[1])then
		local tr = ply:GetEyeTrace();
		local amount = tonumber(args[1]);
		if(amount && amount>0)then
			if(tr.StartPos:Distance(tr.HitPos)<150)then
				if(tr.Entity && tr.Entity:IsPlayer())then
					local pl = tr.Entity;
					if(ply:CanAfford(amount))then
						ply:AddMoney(-amount);
						pl:AddMoney(amount);
						ply:SendMsg("You sent $" ..amount .." to " ..pl:GetRPName()..".");
						pl:SendMsg(ply:GetRPName() .." sent you $" ..amount ..".");
					else
						ply:SendMsg("You don't have that much money!", true);
					end
				else
					ply:SendMsg("This is no player!", true);
				end
			end
		else
			ply:SendMsg("Amount must be a positive number!", true);
		end
	else
		ply:SendMsg("Please specify a amount!", true);
	end
end
concommand.Add("rp_give", ccGive);


function ccChangeModel(ply, cmd, args)
	if(args && #args == 1)then
		local mdl = tostring(args[1]);
		local valid = false;
		for k,model in pairs(RP.jobs[ply:Team()].models) do
			if(model == mdl)then
				valid = true;
			end
		end
		if(valid)then
			ply:SetModel(mdl);
			ply:SetNWString("rp_model", mdl);
			ply:SendMsg("You changed your model.");
		else
			ply:SendMsg("This model isn't allowed!", true);
		end
		
	else
		ply:SendMsg("Invalid parameters!", true);
	end
end
concommand.Add("rp_model", ccChangeModel);


function ccChangePayTime(ply, cmd, args)	
	if(!ply:IsAdmin())then return end
	if(args && args[1]!="")then
		local newtime = tonumber(args[1]);
		for _,pl in pairs(player.GetAll()) do
			timer.Adjust("rpsalary_" ..ply:UniqueID(), newtime*60, 0, function() ply:PaySalary() end);
		end
		RP.Salarytime = newtime;
	end
end
function ccChangePayTimeCompl()
	return {tostring(RP.Salarytime)};
end
concommand.Add("rp_paytime", ccChangePayTime, ccChangePayTimeCompl);

function ccChangeRunSpeed(ply, cmd, args)	
	if(!ply:IsAdmin())then return end
	if(args && args[1]!="")then
		local newspeed = tonumber(args[1]);
		for _,pl in pairs(player.GetAll()) do
			pl:SetRunSpeed(newspeed);
		end
	end
end
concommand.Add("rp_runspeed", ccChangeRunSpeed);

function ccChangeWalkSpeed(ply, cmd, args)	
	if(!ply:IsAdmin())then return end
	if(args && args[1]!="")then
		local newspeed = tonumber(args[1]);
		for _,pl in pairs(player.GetAll()) do
			pl:SetWalkSpeed(newspeed);
		end
	end
end
concommand.Add("rp_walkspeed", ccChangeWalkSpeed);


function ccSetTitle(ply, cmd, args)
	if(args && args[1]!="")then
		local title = args[1];
		local tr = ply:GetEyeTrace();
		if(tr.StartPos:Distance(tr.HitPos)<150)then
			if(tr.Entity && tr.Entity:IsOwnable())then
				local ent = tr.Entity;
				ent:SetTitle(title, ply);
			else
				ply:SendMsg("Can't set the title!", true);
			end
		end
	end
end
concommand.Add("rp_settitle", ccSetTitle);

