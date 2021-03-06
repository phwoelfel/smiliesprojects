------------------------------------
-- by SMILIE[AUT] 
------------------------------------


RP.colors.hudammo = RP.colors.hudgrey;
RP.colors.ammo = RP.colors.black;
RP.colors.hudenergy = RP.colors.hudblue;
RP.colors.energy = RP.colors.green;
RP.colors.energylow = RP.colors.red;
RP.colors.hudmoney = RP.colors.hudorange;
RP.colors.money = RP.colors.white;
RP.colors.hudjob = RP.colors.huddarkblue;
RP.colors.job = RP.colors.white;
RP.colors.enttext = RP.colors.green;


function GM:HUDPaint()
	local ply = LocalPlayer();
	if( !ply || !ply:Alive() )then return end // hide HUD when dead
	if( #ply:GetWeapons() == 0 )then return end
	if(ply:Alive() &&
		ply:GetActiveWeapon()!=nil &&
		ply:GetActiveWeapon():GetClass()=="gmod_camera") then return end // hide HUD when using camera
	
	local bordersize = 20; // bordersize for draw.RoundedBox
	
	
	
	---------------------------------------------------------------------------------------------------------- Health/Armor Hud ----------------------------------------------------------------------------------------------------------
	local healthbox = {}; // values for health box
	healthbox.w = 100;
	healthbox.h = 70;
	healthbox.x = 20;
	healthbox.y = ScrH()-healthbox.h-20;
	healthbox.col = RP.colors.hudenergy;
	
	local healthcolor;
	if(ply:Health()<25)then healthcolor = RP.colors.energylow else healthcolor = RP.colors.energy end
	
	draw.RoundedBox(bordersize, healthbox.x, healthbox.y, healthbox.w, healthbox.h, healthbox.col);
	draw.SimpleText(ply:Health(), "rp_hudtext_large", healthbox.x+healthbox.w/2, healthbox.y+healthbox.h/2, healthcolor, TEXT_ALIGN_CENTER, TEXT_ALIGN_CENTER);
	
	if(ply:Armor()!=0)then
		local armorbox = {}; // values for armor box
		armorbox.w = 100;
		armorbox.h = 70;
		armorbox.x = healthbox.x+healthbox.w+20;
		armorbox.y = ScrH()-armorbox.h-20;
		armorbox.col = RP.colors.hudenergy;
		
		local armorcolor;
		if(ply:Armor()<25)then armorcolor = RP.colors.energylow else armorcolor = RP.colors.energy end
		
		draw.RoundedBox(bordersize, armorbox.x, armorbox.y, armorbox.w, armorbox.h, armorbox.col);
		draw.SimpleText(ply:Armor(), "rp_hudtext_large", armorbox.x+armorbox.w/2, armorbox.y+armorbox.h/2, armorcolor, TEXT_ALIGN_CENTER, TEXT_ALIGN_CENTER);
	end
	
	---------------------------------------------------------------------------------------------------------- RP Hud ----------------------------------------------------------------------------------------------------------
	
	local moneybox = {}; // values for  ammo box
	moneybox.w = 150;
	moneybox.h = 25;
	moneybox.x = 20;
	moneybox.y = ScrH()-healthbox.h-moneybox.h-40;
	moneybox.col = RP.colors.hudmoney;
	
	local money = ply:GetNWInt("rp_money");
	
	draw.RoundedBox(10, moneybox.x, moneybox.y, moneybox.w, moneybox.h, moneybox.col);
	draw.SimpleText("Money: " ..money, "rp_hudtext_small", moneybox.x+moneybox.w/2, moneybox.y+moneybox.h/2, RP.colors.money, TEXT_ALIGN_CENTER, TEXT_ALIGN_CENTER);
	
	local jobbox = {}; // values for  ammo box
	jobbox.w = 150;
	jobbox.h = 20;
	jobbox.x = 20;
	jobbox.y = ScrH()-healthbox.h-moneybox.h-jobbox.h-49;
	jobbox.col = RP.colors.hudjob;
	
	local job = team.GetName(ply:Team());
	
	draw.RoundedBox(10, jobbox.x, jobbox.y, jobbox.w, jobbox.h, jobbox.col);
	draw.SimpleText("Job: " ..job, "rp_hudtext_verysmall", jobbox.x+jobbox.w/2, jobbox.y+jobbox.h/2, RP.colors.job, TEXT_ALIGN_CENTER, TEXT_ALIGN_CENTER);
	
	---------------------------------------------------------------------------------------------------------- Ammo Hud ----------------------------------------------------------------------------------------------------------

	if(ply:GetActiveWeapon() &&
		(ply:GetActiveWeapon():GetClass()=="gmod_camera" ||
		ply:GetActiveWeapon():GetClass()=="gmod_tool" ||
		ply:GetActiveWeapon():GetClass()=="weapon_physgun" ||
		ply:GetActiveWeapon():GetClass()=="weapon_physcannon" ||
		ply:GetActiveWeapon():GetClass()=="weapon_crowbar" ||
		ply:GetActiveWeapon():GetClass()=="weapon_stunstick" ||
		ply:GetActiveWeapon():GetClass()=="hands")
	)then
	else
		local nodraw = false;
		
		local secammobox = {}; // values for  ammo box
		secammobox.w = 100;
		secammobox.h = 70;
		secammobox.x = ScrW()-secammobox.w-20;
		secammobox.y = ScrH()-secammobox.h-20;
		secammobox.col = RP.colors.hudammo;
		
		local secammocolor = RP.colors.ammo;
		local secclipcolor = RP.colors.ammo;
		local secammo = ply:GetAmmoCount(ply:GetActiveWeapon():GetSecondaryAmmoType());
		local secclip = ply:GetActiveWeapon():Clip2();
		
		
		
		
		if(secclip == -1)then
			if(secammo != 0)then
				draw.RoundedBox(bordersize, secammobox.x, secammobox.y, secammobox.w, secammobox.h, secammobox.col);
				draw.SimpleText(secammo, "rp_hudtext_large", secammobox.x+secammobox.w/2, secammobox.y+secammobox.h/2, secammocolor, TEXT_ALIGN_CENTER, TEXT_ALIGN_CENTER);
			else
				nodraw = true;
			end
		else
			draw.RoundedBox(bordersize, secammobox.x, secammobox.y, secammobox.w, secammobox.h, secammobox.col);
			draw.SimpleText(secclip, "rp_hudtext_large", secammobox.x+secammobox.w/2, secammobox.y+5, secclipcolor, TEXT_ALIGN_CENTER, TEXT_ALIGN_TOP);
			draw.SimpleText(secammo, "rp_hudtext_small", secammobox.x+secammobox.w/2, secammobox.y+secammobox.h, secammocolor, TEXT_ALIGN_CENTER, TEXT_ALIGN_BOTTOM);
		end
		local primammobox = {}; // values for  ammo box
		if(nodraw)then
			primammobox.w = 100;
			primammobox.h = 70;
			primammobox.x = ScrW()-primammobox.w-20;
			primammobox.y = ScrH()-primammobox.h-20;
			primammobox.col = RP.colors.hudammo;
		else
			primammobox.w = 100;
			primammobox.h = 70;
			primammobox.x = ScrW()-primammobox.w-secammobox.w-40;
			primammobox.y = ScrH()-primammobox.h-20;
			primammobox.col = RP.colors.hudammo;
		end
		
		local primammocolor = RP.colors.ammo;
		local primclipcolor = RP.colors.ammo;
		local primammo = ply:GetAmmoCount(ply:GetActiveWeapon():GetPrimaryAmmoType());
		local primclip = ply:GetActiveWeapon():Clip1();
		
		
		
		
		if(primclip == -1)then
			draw.RoundedBox(bordersize, primammobox.x, primammobox.y, primammobox.w, primammobox.h, primammobox.col);
			draw.SimpleText(primammo, "rp_hudtext_large", primammobox.x+primammobox.w/2, primammobox.y+primammobox.h/2, primammocolor, TEXT_ALIGN_CENTER, TEXT_ALIGN_CENTER);
		else
			draw.RoundedBox(bordersize, primammobox.x, primammobox.y, primammobox.w, primammobox.h, primammobox.col);
			draw.SimpleText(primclip, "rp_hudtext_large", primammobox.x+primammobox.w/2, primammobox.y+5, primclipcolor, TEXT_ALIGN_CENTER, TEXT_ALIGN_TOP);
			draw.SimpleText(primammo, "rp_hudtext_small", primammobox.x+primammobox.w/2, primammobox.y+primammobox.h, primammocolor, TEXT_ALIGN_CENTER, TEXT_ALIGN_BOTTOM);
		end
	end
	
	---------------------------------------------------------------------------------------------------------- Ownable Things ----------------------------------------------------------------------------------------------------------
	
	local tr = ply:GetEyeTrace();
	local pos = tr.HitPos:ToScreen();
	if(!ply:InVehicle())then
		if(tr.StartPos:Distance(tr.HitPos)<150)then
			if(tr.Entity && tr.Entity:IsOwnable())then
				local ent = tr.Entity;
				local owner = ent:GetOwnerName();
				if(owner != "")then
					draw.SimpleText("Owner: " ..owner, "rp_hudtext_verysmall", pos.x, pos.y, RP.colors.enttext, TEXT_ALIGN_CENTER, TEXT_ALIGN_CENTER);
					if(ent:GetTitle()!="")then
						draw.SimpleText(ent:GetTitle(), "rp_hudtext_verysmall", pos.x, pos.y+20, RP.colors.enttext, TEXT_ALIGN_CENTER, TEXT_ALIGN_CENTER);
					end
				else
					draw.SimpleText("Buy this " ..ent:GetType() .." for " ..ent:GetPrize() .."$!", "rp_hudtext_verysmall", pos.x, pos.y, RP.colors.enttext, TEXT_ALIGN_CENTER, TEXT_ALIGN_CENTER);
				end
			end
		end
	end
	
	
end

function GM:HUDShouldDraw(name)
	if(
		name == "CHudHealth" ||
		name == "CHudBattery" ||
		name == "CHudSuitPower" ||
		name == "CHudAmmo" ||
		name == "CHudSecondaryAmmo"
	  )
	then
		return false;
	else
		return true;
	end
end

