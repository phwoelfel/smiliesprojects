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
		class == "prop_vehicle_jeep" ||
		class == "prop_vehicle_airboat" ||
		class == "prop_vehicle_prisoner_pod")
	then
		return true
	end
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


function entmeta:GetPrize()
	local class = self:GetClass();
	if(self:IsDoor())then
		return GetConVar("rp_doorcost"):GetInt();
	elseif(class == "prop_vehicle_jeep" ||
		class == "prop_vehicle_airboat" ||
		class == "prop_vehicle_prisoner_pod")then
		return GetConVar("rp_vehiclecost"):GetInt()/2; // half the price cause you had to spawn it too
	else
		return self:GetNWInt("rp_prize", 0);
	end
	return 0;
end



function entmeta:SetTitle(title, ply)
	if(self:IsOwner(ply))then
		self:SetNWString("rp_title", tostring(title));
	else
		ply:SendMsg("This is not your " ..self:GetType() .."!", true);
	end
end

function entmeta:GetTitle()
	return self:GetNWString("rp_title", "");
end



function entmeta:SetType(typ)
	self:SetNWString("rp_type", tostring(typ));
end

function entmeta:GetType()
	local typ = self:GetNWString("rp_type", "");
	//RP:print("isvhcl: " ..tostring(self:IsVehicle()));
	if(typ!="")then
		return typ;
	elseif(self:IsDoor())then
		return "door";
	elseif(self:IsVehicle())then
		return "vehicle";
	else
		return "entity";
	end
	return "";
end



function entmeta:GetOwnerName(ply)
	return self:GetNWString("rp_ownername", "");
end

function entmeta:IsOwner(ply)
	if(self:GetNWString("rp_owner", "") == ply:UniqueID())then
		return true;
	else
		return false;
	end
end

function entmeta:Own(ply)
	//local owners = string.Explode(self:GetNWString("rp_owner", ""), ";");
	local owner = self:GetNWString("rp_owner", "");
	if(owner == "")then
		if(ply:CanAfford(self:GetPrize()))then
			ply:AddMoney(-self:GetPrize());
			self:SetNWString("rp_owner", ply:UniqueID());
			self:SetNWString("rp_ownername", ply:GetRPName());
			ply:SendMsg("You paid $" ..self:GetPrize() .." for this " ..self:GetType() ..".");
			return true;
		else
			ply:SendMsg("Can't afford this " ..self:GetType() .."!");
			return false;
		end
	elseif(owner == ply:UniqueID())then
		ply:SendMsg("This " ..self:GetType() .." already belongs to you!");
	else
		ply:SendMsg("This " ..self:GetType() .." belongs to someone else!");
	end
end

function entmeta:UnOwn(ply)
	local owner = self:GetNWString("rp_owner", "");
	if(owner == ply:UniqueID())then
		ply:AddMoney(self:GetPrize());
		self:SetTitle("", ply);
		self:SetNWString("rp_owner", "");
		self:SetNWString("rp_ownername", "");
		ply:SendMsg("You got $" ..self:GetPrize() .." for selling this " ..self:GetType() ..".");
	elseif(owner == "")then
		ply:SendMsg("This " ..self:GetType() .." belongs to nobody!");
	else
		ply:SendMsg("This " ..self:GetType() .." belongs to someone else!");
	end
end



function entmeta:IsLocked(ply)
	return self:GetNWBool("rp_locked", false)
end

function entmeta:RPLock(ply)
	local owner = self:GetNWString("rp_owner", "");
	if(owner == ply:UniqueID())then
		self:SetNWBool("rp_locked", true);
		if(self:IsDoor())then
			self:Fire("lock", "", 0);
			local snd = Sound("doors/door_latch3.wav");
			self:EmitSound(snd);
		end
	elseif(owner == "")then
		ply:SendMsg("This " ..self:GetType() .." belongs to nobody!");
	else
		ply:SendMsg("This " ..self:GetType() .." belongs to someone else!");
	end
end

function entmeta:RPUnLock(ply)
	local owner = self:GetNWString("rp_owner", "");
	if(owner == ply:UniqueID())then
		self:SetNWBool("rp_locked", false);
		if(self:IsDoor())then
			self:Fire("unlock", "", 0);
			local snd = Sound("doors/door_latch3.wav");
			self:EmitSound(snd);
		end
	elseif(owner == "")then
		ply:SendMsg("This " ..self:GetType() .." belongs to nobody!");
	else
		ply:SendMsg("This " ..self:GetType() .." belongs to someone else!");
	end
end

