------------------------------------
-- by SMILIE[AUT] 
------------------------------------

local plmeta = FindMetaTable("Player");

function plmeta:AddMoney(amount)
	self:ReadData();
	local curamount = self:GetNWInt("rp_money");
	local newamount = curamount+amount;
	if(newamount<0)then newamount = 0; end
	self:SetNWInt("rp_money", newamount);
	self:SaveData();
end

function plmeta:CanAfford(amount)
	local curamount = self:GetNWInt("rp_money");
	local newamount = curamount-amount;
	if(newamount<0)then
		return false;
	else
		return true;
	end
	
end

function plmeta:PaySalary()
	if(self:GetNWInt("rp_salary") != 0) then
		self:AddMoney(self:GetNWInt("rp_salary"));
		self:SendMsg("You got your salary! ($" ..self:GetNWInt("rp_salary") ..")");
	else
		self:SendMsg("You don't get any salary, you should maybe get a job.");
	end
end

function plmeta:IsRegistered()
	local sqlusr = sql.Query("select * from " ..GetConVar("rp_sqltable"):GetString() .." where uid = " ..SQLStr(self:UniqueID()));
	if(sqlusr)then //user already joined the server
		return true;
	else
		return false;
	end
end

function plmeta:ReadData()
	local sqlusr = sql.Query("select * from " ..GetConVar("rp_sqltable"):GetString() .." where uid = " ..SQLStr(self:UniqueID()));
	if(sqlusr)then //user already joined the server
		RP:dbgPrint("Data read for " ..self:GetName());
		RP:dbgPrintTable(sqlusr);
		self:SetNWString("rp_name", sqlusr[1].rpname);
		self:SetNWInt("rp_money", sqlusr[1].money);
		self:SetNWInt("rp_salary", RP.jobs[self:Team()].salary);
		return true;
	else
		return false;
	end
end

function plmeta:SaveData()
	sql.Query("update " ..GetConVar("rp_sqltable"):GetString() .." set money = " ..self:GetNWInt("rp_money") ..", rpname = " .. SQLStr(self:GetNWString("rp_name")) .." where uid = " ..SQLStr(self:UniqueID()));
end

function plmeta:SetUp()
	self:ReadData();
	local modrand = math.random(#RP.jobs[self:Team()].models);
	self:SetNWInt("rp_model", RP.jobs[self:Team()].models[modrand]);
	timer.Create("rpsalary_" ..self:UniqueID(), 5*60, 0, function() self:PaySalary() end);
end

function plmeta:Reequip()
	if(self:InVehicle())then self:ExitVehicle() end
	//self:StripWeapons();
	local teamid = self:Team();
	
	for _,wep in pairs(self:GetWeapons()) do
		if(!table.HasValue(self.weps, wep))then
			self:StripWeapon(wep:GetClass());
		end
	end

	for k,wp in pairs(RP.jobs[teamid].weps) do
		self:Give(wp);
	end
	
	for _,ammo in pairs(RP.jobs[teamid].ammo) do
		self:GiveAmmo(ammo[2], ammo[1]);
	end
	
end

function plmeta:SendMsg(msg, iserror)
	self:ChatPrint(msg);
end

function plmeta:GetRPName()
	return self:GetNWString("rp_name");
end

function plmeta:BuyAllowed(entname)
	local teamid = self:Team();
	for k,enttbl in pairs(RP.entities) do
		if(enttbl.class == entname && (table.HasValue(enttbl.jobids, teamid) || #enttbl.jobids==0))then
			return true;
		end
	end
	
	for k,weptbl in pairs(RP.weapons) do
		if(weptbl.class == entname && (table.HasValue(weptbl.jobids, teamid) || #weptbl.jobids==0))then
			return true;
		end
	end
	
	for k,ammotbl in pairs(RP.ammo) do
		if(ammotbl.type == entname && (table.HasValue(ammotbl.jobids, teamid) || #ammotbl.jobids==0))then
			return true;
		end
	end
	
	return false;
end