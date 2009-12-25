-- Read the weapon_real_base if you really want to know what each action does

if (SERVER) then
	AddCSLuaFile("shared.lua")
	SWEP.HoldType 		= "ar2"
end

if (CLIENT) then
	SWEP.PrintName 		= "MP - Navy"
	SWEP.Slot 			= 2
	SWEP.SlotPos 		= 1
	SWEP.IconLetter 		= "x"

	killicon.AddFont("MP - Navy", "CSKillIcons", SWEP.IconLetter, Color( 255, 80, 0, 255 ))
end

SWEP.Instructions 		= ""

SWEP.Base 				= "Basis-SMG"

SWEP.Author 			= "Decure"

SWEP.Spawnable 			= true
SWEP.AdminSpawnable 		= true

SWEP.ViewModel 			= "models/weapons/v_smg_mp5.mdl"
SWEP.WorldModel 			= "models/weapons/w_smg_mp5.mdl"

SWEP.Primary.Sound 		= Sound("Weapon_MP5Navy.Single")
SWEP.Primary.Recoil 		= 0.6
SWEP.Primary.Damage 		= 17
SWEP.Primary.NumShots 		= 1
SWEP.Primary.Cone 		= 0.017
SWEP.Primary.ClipSize 		= 30
SWEP.Primary.Delay 		= 0.075
SWEP.Primary.DefaultClip 	= 30
SWEP.Primary.Automatic 		= true
SWEP.Primary.Ammo 		= "smg1"

SWEP.Secondary.ClipSize 	= -1
SWEP.Secondary.DefaultClip 	= -1
SWEP.Secondary.Automatic 	= false
SWEP.Secondary.Ammo 		= "none"

SWEP.IronSightsPos 		= Vector (4.7494, -4.114, 1.9335)
SWEP.IronSightsAng 		= Vector (1.018, -0.0187, 0)