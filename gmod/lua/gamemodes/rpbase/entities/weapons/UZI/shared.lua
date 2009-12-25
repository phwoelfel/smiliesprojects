-- Read the weapon_real_base if you really want to know what each action does

if (SERVER) then
	AddCSLuaFile("shared.lua")
	SWEP.HoldType 		= "ar2"
end

if (CLIENT) then
	SWEP.PrintName 		= "UZI"
	SWEP.Slot 			= 2
	SWEP.SlotPos 		= 1
	SWEP.IconLetter 		= "l"

	killicon.AddFont("UZI", "CSKillIcons", SWEP.IconLetter, Color( 255, 80, 0, 255 ))
end

SWEP.Instructions 		= ""

SWEP.Base 				= "Basis-SMG"

SWEP.Author 			= "Decure"

SWEP.Spawnable 			= true
SWEP.AdminSpawnable 		= true

SWEP.ViewModel 			= "models/weapons/v_smg_mac10.mdl"
SWEP.WorldModel 			= "models/weapons/w_smg_mac10.mdl"

SWEP.Primary.Sound 		= Sound("Weapon_MAC10.Single")
SWEP.Primary.Recoil 		= 0.7
SWEP.Primary.Damage 		= 14
SWEP.Primary.NumShots 		= 1
SWEP.Primary.Cone 		= 0.024
SWEP.Primary.ClipSize 		= 30
SWEP.Primary.Delay 		= 0.055
SWEP.Primary.DefaultClip 	= 30
SWEP.Primary.Automatic 		= true
SWEP.Primary.Ammo 		= "smg1"

SWEP.Secondary.ClipSize 	= -1
SWEP.Secondary.DefaultClip 	= -1
SWEP.Secondary.Automatic 	= false
SWEP.Secondary.Ammo 		= "none"

SWEP.IronSightsPos 		= Vector (6.4608, -5.159, 2.8794)
SWEP.IronSightsAng 		= Vector (0.8834, 5.3204, 6.7768)