------------------------------------
-- by SMILIE[AUT] 
------------------------------------

local entmeta = FindMetaTable("Entity");

function entmeta:IsOwnable()
	local class = self:GetClass();
	if(
		class == "prop_door_rotating" ||
		class == "func_door" ||
		class == "func_door_rotating" ||
		class == "prop_vehicle_prisoner_pod" ||
		class == "prop_vehicle_jeep" ||
		class == "prop_vehicle_airboat")
	then
		return true
	end
end

function entmeta:GetPrize()
	local class = self:GetClass();
	if(
		class == "prop_door_rotating" ||
		class == "func_door" ||
		class == "func_door_rotating" )
	then
		return GetConVar("rp_doorcost"):GetInt();
	elseif(	
		class == "prop_vehicle_prisoner_pod" ||
		class == "prop_vehicle_jeep" ||
		class == "prop_vehicle_airboat")
	then
		return GetConVar("rp_vehiclecost"):GetInt()/2; // half the price cause you had to spawn it too
	end
	return 0;
end

function entmeta:IsDoor()
	local class = self:GetClass();
	if(
		class == "prop_door_rotating" ||
		class == "func_door" ||
		class == "func_door_rotating" )
	then
		return true;
	else
		return false;
	end
end



function entmeta:IsOwner(ply)
	if(self:GetNWString("rp_owner", "") == ply:UniqueID())then
		return true;
	else
		return false;
	end
end

function entmeta:IsLocked(ply)
	return self:GetNWBool("rp_locked", false)
end

function entmeta:Own(ply)
	local owner = self:GetNWString("rp_owner", "");
	if(owner == "")then
		if(ply:CanAfford(self:GetPrize()))then
			ply:AddMoney(-self:GetPrize());
			self:SetNWString("rp_owner", ply:UniqueID());
			self:SetNWString("rp_ownername", ply:GetRPName());
			if(self:IsDoor())then
				ply:SendMsg("You paid $" ..self:GetPrize() .." for this door.");
			else
				ply:SendMsg("You paid $" ..self:GetPrize() .." for this vehicle.");
			end
			return true;
		else
			ply:SendMsg("Can't afford this!");
			return false;
		end
	elseif(owner == ply:UniqueID())then
		ply:SendMsg("This already belongs to you!");
	else
		ply:SendMsg("This belongs to someone else!");
	end
end

function entmeta:UnOwn(ply)
	local owner = self:GetNWString("rp_owner", "");
	if(owner == ply:UniqueID())then
		ply:AddMoney(self:GetPrize());
		self:SetNWString("rp_owner", "");
		self:SetNWString("rp_ownername", "");
		if(self:IsDoor())then
			ply:SendMsg("You got $" ..self:GetPrize() .." for selling this door.");
		else
			ply:SendMsg("You got $" ..self:GetPrize() .." for selling this vehicle.");
		end
	elseif(owner == "")then
		ply:SendMsg("This belongs to nobody!");
	else
		ply:SendMsg("This belongs to someone else!");
	end
end

function entmeta:RPLock(ply)
	local owner = self:GetNWString("rp_owner", "");
	if(owner == ply:UniqueID())then
		self:SetNWBool("rp_locked", true);
		if(self:IsDoor())then
			self:Fire("lock", "", 0);
		end
	elseif(owner == "")then
		ply:SendMsg("This belongs to nobody!");
	else
		ply:SendMsg("This belongs to someone else!");
	end
end

function entmeta:RPUnLock(ply)
	local owner = self:GetNWString("rp_owner", "");
	if(owner == ply:UniqueID())then
		self:SetNWBool("rp_locked", false);
		if(self:IsDoor())then
			self:Fire("unlock", "", 0);
		end
	elseif(owner == "")then
		ply:SendMsg("This belongs to nobody!");
	else
		ply:SendMsg("This belongs to someone else!");
	end
end