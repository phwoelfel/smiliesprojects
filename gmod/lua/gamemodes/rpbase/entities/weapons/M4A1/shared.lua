-- Read the weapon_real_base if you really want to know what each action does

if (SERVER) then
	AddCSLuaFile("shared.lua")
	SWEP.HoldType 		= "ar2"
end

if (CLIENT) then
	SWEP.PrintName 		= "M4A1"
	SWEP.Slot 			= 2
	SWEP.SlotPos 		= 1
	SWEP.IconLetter 		= "b"

	killicon.AddFont("M4A1", "CSKillIcons", SWEP.IconLetter, Color( 255, 80, 0, 255 ) )
end

SWEP.EjectDelay			= 0.05

SWEP.Instructions 		= ""

SWEP.Base 				= "Basis-Gewehr"

SWEP.Author 			= "Decure"

SWEP.Spawnable 			= true
SWEP.AdminSpawnable 		= true

SWEP.ViewModel 			= "models/weapons/v_rif_m4a1.mdl"
SWEP.WorldModel 			= "models/weapons/w_rif_m4a1.mdl"

SWEP.Primary.Sound 		= Sound("Weapon_M4A1.Single")
SWEP.Primary.Recoil 		= 0.5
SWEP.Primary.Damage 		= 15
SWEP.Primary.NumShots 		= 1
SWEP.Primary.Cone 		= 0.012
SWEP.Primary.ClipSize 		= 30
SWEP.Primary.Delay 		= 0.08
SWEP.Primary.DefaultClip 	= 30
SWEP.Primary.Automatic 		= true
SWEP.Primary.Ammo 		= "smg1"

SWEP.Secondary.ClipSize 	= -1
SWEP.Secondary.DefaultClip 	= -1
SWEP.Secondary.Automatic 	= false
SWEP.Secondary.Ammo 		= "none"

SWEP.IronSightsPos 		= Vector (6.0816, -7.8745, 2.5074)
SWEP.IronSightsAng 		= Vector (2.4511, -0.0486, 0)