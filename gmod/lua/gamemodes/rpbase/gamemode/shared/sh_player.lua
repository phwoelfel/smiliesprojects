------------------------------------
-- by SMILIE[AUT] 
------------------------------------

local plmeta = FindMetaTable("Player");

function plmeta:AddMoney(amount)
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

function plmeta:ReadData()
	local sqlusr = sql.Query("select * from " ..GetConVar("rp_sqltable"):GetString() .." where uid = " ..SQLStr(self:UniqueID()));
	if(sqlusr)then //user already joined the server
		PrintTable(sqlusr);
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
	timer.Create("rpsalary_" ..self:UniqueID(), 5*60, 0, function() self:PaySalary() end);
end

function plmeta:Reequip()
	if(self:InVehicle())then self:ExitVehicle() end
	self:StripWeapons();
	local teamid = self:Team();
	
	if( GetConVar("rp_physgun"):GetInt()==1 || self:IsAdmin() || self:IsSuperAdmin() )then
		self:Give("weapon_physgun");
	end
	if( GetConVar("rp_toolgun"):GetInt()==1 || self:IsAdmin() || self:IsSuperAdmin() )then
		self:Give("gmod_tool");
	end
	self:Give("weapon_physcannon");
	self:Give("gmod_camera");
	
	for _,wp in pairs(RP.jobs[teamid].weps) do
		self:Give(wp);
	end
	
	for _,ammo in pairs(RP.jobs[teamid].ammo) do
		self:GiveAmmo(ammo[2], ammo[1]);
	end
end

function plmeta:SendMsg(msg)
	self:ChatPrint(msg);
end

function plmeta:GetRPName()
	return self:GetNWString("rp_name");
end