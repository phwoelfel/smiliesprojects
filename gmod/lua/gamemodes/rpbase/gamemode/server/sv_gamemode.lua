------------------------------------
-- by SMILIE[AUT] 
------------------------------------

function GM:ShowHelp(ply)
	
end

function GM:ShowTeam(ply)
	ply:ConCommand("rp_jobs");
end

function GM:ShowSpare1(ply)
	ply:ConCommand("rp_bmenu");
end

function GM:ShowSpare2(ply)

end


function GM:PlayerSpawnEffect(ply)
	if(ply:IsAdmin())then
		return true;
	else
		return false;
	end
end

function GM:PlayerSpawnNPC(ply)
	if(GetConVar("rp_allowspawnnpcs"):GetInt()==1 || ply:IsAdmin())then
		local maxspawn = server_settings.Int( "sbox_maxnpcs", 0 )
		local curspawn = ply:GetCount("npcs");
		if(curspawn >= maxspawn)then
			ply:LimitHit("npcs");
		else
			local cost = GetConVar("rp_npccost"):GetInt();
			if(ply:CanAfford(cost))then
				ply:AddMoney(-cost);
				ply:SendMsg("You paid $" ..cost .." for this NPC.");
				return true;
			else
				ply:SendMsg("You can't afford this!");
				return false;
			end
			
		end
	else
		ply:SendMsg("This is not allowed!");
		return false;
	end
end

function GM:PlayerSpawnProp(ply)
	if(GetConVar("rp_allowspawnprops"):GetInt()==1 || ply:IsAdmin())then
		local maxspawn = server_settings.Int( "sbox_maxprops", 0 )
		local curspawn = ply:GetCount("props");
		if(curspawn >= maxspawn)then
			ply:LimitHit("props");
		else
			local cost = GetConVar("rp_propcost"):GetInt();
			if(ply:CanAfford(cost))then
				ply:AddMoney(-cost);
				ply:SendMsg("You paid $" ..cost .." for this prop.");
				return true;
			else
				ply:SendMsg("You can't afford this!");
				return false;
			end
			
		end
	else
		ply:SendMsg("This is not allowed!");
		return false;
	end
end

function GM:PlayerSpawnRagdoll(ply)
	if(GetConVar("rp_allowspawnragdolls"):GetInt()==1 || ply:IsAdmin())then
		local maxspawn = server_settings.Int( "sbox_maxragdolls", 0 )
		local curspawn = ply:GetCount("ragdolls");
		if(curspawn >= maxspawn)then
			ply:LimitHit("ragdolls");
		else
			local cost = GetConVar("rp_propcost"):GetInt();
			if(ply:CanAfford(cost))then
				ply:AddMoney(-cost);
				ply:SendMsg("You paid $" ..cost .." for this ragdoll.");
				return true;
			else
				ply:SendMsg("You can't afford this!");
				return false;
			end
		end
	else
		ply:SendMsg("This is not allowed!");
		return false;
	end
end

function GM:PlayerSpawnSENT(ply)
	if(GetConVar("rp_allowspawnsents"):GetInt()==1 || ply:IsAdmin())then
		local cost = GetConVar("rp_sentcost"):GetInt();
		if(ply:CanAfford(cost))then
			ply:AddMoney(-cost);
			ply:SendMsg("You paid $" ..cost .." for this SENT.");
			return true;
		else
			ply:SendMsg("You can't afford this!");
			return false;
		end
	else
		ply:SendMsg("This is not allowed!");
		return false;
	end
end

function GM:PlayerSpawnSWEP(ply)
	if(GetConVar("rp_allowspawnsweps"):GetInt()==1 || ply:IsAdmin())then
		local cost = GetConVar("rp_swepcost"):GetInt();
		if(ply:CanAfford(cost))then
			ply:AddMoney(-cost);
			ply:SendMsg("You paid $" ..cost .." for this SWEP.");
			return true;
		else
			ply:SendMsg("You can't afford this!");
			return false;
		end
	else
		ply:SendMsg("This is not allowed!");
		return false;
	end
end

function GM:PlayerGiveSWEP(ply)
	if(GetConVar("rp_allowspawnsweps"):GetInt()==1 || ply:IsAdmin())then
		local cost = GetConVar("rp_swepcost"):GetInt();
		if(ply:CanAfford(cost))then
			ply:AddMoney(-cost);
			ply:SendMsg("You paid $" ..cost .." for this SWEP.");
			return true;
		else
			ply:SendMsg("You can't afford this!");
			return false;
		end
	else
		ply:SendMsg("This is not allowed!");
		return false;
	end
end

function GM:PlayerSpawnVehicle(ply)
	if(GetConVar("rp_allowspawnvehicles"):GetInt()==1 || ply:IsAdmin())then
		local maxspawn = server_settings.Int( "sbox_maxvehicles", 0 )
		local curspawn = ply:GetCount("vehicles");
		if(curspawn >= maxspawn)then
			ply:LimitHit("vehicles");
		else
			local cost = GetConVar("rp_vehiclecost"):GetInt()/2; // half the price cause you need to own it too
			if(ply:CanAfford(cost))then
				ply:AddMoney(-cost);
				ply:SendMsg("You paid $" ..cost .." for this vehicle.");
				return true;
			else
				ply:SendMsg("You can't afford this!");
				return false;
			end
		end
	else
		ply:SendMsg("This is not allowed!");
		return false;
	end
end

function GM:PlayerNoClip(ply)
	if ( ply:InVehicle() ) then return false end
	if(GetConVar("rp_noclip"):GetInt()==1 || ply:IsAdmin())then
		return true;
	else
		return false;
	end
end

function GM:CanPlayerEnterVehicle(ply, vhcl)
	if(!vhcl:IsLocked())then
		return true;
	else
		ply:SendMsg("This vehicle is locked!");
		return false;
	end
end