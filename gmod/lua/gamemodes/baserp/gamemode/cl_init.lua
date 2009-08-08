------------------------------------
-- by SMILIE[AUT] 
-- 08.08.2009
------------------------------------

DeriveGamemode("sandbox");
include("shared.lua");

local clIncFiles = file.FindInLua("../" ..GM.Folder .."/gamemode/client/*.lua");
for _,f in pairs(clIncFiles) do
	print("[RP Base][Client] Included "..f);
	include(GM.Folder .."/client/"..f);
end

GM.colors = {};
GM.colors.blue = Color(86, 142, 255, 255);
GM.colors.green = Color(176, 255, 86, 255);
GM.colors.red = Color(255, 111, 86, 255);
GM.colors.grey = Color(140, 140, 140, 255);
GM.colors.black = Color(0, 0, 0, 255);
GM.colors.hudblue = Color(86, 142, 255, 200);
GM.colors.hudgrey = Color(140, 140, 140, 200);

// surface.CreateFont( string font_name, number size, number weight, boolean antialiasing, boolean italic, string new_font_name )
surface.CreateFont("coolvetica", ScreenScale(20), 400, true, false, "rp_hudtext_large");
surface.CreateFont("coolvetica", ScreenScale(10), 400, true, false, "rp_hudtext_small");

function GM:HUDPaint()
	local ply = LocalPlayer();
	if( !ply || !ply:Alive() )then return end // hide HUD when dead
	if(ply:GetActiveWeapon() && ply:GetActiveWeapon():GetClass()=="gmod_camera") then return end // hide HUD when using camera
	
	local bordersize = 20; // bordersize for draw.RoundedBox
	
	---------------------------------------------------------------------------------------------------------- Health/Armor Hud ----------------------------------------------------------------------------------------------------------
	local healthbox = {}; // values for health box
	healthbox.w = 100;
	healthbox.h = 70;
	healthbox.x = 20;
	healthbox.y = ScrH()-healthbox.h-20;
	healthbox.col = self.colors.hudblue;
	
	local healthcolor;
	if(ply:Health()<25)then healthcolor = self.colors.red else healthcolor = self.colors.green end
	
	draw.RoundedBox(bordersize, healthbox.x, healthbox.y, healthbox.w, healthbox.h, healthbox.col);
	draw.SimpleText(ply:Health(), "rp_hudtext_large", healthbox.x+healthbox.w/2, healthbox.y+healthbox.h/2, healthcolor, TEXT_ALIGN_CENTER, TEXT_ALIGN_CENTER);
	
	if(ply:Armor()!=0)then
		local armorbox = {}; // values for health box
		armorbox.w = 100;
		armorbox.h = 70;
		armorbox.x = healthbox.x+healthbox.w+20;
		armorbox.y = ScrH()-armorbox.h-20;
		armorbox.col = self.colors.hudblue;
		
		local armorcolor;
		if(ply:Armor()<25)then armorcolor = self.colors.red else armorcolor = self.colors.green end
		
		draw.RoundedBox(bordersize, armorbox.x, armorbox.y, armorbox.w, armorbox.h, armorbox.col);
		draw.SimpleText(ply:Armor(), "rp_hudtext_large", armorbox.x+armorbox.w/2, armorbox.y+armorbox.h/2, armorcolor, TEXT_ALIGN_CENTER, TEXT_ALIGN_CENTER);
	end
	
	
	---------------------------------------------------------------------------------------------------------- Ammo Hud ----------------------------------------------------------------------------------------------------------
	
	if(ply:GetActiveWeapon() &&
		(ply:GetActiveWeapon():GetClass()=="gmod_camera" ||
		ply:GetActiveWeapon():GetClass()=="gmod_tool" ||
		ply:GetActiveWeapon():GetClass()=="weapon_physgun" ||
		ply:GetActiveWeapon():GetClass()=="weapon_physcannon" ||
		ply:GetActiveWeapon():GetClass()=="weapon_crowbar" ||
		ply:GetActiveWeapon():GetClass()=="weapon_stunstick")
	)then return end
	
	
	local primammobox = {}; // values for  ammo box
	primammobox.w = 100;
	primammobox.h = 70;
	primammobox.x = ScrW()-primammobox.w-20;
	primammobox.y = ScrH()-primammobox.h-20;
	primammobox.col = self.colors.hudgrey;
	
	local primammocolor = self.colors.black;
	local primclipcolor = self.colors.black;
	local primammo = ply:GetAmmoCount(ply:GetActiveWeapon():GetPrimaryAmmoType());
	local primclip = ply:GetActiveWeapon():Clip1();
	
	
	draw.RoundedBox(bordersize, primammobox.x, primammobox.y, primammobox.w, primammobox.h, primammobox.col);
	
	if(primclip == -1)then
		draw.SimpleText(primammo, "rp_hudtext_large", primammobox.x+primammobox.w/2, primammobox.y+primammobox.h/2, primammocolor, TEXT_ALIGN_CENTER, TEXT_ALIGN_CENTER);
	else
		draw.SimpleText(primammo, "rp_hudtext_small", primammobox.x+primammobox.w-5, primammobox.y+primammobox.h-20, primammocolor, TEXT_ALIGN_RIGHT, TEXT_ALIGN_CENTER);
		draw.SimpleText(primclip, "rp_hudtext_large", primammobox.x+10, primammobox.y+primammobox.h/2, primclipcolor, TEXT_ALIGN_LEFT, TEXT_ALIGN_CENTER);
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
