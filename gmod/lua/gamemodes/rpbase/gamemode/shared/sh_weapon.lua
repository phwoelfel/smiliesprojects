------------------------------------
-- by SMILIE[AUT] 
------------------------------------

local AmmoTypes = {
	"AR2",
	"AR2AltFire",
	"Pistol",
	"SMG1",
	"357",
	"XBowBolt",
	"Buckshot",
	"RPG_Round",
	"SMG1_Grenade",
	"Grenade",
	"slam"
};


local wepmeta = FindMetaTable("Weapon");

function wepmeta:GetPrimaryAmmoClass()
	local typ = self:GetPrimaryAmmoType();
	if(typ<1)then
		return "none";
	else
		return AmmoTypes[typ];
	end
end

function wepmeta:GetSecondaryAmmoClass()
	local typ = self:GetSecondaryAmmoType();
	if(typ<1)then
		return "none";
	else
		return AmmoTypes[typ];
	end
end

/*
5 weapon_357
3 weapon_pistol
4 weapon_smg1
1 weapon_ar2
7 weapon_shotgun
6 weapon_crossbow
10 weapon_frag
8 weapon_rpg
-1 weapon_physcannon
-1 weapon_crowbar
-1 weapon_slam
11 weapon_slam
2 weapon_ar2
9 weapon_smg1

*/