if (SERVER) then
	AddCSLuaFile("shared.lua")
end

if (CLIENT) then
	SWEP.PrintName = "Hands"
	SWEP.Slot = 1
	SWEP.SlotPos = 1
	SWEP.DrawAmmo = false
	SWEP.DrawCrosshair = false
end

-- Variables that are used on both client and server

SWEP.Author = "SMILE[AUT]"
SWEP.Instructions = ""
SWEP.Contact = ""
SWEP.Purpose = ""

SWEP.ViewModel			= ""
SWEP.WorldModel		= ""

SWEP.ViewModelFOV = 62
SWEP.ViewModelFlip = false
SWEP.AnimPrefix	 = "rpg"

SWEP.Spawnable = false
SWEP.AdminSpawnable = true
SWEP.Primary.ClipSize = -1
SWEP.Primary.DefaultClip = 0
SWEP.Primary.Automatic = false
SWEP.Primary.Ammo = ""

SWEP.Secondary.ClipSize = -1
SWEP.Secondary.DefaultClip = 0
SWEP.Secondary.Automatic = false
SWEP.Secondary.Ammo = ""


function SWEP:Initialize()
	if (SERVER) then
		self:SetWeaponHoldType("normal")
	end
end

function SWEP:Deploy()
	if (SERVER) then
		self.Owner:DrawViewModel(false)
		self.Owner:DrawWorldModel(false)
	end
end


function SWEP:PrimaryAttack()
	
end


function SWEP:SecondaryAttack()
	
end